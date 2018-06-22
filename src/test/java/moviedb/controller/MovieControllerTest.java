package moviedb.controller;

import moviedb.controller.movie.AdminMovieRestController;
import moviedb.controller.movie.UserMovieRestController;
import moviedb.model.Movie;
import moviedb.service.MovieService;
import moviedb.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static moviedb.TestUtil.*;
import static moviedb.testdata.MovieTestData.*;
import static moviedb.testdata.UserTestData.ADMIN;
import static moviedb.testdata.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MovieControllerTest extends AbstractControllerTest {
    private static final String ADMIN_REST_URL = AdminMovieRestController.REST_URL + '/';
    private static final String USER_REST_URL = UserMovieRestController.REST_URL + '/';

    @Autowired
    private MovieService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(USER_REST_URL + MOVIE_ID_1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MOVIE1));
    }

    @Test
    public void testGetInvalid() throws Exception {
        mockMvc.perform(get(USER_REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value("DATA_NOT_FOUND"));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(USER_REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(MOVIE1, MOVIE3, MOVIE2, MOVIE));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL + MOVIE_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), MOVIE1, MOVIE3, MOVIE2);
    }

    @Test
    public void testDeleteInvalid() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value("DATA_NOT_FOUND"));
    }

    @Test
    public void testUpdate() throws Exception {
        Movie updated = new Movie(MOVIE_ID, "Z movie", 2018);

        mockMvc.perform(put(ADMIN_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        assertMatch(service.get(MOVIE_ID), updated);
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Movie updated = new Movie(MOVIE_ID, "Avatar", 2018);

        mockMvc.perform(put(ADMIN_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value("DATA_ERROR"));
    }

    @Test
    public void testCreate() throws Exception {
        Movie created = new Movie("Z movie", 2018);
        ResultActions action = mockMvc.perform(post(ADMIN_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Movie returned = readFromJson(action, Movie.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), MOVIE1, MOVIE3, MOVIE2, MOVIE, created);
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        Movie created = new Movie("Avatar", 2018);
        mockMvc.perform(post(ADMIN_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value("DATA_ERROR"));
    }
}
