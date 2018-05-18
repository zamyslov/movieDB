package moviedb.service.impl;

import moviedb.model.Actor;
import moviedb.repository.ActorRepository;
import moviedb.service.ActorService;
import moviedb.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static moviedb.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository repository;

    @Autowired
    public ActorServiceImpl(ActorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Actor create(Actor actor) {
        Assert.notNull(actor, "actor must not be null");
        return repository.save(actor);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Actor get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Actor> getAll() {
        return repository.getAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void update(Actor actor) {
        Assert.notNull(actor, "actor must not be null");
        checkNotFoundWithId(repository.save(actor), actor.getId());
    }

}