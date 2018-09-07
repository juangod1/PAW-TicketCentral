package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.ShowroomsService;
import ar.edu.itba.paw2018b.interfaces.service.TransactionService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
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
    ScreeningService screeningService;

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
    public Boolean confirmCheckout(String userDNI, int screeningId, List<String> seatNames, List<String> foodIds) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        int purchaseId = getAutoIncrementalId();
        for(String seat : seatNames)
        {
            double price = hardCodedPrice();
            boolean paid = true;
            transactionDao.create(purchaseId,userDNI,screeningId,seat,price,paid,now);
        }

        return false;
    }

    private int getAutoIncrementalId() {
        return 1; //TODO: conectar a bd?
    }

    public double hardCodedPrice()
    {
        return 10;
    }
    public User hardCodedUser()
    {
        return new User("123123123","Juan","Godfrid","1568901234","jgodfrid@itba.edu.ar");
    }
}
