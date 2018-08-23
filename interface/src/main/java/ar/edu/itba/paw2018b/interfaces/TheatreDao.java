package ar.edu.itba.paw2018b.interfaces;

import ar.edu.itba.paw2018b.models.Theatre;

import java.util.List;

public interface TheatreDao {

    List<Theatre> getAll();

    void insert(String name, String address, String city);

    void delete(String name);

    Theatre getTheatreByName(String name);

}
