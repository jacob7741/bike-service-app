package bike.service.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Client;
import bike.service.app.model.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        System.out.println("getAllClients");
        List<Client> clientList = clientRepository.findAll();
        if (clientList.isEmpty())
            return new ArrayList<>();
        return clientList;
    }

    public Client getClientById(int id) {
        System.out.println("getClientById");
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new RuntimeException("no Id client found");
        }
    }

    public Client getClientByPhoneNumber(int phoneNumber) {
        System.out.println("getClinetByPhoneNumber");
        List<Client> clientList = getAllClients();

        for (Client client : clientList) {
            if (client.getPhoneNumber() == phoneNumber) {
                return client;
            }
        }
        throw new RuntimeException("not phone number found");
    }

    public Client addNewClient(Client client) {
        System.out.println("new Client Added");
        if (client.getClientId() == 0) {
            clientRepository.save(client);
        } else {
            throw new RuntimeException("client already exsist");
        }
        return client;
    }

    public void deletedClientById(int id) {
        System.out.println("client deleted");
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("not Id found");
        }
    }
}
