package application;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Clients.OU;
import Clients.VIP;
import Email.Email;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    
    private UserList userList = new UserList();
    
	private Information_List Info_List = new Information_List();
	
	private Group_List G_List = new Group_List();
    
    //Patterns for the registring fields
    //Start with an uppercase letter followed by at least two characters (includes Firstname, Lastname, and Interest)
    private final Pattern patternName = Pattern.compile("[A-Z][a-z]{2,}$");
    private final Pattern patternReferer = Pattern.compile("[A-Z][a-z]{2,}\\s[A-Z][a-z]{2,}$");
    private final Pattern patternEmail = Pattern.compile("[a-zA-Z_0-9]{4,}@([a-zA-z]{1,}\\.{1}){1,}[a-zA-Z]{2,}$");

    @FXML
    void initialize() {

    }
    
    @FXML
    void Back_Click(ActionEvent event)throws IOException {
    	//Move to the login page
    	FXMLLoader Loader = sceneSwitch("LoginPage.fxml", "Login");
    	LoginController lc = Loader.getController();
    	lc.SignupToLogin(this.userList, this.Info_List, this.G_List);
	}

    @FXML
    void Signup_Click(ActionEvent event)throws IOException {
    	if(userList.getGuest().getName() != null)
    		showAlert(AlertType.WARNING, "Can't signup at this moment."); 	
    	else if (ValidateFields()) {
        	//Show the user that she/he has completed the registration
        	Alert alert = showAlert(AlertType.CONFIRMATION, "Registration completed. Please wait for the final decision.");

        	//If the user click OK then move the the login page.
        	if (alert.getResult() == ButtonType.OK) {
            	FXMLLoader Loader = sceneSwitch("LoginPage.fxml", "Login");
            	LoginController lc = Loader.getController();
            	lc.SignupToLogin(this.userList, this.Info_List, this.G_List);
        	}	
    	}
    }
 
    private Alert showAlert(AlertType at, String message) {
        Alert alert = new Alert(at, message, ButtonType.OK);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert;
    }
    
	private FXMLLoader sceneSwitch(String url, String title) throws IOException {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource(url));
        Parent home_pane = Loader.load();
        Stage stage = (Stage) parentPane.getScene().getWindow();
        stage.setScene(new Scene(home_pane));
        stage.setTitle(title);
        return Loader;
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
        if (!patternReferer.matcher(Referer.getText()).matches() || !isInvalidReferer()) {
        	Referer_Error.setVisible(true);
        	rf.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	Referer_Error.setVisible(false);
        	rf.removeAll(Collections.singleton("error"));
        }
        if (!patternEmail.matcher(Email.getText()).matches() || isUsedEmail()) {
        	Email_Error.setVisible(true);
        	em.removeAll(Collections.singleton("correct"));
        	result = false;
        }
        else {
        	Email_Error.setVisible(false);
        	em.removeAll(Collections.singleton("error"));
        }
        //Save Visitor informations
        if(result) {
        	String str = "Name: " + Firstname.getText().toString() + " " + Lastname.getText().toString() + "\n" +
        				"Interest: " + Interest.getText().toString() + "\n" +
        				"Recommender: " + Referer.getText().toString() + "\n" +
        				"Email: " + Email.getText().toString();
        	
        	//Copy registration information to SU
        	Email email = new Email("Registration", str, Firstname.getText() + " " + Lastname.getText());
        	this.Info_List.CreateEmail(userList.getSU_User().get(0).getID(), email);
        	
        	//Save guest
        	userList.getGuest().setName(Firstname.getText() + " " + Lastname.getText());
        	userList.getGuest().setInterest(Interest.getText());
        	userList.getGuest().setRecommender(Referer.getText());
        	userList.getGuest().setEmail(Email.getText());

        	//Save referer
        	for(int i = 0; i < userList.getAll_Size(); ++i) {
        		if(userList.getAll_User().get(i).getName().compareTo(userList.getGuest().getName()) == 0) {
        			if(userList.getAll_User().get(i) instanceof OU)
        				((OU)userList.getAll_User().get(i)).addPresentee(userList.getGuest());
        			else if(userList.getAll_User().get(i) instanceof VIP)
        				((VIP)userList.getAll_User().get(i)).addPresentee(userList.getGuest());
        		}
        	}
        }
        return result;
    }
  
    private boolean isUsedEmail() {
    	//Email can't be used.
    	for(int i = 0; i < userList.getAll_Size(); ++i) {
    		if(userList.getAll_User().get(i).getEmail().compareTo(Email.getText()) == 0) {
    			return true;
    		}
    	}
		//Check whether the Guest email and name is in the system blacklist
    	for(int i = 0; i < this.Info_List.getSystem_Blacklist().size(); ++i) {
    		if(this.Info_List.getSystem_Blacklist().get(i).getEmail().compareTo(Email.getText()) == 0 ||
    				this.Info_List.getSystem_Blacklist().get(i).getName().compareTo(Firstname.getText() + " " + Lastname.getText()) == 0) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean isInvalidReferer() {
    	boolean result = false;
    	//Email can't be used.
    	for(int i = 0; i < userList.getAll_Size(); ++i) {
    		if(userList.getAll_User().get(i).getName().compareTo(Referer.getText()) == 0) {
    			result = true;
    		}
    	}
    	return result;
    }
    
	public void LoginToSignup(UserList ul, Information_List il, Group_List gl) {
		this.userList = ul;
		this.Info_List = il;
		this.G_List = gl;
	}

}
