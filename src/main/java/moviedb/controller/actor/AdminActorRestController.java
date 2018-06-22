package moviedb.controller.actor;

import javassist.NotFoundException;
import moviedb.model.Actor;
import moviedb.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminActorRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminActorRestController {
    public static final String REST_URL = "/rest/admin/actors";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActorService service;

    @GetMapping
    public List<Actor> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Actor get(@PathVariable("id") int id) throws NotFoundException {
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) throws NotFoundException {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Actor> create(@Validated @RequestBody Actor actor) {
        log.info("create {}", actor);
        Actor created = service.create(actor);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated @RequestBody Actor actor) {
        log.info("update {}", actor);
        service.update(actor);
    }
}
