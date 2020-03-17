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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    
    @FXML
    private AnchorPane parentPane;
    
    @FXML
    private GridPane Gridroot;
    
    @FXML
    private ImageView Background;
    
    
    //List of each information
    private List<Personal_Information> lst = new ArrayList<>();
    
    //Username for checkbox
    private String CB_username;

    @FXML
    void Login_Click(ActionEvent event) throws IOException {
    	
    	Parent homepage = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage home_scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_scene.setScene(new Scene(homepage));
        home_scene.setTitle("Searching area");
        home_scene.show();
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
    void initialize() throws IOException {
    	//Resize the background image
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
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
    void Guest_Mode(ActionEvent event) {
    }

}
