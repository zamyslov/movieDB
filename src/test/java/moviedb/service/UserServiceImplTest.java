package moviedb.service;

import moviedb.AbstractServiceTest;
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
import java.util.List;
import java.util.Objects;

import static moviedb.testdata.UserTestData.*;

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
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, USER1, USER2, USER3, USER, newUser);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createDuplicateLogin() {
        User newUser = new User("user3@ukr.net", "password", "UserNew", EnumSet.of(Role.ROLE_USER));
        service.create(newUser);
    }

    @Test
    public void delete() {
        service.delete(USER_ID_3);
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, USER1, USER2, USER);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1);
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void getByLogin() {
        User user = service.getByLogin("user1@ukr.net");
        assertMatch(user, USER1);
    }

    @Test(expected = NotFoundException.class)
    public void getByLoginNotFound() {
        service.getByLogin("login");
    }

    @Test
    public void getAll() {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, USER1, USER2, USER3, USER);
    }

    @Test
    public void update() {
        User user = service.getByLogin("user1@ukr.net");
        user.setName("Updated");
        service.update(user);
        User updatedUser = service.getByLogin("user1@ukr.net");
        assertMatch(user, updatedUser);
    }

    @Test
    public void getWithVotes() {
    }

    @Test
    public void getWithFavoriteMovies() {
    }

}