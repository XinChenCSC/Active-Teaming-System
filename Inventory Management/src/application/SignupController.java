package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TextField FirstnameF;

    @FXML
    private TextField idF;

    @FXML
    private TextField lastnameF;

    @FXML
    private TextField newpasswordF;

    @FXML
    private TextField repeatpasswordF;

    @FXML
    private Button signupButton;


    @FXML
    void Back_Click(ActionEvent event) throws IOException {
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(login_page));
        primaryStage.setMaximized(true);
    }

    @FXML
    void Signup_Click(ActionEvent event) throws IOException {
    	Parent completed_page = FXMLLoader.load(getClass().getResource("AccCreated.fxml"));
        Stage completed_scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        completed_scene.setScene(new Scene(completed_page));
        completed_scene.setMaximized(true);
    }

    @FXML
    void initialize() {
    }

}
