package application.services;

import models.Client;
import repositories.ClientRepository;

public class ClientService {
    private ClientRepository clientRepository;
    
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    public void createClient(Client client) {
        // Валидируем данные клиента
        if (ValidationService.validateClientData(client)) {
            clientRepository.save(client);
            System.out.println("Клиент создан: " + client);
        } else {
            System.out.println("Неверные данные клиента!");
        }
    }
    
    public Client getClientById(int clientId) {
        return clientRepository.getClientById(clientId);
    }
    
    public void updateClient(Client client) {
        clientRepository.updateClient(client);
    }
    
    public void deleteClient(int clientId) {
        clientRepository.deleteClient(clientId);
    }
}
