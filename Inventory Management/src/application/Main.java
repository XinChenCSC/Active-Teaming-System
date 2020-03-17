package application;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
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

	        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	        System.out.println(primaryScreenBounds);
	        
	        //set Stage boundaries to visible bounds of the main screen
	        primaryStage.setWidth(primaryScreenBounds.getWidth()/1.5);
	        primaryStage.setHeight(primaryScreenBounds.getHeight()/1.2);
	        
	        primaryStage.setScene(fir_scene);
			primaryStage.setTitle("Login");
	        primaryStage.show();
	        primaryStage.setFullScreen(true);
	        //primaryStage.setMaximized(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
