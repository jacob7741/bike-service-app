package bike.service.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class MechanicControllerTest {
    
    @Autowired
    private MechanicController mController;

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
        this.mockMvc.perform(get("/mechanic"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateService() {

    }
}
