package application;

import java.io.*;
import java.util.ResourceBundle;

import Group.Group_List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
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

import java.util.Optional;

public class LoginController{
	
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
    
    private UserList userList = new UserList();
    
	private Information_List Info_List = new Information_List();
    
	private Group_List G_List = new Group_List();
	
    //Username for checkbox
    private String CB_username;

    //Resize the background image
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    //The width and height of mini-window
    
    @FXML  
    void initialize() throws IOException {
        Background.setFitWidth(screen.getWidth());
        Background.setFitHeight(screen.getHeight()*1.5);
        //////////////////////////////////////////////////////////
       
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
    void Login_Click(ActionEvent event) throws IOException{	        
        //check login information
        for(int i = 0; i < userList.getAll_Size(); ++i) {
        	if(userList.getAll_User().get(i).getID().compareTo(usernameF.getText()) == 0 &&	
        			userList.getAll_User().get(i).getPassword().compareTo(passwordF.getText()) == 0) {
        		FXMLLoader Loader = sceneSwitch("Homepage.fxml", "Homepage");	//Move to the homepage
        		boolean evaluation = false;
        		//Chec whether anyone of your presentee needs to be evaluated.  
        		if(userList.getGuest().getRecommender() != null)
        			if(userList.getGuest().getRecommender().compareTo(userList.getAll_User().get(i).getName()) == 0 && userList.getGuest().isLogin()) {
        				evaluation = true;
        			}
        		HomepageController hc = Loader.getController();
        		//para1: eavluation; para2: password change; para3: transit userList; para4: specific user
        		hc.carryingInformation(evaluation, false, this.userList,
        				userList.getAll_User().get(i), this.Info_List, this.G_List);        			
        	}
        }
    	wrong_info.setVisible(true); // display incorrect notification 
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
    	FXMLLoader Loader = sceneSwitch("SignUpPage.fxml", "Registration");
    	SignupController sc = Loader.getController();
    	sc.LoginToSignup(userList, Info_List);
    }

    @FXML
    private void Guest_Mode(ActionEvent event) throws IOException {
    	FXMLLoader Loader = sceneSwitch("Homepage.fxml", "Homepage");
    	HomepageController hc = Loader.getController();
    	hc.GuestMode(this.G_List);
    }

    //If your registration passed, a popup alert will notice you.
	void SuccessedSignupAlert() throws IOException {
    	ButtonType login = new ButtonType("Login", ButtonData.OK_DONE);
    	Alert alert = new Alert(AlertType.CONFIRMATION,"Account ID: " + userList.getGuest().getID() +
    			"\nYour password: " + userList.getGuest().getPassword(), login);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Congratulation! Your account has been acitviated. You can login by click the login button!");
    	//check click button types
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if(result.get() == login) {
    		FXMLLoader Loader = sceneSwitch("Homepage.fxml", "Homepage");	//Move to the homepage
    		HomepageController hc = Loader.getController();
			hc.carryingInformation(false, true, userList,
					userList.getGuest(), this.Info_List, this.G_List);   
    	}
    }
	
	//Fail for registration, an additional resumbit with a proper reason is required.
	void FailedSignupAlert() throws IOException {		
    	ButtonType later = new ButtonType("Later", ButtonData.FINISH);
    	ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
    	Alert alert = new Alert(AlertType.ERROR,
    			"Your registration has been denied, but you can submit again by click the submit button. ",
    			 submit, later);
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
	    
	//Carry information from the main class
	void signupAlert(UserList ul, Information_List il, Group_List gl) throws IOException{
		this.userList = ul; //set database
		this.Info_List = il;
		this.G_List = gl;
		if(userList.getGuest().getNumRegister() > 0 && !userList.getGuest().isActivated())
			FailedSignupAlert();	
		else if(userList.getGuest().getNumRegister() != 0 && !userList.getGuest().isActivated())
			SuccessedSignupAlert();
	}
	
	private FXMLLoader	sceneSwitch(String url, String title) throws IOException {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource(url));
        Parent home_pane = Loader.load();
        Stage stage = (Stage) parentPane.getScene().getWindow();
        stage.setScene(new Scene(home_pane));
        stage.setTitle(title);
        return Loader;
	}

	public void HomeToLogin(UserList ul, Information_List il, Group_List gl) {
		this.userList = ul;
		this.Info_List = il;
		this.G_List = gl;
	}

	public void SignupToLogin(UserList ul, Information_List il) {
		this.userList = ul;
		this.Info_List = il;
	}
	
}
