package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Client;
import bike.service.app.model.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    private void setup() {
    }

    @AfterEach
    public void cleanUp() {
        clientRepository.deleteAll();
    }

    // Arange
    private static Client client() {
        Client client = new Client();
        client.setClientId(543);
        client.setFirst_name("Elora");
        client.setLast_name("Kudla");
        client.setPhoneNumber(708902423);
        client.setEmail("elora@kundla.com");
        return client;
    }

    private static List<Client> clientList = Arrays.asList(client());

    @Test
    void getClientByPhoneNumber() {

        // Zamockowanie metody getAllClients
        when(clientRepository.findAll()).thenReturn(clientList);
        // Act
        Client result = clientService.getClientByPhoneNumber(client().getPhoneNumber());
        // Assert
        assertEquals(708902423, result.getPhoneNumber());
        assertEquals("elora@kundla.com", result.getEmail());
    }

    @Test
    void addNewClient() {
        // Arrange
        Client client = client();
        client.setClientId(0);
        when(clientRepository.save(any())).thenReturn(client);

        // Act
        Client savedClient = clientService.addNewClient(client);

        assertNotNull(savedClient);
        assertEquals("Elora", savedClient.getFirst_name());
        verify(clientRepository, times(1)).save(any(Client.class));

    }

    @Test
    void testDeletedClientById() {

        Client client = client();
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(client.getClientId());
        clientService.deletedClientById(543);

        assertEquals(543, result.getClientId());
        verify(clientRepository, times(1)).deleteById(543);
    }

    @Test
    void testGetAllClients() {

        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals("Elora", result.get(0).getFirst_name());
    }

    @Test
    void testGetClientById() {

        Client client = client();

        when(clientRepository.findById(any())).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(543);
        
        assertNotNull(result);
        assertEquals(client.getFirst_name(), result.getFirst_name());
        assertEquals(client.getClientId(), result.getClientId());
    }
}