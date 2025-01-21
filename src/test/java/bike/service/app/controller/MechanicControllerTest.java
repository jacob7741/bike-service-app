package bike.service.app.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MechanicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDoneButton() {

    }

    @Test
    void testGetEditForm() {

    }

    @Test
    void testMechanicSite() throws Exception {
        this.mockMvc.perform(get("/mechanic")).andDo(print()).andExpect(status().isOk())
                        .andExpect(content().string(containsString("mechanic")))
                        .andExpect(result ->{
                            if (!(result.getResponse().getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value())) {
                                   assertThat(result.getResponse().getContentAsString(), containsString("Whitelabel Error Page"));
                            }
                        });
     }

    @Test
    void testUpdateService() {

    }
}
