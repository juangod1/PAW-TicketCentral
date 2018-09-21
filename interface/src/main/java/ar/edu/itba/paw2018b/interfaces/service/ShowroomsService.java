package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Showroom;

public interface ShowroomsService {

    Showroom getByTheatreAndName(String theatreName, String showroomName);

    boolean isValidSeat(String showroom, String theatre, String seat);
}
