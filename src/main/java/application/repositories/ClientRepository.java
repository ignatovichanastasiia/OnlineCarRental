package application.repositories;

import models.Client;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ClientRepository {
    private List<Client> clientList = new ArrayList<>();

    // CRUD-методы
    public void save(Client client) {
        clientList.add(client);
    }
    
    public Client getClientById(int id) {
        for (Client client : clientList) {
            if (client.getId() == id)
                return client;
        }
        return null;
    }
    
    public void updateClient(Client updatedClient) {
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getId() == updatedClient.getId()) {
                clientList.set(i, updatedClient);
                break;
            }
        }
    }
    
    public void deleteClient(int id) {
        clientList.removeIf(client -> client.getId() == id);
    }
    
    // Сериализация: сохранение в файл
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(clientList);
            System.out.println("Данные клиентов успешно сохранены в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных клиентов: " + e.getMessage());
        }
    }
    
    // Десериализация: загрузка из файла
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            clientList = (List<Client>) ois.readObject();
            System.out.println("Данные клиентов успешно загружены из файла: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке данных клиентов: " + e.getMessage());
        }
    }
    
    public List<Client> getAllClients() {
        return clientList;
    }
}