package moviedb.testdata;

import moviedb.model.Movie;
import moviedb.model.Role;
import moviedb.model.User;
import moviedb.model.Vote;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static moviedb.model.AbstractBaseEntity.START_SEQ;
import static moviedb.testdata.MovieTestData.*;
import static moviedb.testdata.VoteTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    private static final int USER_ID_1 = USER_ID + 1;
    private static final int USER_ID_2 = USER_ID_1 + 1;
    public static final int USER_ID_3 = USER_ID_2 + 1;
    private static final int ADMIN_ID = USER_ID_3 + 1;

    public static final User USER = new User(USER_ID, "user@ukr.net", "password", "User", EnumSet.of(Role.ROLE_USER));
    public static final User USER1 = new User(USER_ID_1, "user1@ukr.net", "password", "User1", Role.ROLE_USER);
    public static final User USER2 = new User(USER_ID_2, "user2@ukr.net", "password", "User2", Role.ROLE_USER);
    public static final User USER3 = new User(USER_ID_3, "user3@ukr.net", "password", "User3", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin@gmail.com", "adminpassword", "Admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static final Set<Movie> FAVORITE_MOVIES = Stream.of(MOVIE, MOVIE1, MOVIE2).collect(Collectors.toSet());
    public static final Set<Vote> VOTES = Stream.of(VOTE, VOTE1, VOTE2).collect(Collectors.toSet());

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
