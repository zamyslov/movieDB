package moviedb.service;

import moviedb.AbstractServiceTest;
import moviedb.model.User;
import moviedb.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static moviedb.testdata.UserTestData.*;

public class UserServiceImplTest extends AbstractServiceTest{

    @Autowired
    protected UserServiceImpl service;

    @Test
    public void create() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getByLogin() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getWithVotes() {
    }

    @Test
    public void getWithFavoriteMovies() {
    }

    @Test
    public void loadUserByUsername() {
    }
}