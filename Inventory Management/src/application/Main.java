package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import Clients.SU;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	
//    private File fileStorage = null;

	@Override
	public void start(Stage primaryStage) {
		try {
//			readDatabase();
			FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent first_pane = firstLoader.load();
	        Scene fir_scene = new Scene(first_pane);
	        primaryStage.setScene(fir_scene);
			primaryStage.setTitle("Login");
	        primaryStage.show();
	  	       	        
	        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	        //set Stage boundaries to visible bounds of the main screen
	        primaryStage.setY(primaryScreenBounds.getMinY());
	        primaryStage.setX(primaryScreenBounds.getMinX());

	        primaryStage.setWidth(primaryScreenBounds.getWidth());
	        primaryStage.setHeight(primaryScreenBounds.getHeight());
	        
	        LoginController login = firstLoader.getController();
	        login.SuccessedSignupAlert();
	        
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void writeDatabase() {
		UserList userList = new UserList();
		userList.addSU_User(new SU());
		//read file
		//File file = new File("Database.xml");
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
        int r = j.showOpenDialog(null); 
        if (r == JFileChooser.APPROVE_OPTION) {
        	try {
            	XMLEncoder file = new XMLEncoder(new BufferedOutputStream
            			(new FileOutputStream(j.getSelectedFile().getAbsolutePath()))); 
            	file.writeObject(userList.getSU_User());
            	file.close();
        	}
        	catch (IOException e) {
        		e.printStackTrace();
        	}
        } 
        
	}
	
	/*@SuppressWarnings("unchecked")
	public void readDatabase() throws FileNotFoundException {
		UserList ul = new UserList();
		
		XMLDecoder file = new XMLDecoder(new BufferedInputStream
    			(new FileInputStream("@../../src/application/Database.xml")));
    	try {
    		ul.setSU((ArrayList<SU>) file.readObject());
    		//Test------------------
    		System.out.println(ul.getSU_User().get(0).getName());
    	}
    	catch(ArrayIndexOutOfBoundsException e) {
    	}
    	file.close();
     }*/
}
