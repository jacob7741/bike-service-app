package bike.service.app.service;

import bike.service.app.model.Client;
import bike.service.app.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;



    public Client addNewClient(Client client) {
        System.out.println("newClientAdded");
        if (client.getUniqId() == 0)
            clientRepository.save(client);
        return client;
    }
}
