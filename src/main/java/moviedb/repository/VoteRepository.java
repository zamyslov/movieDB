package moviedb.repository;

import moviedb.model.Vote;

import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote);

    List<Vote> getAll();

    List<Vote> getByUser(int user_id);

    // false if not found
    boolean delete(int movie_id, int user_id);
}