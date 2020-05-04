package application;

import java.io.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button LoginButton;

    @FXML
    private CheckBox SaveCheckBox;

    @FXML
    private Hyperlink SignupLink;

    @FXML
    private PasswordField passwordF;

    @FXML
    private TextField usernameF;

    @FXML
    private Label wrong_info;
    
    @FXML
    private Button guestMode_B;
    
    @FXML
    private AnchorPane parentPane;
    
    @FXML
    private GridPane Gridroot;
    
    @FXML
    private ImageView Background;
    
    
    //List of each information
    @SuppressWarnings("unused")
	private List<Personal_Information> lst = new ArrayList<>();
    
    //Username for checkbox
    private String CB_username;

    //Resize the background image
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    //The width and height of mini-window
    //private double width = screen.getWidth()*0.3;
    //private double height = screen.getHeight()*0.3;
    
    @FXML  
    void initialize() throws IOException {
        Background.setFitWidth(screen.getWidth());
        Background.setFitHeight(screen.getHeight()*1.5);
        //////////////////////////////////////////////////////////
       
    	wrong_info.setOpacity(0);

    	//Get the current dir. path
    	File currentDirectory = new File(new File(".").getAbsolutePath().concat("\\Datas"));
    	
    	//Specify the path
    	File f = new File(currentDirectory.getCanonicalPath().concat("\\CheckBox_info.txt"));

		//Check whether the save button has clicked?
		BufferedReader br = new BufferedReader(new FileReader(f));
		// If yes, make the save button to be selected and display the username as well.
		if((CB_username = br.readLine()) != null) {
			SaveCheckBox.setSelected(true);
			usernameF.setText(CB_username);
		}
		br.close(); 
    }
    
    @FXML
    void Login_Click(ActionEvent event) throws IOException {	
    	FXMLLoader Loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent home_pane = Loader.load();
        Stage home_scene = (Stage) parentPane.getScene().getWindow();
        home_scene.setScene(new Scene(home_pane));
        home_scene.setTitle("Homepage");
        home_scene.show();
        homepageController hc = Loader.getController();
        hc.recommenderEvaluation();
        
    	/*for (int i = 1; i <= lst.size(); ++i) {
    		if(lst.get(i).getId().compareTo(usernameF.getText()) == 0 &&
    				lst.get(i).getPassword().compareTo(passwordF.getText()) == 0) {
    			//Enter the home page
    			break; // no more iterations
    		}
    	}
    	wrong_info.setOpacity(1); // display incorrect notification */
    }

    @FXML
    void Save_Click(ActionEvent event) throws IOException {
    	//Get the current dir. path
    	File currentDirectory = new File(new File(".").getAbsolutePath().concat("\\Datas"));
    	
    	//Specify the path
    	File f = new File(currentDirectory.getCanonicalPath().concat("\\CheckBox_info.txt"));
    	
    	//open the file
    	BufferedWriter br = new BufferedWriter(new FileWriter(f));
    	
    	if (!SaveCheckBox.isSelected()) {
    		//clear the file
    		br.write("");
    	}
    	else {
    		//Read the username from the field
    		CB_username = usernameF.getText();
    		// write the lastest username that needs to be saved.
        	br.write(CB_username);
    	}
    	br.close();
    }
    
    @FXML
   
    void Signup_Click(ActionEvent event) throws IOException {
    	//Switch to the signup scene..
    	Parent signup_page = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        Stage signup_scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
        signup_scene.setScene(new Scene(signup_page));
        signup_scene.setTitle("Signup");
        signup_scene.show();
    }

    @FXML
    private void Guest_Mode(ActionEvent event) throws IOException {
    	FXMLLoader Loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent home_pane = Loader.load();
        Stage home_scene = (Stage) parentPane.getScene().getWindow();
        home_scene.setScene(new Scene(home_pane));
        home_scene.setTitle("Guest mode");
        home_scene.show();
    }

    //If your registration passed, a popup alert will notice you.
	void SuccessedSignupAlert() throws IOException {
    	ButtonType login = new ButtonType("Login", ButtonData.OK_DONE);
    	Alert alert = new Alert(AlertType.CONFIRMATION,"Account ID: Unknown\nTemporary password: Unknown", login);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Congratulation! Your account has been acitviated. You can login by click the login button!");
    	//check click button types
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == login) {
    		Parent homepage = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            Stage home_scene = (Stage) parentPane.getScene().getWindow();
            home_scene.setScene(new Scene(homepage));
            home_scene.setTitle("Home page");
            home_scene.show();
    	}
    }
	
	//Fail for registration, an additional resumbit with a proper reason is required.
	void FailedSignupAlert() throws IOException {
    	ButtonType later = new ButtonType("Later", ButtonData.FINISH);
    	ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
    	Alert alert = new Alert(AlertType.ERROR,
    			"Your registration has been denied, but you can submit again by click the submit button. "
    			+ "You will lose your last chance to submit if your click the later button"
    			, submit, later);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText(null);
    	//check click button types
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == submit) {
    		Alert confirmAlert = new Alert(AlertType.CONFIRMATION, "Submission completed.", ButtonType.OK);
    		confirmAlert.setTitle("Confirmation");
        	confirmAlert.setHeaderText(null);
        	confirmAlert.showAndWait();
    	}
	}

/*   private Alert getAlert(AlertType at, String content, ButtonType bt, String title) {
    	Alert alert = new Alert(at, content, bt);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();    	
    	return alert;
    }*/
    
	void signupAlert() throws IOException{
		// TODO Auto-generated method stub
		//SuccessedSignupAlert();
		FailedSignupAlert();
	}
}
