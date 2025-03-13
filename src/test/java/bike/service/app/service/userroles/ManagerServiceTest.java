package bike.service.app.service.userroles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Order;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.ServicesRepository;
import bike.service.app.service.userroles.ManagerService;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ServicesRepository servicesRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private BikeRepository bikeRepository;
    @InjectMocks
    private ManagerService managerService;

    @Test
    void deleteOrderById() {
        Order doneOrder = new Order();

        doneOrder.setStatus(Order.Status.DONE);
        doneOrder.setOrderId(1);
        
        when(orderRepository.getReferenceById(1)).thenReturn(doneOrder);

        managerService.deleteOrderById(1);

        verify(orderRepository).deleteById(1);
    }
}
