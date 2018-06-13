package moviedb.controller.user;

import javassist.NotFoundException;
import moviedb.controller.AuthorizedUser;
import moviedb.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() throws NotFoundException {
        return super.get(AuthorizedUser.get().getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() throws NotFoundException {
        super.delete(AuthorizedUser.get().getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user) {
        super.update(user, AuthorizedUser.get().getId());
    }

}