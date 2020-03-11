package application;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class CompletedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Hyperlink backB;

    @FXML
    private Label username;

    @FXML
    void Click_link(ActionEvent event) throws IOException{
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(login_page));
        primaryStage.setMaximized(true);
    }

    @FXML
    void initialize() {
    	username.setOpacity(1);
    	username.setText("2363593");
    }

}
