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

        Timestamp now = new Timestamp(System.currentTimeMillis());
        int purchaseId = getAutoIncrementalId();
        for(Seat seat : seats)
        {
            int screeningId = screening.getId();
            double price = hardCodedPrice();
            boolean paid = true;
            transactionDao.create(purchaseId,user.getDni(),screeningId,seat.getName(),price,paid,now);
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
