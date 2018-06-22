package moviedb.controller.vote;

import moviedb.controller.AuthorizedUser;
import moviedb.model.Vote;
import moviedb.service.MovieService;
import moviedb.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    public static final String REST_URL = "/rest/vote";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @Autowired
    private MovieService serviceMovie;

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int movie_id) {
        log.info("delete for movie id {} and user id {}", movie_id, AuthorizedUser.id());
        service.delete(movie_id, AuthorizedUser.id());
    }

    @PostMapping
    public ResponseEntity<Vote> create(@RequestParam("movie_id") int movie_id, @RequestParam("mark") int mark) {
        Vote vote = new Vote(AuthorizedUser.get().getUser(), serviceMovie.get(movie_id), mark);
        log.info("create vote for movie {} with mark {} from {}", vote);
        Vote created = service.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/user")
    public List<Vote> getByUser() {
        log.info("get votes for user id {}", AuthorizedUser.id());
        return service.getByUser();
    }
}
