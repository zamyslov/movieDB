package moviedb.repository.datajpa.impl;

import moviedb.model.Vote;
import moviedb.repository.VoteRepository;
import moviedb.repository.datajpa.CrudVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudRepository;

    @Override
    public Vote save(Vote vote) {
        return crudRepository.save(vote);
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.getAll();
    }

    @Override
    public boolean delete(int movie_id, int user_id) {
        return crudRepository.delete(movie_id, user_id) != 0;
    }

}
