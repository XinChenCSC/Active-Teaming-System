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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
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
    private TextField FirstnameF;
    
    @FXML
    private TextField lastnameF;
    
    @FXML
    private TextField Interest;
    
    @FXML
    private TextField Referer;
    
    @FXML
    private TextField Email;
    
    @FXML
    private Button BackButton;

    @FXML
    private Label Email_A_Error;

    @FXML
    private Label First_N_Error;

    @FXML
    private Label Interest_Error;

    @FXML
    private Label Last_N_Error;

    @FXML
    private Label Referer_Error;

    @FXML
    private Button signupButton;
    
    //Patterns for the registring fields
    private final Pattern patternName = Pattern.compile("[A-Z]+[a-z]{2,}\\s*"); 



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
        primaryStage.setFullScreen(true);
        //primaryStage.setMaximized(true);
    }
    
    // Check the pattern of fields
    private boolean ValidateFields() {
        boolean result = true;
        
        ObservableList<String> styleClass = FirstnameF.getStyleClass();

        if (!patternName.matcher(FirstnameF.getText()).matches()) {
        	First_N_Error.setVisible(true);
        	styleClass.removeAll(Collections.singleton("correct"));
        	 result = false;
        }
        return result;
    }
    @FXML
    void initialize() {
    }

}
