package moviedb.controller;

import moviedb.controller.vote.VoteRestController;
import moviedb.model.Actor;
import moviedb.service.VoteService;
import moviedb.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static moviedb.TestUtil.*;
import static moviedb.testdata.ActorTestData.*;
import static moviedb.testdata.ActorTestData.assertMatch;
import static moviedb.testdata.MovieTestData.MOVIE_ID;
import static moviedb.testdata.UserTestData.ADMIN;
import static moviedb.testdata.UserTestData.USER;
import static moviedb.testdata.VoteTestData.*;
import static moviedb.testdata.VoteTestData.assertMatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    public void testDelete() throws Exception {
        //mockAuthorize(USER);
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
    public void testUpdate() throws Exception {
        Actor updated = new Actor(ACTOR_ID_4, "Oleg", "Petrov", LocalDate.of(2018, 1, 1));

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

//        assertMatch(service.get(ACTOR_ID_4), updated);
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Actor updated = new Actor(ACTOR_ID_4, "Kate", "Winslet", LocalDate.of(2018, 1, 1));

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value("DATA_ERROR"));
    }
}
