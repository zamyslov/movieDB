package moviedb.service;

import moviedb.AbstractServiceTest;
import moviedb.model.Vote;
import moviedb.service.impl.VoteServiceImpl;
import moviedb.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static moviedb.testdata.MovieTestData.MOVIE;
import static moviedb.testdata.MovieTestData.MOVIE3;
import static moviedb.testdata.UserTestData.USER;
import static moviedb.testdata.VoteTestData.*;

public class VoteServiceImplTest extends AbstractServiceTest {
    @Autowired
    private VoteServiceImpl service;

    @Test
    public void getAll() {
        assertMatch(service.getAll(), VOTE, VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7);
    }

    @Test
    public void create() {
        Vote vote = new Vote(USER, MOVIE3, 5);
        service.create(vote);
        assertMatch(service.getAll(), VOTE, VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7, vote);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void delete() {
        service.delete(MOVIE.getId(), USER.getId());
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(0,0);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void getByUser() {
        assertMatch(service.getByUser(), VOTE, VOTE1, VOTE2);
    }

}