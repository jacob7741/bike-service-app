package bike.service.app.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Client;
import bike.service.app.model.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    @Test
    void get_Client_By_Phone_Number() {
        // Arrange
        Client client = new Client();
        client.setPhoneNumber(708902423);
        List<Client> clientList = Arrays.asList(client);
        // Zamockowanie metody getAllClients
        when(clientRepository.findAll()).thenReturn(clientList);
        // Act
        Client result = clientService.getClientByPhoneNumber(client.getPhoneNumber());
        // Assert
        assertEquals(client.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(client, result);
    }


    @Test
    void addNewClient() {
        Client client = new Client();
        client.setFirst_name("Elora");
        client.setLast_name("Kudla");
        client.setPhoneNumber(48494);
        client.setEmail("elora@kundla.com");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.addNewClient(client);

        assertNotNull(savedClient);
        assertEquals("Elora", savedClient.getFirst_name());
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}