package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;


public class homepageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Background;


    @FXML
    void initialize() {
    	//Resize the background image
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Background.setFitWidth(screen.getWidth());
        Background.setFitHeight(screen.getHeight()*1.5);
    }

}
