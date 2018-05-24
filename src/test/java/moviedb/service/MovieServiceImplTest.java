package moviedb.service;

import moviedb.AbstractServiceTest;
import moviedb.service.impl.MovieServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static moviedb.testdata.MovieTestData.*;

public class MovieServiceImplTest extends AbstractServiceTest{

    @Autowired
    private MovieServiceImpl service;

    @Test
    public void create() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
        assertMatch(service.get(MOVIE_ID_1), MOVIE1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), MOVIE1, MOVIE3, MOVIE2, MOVIE);
    }

    @Test
    public void update() {
    }

    @Test
    public void addActor() {
    }

    @Test
    public void deleteActor() {
    }

    @Test
    public void getAverageMark() {
    }
}