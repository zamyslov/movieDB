package moviedb.controller;

import moviedb.controller.vote.VoteRestController;
import moviedb.model.Vote;
import moviedb.service.VoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static moviedb.TestUtil.*;
import static moviedb.testdata.MovieTestData.*;
import static moviedb.testdata.UserTestData.ADMIN;
import static moviedb.testdata.UserTestData.USER;
import static moviedb.testdata.VoteTestData.*;
import static moviedb.testdata.VoteTestData.assertMatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MOVIE_ID)
                .with(userAuth(USER)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7);
    }

    @Test
    public void testDeleteInvalid() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value("DATA_NOT_FOUND"));
    }

    @Test
    public void testCreate() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL + "?movie_id=" + MOVIE3.getId() + "&mark=" + 5)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)));

        Vote created = readFromJson(action, Vote.class);
        created.setMovie(MOVIE3);
        created.setUser(USER);
        assertMatch(service.getAll(), VOTE, VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7, created);
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        mockMvc.perform(post(REST_URL + "?movie_id=" + MOVIE.getId() + "&mark=" + 5)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value("DATA_ERROR"));
    }

    @Test
    public void testGetByUser() throws Exception {
        mockMvc.perform(get(REST_URL + "user")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(VOTE, VOTE1, VOTE2));
    }
}
