package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.*;
import ar.edu.itba.paw2018b.interfaces.service.*;
import ar.edu.itba.paw2018b.models.*;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    ShowroomsService showroomsService;

    @Autowired
    ScreeningService screeningService;

    @Autowired
    FoodService foodService;

    @Autowired
    UserService userService;


    @Override
    public List<Transaction> getTransactionsByScreening(int screeningId)
    {
        return transactionDao.getTransactionsByScreening(screeningId);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        return transactionDao.getTransactionsByUserId(userId);
    }

    @Override
    public Transaction getTransactionById(int id) throws NotFoundException {
        return transactionDao.getTransactionById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public List<Seat> getSeatsByScreening(int screeningId) throws NotFoundException{
        ArrayList<Seat> ret = new ArrayList<>();

        Screening screening = screeningService.getScreeningById(screeningId);
        if(screening==null || screening.getTheatre()==null || screening.getShowroom()==null)
        {
            throw new NotFoundException("No se han encontrado funciones!");
        }

        Showroom showroom = showroomsService.getByTheatreAndName(screening.getTheatre(),screening.getShowroom());
        if(showroom==null)
        {
            throw new NotFoundException("No se han encontrado salas!");
        }

        List<Transaction> transactionList = getTransactionsByScreening(screeningId);
        List<String> occupiedSeatList = new ArrayList<>();
        for(Transaction transaction: transactionList)
        {
            String transactionSeats = transaction.getSeat();
            String[] seats = transactionSeats.split(";");
            for(String seat :seats)
            {
                occupiedSeatList.add(seat);
            }
        }

        String[] rows = showroom.getLayout().split("n|\n");
        for(int i=0; i<rows.length; i++)
        {
            String row = rows[i];
            int orden=1;
            for(int j=0; j<row.length(); j++)
            {
                char c = row.charAt(j);
                if(c=='0')
                {
                    String seatName = ""+(Character.toString((char)('A'+i)))+orden; //hace B2 H12 etc.
                    boolean occupied= occupiedSeatList.contains(seatName);
                    ret.add(new Seat(j,i,occupied, seatName));
                    orden++;
                }
            }


        }
        return ret;
    }

    @Transactional
    @Override
    public Integer confirmCheckout(int userId, int screeningId, List<String> seatNames, List<String> foodIdsAndQuantity)  throws IllegalArgumentException{

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String seatNamesForDb = new String();
        StringBuilder foodDetailsForDb = new StringBuilder();

        double price = screeningService.getScreeningById(screeningId).getPrice();

        List<Seat> validSeatList = getSeatsByScreening(screeningId);
        List<String> validSeatNameList = new ArrayList<>();
        for(Seat validSeat : validSeatList){
            if(!validSeat.getOccupied()) validSeatNameList.add(validSeat.getName());
        }


        User user=userService.findById(userId);
        if(user==null)
        {
            throw new IllegalArgumentException("No user found with id: "+userId);
        }

        Screening screening = screeningService.getScreeningById(screeningId);
        if(screening==null)
        {
            throw new IllegalArgumentException("No screening found with id" +screeningId);
        }
        for(String seat : seatNames)
        {
            Set<String> set = new HashSet<String>();
            if (!set.add(seat)){
                throw new IllegalArgumentException("Illegal seatName list. Found duplicate seatName: "+seat);

            }
            if(!validSeatNameList.contains(seat)){
                throw new IllegalArgumentException("Illegal seatName list. Found invalid or occupied seat" + seat);
            }
            seatNamesForDb+=seat+";";
        }
        for(String food: foodIdsAndQuantity)
        {
            String[] data = food.split(",");
            if(data.length!=2)
            {
                throw new IllegalArgumentException("Illegal food string: "+ food);
            }
            int id;
            int amount;
            try
            {
                id =Integer.parseInt(data[0]);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("Illegal Food id: "+ data[0]);
            }

            try
            {
                amount = Integer.parseInt(data[1]);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("Illegal Food amount: "+ data[1]);
            }


            Food foodObj=foodService.getFoodById(id);
            if(foodObj==null)
            {
                throw new IllegalArgumentException("No Food found for id: "+ food);
            }
            price += foodObj.getPrice()*amount;
            foodDetailsForDb.append(data[0]).append(",").append(data[1]).append(";");
        }



        Transaction transaction = transactionDao.create(userId,screeningId,seatNamesForDb,foodDetailsForDb.toString(),price,true,now);

        if(transaction==null || transaction.getId()==null)
        {
            return null;
        }

        return transaction.getId();
    }

}
