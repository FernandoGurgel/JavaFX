package ch.makery.address.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	private StringProperty fistName;
	private StringProperty lastName;
	private StringProperty street;
	private StringProperty city;
	private StringProperty postalCode;
	private final ObjectProperty<LocalDate> bithday;
	

	public Person() {
		this(null,null);
	}
	
	public Person(String nome, String sobre) {
		this.fistName = new SimpleStringProperty(nome);
		this.lastName = new SimpleStringProperty(sobre);
		this.street = new SimpleStringProperty("Manaus");
		this.city = new SimpleStringProperty("85858");
		this.postalCode = new SimpleStringProperty("69.089-102");
		this.bithday = new SimpleObjectProperty<LocalDate>(LocalDate.now());
				
	}
	
	public StringProperty firstNameProperty() {
        return fistName;
    }

	public StringProperty lastNameProperty() {
        return lastName;
    }

	public String getFistName() {
		return fistName.get();
	}

	public void setFistName(String fistName) {
		this.fistName.set(fistName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public String getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(String postalCode) {
		this.postalCode.set( postalCode);
	}

	public ObjectProperty<LocalDate> getBithday() {
		return bithday;
	}
}
