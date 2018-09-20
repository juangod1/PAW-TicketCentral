package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Showroom;

import java.util.List;
import java.util.Optional;

public interface ShowroomsDao {

    List<Showroom> getByTheatre(String theatreName);

    int getCapacity(String theatreName, String showroomName);

    Optional<Showroom> getShowroom(String theatreName, String showroomName);

    Showroom create(String theatre, String showroom, int capacity, String layout);

    int delete(String theatre, String showroomName);

}
