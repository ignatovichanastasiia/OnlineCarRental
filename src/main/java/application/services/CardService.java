package application.services;

import application.models.Card;

public class CardService {

	public void addCard(String strCardNumber, String clientFirstName, String clientLastName, String clientEmail,
			String clientPhone, String cardCvv, int intCardMonth, int intCardYear, String cardHolder, String clientID) {
		Card card = new Card(strCardNumber, clientFirstName, clientLastName, clientEmail,
			clientPhone, cardCvv, intCardMonth, intCardYear, cardHolder, clientID);
		
	}
	


}
