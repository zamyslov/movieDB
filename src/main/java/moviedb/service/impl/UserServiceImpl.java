package moviedb.service.impl;

import moviedb.controller.AuthorizedUser;
import moviedb.model.User;
import moviedb.repository.UserRepository;
import moviedb.service.UserService;
import moviedb.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static moviedb.util.ValidationUtil.checkNotFound;
import static moviedb.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByLogin(String login) throws NotFoundException {
        Assert.notNull(login, "login must not be null");
        return checkNotFound(repository.getByLogin(login), "login=" + login);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public User getWithVotes(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithVotes(id), id);
    }

    @Override
    public User getWithFavoriteMovies(int id) {
        return checkNotFoundWithId(repository.getWithFavoriteMovies(id), id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.getByLogin(login.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return new AuthorizedUser(user);
    }
}