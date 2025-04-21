package application.models;

import java.time.LocalDate;

public class DriversLicense {
	String taudatZehut;
	String id;
	LocalDate dateBirth;
	char category;
	LocalDate issue;
	LocalDate expiration;

	public DriversLicense(String number, String id, LocalDate dateBirth, LocalDate issue,
			LocalDate expiration) {
		this.taudatZehut = number;
		this.id = id;
		this.dateBirth = dateBirth;
		this.category = 'B';
		this.issue = issue;
		this.expiration = expiration;
	}

	public String getNumberTZ() {
		return taudatZehut;
	}

	public void setNumberTZ(String number) {
		this.taudatZehut = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public char getCategory() {
		return category;
	}

	public void setCategory(char ctegory) {
		this.category = category;
	}

	public LocalDate getIssue() {
		return issue;
	}

	public void setIssue(LocalDate issue) {
		this.issue = issue;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "DriverLicens [number=" + taudatZehut + ", id=" + id + ", dateBirth=" + dateBirth + ", category=" + category
				+ ", issue=" + issue + ", expiration=" + expiration + "]";
	}

}
