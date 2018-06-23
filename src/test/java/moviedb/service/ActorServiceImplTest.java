package moviedb.service;

import moviedb.model.Actor;
import moviedb.service.impl.ActorServiceImpl;
import moviedb.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static moviedb.testdata.ActorTestData.*;

public class ActorServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected ActorServiceImpl service;

    @Test
    public void create() {
        Actor newActor = new Actor("ZZ", "ZZ", LocalDate.of(2000, 4, 4));
        service.create(newActor);
        assertMatch(service.getAll(), ACTOR, ACTOR4, ACTOR6, ACTOR5, ACTOR2, ACTOR1, ACTOR3, newActor);
    }

    @Test
    public void delete() {
        service.delete(ACTOR_ID_1);
        assertMatch(service.getAll(), ACTOR, ACTOR4, ACTOR6, ACTOR5, ACTOR2, ACTOR3);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1);
    }

    @Test
    public void get() {
        assertMatch(service.get(ACTOR_ID_1), ACTOR1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), ACTOR, ACTOR4, ACTOR6, ACTOR5, ACTOR2, ACTOR1, ACTOR3);
    }

    @Test
    public void update() {
        Actor actor = service.get(ACTOR_ID_1);
        actor.setName("Oleg");
        service.update(actor);
        assertMatch(service.getAll(), ACTOR, ACTOR4, ACTOR6, ACTOR5, ACTOR2, actor, ACTOR3);
    }
}