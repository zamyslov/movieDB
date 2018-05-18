package moviedb.service;

import javassist.NotFoundException;
import moviedb.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByLogin(String login) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    User getWithVotes(int id);

    User getWithFavoriteMovies(int id);

}