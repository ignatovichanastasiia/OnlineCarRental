package application.services;

import application.exceptions.InvalidClientDataException;
import models.Client;
import repositories.ClientRepository;

/**
 * The ClientService class provides business operations for managing Client objects.
 * It interacts with the ClientRepository to perform CRUD operations and uses validation
 * methods to ensure that client data is valid before saving or updating.
 */
public class ClientService {
    // Repository for performing client data operations.
    private ClientRepository clientRepository;

    /**
     * Constructs a new ClientService using the specified ClientRepository.
     *
     * @param clientRepository the repository for client data operations.
     */
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new client after validating its data.
     * <p>
     * The method performs the following steps:
     * <ul>
     *   <li>Validates the client's data using the ValidationService.</li>
     *   <li>If validation passes, saves the client to the repository.</li>
     *   <li>If validation fails, prints an error message.</li>
     * </ul>
     *
     * @param client the Client object to be created.
     */
    public void createClient(Client client) {
        try {
            // Validate client data before saving.
            ValidationService.validateClientData(client);
            clientRepository.save(client);
            System.out.println("Client created: " + client);
        } catch (InvalidClientDataException e) {
            System.err.println("Error creating client: " + e.getMessage());
        }
    }

    /**
     * Retrieves a client by its unique identifier.
     *
     * @param clientId the unique identifier of the client.
     * @return the Client object with the matching ID, or null if no such client exists.
     */
    public Client getClientById(int clientId) {
        return clientRepository.getClientById(clientId);
    }

    /**
     * Updates an existing client's information after validating its data.
     * <p>
     * The method performs the following steps:
     * <ul>
     *   <li>Validates the updated client's data using the ValidationService.</li>
     *   <li>If validation passes, updates the client in the repository.</li>
     *   <li>If validation fails, prints an error message.</li>
     * </ul>
     *
     * @param client the Client object containing updated data.
     */
    public void updateClient(Client client) {
        try {
            // Validate client data before updating.
            ValidationService.validateClientData(client);
            clientRepository.updateClient(client);
            System.out.println("Client updated: " + client);
        } catch (InvalidClientDataException e) {
            System.err.println("Error updating client: " + e.getMessage());
        }
    }

    /**
     * Deletes a client from the repository based on its unique identifier.
     *
     * @param clientId the unique identifier of the client to be deleted.
     */
    public void deleteClient(int clientId) {
        clientRepository.deleteClient(clientId);
        System.out.println("Client deleted (ID: " + clientId + ")");
    }
}