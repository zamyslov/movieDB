package moviedb.repository.datajpa.impl;

import moviedb.model.Movie;
import moviedb.repository.MovieRepository;
import moviedb.repository.datajpa.CrudMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudMovieRepository crudRepository;

    @Override
    public Movie save(Movie movie) {
        return crudRepository.save(movie);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Movie get(int id) {
        return crudRepository.getById(id).orElse(null);
    }

    @Override
    public List<Movie> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    @Override
    public Movie getWithVotes(int id) {
        return crudRepository.getWithVotes(id);
    }

    @Override
    public double getAverageMark(int id) {
        return crudRepository.getAverageMark(id);
    }
}
