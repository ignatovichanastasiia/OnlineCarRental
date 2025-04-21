package application.repositories;

import application.models.Card;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
}