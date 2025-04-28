package application.repositories;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.models.Card;

/**
 * The CardRepository class maintains a list of Card objects and provides methods
 * to serialize and deserialize the list to/from a file.
 */
public class CardRepository {

    // File where the card list will be serialized.
    private static final String CLIENTS_CARDS = "cards.dat";

    // The list holding all Card objects.
    private List<Card> cards;

    /**
     * Constructs a new CardRepository.
     * Attempts to load the list of cards from the file.
     * If no file exists, initializes an empty list.
     */
    public CardRepository() {
        cards = loadCardsFromFile();
        if (cards == null) {
            cards = new ArrayList<>();
        }
    }

    /**
     * Returns the current list of Card objects.
     *
     * @return the list of cards.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds a new Card to the repository and saves the updated list to file.
     *
     * @param card the Card to add.
     */
    public void addCard(Card card) {
        cards.add(card);
        saveCardsToFile();
    }

    /**
     * Removes a Card from the repository and saves the updated list to file.
     *
     * @param card the Card to remove.
     */
    public void removeCard(Card card) {
        cards.remove(card);
        saveCardsToFile();
    }

    /**
     * Saves the current list of cards to the file via serialization.
     */
    public void saveCardsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLIENTS_CARDS))) {
            oos.writeObject(cards);
        } catch (IOException e) {
            System.err.println("Error saving cards: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads and returns the list of cards from the file via deserialization.
     *
     * @return the list of cards if successfully loaded; null otherwise.
     */
    @SuppressWarnings("unchecked")
    public List<Card> loadCardsFromFile() {
        File file = new File(CLIENTS_CARDS);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Card>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading cards: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Updates an existing Card object in the repository.
     * 
     * This method searches for a Card object with the same unique identifier (id) as the provided Card.
     * If a matching Card is found, it is replaced with the provided updated Card object.
     *
     * @param card The updated Card object containing new data.
     * @return true if the update was successful (i.e., the Card was found and replaced),
     *         false otherwise (i.e., no matching Card was found).
     */
    public boolean updateCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getClientAppID().equals(card.getClientAppID())) { // Matching Card found by its unique ID
                cards.set(i, card); // Replace the old Card with the updated one
                return true; // Return true to indicate successful update
            }
        }
        return false; // Return false if no matching Card was found
    }
    
    /**
     * Deletes a Card object from the repository using its unique identifier.
     * 
     * This method removes a Card from the repository if its unique identifier matches the given id.
     *
     * @param id The unique identifier of the Card to be deleted.
     * @return true if the Card was successfully deleted (i.e., it was found and removed),
     *         false otherwise (i.e., no matching Card was found).
     */
    public boolean deleteCard(int id) {
        return cards.removeIf(card -> card.getClientAppID().equals(String.valueOf(id)));
    }
          
    /**
     * Retrieves a Card object by its unique identifier.
     * 
     * This method searches the repository for a Card object where the Client Application ID
     * matches the given unique identifier (id). The search is conducted using a stream operation.
     * If a matching Card is found, it is returned; otherwise, the method returns null.
     *
     * @param id The unique identifier (Client Application ID) of the Card to be retrieved.
     * @return The Card object with the matching Client Application ID, or null if no match is found.
     */
    public Card getCardById(int id) {
        return cards.stream()
                    .filter(card -> card.getClientAppID().equals(String.valueOf(id)))
                    .findFirst()
                    .orElse(null);
    }
}