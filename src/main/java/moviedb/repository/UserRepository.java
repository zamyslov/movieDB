package moviedb.repository;

import moviedb.model.User;
import moviedb.model.Vote;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    User getByLogin(String login);

    List<User> getAll();

    User getWithVotes(int id);

    User getWithFavoriteMovies(int id);

}