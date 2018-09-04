package moviedb.controller.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(AccountRestController.REST_URL)
public class AccountRestController {
    public static final String REST_URL = "/rest/account";

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        return principal;
    }

}
