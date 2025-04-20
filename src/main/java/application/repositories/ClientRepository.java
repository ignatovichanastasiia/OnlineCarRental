package application.repositories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import application.models.Client;

/**
 * The ClientRepository class provides CRUD operations for managing Client
 * objects. It allows adding, retrieving, updating, and deleting clients from an
 * in-memory list. Additionally, it supports serialization and deserialization
 * of this list to persist client data.
 */
public class ClientRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	// The list that stores all Client objects.
	private List<Client> clientList = new ArrayList<>();

	// CONSTRACT W Connection
	public ClientRepository(Connection connection) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Saves a new client to the repository.
	 *
	 * @param client the Client object to be saved.
	 */
	public void save(Client client) {
		clientList.add(client);
	}

	/**
	 * Retrieves a client by its unique identifier.
	 *
	 * @param id the unique identifier of the client.
	 * @return the Client object with the matching id, or null if no such client
	 *         exists.
	 */
	public Client getClientById(int id) {
		for (Client client : clientList) {
			if (client.getId() == id)
				return client;
		}
		return null;
	}

	/**
	 * Updates the information of an existing client in the repository.
	 *
	 * @param updatedClient the Client object containing updated data.
	 */
	public void updateClient(Client updatedClient) {
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getId() == updatedClient.getId()) {
				clientList.set(i, updatedClient);
				break;
			}
		}
	}

	/**
	 * Deletes a client from the repository using its unique identifier.
	 *
	 * @param id the unique identifier of the client to be deleted.
	 */
	public void deleteClient(int id) {
//		clientList.removeIf(client -> client.getId() == id);
	}

	/**
	 * Saves the current list of clients to a file.
	 *
	 * @param filename the path and name of the file where client data will be
	 *                 saved.
	 */
	public void saveToFile(String filename) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(clientList);
			System.out.println("Client data successfully saved to file: " + filename);
		} catch (IOException e) {
			System.err.println("Error saving client data: " + e.getMessage());
		}
	}

	/**
	 * Loads the client list from a file into the repository.
	 *
	 * @param filename the path and name of the file from which client data will be
	 *                 loaded.
	 */
	@SuppressWarnings("unchecked")
	public void loadFromFile(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			clientList = (List<Client>) ois.readObject();
			System.out.println("Client data successfully loaded from file: " + filename);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading client data: " + e.getMessage());
		}
	}

	/**
	 * Retrieves the complete list of clients stored in the repository.
	 *
	 * @return a List containing all Client objects.
	 */
	public List<Client> getAllClients() {
		return clientList;
	}
}
