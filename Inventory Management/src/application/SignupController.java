package application;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SignupController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Firstname;
    
    @FXML
    private TextField Lastname;
    
    @FXML
    private TextField Interest;
    
    @FXML
    private TextField Referer;
    
    @FXML
    private TextField Email;
    
    @FXML
    private Button BackButton;

    @FXML
    private Label Email_Error;

    @FXML
    private Label First_Error;

    @FXML
    private Label Interest_Error;

    @FXML
    private Label Last_Error;

    @FXML
    private Label Referer_Error;

    @FXML
    private Button signupButton;
    
    @FXML
    private AnchorPane parentPane;
    
    @FXML
    private GridPane Gridroot;
    
    //Patterns for the registring fields
    //Start with an uppercase letter followed by at least two characters (includes Firstname, Lastname, and Interest)
    private final Pattern patternName = Pattern.compile("[A-Z][a-z]{2,}$");
    private final Pattern patternReferer = Pattern.compile("[A-Z][a-z]{2,}\\s[A-Z][a-z]{2,}$");
    private final Pattern patternEmail = Pattern.compile("[a-zA-Z_0-9]{4,}@([a-zA-z]{1,}\\.{1}){1,}[a-zA-Z]{2,}$");

    @FXML
    void Back_Click(ActionEvent event)throws IOException {
    	//Move to the login page
    	MoveToLoginPage(event);
	}

    @FXML
    void Signup_Click(ActionEvent event)throws IOException {
    	if (ValidateFields()) {
        	//Show the user that she/he has completed the registration
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information");
        	alert.setHeaderText("Registration Completed");
        	alert.setContentText("The final decision will be send to you through Email within 24 hours.");
        	alert.showAndWait();

        	//If the user click OK then move the the login page.
        	if (alert.getResult() == ButtonType.OK) {
            	MoveToLoginPage(event);
        	}	
    	}
    	else {
    		
    	}
    }

    // Login page shortout
    private void MoveToLoginPage(ActionEvent event)throws IOException{
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
        primaryStage.setScene(new Scene(login_page));
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
    
    // Check the pattern of fields
    private boolean ValidateFields() {
        boolean result = true;
        
        ObservableList<String> fn = Firstname.getStyleClass();
        ObservableList<String> ln = Lastname.getStyleClass();
        ObservableList<String> it = Interest.getStyleClass();
        ObservableList<String> rf = Referer.getStyleClass();
        ObservableList<String> em = Email.getStyleClass();
        
        if (!patternName.matcher(Firstname.getText()).matches()) {
        	First_Error.setVisible(true);
        	fn.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	First_Error.setVisible(false);
        	fn.removeAll(Collections.singleton("error"));
        }
        if (!patternName.matcher(Lastname.getText()).matches()) {
        	Last_Error.setVisible(true);
        	ln.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	Last_Error.setVisible(false);
        	ln.removeAll(Collections.singleton("error"));
        }
        if (!patternName.matcher(Interest.getText()).matches()) {
        	Interest_Error.setVisible(true);
        	it.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	Interest_Error.setVisible(false);
        	it.removeAll(Collections.singleton("error"));
        }
        if (!patternReferer.matcher(Referer.getText()).matches()) {
        	Referer_Error.setVisible(true);
        	rf.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	Referer_Error.setVisible(false);
        	rf.removeAll(Collections.singleton("error"));
        }
        if (!patternEmail.matcher(Email.getText()).matches()) {
        	Email_Error.setVisible(true);
        	em.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	Email_Error.setVisible(false);
        	em.removeAll(Collections.singleton("error"));
        }
        return result;
    }
  
    @FXML
    void initialize() {
    }

}
