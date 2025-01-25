package bike.service.app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import bike.service.app.service.userroles.MechanicService;

@SpringBootTest
@AutoConfigureMockMvc
public class MechanicControllerTest {

    @InjectMocks
    private MechanicController mController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MechanicService mService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mController).build();
    }

    @Test
    void testDoneButton() throws Exception {

        int id = 1;

        doNothing().when(mService).doneStatusById(id);

        mockMvc.perform(post("/mechanic/done/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mechanic"));
                

        verify(mService, times(1)).doneStatusById(id);
    }

    @Test
    void testGetEditForm() {

    }

    @Test
    void testMechanicSite() throws Exception {
        this.mockMvc.perform(get("/mechanic"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testUpdateService() {

    }
}
