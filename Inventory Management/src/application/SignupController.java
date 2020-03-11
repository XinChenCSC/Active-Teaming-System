package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Button BackButton;

    @FXML
    private TextField Email;

    @FXML
    private Label Email_A_Error;

    @FXML
    private Label First_N_Error;

    @FXML
    private TextField FirstnameF;

    @FXML
    private TextField Interest;

    @FXML
    private Label Interest_Error;

    @FXML
    private Label Last_N_Error;

    @FXML
    private TextField Referer;

    @FXML
    private Label Referer_Error;

    @FXML
    private TextField lastnameF;

    @FXML
    private Button signupButton;


    @FXML
    void Back_Click(ActionEvent event)throws IOException {
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(login_page));
        primaryStage.setMaximized(true);
    }

    @FXML
    void Signup_Click(ActionEvent event)throws IOException {
       	Parent completed_page = FXMLLoader.load(getClass().getResource("AccCreated.fxml"));
        Stage completed_scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        completed_scene.setScene(new Scene(completed_page));
        completed_scene.setMaximized(true);
    }

    @FXML
    void initialize() {
    }

}
