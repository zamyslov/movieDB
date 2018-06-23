package moviedb.service;

import javassist.NotFoundException;
import moviedb.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id);

    User get(int id);

    User getByLogin(String login);

    void update(User user);

    List<User> getAll();

}
