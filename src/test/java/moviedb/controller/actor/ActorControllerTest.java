package moviedb.controller.actor;

import moviedb.controller.AbstractControllerTest;
import moviedb.service.ActorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static moviedb.TestUtil.contentJson;
import static moviedb.TestUtil.userHttpBasic;
import static moviedb.testdata.ActorTestData.ACTOR;
import static moviedb.testdata.ActorTestData.ACTOR_ID;
import static moviedb.testdata.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActorControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminActorRestController.REST_URL + '/';

    @Autowired
    private ActorService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ACTOR_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ACTOR));
    }

}
