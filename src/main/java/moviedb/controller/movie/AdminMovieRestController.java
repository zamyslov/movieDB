package moviedb.controller.movie;

import javassist.NotFoundException;
import moviedb.model.Movie;
import moviedb.service.MovieService;
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

@RestController
@RequestMapping(value = AdminMovieRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMovieRestController {
    public static final String REST_URL = "/rest/admin/movies";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService service;

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) throws NotFoundException {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> create(@Validated @RequestBody Movie movie) {
        log.info("create {}", movie);
        Movie created = service.create(movie);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated @RequestBody Movie movie) {
        log.info("update {}", movie);
        service.update(movie);
    }
}
