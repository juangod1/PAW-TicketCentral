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
}
