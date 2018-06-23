package moviedb.service;

import moviedb.model.Role;
import moviedb.model.User;
import moviedb.service.impl.UserServiceImpl;
import moviedb.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.EnumSet;
import java.util.Objects;

import static moviedb.testdata.MovieTestData.assertMatch;
import static moviedb.testdata.UserTestData.ADMIN;
import static moviedb.testdata.UserTestData.FAVORITE_MOVIES;
import static moviedb.testdata.UserTestData.USER;
import static moviedb.testdata.UserTestData.USER1;
import static moviedb.testdata.UserTestData.USER2;
import static moviedb.testdata.UserTestData.USER3;
import static moviedb.testdata.UserTestData.USER_ID;
import static moviedb.testdata.UserTestData.USER_ID_3;
import static moviedb.testdata.UserTestData.assertMatch;
import static moviedb.testdata.VoteTestData.assertMatch;
import static moviedb.testdata.VoteTestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected UserServiceImpl service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUpCacheUsers() {
        Objects.requireNonNull(cacheManager.getCache("users")).clear();
    }

    @Test
    public void create() {
        User newUser = new User("usernew@ukr.net", "password", "UserNew", EnumSet.of(Role.ROLE_USER));
        service.create(newUser);
        assertMatch(service.getAll(), ADMIN, USER1, USER2, USER3, USER, newUser);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createDuplicateLogin() {
        User newUser = new User("user3@ukr.net", "password", "UserNew", EnumSet.of(Role.ROLE_USER));
        service.create(newUser);
    }

    @Test
    public void delete() {
        service.delete(USER_ID_3);
        assertMatch(service.getAll(), ADMIN, USER1, USER2, USER);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1);
    }

    @Test
    public void get() {
        assertMatch(service.get(USER_ID), USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void getByLogin() {
        assertMatch(service.getByLogin("user1@ukr.net"), USER1);
    }

    @Test(expected = NotFoundException.class)
    public void getByLoginNotFound() {
        service.getByLogin("login");
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), ADMIN, USER1, USER2, USER3, USER);
    }

    @Test
    public void update() {
        User user = service.getByLogin("user1@ukr.net");
        user.setName("Updated");
        service.update(user);
        assertMatch(service.getByLogin("user1@ukr.net"), user);
    }

    @Test
    public void getVotes() {
        User user = service.get(USER_ID);
        assertMatch(user.getVotes(), VOTE2, VOTE, VOTE1);
    }

    @Test
    public void getFavoriteMovies() {
        User user = service.get(USER_ID);
        assertMatch(user.getFavoriteMovies(), FAVORITE_MOVIES);
    }

}