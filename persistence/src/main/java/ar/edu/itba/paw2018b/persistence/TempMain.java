package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static java.sql.Timestamp.valueOf;

public class TempMain {
    public static void main( String[] args )
    {
//        SimpleDriverDataSource ds = new SimpleDriverDataSource();
//        ds.setDriverClass(org.postgresql.Driver.class);
//        ds.setUrl("jdbc:postgresql://localhost:1414/postgres");
//        ds.setUsername("postgres");
//        ds.setPassword("atlas1");
//
//        MovieDaoImpl movieDao = new MovieDaoImpl(ds);
//        FoodDaoImpl foodDao = new FoodDaoImpl(ds);
//        ScreeningDaoImpl screeningDao = new ScreeningDaoImpl(ds);
//        TheatreDaoImpl theatreDao = new TheatreDaoImpl(ds);
//        UserDaoImpl userDao = new UserDaoImpl(ds);
//        TransactionDaoImpl transactionDao = new TransactionDaoImpl(ds);

//        List<Screening> screenings = screeningDao.getByMovie(movieDao.findMovieByTitle("Wonder Woman"));
//        if(screenings != null){
//            for(Screening s: screenings) s.printScreening();
//        }
//
//        List<Screening> screenings1 = screeningDao.getByTheatre(theatreDao.getTheatreByName("ATLAS CENTRO"));
//        if(screenings1 != null){
//            for(Screening s: screenings1) s.printScreening();
//        }
//        List<Movie> movies = movieDao.getAll();
//        for(Movie m: movies){
//            System.out.println(m.printMovie());
//        }

//        movieDao.delete(movieDao.findMovieByTitle("Joe Dick"));
//        System.out.println();
//        System.out.println();
//        List<Movie> movies1 = movieDao.getAll();
//        for(Movie m: movies1){
//            System.out.println(m.printMovie());
//        }

//        List<Food> foods = foodDao.getAll();
//        for(Food f: foods){
//            System.out.println(f.getName());
//        }
//        foodDao.insert("f3","Pochoclos",50,180);
        //foodDao.delete(foodDao.findById("f2"));

//      userDao.insert("12345678","John","Doe","1511223344","johndoe@gmail.com");
//        Screening s = screeningDao.insert("Atlas 1","tt0360556", Timestamp.valueOf("2018-08-23 22:00:00"),"2D","Subtitulado","ATLAS CENTRO");
//        transactionDao.insert(1,"12345678",s.getId(),"A1",120,false,Timestamp.valueOf("2018-08-22 18:45:02"));

    }
}
