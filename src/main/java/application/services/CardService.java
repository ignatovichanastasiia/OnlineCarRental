package application.services;

import application.exceptions.InvalidCreditCardNumberException;
import application.exceptions.InvalidEmailException;
import application.exceptions.InvalidClientDataException;
import application.models.Card;
import application.repositories.CardRepository;
import application.services.ValidationService;

/**
 * CardService provides CRUD operations for Card objects.
 * It delegates validation to ValidationService and handles exceptions.
 */
public class CardService {
    
    private CardRepository cardRepository;
    
    /**
     * Constructs a new CardService with the specified CardRepository.
     * 
     * @param cardRepository the repository to persist Card objects.
     */
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    
    /**
     * Creates a new Card after validating input data via ValidationService.
     *
     * @param strCardNumber   The card number.
     * @param clientFirstName The first name of the cardholder.
     * @param clientLastName  The last name of the cardholder.
     * @param clientEmail     The email address of the cardholder.
     * @param clientPhone     The phone number of the cardholder.
     * @param cardCvv         The card CVV number.
     * @param intCardMonth    The expiry month of the card.
     * @param intCardYear     The expiry year of the card.
     * @param cardHolder      The full name as printed on the card.
     * @return The newly created Card object.
     * @throws InvalidCreditCardNumberException if the card number is invalid.
     * @throws InvalidEmailException if the email is invalid.
     * @throws InvalidClientDataException if any required data is missing or invalid.
     */
    public Card addCard(String strCardNumber, String clientFirstName, String clientLastName, String clientEmail,
                        String clientPhone, String cardCvv, int intCardMonth, int intCardYear, String cardHolder, String clientAppId)
                        throws InvalidCreditCardNumberException, InvalidEmailException, InvalidClientDataException {
        
        // Perform validation by delegating to ValidationService.
        ValidationService.validateCreditCardNumber(strCardNumber);
        ValidationService.validateEmail(clientEmail);
        ValidationService.validatePhone(clientPhone);
        ValidationService.validateName(clientFirstName, clientLastName);
        ValidationService.validateCvv(cardCvv);
        ValidationService.validateCardExpiry(intCardMonth, intCardYear);
        
        // Create a new Card object.
        Card card = new Card(strCardNumber, clientFirstName, clientLastName, clientEmail,
                             clientPhone, cardCvv, intCardMonth, intCardYear, cardHolder, clientAppId);
        
        // Save the new card via the repository.
        cardRepository.addCard(card);
        
        return card;
    }
    
    /**
     * Retrieves a Card by its unique identifier.
     *
     * @param id The card's unique id.
     * @return The Card object if found, or null otherwise.
     */
    public Card getCardById(int id) {
        return cardRepository.getCardById(id);
    }
    
    /**
     * Updates an existing Card after validating the new data.
     *
     * @param card The Card object containing updated data.
     * @return The updated Card.
     * @throws InvalidCreditCardNumberException if the card number is invalid.
     * @throws InvalidEmailException if the email is invalid.
     * @throws InvalidClientDataException if any required data is missing or invalid.
     */
    public Card updateCard(Card card) throws InvalidCreditCardNumberException, InvalidEmailException, InvalidClientDataException {
        // Perform validation via ValidationService.
        ValidationService.validateCreditCardNumber(card.getNumber());
        ValidationService.validateEmail(card.getEmail());
        ValidationService.validatePhone(card.getPhone());
        ValidationService.validateName(card.getFirstName(), card.getLastName());
        ValidationService.validateCvv(card.getCvv());
        ValidationService.validateCardExpiry(card.getMonth(), card.getYear());
        
        cardRepository.updateCard(card);
        return card;
    }
    
    /**
     * Deletes a Card by its unique identifier.
     *
     * @param id The id of the Card to delete.
     */
    public void deleteCard(int id) {
        cardRepository.deleteCard(id);
    }
}