package moviedb.repository.datajpa.impl;

import moviedb.model.Actor;
import moviedb.repository.ActorRepository;
import moviedb.repository.datajpa.CrudActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
    private static final Sort SORT_SURNAME_NAME = new Sort(Sort.Direction.ASC, "surname,name");

    @Autowired
    private CrudActorRepository crudRepository;

    @Override
    public Actor save(Actor actor) {
        return crudRepository.save(actor);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Actor get(int id) {
        return crudRepository.getById(id).orElse(null);
    }

    @Override
    public List<Actor> getAll() {
        return crudRepository.findAll(SORT_SURNAME_NAME);
    }

}
