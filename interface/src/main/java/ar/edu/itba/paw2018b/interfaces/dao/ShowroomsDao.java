package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Showroom;

import java.util.List;

public interface ShowroomsDao {

    List<Showroom> getByTheatre(String theatreName);

    int getCapacity(String theatreName, String showroomName);

    Showroom getShowroom(String theatreName, String showroomName);

    Showroom insert(String theatre, String showroom, int capacity, String layout);

    void delete(String theatre, String showroomName);

}
