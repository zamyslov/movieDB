package moviedb.controller;

import moviedb.controller.actor.AdminActorRestController;
import moviedb.model.Actor;
import moviedb.service.ActorService;
import moviedb.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static moviedb.TestUtil.*;
import static moviedb.testdata.ActorTestData.*;
import static moviedb.testdata.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ActorControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminActorRestController.REST_URL + '/';

    @Autowired
    private ActorService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ACTOR_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ACTOR));
    }

    @Test
    public void testGetInvalid() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value("DATA_NOT_FOUND"));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(ACTOR, ACTOR1, ACTOR2, ACTOR3, ACTOR4, ACTOR5, ACTOR6));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + ACTOR_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), ACTOR4, ACTOR6, ACTOR5, ACTOR2, ACTOR1, ACTOR3);
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

        assertMatch(service.get(ACTOR_ID_4), updated);
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

    @Test
    public void testCreate() throws Exception {
        Actor created = new Actor("Oleg", "Petrov", LocalDate.of(2018, 1, 1));
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Actor returned = readFromJson(action, Actor.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), ACTOR, ACTOR4, ACTOR6, ACTOR5, created, ACTOR2, ACTOR1, ACTOR3);
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        Actor created = new Actor("Kate", "Winslet", LocalDate.of(2018, 1, 1));
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value("DATA_ERROR"));
    }
}
