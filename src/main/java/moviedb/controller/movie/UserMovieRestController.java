package moviedb.controller.movie;

import javassist.NotFoundException;
import moviedb.model.Movie;
import moviedb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserMovieRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMovieRestController {
    static final String REST_URL = "/rest/user/movies";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService service;

    @GetMapping
    public List<Movie> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Movie get(@PathVariable("id") int id) throws NotFoundException {
        log.info("get {}", id);
        return service.get(id);
    }

}
