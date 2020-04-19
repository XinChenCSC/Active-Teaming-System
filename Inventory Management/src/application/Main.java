package application;

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
			
			FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("GroupPage.fxml"));
            Parent first_pane = firstLoader.load();
	        Scene fir_scene = new Scene(first_pane);
	        primaryStage.setScene(fir_scene);
			primaryStage.setTitle("Login");
	        primaryStage.show();
	        //primaryStage.setFullScreen(true);
	        //primaryStage.setMaximized(true);
	        
	        //Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	        
	        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	        //set Stage boundaries to visible bounds of the main screen
	        primaryStage.setY(primaryScreenBounds.getMinY());
	        primaryStage.setX(primaryScreenBounds.getMinX());

	        primaryStage.setWidth(primaryScreenBounds.getWidth());
	        primaryStage.setHeight(primaryScreenBounds.getHeight());
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
