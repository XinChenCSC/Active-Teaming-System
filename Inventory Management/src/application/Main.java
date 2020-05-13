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

import Notice.Notice;
import Clients.Client;
import Clients.Guest;
import Clients.Information_Container;
import Clients.OU;
import Clients.SU;
import Clients.VIP;
import Group.Group;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {	
	private UserList userList = new UserList();	

	private Information_List il = new Information_List();

	private Group_List gl = new Group_List();
	
	private final String[] xmlFile = {"@../../src/Database/All_User.xml",
			"@../../src/Database/Informations.xml",
			"@../../src/Database/Group.xml"};
	

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
	        login.signupAlert(userList, il, gl);
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
//		OU ou6 = new OU("Ever Imre", "65428", "EverImre@gmail.com", "OU", "TaiChi", "Qichen You", "66666",
//				-1, 2, 2, 0, "Good", "On", "05/06/2019");
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
//		userList.addOU_User(ou6);
//		userList.addAll_User(ou6);
//		
//		il.addInfo_Con(new Information_Container("23333"));
//		il.addInfo_Con(new Information_Container("77777"));
//		il.addInfo_Con(new Information_Container("55555"));
//		il.addInfo_Con(new Information_Container("13579"));
//		il.addInfo_Con(new Information_Container("24680"));
//		il.addInfo_Con(new Information_Container("98374"));
//		il.addInfo_Con(new Information_Container("43754"));
//		il.addInfo_Con(new Information_Container("29384"));
//		il.addInfo_Con(new Information_Container("65428"));
//		
//		//Add to system blacklist
////		il.addSystemBlacklist(ou6);
//		
//		Notice no1 = new Notice(true, "11111", "Message1");
//		Notice no2 = new Notice(true, "11111", "Message2");
//		Notice no3 = new Notice(true, "11111", "Message3");
//		Notice no4 = new Notice(true, "11111", "Message4");
//		
//		this.il.CreateNotice(this.userList.getVip_User().get(0).getID(), no1);
//		this.il.CreateNotice(this.userList.getVip_User().get(0).getID(), no2);
//		this.il.CreateNotice(this.userList.getVip_User().get(0).getID(), no3);
//		this.il.CreateNotice(this.userList.getVip_User().get(0).getID(), no4);
		
		//read file
		for(int i = 0; i < xmlFile.length; ++i) {
			try {
            	XMLEncoder file = new XMLEncoder(new BufferedOutputStream
            			(new FileOutputStream(xmlFile[i])));          	
            	if(i == 0) {
            		file.writeObject(userList.getAll_User());
            		file.writeObject(userList.getGuest());
            		file.writeObject(userList.getOU_User());
            		file.writeObject(userList.getSU_User());
            		file.writeObject(userList.getVip_User());
            	}
            	else if (i == 1){
            		file.writeObject(il.getInfo_Con());
            		file.writeObject(il.getSystem_Blacklist());
            	}
            	else {
            		file.writeObject(gl.getGroup_List());
            	}
            	file.close();
        	}
        	catch (IOException e) {
        		e.printStackTrace();
        	} 
		}
//		System.out.println(userList.getAll_Size());
//		System.out.println(userList.getOU_Size());
//		System.out.println(userList.getVIP_Size());
//		System.out.println(userList.getSU_Size());
//		System.out.println(userList.getOU_User().get(0).isCreatingGroup());
//		System.out.println(this.userList.getOU_User().get(0).isNeedAppeal());
//		System.out.println(this.gl.getGroup_List().get(0).getTarget()[2].getName());
//		System.out.println(this.gl.getGroup_List().get(0).getA_Group().get().getWarnings());
//		System.out.println(this.userList.getAll_User().get(this.userList.getAll_Size()-1).getPassword());
//		System.out.println(this.gl.getGroup_List().get(0).getA_Group().get(0).isEvaluation());
//		System.out.println(this.userList.getGuest().isActivate());
//		System.out.println(this.gl.getGroup_List().get(0).getVT(5));
//		System.out.println(this.gl.getGroup_List().get(0).getGroup_ID());
//		System.out.println(this.gl.getGroup_List().get(0).getTotalMember());
//		System.out.println(this.il.getInfo_Con().get(1).getAppointment().get(13).isStatus());
	}
	
	@SuppressWarnings("unchecked")
	public void readDatabase() throws FileNotFoundException {	
    	for(int i = 0; i < xmlFile.length; ++i) {
    		XMLDecoder file = new XMLDecoder(new BufferedInputStream
        			(new FileInputStream(xmlFile[i])));
    		if(i == 0) {
    			userList.setAll_User((ArrayList<Client>) file.readObject());
    			userList.setGuest((Guest) file.readObject());
    			userList.setOU_User((ArrayList<OU>) file.readObject());
    			userList.setSU_User((ArrayList<SU>) file.readObject());
    			userList.setVIP_User((ArrayList<VIP>) file.readObject());
    		}
    		else if(i == 1){
    			il.setInfo_Con((ArrayList<Information_Container>) file.readObject());
    			il.setSystem_Blacklist((ArrayList<Client>) file.readObject());
    		}
    		else {
    			gl.setGroup_List((ArrayList<Group>) file.readObject());
    		}
    		file.close();
    	}
    	
		//Check if any OU has a chance to promote up.
		for(int i = 0; i < this.userList.getOU_Size(); ++i) {
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
				
				new_vip.setCreatingGroup(((OU)this.userList.getOU_User().get(i)).isCreatingGroup());
				new_vip.setInGroup((((OU)this.userList.getOU_User().get(i)).isInGroup()));
				new_vip.setImageURL((((OU)this.userList.getOU_User().get(i)).getImageURL()));
				new_vip.setNumCompliment((((OU)this.userList.getOU_User().get(i)).getNumCompliment()));
				new_vip.setNeedAppeal((((OU)this.userList.getOU_User().get(i)).isNeedAppeal()));
				new_vip.setPresentee((((OU)this.userList.getOU_User().get(i)).getPresenteeList()));
				
				this.userList.removeOU_User(this.userList.getOU_User().get(i));
				this.userList.addVIP_User(new_vip);
				this.userList.addAll_User(new_vip);
			}
			else if(this.userList.getOU_User().get(i).getReputationScore() < 0) {
				this.il.addSystemBlacklist(this.userList.getOU_User().get(i));	//Move to the system blacklist
				this.userList.removeOU_User(this.userList.getOU_User().get(i));	
			}
		}
		//Check if any VIP needs to demote 
		for(int i = 0; i < this.userList.getVIP_Size(); ++i) {
			if(this.userList.getVip_User().get(i).getReputationScore() < 30) {
				OU new_ou = new OU(this.userList.getVip_User().get(i).getName(),
						this.userList.getVip_User().get(i).getID(),
						this.userList.getVip_User().get(i).getEmail(),
						"OU", 
						this.userList.getVip_User().get(i).getInterest(),
						this.userList.getVip_User().get(i).getRecommender(),
						this.userList.getVip_User().get(i).getPassword(),
						this.userList.getVip_User().get(i).getReputationScore(),
						this.userList.getVip_User().get(i).getTotal_Project_Completed(),
						this.userList.getVip_User().get(i).getTotal_Group_Engaged(),
						this.userList.getVip_User().get(i).getTotal_Penalty_Received(),
						this.userList.getVip_User().get(i).getStatus(),
						this.userList.getVip_User().get(i).getEvaluation(),
						this.userList.getVip_User().get(i).getDate_Of_Join());
				
				new_ou.setCreatingGroup(((VIP)this.userList.getVip_User().get(i)).isCreatingGroup());
				new_ou.setInGroup((((VIP)this.userList.getVip_User().get(i)).isInGroup()));
				new_ou.setImageURL((((VIP)this.userList.getVip_User().get(i)).getImageURL()));
				new_ou.setNumCompliment((((VIP)this.userList.getVip_User().get(i)).getNumCompliment()));
				new_ou.setNeedAppeal((((VIP)this.userList.getVip_User().get(i)).isNeedAppeal()));
				new_ou.setPresentee((((VIP)this.userList.getVip_User().get(i)).getPresenteeList()));
				
				this.userList.removeVIP_User(this.userList.getVip_User().get(i));
				this.userList.addOU_User(new_ou);
				this.userList.addAll_User(new_ou);
			}
		}
		
//		//If all user is not in group and the closure poll is passed, close the group
//		for(int i = 0; i < this.gl.getGroup_List().size(); ++i) {
//			int index = this.gl.getGroup_List().get(i).getA_Group().size();
//			for(int j = 0; j < this.gl.getGroup_List().get(i).getA_Group().size(); ++i) {
//				if(this.gl.getGroup_List().get(i).getA_Group().get(index-1).getUser() instanceof OU) {
//					if(!((OU)this.gl.getGroup_List().get(i).getA_Group().get(index-1).getUser()).isInGroup() && !this.gl.getGroup_List().get(i).isClose())
//						--index;
//				}
//			}
//			if(index == 0) {
//				this.gl.getGroup_List().get(i).setClose(true);
//				this.gl.getGroup_List().get(i).setOpen(false);
//			}
//		}
     }
}
