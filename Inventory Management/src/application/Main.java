package application;
	
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Get the screen resolution 
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth(); 	//Get the width
			int height = gd.getDisplayMode().getHeight();	//Get the height
			
			FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent first_pane = firstLoader.load();
			Scene fir_scene = new Scene(first_pane, width/2, height/2);
	        
			primaryStage.setScene(fir_scene);
			System.out.println("Good");
			primaryStage.setTitle("Inventory Management");
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
