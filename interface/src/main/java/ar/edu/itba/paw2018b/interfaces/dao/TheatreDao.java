package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Theatre;

import java.util.List;
import java.util.Optional;

public interface TheatreDao {

    List<Theatre> getAll();

    Theatre create(String name, String address, String city);

    int delete(String name);

    Optional<Theatre> getTheatreByName(String name);

    List<Theatre> getByMovie(int movieId);
}
