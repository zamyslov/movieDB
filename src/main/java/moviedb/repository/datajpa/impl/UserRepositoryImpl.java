package moviedb.repository.datajpa.impl;

import moviedb.model.User;
import moviedb.model.Vote;
import moviedb.repository.UserRepository;
import moviedb.repository.datajpa.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudUserRepository crudRepository;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.getById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getByLogin(String login) { return crudRepository.getByLogin(login).orElse(null); }

    @Override
    public User getWithVotes(int id) { return crudRepository.getWithVotes(id); }

    @Override
    public User getWithFavoriteMovies(int id) { return crudRepository.getWithFavoriteMovies(id); }
}
