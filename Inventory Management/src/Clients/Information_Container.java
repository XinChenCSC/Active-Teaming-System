package Clients;

import java.util.ArrayList;

import Email.Email;
import Message.Message_Container;
import application.Notification.Notification;

public class Information_Container {
	//Messages
	private ArrayList<Message_Container> Message_Content = new ArrayList<Message_Container>(); 
	
	//Emails
	private ArrayList<Email> Email_Content = new ArrayList<Email>();
	
	//blacklist 
	private ArrayList<Client> Personal_Blacklist = new ArrayList<Client>();
	
	//whitebox
	private ArrayList<Client> Whitebox = new ArrayList<Client>();
	
	//System blacklist
	private ArrayList<Client> System_Blacklist = new ArrayList<Client>();
	
	//Notification
	private ArrayList<Notification> Notification = new ArrayList<Notification>();
	
	private String ID;
	
//	-----------------------------------Contructors----------------------------------------
	public Information_Container() {}
	
	public Information_Container(String ID) {this.setID(ID);}

//	-----------------------------------ID getter and setter----------------------------------------
	public String getID() {return ID;}

	public void setID(String ID) {this.ID = ID;}
	
//	-----------------------------------Notification----------------------------------------
	public ArrayList<Notification> getNotification() {return Notification;}

	public void setNotification(ArrayList<Notification> notification) {Notification = notification;}
	
	public void addNotification(Notification notification) {Notification.add(notification);}
	
	public void removeNotification(Notification notification) {Notification.remove(notification);}
	
//	-----------------------------------Whitebox----------------------------------------
	public ArrayList<Client> getWhitebox() {return Whitebox;}
	
	public void setWhitebox(ArrayList<Client> whitebox) {Whitebox = whitebox;}
	
	public void removeFriend(Client Name) {Whitebox.remove(Name);}
	
	public void addFriend(Client Name) {Whitebox.add(Name);}
	
//	-----------------------------------Personal blacklist----------------------------------------
	public void removeBLMember(Client Name) {Personal_Blacklist.remove(Name);}
	
	public void addBLMember(Client Name) {Personal_Blacklist.add(Name);}
	
	public ArrayList<Client> getPersonal_Blacklist() {return Personal_Blacklist;}

	public void setPersonal_Blacklist(ArrayList<Client> personal_Blacklist) {Personal_Blacklist = personal_Blacklist;}
	
//	-----------------------------------Message Content----------------------------------------
	public void addMessage(Message_Container MC) {Message_Content.add(MC);}
	
	public void removeMessage(Message_Container MC) {Message_Content.remove(MC);}
	
	public ArrayList<Message_Container> getMessage_Content() {return Message_Content;}
	
	public void setMessage_Content(ArrayList<Message_Container> message_Content) {Message_Content = message_Content;}	
	
//	-----------------------------------Email Content----------------------------------------
	public void addEmail(Email email) {Email_Content.add(email);}
	
	public void removeEmail(Email email) {Email_Content.remove(email);}
	
	public ArrayList<Email> getEmail_Content() {return Email_Content;}

	public void setEmail_Content(ArrayList<Email> email_Content) {Email_Content = email_Content;}
	
//	-----------------------------------System Blacklist----------------------------------------
	public void addSystemBlacklist(Client client) {this.System_Blacklist.add(client);}
	
	public void removeSystemBlacklist(Client client) {this.System_Blacklist.remove(client);}
	
	public ArrayList<Client> getSystem_Blacklist() {return System_Blacklist;}

	public void setSystem_Blacklist(ArrayList<Client> system_Blacklist) {System_Blacklist = system_Blacklist;}
}
