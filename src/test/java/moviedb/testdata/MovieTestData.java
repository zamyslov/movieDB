package moviedb.testdata;

import moviedb.model.Movie;

import java.util.Arrays;

import static moviedb.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MovieTestData {
    public static final int MOVIE_ID = START_SEQ + 5;
    public static final int MOVIE_ID_1 = MOVIE_ID + 1;
    public static final int MOVIE_ID_2 = MOVIE_ID_1 + 1;
    public static final int MOVIE_ID_3 = MOVIE_ID_2 + 1;

    public static final Movie MOVIE = new Movie(MOVIE_ID, "Titanic", 1997);
    public static final Movie MOVIE1 = new Movie(MOVIE_ID_1, "Avatar", 2009);
    public static final Movie MOVIE2 = new Movie(MOVIE_ID_2, "Iron Man", 2008);
    public static final Movie MOVIE3 = new Movie(MOVIE_ID_3, "Avengers", 2012);

    public static void assertMatch(Movie actual, Movie expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "users", "cast", "votes");
    }

    public static void assertMatch(Iterable<Movie> actual, Movie... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Movie> actual, Iterable<Movie> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("users", "cast", "votes").isEqualTo(expected);
    }
}
