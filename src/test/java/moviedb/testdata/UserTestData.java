package moviedb.testdata;

import moviedb.model.Role;
import moviedb.model.User;

import java.util.Arrays;

import static moviedb.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int USER_ID_1 = USER_ID + 1;
    public static final int USER_ID_2 = USER_ID_1 + 1;
    public static final int USER_ID_3 = USER_ID_2 + 1;
    public static final int ADMIN_ID = USER_ID_3 + 1;

    public static final User USER = new User(USER_ID, "user@ukr.net", "password", "User", Role.ROLE_USER);
    public static final User USER1 = new User(USER_ID_1, "user1@ukr.net", "password", "User1", Role.ROLE_USER);
    public static final User USER2 = new User(USER_ID_2, "user2@ukr.net", "password", "User2", Role.ROLE_USER);
    public static final User USER3 = new User(USER_ID_3, "user3@ukr.net", "password", "User3", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin@gmail.com", "adminpassword", "Admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "votes", "favoriteMovies", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("votes", "favoriteMovies", "password").isEqualTo(expected);
    }
}
