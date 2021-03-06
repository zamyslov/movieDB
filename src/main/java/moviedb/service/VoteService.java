package moviedb.service;


import moviedb.model.Vote;

import java.util.List;

public interface VoteService {

    Vote create(Vote vote);

    List<Vote> getAll();

    void delete(int movie_id, int user_id);

    List<Vote> getByUser();

}