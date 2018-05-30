package moviedb.service;

import moviedb.AbstractServiceTest;
import moviedb.model.Movie;
import moviedb.service.impl.MovieServiceImpl;
import moviedb.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static moviedb.testdata.ActorTestData.*;
import static moviedb.testdata.ActorTestData.assertMatch;
import static moviedb.testdata.MovieTestData.*;
import static moviedb.testdata.MovieTestData.assertMatch;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MovieServiceImplTest extends AbstractServiceTest {

    @Before
    public void setUpCast() {
        MOVIE1.setCast(Stream.of(ACTOR2, ACTOR3).collect(Collectors.toSet()));
    }

    @Autowired
    private MovieServiceImpl service;

    @Test
    public void create() {
        Movie movie = new Movie("Zoolander", 2001);
        service.create(movie);
        assertMatch(service.getAll(), MOVIE1, MOVIE3, MOVIE2, MOVIE, movie);
    }

    @Test
    public void delete() {
        service.delete(MOVIE_ID_2);
        assertMatch(service.getAll(), MOVIE1, MOVIE3, MOVIE);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1);
    }

    @Test
    public void get() {
        assertMatch(service.get(MOVIE_ID_1), MOVIE1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), MOVIE1, MOVIE3, MOVIE2, MOVIE);
    }

    @Test
    public void update() {
        Movie movie = service.get(MOVIE_ID_1);
        movie.setYear(2000);
        service.update(movie);
        assertMatch(service.get(MOVIE_ID_1), movie);
    }

    @Test
    public void addActor() {
        service.addActor(MOVIE1, ACTOR4);
        assertMatch(MOVIE1.getCast(), ACTOR3, ACTOR4, ACTOR2);
    }

    @Test
    public void deleteActor() {
        service.deleteActor(MOVIE1, ACTOR2);
        assertMatch(MOVIE1.getCast(), ACTOR3);
    }

    @Test
    public void getAverageMark() {
        assertEquals(service.getAverageMark(MOVIE_ID), 4.25, 0.01);
    }
}