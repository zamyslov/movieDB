package moviedb.service.impl;

import moviedb.model.Vote;
import moviedb.repository.VoteRepository;
import moviedb.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static moviedb.util.ValidationUtil.checkNotFound;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    @Override
    public void delete(int movie_id, int user_id) {
        checkNotFound(repository.delete(movie_id, user_id), "movie:" + movie_id + "user:" + user_id);
    }

}