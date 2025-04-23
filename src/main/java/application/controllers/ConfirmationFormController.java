package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GUI.CarSelectionForm;
import application.GUI.RentalSummaryForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ConfirmationFormController implements Initializable {
	private static String CONTRACT_TEXT;
	private AppContext context;
	
	@FXML
	private TextArea contractText;

	@FXML
	private CheckBox agreeCheckBox;

	@FXML
	private Button nextButton;

	@FXML
	private Button backButton;

	public ConfirmationFormController(AppContext context) {
		this.context = context;
		setDefaultText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contractText.setText(CONTRACT_TEXT);

		nextButton.setOnAction(ev -> {
			System.out.println("Click to next!");
			clickNextButton(ev);
		});
		
		nextButton.setOnAction(ev -> {
			System.out.println("Click to back!");
			clickBackButton(ev);
		});

	}

	private void clickNextButton(ActionEvent ev) {
		if(agreeCheckBox.isSelected()) {
			//change window
			Stage currentStage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
			new RentalSummaryForm().startRentalSummaryForm(currentStage, context);
		}
	}

	public void clickBackButton(ActionEvent ev) {
		//change window
		Stage currentStage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
		new CarSelectionForm().startCarSelectionForm(currentStage, context); 
		}

	public void setContractText(String text) {
		ConfirmationFormController.CONTRACT_TEXT = text;
	}

	public void setDefaultText() {
		ConfirmationFormController.CONTRACT_TEXT =

				"""
						Car Rental Offer Agreement

						Effective Date: [Insert Date]
						Company Name: [Insert Rental Company Name]
						Address: [Insert Company Address]
						Phone: [Insert Phone Number]
						Email: [Insert Email Address]
						Website: [Insert Website URL]

						This Public Offer Agreement (\"Agreement\") governs the terms and conditions under which the Car Rental Company (\"Company\") offers vehicle rental services to any individual (\"Client\") who accepts the terms of this Agreement.

						By booking or using the rental services, the Client confirms acceptance of the following terms:

						1. Subject of the Agreement

						1.1 The Company agrees to provide the Client with a rental vehicle for temporary use, and the Client agrees to accept and pay for such services in accordance with the terms of this Agreement.

						1.2 The vehicle type, rental period, and cost shall be specified in the booking confirmation provided to the Client via email or the Company's website.

						2. Terms of Use

						2.1 The Client must be at least 21 years old and possess a valid driver’s license recognized in the country of rental.

						2.2 The Client agrees to operate the vehicle in a lawful manner and return it in the same condition as received, excluding normal wear and tear.

						2.3 Smoking and transporting illegal substances in the vehicle is strictly prohibited.

						3. Rental Period and Return

						3.1 The rental period begins at the date and time the vehicle is picked up and ends when the vehicle is returned.

						3.2 Late returns may incur additional fees as per the Company's pricing policy.

						3.3 Early returns do not entitle the Client to a refund for unused rental time unless specified otherwise in writing.

						4. Payment and Deposit

						4.1 The total rental fee shall be paid in advance or upon vehicle pick-up, as per the Company's procedures.

						4.2 A refundable security deposit may be required and will be held against damages, fines, or violations.

						4.3 The deposit will be refunded within [X] business days after the vehicle is returned in satisfactory condition.

						5. Liability and Insurance

						5.1 The vehicle is insured according to the applicable laws and insurance policies.

						5.2 The Client is financially responsible for any damage, loss, or fines incurred due to negligence, violation of the terms, or unauthorized use.

						5.3 Optional full coverage insurance may be offered at an additional fee.

						6. Cancellations and Refunds

						6.1 Cancellations made [X] hours before the rental period are eligible for a full refund.

						6.2 Later cancellations may result in a partial or full charge, depending on the Company’s policy.

						7. Force Majeure

						7.1 Neither party shall be liable for delays or failure to perform due to causes beyond their reasonable control, including but not limited to natural disasters, government regulations, or strikes.

						8. Governing Law

						8.1 This Agreement shall be governed by and interpreted in accordance with the laws of [Insert Jurisdiction].

						8.2 Any disputes arising under this Agreement shall be subject to the exclusive jurisdiction of the courts of [Insert Location].

						9. Final Provisions

						9.1 This Agreement is a public offer. By proceeding with the booking or rental, the Client agrees to all terms outlined herein.

						9.2 The Company reserves the right to update this Agreement. The current version will always be available on the Company’s website.

						For inquiries, please contact us:
						[Insert support email or phone]
						""";
	}


}
