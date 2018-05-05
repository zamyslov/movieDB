package moviedb.repository;

import moviedb.model.Actor;

import java.util.List;

public interface ActorRepository {
    Actor save(Actor actor);

    // false if not found
    boolean delete(int id);

    // null if not found
    Actor get(int id);

    List<Actor> getAll();

}