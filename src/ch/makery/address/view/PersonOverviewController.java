package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> nomeColuna;
	@FXML
	private TableColumn<Person, String> sobrenomeColuna;
	@FXML
	private Label nomeLabel;
	@FXML
	private Label sobreLabel;
	@FXML
	private Label enderecoLabel;
	@FXML
	private Label cepLabel;
	@FXML
	private Label cidadeLabel;
	@FXML
	private Label anivesarioLabel;
	
	private MainApp app;
	
	public PersonOverviewController() {
		// TODO Auto-generated constructor stub
	}
	@FXML
	private void initialize(){
		nomeColuna.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		sobrenomeColuna.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		showPersonDetails(null);
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(Observable,oldValue,newValue) -> showPersonDetails(newValue));
		
	}
	private void showPersonDetails(Person person) {
		if(person != null){
			nomeLabel.setText(person.getFistName());
			sobreLabel.setText(person.getLastName());
			enderecoLabel.setText(person.getStreet());
			cidadeLabel.setText(person.getCity());
			cepLabel.setText(person.getPostalCode());
			//sanivesarioLabel.setText(person.getBithday());
		}else{
			nomeLabel.setText("");
			sobreLabel.setText("");
			enderecoLabel.setText("");
			cidadeLabel.setText("");
			cepLabel.setText("");
			anivesarioLabel.setText("");
		}
	}
	
	@FXML
	private void handleNewPerson(){
		Person tempPerson = new Person();
		boolean okClick = app.showPersonEditDialog(tempPerson);
		if(okClick){
			app.getPersonDate().add(tempPerson);
		}
	}
	
	@FXML
	private void handleEditPerson(){
		Person selectPerson = personTable.getSelectionModel().getSelectedItem();
		if(selectPerson != null){
			boolean okClick = app.showPersonEditDialog(selectPerson);
			if(okClick){
				showPersonDetails(selectPerson);
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma Selecao");
			alert.setHeaderText("Nenhuma pessoa Selecao");
			alert.setContentText("Por favor,selecione uma pessoa na tabela");
			alert.showAndWait();
		}
	}
	
	public void setApp(MainApp app) {
		this.app = app;
		personTable.setItems(app.getPersonDate());
		
		
	}
	
	
	@FXML
	private void handleDeletePerson(){
		int selectIndex = personTable.getSelectionModel().getSelectedIndex();
		personTable.getItems().remove(selectIndex);
	}
	
	

}
