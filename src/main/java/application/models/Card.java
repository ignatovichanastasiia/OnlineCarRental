package application.models;

public class Card {
	String number;
	String firstName;
	String lastName;
	String email;
	String phone;
	String cvv;
	int month;
	int year;
	String cardholder;
	String clientAppID;
	
	public Card(String number, String firstName, String lastName, String email, String phone,
			String cvv, int month, int year, String cardholder, String clientAppID) {
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.cvv = cvv;
		this.month = month;
		this.year = year;
		this.cardholder = cardholder;
		this.clientAppID = clientAppID;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	
	public String getClientAppID() {
		return clientAppID;
	}
	
	public void setClientAppID(String clientAppID) {
		this.clientAppID = clientAppID;
	}
	
	@Override
	public String toString() {
		return "Card [number=" + number + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", cvv=" + cvv + ", month=" + month + ", year=" + year + ", cardholder="
				+ cardholder + ", clientAppID=" + clientAppID + "]";
	}

	
}
