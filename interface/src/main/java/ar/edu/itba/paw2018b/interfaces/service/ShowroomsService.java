package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Showroom;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;

public interface ShowroomsService {

    Showroom getByTheatreAndName(String theatreName, String showroomName) throws NotFoundException;
}
