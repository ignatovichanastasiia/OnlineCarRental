package application.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.models.DriversLicense;

public class DriversLicenseService {

	//TODO
	List<DriversLicense> licenseList = new ArrayList();
	
	public DriversLicense getLicenseByNumber(String lisenseNumber) {
		return null;
	}
	
	public DriversLicense getLicenseByIDNumber(String clientIDNumber) {
		return null;
	}

	public void addDriversLicense(String taudatZehudNumber, String strLicenseNumber, LocalDate clientBirth,
			LocalDate dateIssue, LocalDate dateExpir) {
		DriversLicense drLicense = new DriversLicense(taudatZehudNumber, strLicenseNumber, clientBirth, dateIssue, dateExpir);
//!!TODO NOW - не правильный метод! ПРодумать путь!		ClientService.getClient.setLicense(drLicense);
	}
}
