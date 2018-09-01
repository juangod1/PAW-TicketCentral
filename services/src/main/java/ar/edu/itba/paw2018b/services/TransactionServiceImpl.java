package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
import ar.edu.itba.paw2018b.interfaces.service.TransactionService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Override
    public List<Seat> getSeatsByScreening(Screening screening) {
        String format = screening.getFormat();
        String[] rows = format.split("\n");
        ArrayList<Seat> ret = new ArrayList<>();
        List<Transaction> transactionList = transactionDao.getOcuppiedSeatsByScreening(screening.getId());
        List<String> occupiedSeatList = new ArrayList<>();
        for(Transaction transaction: transactionList)
        {
            occupiedSeatList.add(transaction.getSeat());
        }
        for(int i=0; i<rows.length; i++)
        {
            String row = rows[i];
            int orden=1;
            for(int j=0; j<row.length(); j++)
            {
                char c = row.charAt(j);
                if(c=='0')
                {
                    String seatName = ""+('A'+i)+orden; //hace B2 H12 etc.
                    boolean occupied= occupiedSeatList.contains(seatName);
                    ret.add(new Seat(j,i,occupied, seatName));
                    orden++;
                }
            }


        }
        return ret;
    }


    @Override
    public Boolean confirmCheckout(User user, Screening screening, List<Seat> seats, List<Food> foods) {

        for(Seat seat : seats)
        {
            Timestamp now = new Timestamp(System.currentTimeMillis());
          //  User user = hardCodedUser();
            int screeningId = screening.getId();

            //transactionDao.create()
        }

        return false;
    }
}
