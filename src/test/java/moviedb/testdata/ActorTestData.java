package moviedb.testdata;

import moviedb.model.Actor;

import java.time.LocalDate;
import java.util.Arrays;

import static moviedb.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class ActorTestData {
    public static final int ACTOR_ID = START_SEQ + 9;
    public static final int ACTOR_ID_1 = ACTOR_ID + 1;
    private static final int ACTOR_ID_2 = ACTOR_ID_1 + 1;
    private static final int ACTOR_ID_3 = ACTOR_ID_2 + 1;
    private static final int ACTOR_ID_4 = ACTOR_ID_3 + 1;
    private static final int ACTOR_ID_5 = ACTOR_ID_4 + 1;
    private static final int ACTOR_ID_6 = ACTOR_ID_5 + 1;

    public static final Actor ACTOR = new Actor(ACTOR_ID, "Leonardo", "DiCaprio", LocalDate.of(1974, 11, 11));
    public static final Actor ACTOR1 = new Actor(ACTOR_ID_1, "Kate", "Winslet", LocalDate.of(1975, 10, 5));
    public static final Actor ACTOR2 = new Actor(ACTOR_ID_2, "Zoe", "Saldana", LocalDate.of(1978, 6, 19));
    public static final Actor ACTOR3 = new Actor(ACTOR_ID_3, "Samuel", "Worthington", LocalDate.of(1976, 8, 2));
    public static final Actor ACTOR4 = new Actor(ACTOR_ID_4, "Robert", "Downey Jr", LocalDate.of(1965, 4, 4));
    public static final Actor ACTOR5 = new Actor(ACTOR_ID_5, "Gwyneth", "Paltrow", LocalDate.of(1972, 9, 27));
    public static final Actor ACTOR6 = new Actor(ACTOR_ID_6, "Chris", "Evans", LocalDate.of(1981, 6, 13));

    public static void assertMatch(Actor actual, Actor expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "movies");
    }

    public static void assertMatch(Iterable<Actor> actual, Actor... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Actor> actual, Iterable<Actor> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("movies").isEqualTo(expected);
    }
}
