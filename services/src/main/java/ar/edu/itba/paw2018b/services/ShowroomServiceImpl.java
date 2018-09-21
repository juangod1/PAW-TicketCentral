package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ShowroomsDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.ShowroomsService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Seat;
import ar.edu.itba.paw2018b.models.Showroom;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowroomServiceImpl implements ShowroomsService {

    @Autowired
    ShowroomsDao showroomsDao;

    @Override
    public Showroom getByTheatreAndName(String theatreName, String showroomName) {

        Optional<Showroom> showroom = showroomsDao.getShowroom(theatreName,showroomName);
        return showroom.orElseThrow(() -> new NotFoundException("No se encontraron salas!"));
    }

    @Override
    public boolean isValidSeat(String showroomName,String theatreName,String seat) {
        return  false;
        /*Showroom showroom = getByTheatreAndName(theatreName,showroomName);

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
        return false;*/
    }
}
