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
//		OU ou1 = new OU("John Smith", "13579", "JohnSmith@gmail.com", "OU", "Sing", "Qichen You", "66666",
//				10, 2, 2, 0, "Good", "On", "05/02/2019");
//		OU ou2 = new OU("Juan Carlos", "24680", "JuanCarlos@gmail.com", "OU", "Hike", "Qichen You", "66666",
//				31, 2, 2, 0, "Good", "On", "05/03/2019");
//		OU ou3 = new OU("Mike Jones", "43754", "MikeJones@gmail.com", "OU", "Ski", "Qichen You", "66666",
//				16, 2, 2, 0, "Good", "On", "05/04/2019");
//		OU ou4 = new OU("Rafael Thomas", "98374", "RafealThomas@gmail.com", "OU", "Sleep", "Qichen You", "66666",
//				17, 2, 2, 0, "Good", "On", "05/05/2019");
//		OU ou5 = new OU("Carmen Gabriela", "29384", "CarmenGabriela@gmail.com", "OU", "Swimming", "Qichen You", "66666",
//				18, 2, 2, 0, "Good", "On", "05/06/2019");	
//		userList.addSU_User(su);
//		userList.addAll_User(su);
//		userList.addOU_User(ou);
//		userList.addAll_User(ou);
//		userList.addVIP_User(vip);
//		userList.addAll_User(vip);
//		userList.addOU_User(ou1);
//		userList.addAll_User(ou1);
//		userList.addOU_User(ou2);
//		userList.addAll_User(ou2);
//		userList.addOU_User(ou3);
//		userList.addAll_User(ou3);
//		userList.addOU_User(ou4);
//		userList.addAll_User(ou4);
//		userList.addOU_User(ou5);
//		userList.addAll_User(ou5);
//		il.addInfo_Con(new Information_Container("23333"));
//		il.addInfo_Con(new Information_Container("77777"));
//		il.addInfo_Con(new Information_Container("55555"));
//		il.addInfo_Con(new Information_Container("13579"));
//		il.addInfo_Con(new Information_Container("24680"));
//		il.addInfo_Con(new Information_Container("98374"));
//		il.addInfo_Con(new Information_Container("43754"));
//		il.addInfo_Con(new Information_Container("29384"));
		
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
    	int remove[] = new int[this.userList.getOU_Size()];
		//Check if any OU has a chance to promote up.
		for(int i = 0, j = 0; i < this.userList.getOU_Size(); ++i) {
			if(this.userList.getOU_User().get(i).getReputationScore() >= 30) {
				VIP new_vip = new VIP(this.userList.getOU_User().get(i).getName(),
						this.userList.getOU_User().get(i).getID(),
						this.userList.getOU_User().get(i).getEmail(),
						"VIP", 
						this.userList.getOU_User().get(i).getInterest(),
						this.userList.getOU_User().get(i).getRecommender(),
						this.userList.getOU_User().get(i).getPassword(),
						this.userList.getOU_User().get(i).getReputationScore(),
						this.userList.getOU_User().get(i).getTotal_Project_Completed(),
						this.userList.getOU_User().get(i).getTotal_Group_Engaged(),
						this.userList.getOU_User().get(i).getTotal_Penalty_Received(),
						this.userList.getOU_User().get(i).getStatus(),
						this.userList.getOU_User().get(i).getEvaluation(),
						this.userList.getOU_User().get(i).getDate_Of_Join());
				remove[j++] = i;
				this.userList.addVIP_User(new_vip);
			}
		}
		for(int i = 0; i < this.userList.getOU_Size(); ++i) {
			if(remove[i] > 0) {
				this.userList.removeOU_User(this.userList.getOU_User().get(remove[i]));
			}
		}
     }
}
