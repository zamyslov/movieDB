package moviedb.testdata;

import moviedb.model.Vote;

import java.util.Arrays;

import static moviedb.model.AbstractBaseEntity.START_SEQ;
import static moviedb.testdata.MovieTestData.*;
import static moviedb.testdata.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


public class VoteTestData {
    public static final int VOTE_ID = START_SEQ + 16;
    public static final int VOTE_ID_1 = VOTE_ID + 1;
    public static final int VOTE_ID_2 = VOTE_ID_1 + 1;
    public static final int VOTE_ID_3 = VOTE_ID_2 + 1;
    public static final int VOTE_ID_4 = VOTE_ID_3 + 1;
    public static final int VOTE_ID_5 = VOTE_ID_4 + 1;
    public static final int VOTE_ID_6 = VOTE_ID_5 + 1;
    public static final int VOTE_ID_7 = VOTE_ID_6 + 1;

    public static final Vote VOTE = new Vote(VOTE_ID, USER, MOVIE, 5);
    public static final Vote VOTE1 = new Vote(VOTE_ID_1, USER, MOVIE1, 5);
    public static final Vote VOTE2 = new Vote(VOTE_ID_2, USER, MOVIE2, 4.5);
    public static final Vote VOTE3 = new Vote(VOTE_ID_3, USER2, MOVIE2, 5);
    public static final Vote VOTE4 = new Vote(VOTE_ID_4, USER2, MOVIE, 3.5);
    public static final Vote VOTE5 = new Vote(VOTE_ID_5, USER3, MOVIE3, 5);
    public static final Vote VOTE6 = new Vote(VOTE_ID_6, USER1, MOVIE2, 4);
    public static final Vote VOTE7 = new Vote(VOTE_ID_7, USER1, MOVIE1, 5);

    @SuppressWarnings("ConstantConditions")
    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).usingComparator((v1,v2)->v1.getMovie().getId()-v2.getMovie().getId()
                +v1.getUser().getId()-v2.getUser().getId()).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    @SuppressWarnings("ConstantConditions")
    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparator((v1,v2)->v1.getMovie().getId()-v2.getMovie().getId()
                +v1.getUser().getId()-v2.getUser().getId()).isEqualTo(expected);
    }
}
