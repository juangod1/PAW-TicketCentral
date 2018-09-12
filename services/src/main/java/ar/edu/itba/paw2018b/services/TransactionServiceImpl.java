package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.ShowroomsService;
import ar.edu.itba.paw2018b.interfaces.service.TransactionService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    ShowroomsService showroomsService;

    @Autowired
    ScreeningService screeningService; //cambiar por dao???

    @Autowired
    FoodService foodService;

    @Override
    public List<Seat> getSeatsByScreening(int screeningId){
        ArrayList<Seat> ret = new ArrayList<>();

        Screening screening = screeningService.getScreeningById(screeningId);
        if(screening==null || screening.getTheatre()==null || screening.getShowroom()==null)
        {
            return ret;
        }

        Showroom showroom = showroomsService.getByTheatreAndName(screening.getTheatre(),screening.getShowroom());
        if(showroom==null)
        {
            return ret;
        }

        List<Transaction> transactionList = transactionDao.getOcuppiedSeatsByScreening(screeningId);
        List<String> occupiedSeatList = new ArrayList<>();
        for(Transaction transaction: transactionList)
        {
            occupiedSeatList.add(transaction.getSeat());
        }

        String[] rows = showroom.getLayout().split("\n");
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


    @Override
    public Integer confirmCheckout(String userDNI, int screeningId, List<String> seatNames, List<String> foodIdsAndQuantity) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String seatNamesForDb = new String();
        String foodDetailsForDb = new String();
        double price=0;
        for(String seat : seatNames)
        {
            seatNamesForDb+=seat+";";
        }
        for(String food: foodIdsAndQuantity)
        {
            String[] data = food.split(",");
            if(data.length!=2)
            {
                throw new IllegalArgumentException("illegal food string: "+ food);
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
            foodDetailsForDb+=data+";";
        }



        Transaction transaction = transactionDao.create(userDNI,screeningId,seatNamesForDb,foodDetailsForDb,price,true,now);

        if(transaction==null || transaction.getId()==null)
        {
            return null;
        }
        return transaction.getId();
    }
}
