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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

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
    
    
    //List of each information
    private List<Personal_Information> lst = new ArrayList<>();
    
    //Username for checkbox
    private String CB_username;

	private ImageView imageView;


    @FXML
    void Login_Click(ActionEvent event) throws IOException {
    	for (int i = 1; i <= lst.size(); ++i) {
    		if(lst.get(i).getId().compareTo(usernameF.getText()) == 0 &&
    				lst.get(i).getPassword().compareTo(passwordF.getText()) == 0) {
    			//Enter the home page
    			break; // no more iterations
    		}
    	}
    	wrong_info.setOpacity(1); // display incorrect notification 
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
        signup_scene.setFullScreen(true);
        //signup_scene.setMaximized(true);
    }

    @FXML
    void initialize() throws IOException {
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
    void Guest_Mode(ActionEvent event) {
    }

}
