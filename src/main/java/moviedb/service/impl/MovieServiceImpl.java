package moviedb.service.impl;

import moviedb.model.Actor;
import moviedb.model.Movie;
import moviedb.repository.MovieRepository;
import moviedb.service.MovieService;
import moviedb.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

import static moviedb.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie create(Movie movie) {
        Assert.notNull(movie, "movie must not be null");
        return repository.save(movie);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Movie get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Movie> getAll() {
        return repository.getAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void update(Movie movie) {
        Assert.notNull(movie, "movie must not be null");
        checkNotFoundWithId(repository.save(movie), movie.getId());
    }

    @Override
    public void addActor(Movie movie, Actor actor) {
        Set<Actor> cast = movie.getCast();
        cast.add(actor);
        movie.setCast(cast);
    }

    @Override
    public void deleteActor(Movie movie, Actor actor) {
        Set<Actor> cast = movie.getCast();
        cast.remove(actor);
        movie.setCast(cast);
    }
}