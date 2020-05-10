package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Appointment.Appointment;
import Clients.Client;
import Clients.Guest;
import Clients.Information_Container;
import Clients.OU;
import Clients.SU;
import Clients.VIP;
import Email.Email;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {	
	private UserList userList = new UserList();	

	private final String[] xmlFile = {"@../../src/Database/All_User.xml",
			"@../../src/Database/Guest.xml",
			"@../../src/Database/OU.xml",
			"@../../src/Database/SU.xml",
			"@../../src/Database/VIP.xml",
			"@../../src/Database/Informations.xml"};
	
	private Information_List il = new Information_List();

	@Override
	public void start(Stage primaryStage) {
		try {
			
			readDatabase(); //Load database
			
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
	        	        
	        //When the close x button click all datas will be saved automatically
	        primaryStage.setOnCloseRequest(close->{
	        	try {
					writeDatabase();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	        
	        //Transfer userList to other scene
	        LoginController login = firstLoader.getController();
	        login.signupAlert(userList, il);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void writeDatabase() throws IOException {
//		SU su = new SU();
//		OU ou = new OU();
//		VIP vip = new VIP();
//		userList.addSU_User(su);
//		userList.addOU_User(ou);
//		userList.addVIP_User(vip);
//		Appointment app1 = new Appointment("2021-02-21 9:00AM", 2);
//		Appointment app2 = new Appointment("2021-04-10 10:00AM", 3);
//		Appointment app3 = new Appointment("2020-01-01 11:00AM", 4);
//		this.il.addAppointment(su.getID(), app1);
//		this.il.addAppointment(su.getID(), app2);
//		this.il.addAppointment(su.getID(), app3);
		//read file
		for(int i = 0; i < xmlFile.length; ++i) {
			try {
            	XMLEncoder file = new XMLEncoder(new BufferedOutputStream
            			(new FileOutputStream(xmlFile[i])));          	
            	if(i == 0)
            		file.writeObject(userList.getAll_User());
            	else if(i == 1)
            		file.writeObject(userList.getGuest());
            	else if(i == 2)
            		file.writeObject(userList.getOU_User());
            	else if(i == 3)
            		file.writeObject(userList.getSU_User());
            	else if(i == 4)
            		file.writeObject(userList.getVip_User());
            	else
            		file.writeObject(il.getInfo_Con());
            		
            	file.close();
        	}
        	catch (IOException e) {
        		e.printStackTrace();
        	} 
		}
		System.out.println(userList.getAll_Size());
	}
	
	@SuppressWarnings("unchecked")
	public void readDatabase() throws FileNotFoundException {	
    	for(int i = 0; i < xmlFile.length; ++i) {
    		XMLDecoder file = new XMLDecoder(new BufferedInputStream
        			(new FileInputStream(xmlFile[i])));
    		if(i == 0)
    			userList.setAll_User((ArrayList<Client>) file.readObject());
    		else if(i == 1)
    			userList.setGuest((Guest) file.readObject());
    		else if (i == 2)
    			userList.setOU_User((ArrayList<OU>) file.readObject());
    		else if (i == 3)
    			userList.setSU_User((ArrayList<SU>) file.readObject());
    		else if (i == 4)
    			userList.setVIP_User((ArrayList<VIP>) file.readObject());
    		else
    			il.setInfo_Con((ArrayList<Information_Container>) file.readObject());
    		file.close();
    	}
//    	System.out.println(userList.getGuest().getRecommender());
     }
}
