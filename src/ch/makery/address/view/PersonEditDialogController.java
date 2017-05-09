package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
	
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField codeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;
	
	private Stage dialogStage;
	private Person person;
	private boolean onClick = false;
	
	@FXML
	private void initilize(){
		
	}
	
	public void setDialogStage (Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void setPerson(Person person){
		this.person = person;
		
		firstNameField.setText(person.getFistName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		codeField.setText(person.getPostalCode());
		cityField.setText(person.getCity());
		birthdayField.setText("");
		birthdayField.setPromptText("dd.mm.yyyy");
	}
	
	public boolean isOnClicked(){
		return onClick;
	}
	
	@FXML
	private void handleOk(){
		if(isInputValid()){
			person.setFistName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setCity(cityField.getText());
			person.setStreet(streetField.getText());
			//person.setPostalCode(codeField.getText());
			
			onClick = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}

	private boolean isInputValid() {
		String erroMensagem = "";
		
		if(firstNameField.getText() == null || firstNameField.getText().length() ==0 ){
			erroMensagem+= "Nome Invalido!!";
		}
		if(lastNameField.getText() == null || lastNameField.getText().length() ==0 ){
			erroMensagem+= "Sobrenome Invalido!!";
		}
		if(streetField.getText() == null || streetField.getText().length() ==0 ){
			erroMensagem+= "Rua Invalido!!";
		}
		if(codeField.getText() == null || codeField.getText().length() ==0 ){
			erroMensagem+= "Cep Invalido!!";
		}else{
			try {
				Integer.parseInt(codeField.getText());
			} catch (NumberFormatException e) {
				erroMensagem += "Cep tem que ser inteiro!!";
			}
		}
		
		if(cityField.getText() == null || cityField.getText().length() == 0){
			erroMensagem += "Cidade Invalida";
		}
		
		if (erroMensagem.length() == 0){
			return true;
		}else{
			Alert alert = new Alert (AlertType.ERROR);
			alert.setTitle("Campos Invalido");
			alert.setHeaderText("Por favor, corrija os campos invalidos");
			alert.setContentText(erroMensagem);
			alert.showAndWait();
			
			return false;
		}	
	}
}
