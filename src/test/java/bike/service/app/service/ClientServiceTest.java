package bike.service.app.service;

import bike.service.app.model.Client;
import bike.service.app.model.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

//    @Test
//    void get_Client_By_Phone_Number() {
//        // Arrange
//        Client client = new Client();
//        client.setPhoneNumber(708902423);
//        List<Client> clientList = Arrays.asList(client);
//        // Zamockowanie metody getAllClients
//        when(clientRepository.findAll()).thenReturn(clientList);
//        // Act
//        Client result = clientService.getClientByPhoneNumber(client.getPhoneNumber());
//        // Assert
//        assertEquals(client.getPhoneNumber(), result.getPhoneNumber());
//        assertEquals(client, result);
//    }
}