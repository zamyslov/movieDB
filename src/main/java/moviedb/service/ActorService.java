package moviedb.service;

import javassist.NotFoundException;
import moviedb.model.Actor;
import moviedb.model.User;

import java.util.List;

public interface ActorService {

    Actor create(Actor actor);

    void delete(int id) throws NotFoundException;

    Actor get(int id) throws NotFoundException;

    void update(Actor actor);

    List<Actor> getAll();
}
