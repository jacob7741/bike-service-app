package bike.service.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import bike.service.app.model.Order;
import bike.service.app.service.LoginService;
import bike.service.app.service.userroles.MechanicService;

@AutoConfigureMockMvc
public class MechanicControllerTest {

    @InjectMocks
    private MechanicController mController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoginService lService;

    @Mock
    private MechanicService mService;

    @Mock
    private View mockView;

    @Mock
    private Order order;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mController)
                .setSingleView(mockView)
                .build();
    }

    @Test
    void testDoneButton() throws Exception {
        int id = 9365;

        doNothing().when(mService).doneStatusById(id);

        mockMvc.perform(post("/mechanic/done/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/mechanic"))
                .andReturn();

    }

    @Test
    void testGetEditForm() throws Exception {
        int id = 3578;

        order = new Order();

        when(mService.getOrderById(id)).thenReturn(order);

        mockMvc.perform(get("/mechanic/edit/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("update"))
                .andExpect(model().attribute("order", order))
                .andReturn();
    }

    @Test
    void testMechanicSite() throws Exception {

        AtomicReference<String> fullName = new AtomicReference<>("Test User");
        List<Order> pList = Collections.emptyList();

        when(lService.getPersonalList(any())).thenAnswer(invocation -> {
            AtomicReference<String> arg = invocation.getArgument(0);
            arg.set(fullName.get());
            return pList;
        });

        mockMvc.perform(get("/mechanic"))
                .andExpect(status().isOk())
                .andExpect(view().name("mechanic"))
                .andExpect(model().attribute("username", "Test User"))
                .andExpect(model().attribute("orderList", pList));
    }

    @Test
    void testUpdateService() throws Exception {

        String service = "service";
        int id = 23404;

        doNothing().when(mService).editOrderById(service, id);

        mockMvc.perform(post("/orders/updateService")
                .param("service", service)
                .param("orderId", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/mechanic"));
    }
}