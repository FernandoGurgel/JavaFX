package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.print.PageOrientation;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> personDate = FXCollections.observableArrayList();
	
	public MainApp() {
		personDate.add (new Person("Hans","Muster"));
		personDate.add (new Person("Fernando","Gurgel"));
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		
		iniRootLayout();
		showPersonOverView();
		//initPerson();
	}
	
	
	
	private void showPersonOverView() {
		try {
	        // Carrega a person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/PersonOverView.fxml"));
	        AnchorPane personOverview = (AnchorPane) loader.load();

	        // Define a person overview no centro do root layout.
	        rootLayout.setCenter(personOverview);
	        
	      
	        // Dá ao controlador acesso à the main app.
	        PersonOverviewController controller = loader.getController();
	        controller.setApp(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean showPersonEditDialog(Person person){
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Page");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene stage = new Scene(page);
			dialogStage.setScene(stage);
			
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);
			
			dialogStage.showAndWait();
			return controller.isOnClicked();			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void iniRootLayout() {
		try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public ObservableList<Person> getPersonDate(){
		return personDate;
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
