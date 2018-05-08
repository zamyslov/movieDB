package moviedb.repository;

import moviedb.model.Movie;

import java.util.List;

public interface MovieRepository {
    Movie save(Movie movie);

    // false if not found
    boolean delete(int id);

    // null if not found
    Movie get(int id);

    List<Movie> getAll();

    Movie getWithVotes(int id);

    double getAverageMark(int id);
}