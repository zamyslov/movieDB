package moviedb.service;

import javassist.NotFoundException;
import moviedb.model.Movie;

import java.util.List;

public interface MovieService {

    Movie create(Movie movie);

    void delete(int id) throws NotFoundException;

    Movie get(int id);

    void update(Movie movie);

//    void addActor(Movie movie, Actor actor);
//
//    void deleteActor(Movie movie, Actor actor);

    List<Movie> getAll();

    double getAverageMark(int id);
}
