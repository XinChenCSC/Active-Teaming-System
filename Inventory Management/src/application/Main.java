package application;
	
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Rectangle2D;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent first_pane = firstLoader.load();
			Scene fir_scene = new Scene(first_pane);
			
			primaryStage.setScene(fir_scene);

	        javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

			primaryStage.setTitle("Login");
	        //set Stage boundaries to visible bounds of the main screen
	        primaryStage.setX(primaryScreenBounds.getMinX());
	        primaryStage.setY(primaryScreenBounds.getMinY());
	        primaryStage.setWidth(primaryScreenBounds.getWidth());
	        primaryStage.setHeight(primaryScreenBounds.getHeight());
	        primaryStage.setMaximized(true);
	        primaryStage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
