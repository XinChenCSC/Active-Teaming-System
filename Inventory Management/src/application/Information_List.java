package application;

import java.util.ArrayList;

import Appointment.Appointment;
import Clients.Client;
import Clients.Information_Container;
import Email.Email;
import Message.Message_Container;
import application.Notification.Notification;

public class Information_List {
	private ArrayList<Information_Container> Info_Con = new ArrayList<Information_Container>();
	
//	----------------------------Constructor-----------------------------	
	public Information_List() {}	
	
//	----------------------------Email-----------------------------
	public void CreateEmail(String id, Email email) {
		int i = isExist(id);
		if(i > -1) {
			this.Info_Con.get(i).addEmail(email);
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addEmail(email);
			this.Info_Con.add(ic);			
		}
	}
	
	public void removeEmail(String id, Email email) {
		int i = isExist(id);
		this.Info_Con.get(i).removeEmail(email);
	}
	
//	----------------------------Message-----------------------------
	public void CreateMessage(String id, Message_Container message) {
		int i = isExist(id);
		if(i > -1){
			this.Info_Con.get(i).addMessage(message);
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addMessage(message);
			this.Info_Con.add(ic);			
		}
	}
	
	public void removeMessage(String id, Message_Container message) {
		int i = isExist(id);
		this.Info_Con.get(i).removeMessage(message);
	}
	
//	----------------------------Notification-----------------------------
	public void CreateNotification(String id, Notification notification) {
		int i = isExist(id);
		if(i > -1) {
			Info_Con.get(i).addNotification(notification);
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addNotification(notification);;
			this.Info_Con.add(ic);			
		}
	}
	
	public void removeNotification(String id, Notification notification) {
		int i = isExist(id);
		this.Info_Con.get(i).removeNotification(notification);
	}
	
//	----------------------------Friend-----------------------------
	public void addFriend(String id, Client client) {
		int i = isExist(id);
		if(i > -1) {
			this.Info_Con.get(i).addFriend(client);			
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addFriend(client);
			this.Info_Con.add(ic);
		}
	}
	
	public void removeFriend(String id, Client client) {
		int i = isExist(id);
		this.Info_Con.get(i).removeFriend(client);
	}	

	//	----------------------------Blacklist-----------------------------
	public void addBlacklist(String id, Client client) {
		int i = isExist(id);
		if(i > -1) {
			this.Info_Con.get(i).addBLMember(client);			
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addBLMember(client);
			this.Info_Con.add(ic);
		}
	}
	
	public void removeBlacklist(String id, Client client) {
		int i = isExist(id);
		this.Info_Con.get(i).removeBLMember(client);
	}

//	----------------------------System Blacklist-----------------------------
	public void addSystem(String id, Client client) {
		int i = isExist(id);
		if(i > -1) {
			this.Info_Con.get(i).addSystemBlacklist(client);		
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addSystemBlacklist(client);;
			this.Info_Con.add(ic);
		}
	}
	
	public void removeSystem(String id, Client client) {
		int i = isExist(id);
		this.Info_Con.get(i).removeSystemBlacklist(client);;
	}

//	----------------------------Appointment---------------------------------------
	public void addAppointment(String id, Appointment appointment) {
		int i = isExist(id);
		if(i > -1) {
			this.Info_Con.get(i).addAppointment(appointment);
		}
		else {
			Information_Container ic = new Information_Container(id);
			ic.addAppointment(appointment);
			this.Info_Con.add(ic);
		}
	}
	
	public void removeAppointment(String id, Appointment appointment) {
		int i = isExist(id);
		this.Info_Con.get(i).removeAppointment(appointment);
	}
	
//	--------------------------------------------------------------------------------
	public ArrayList<Information_Container> getInfo_Con(){
		return this.Info_Con;
	}
	
	public void setInfo_Con(ArrayList<Information_Container> info_con) {
		this.Info_Con = info_con;
	}
	
	private int isExist(String id) {
		for(int i = 0; i < this.Info_Con.size(); ++i) {
			if(id.compareTo(this.Info_Con.get(i).getID()) == 0) {
				return i;
			}
		}
		return -1;	
	}
}
