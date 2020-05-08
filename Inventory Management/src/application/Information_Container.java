package application;

import java.util.ArrayList;

import Clients.Client;
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
	
	//Notification
	private ArrayList<Notification> Notification = new ArrayList<Notification>();
	
	private String ID;
	
	public Information_Container() {}
	
	public Information_Container(String ID) {
		this.setID(ID);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	
	public ArrayList<Message_Container> getMessage_Content() {
		return Message_Content;
	}

	public void setMessage_Content(ArrayList<Message_Container> message_Content) {
		Message_Content = message_Content;
	}

	public ArrayList<Email> getEmail_Content() {
		return Email_Content;
	}

	public void setEmail_Content(ArrayList<Email> email_Content) {
		Email_Content = email_Content;
	}

	public ArrayList<Client> getPersonal_Blacklist() {
		return Personal_Blacklist;
	}

	public void setPersonal_Blacklist(ArrayList<Client> personal_Blacklist) {
		Personal_Blacklist = personal_Blacklist;
	}

	public ArrayList<Client> getWhitebox() {
		return Whitebox;
	}

	public void setWhitebox(ArrayList<Client> whitebox) {
		Whitebox = whitebox;
	}

	public ArrayList<Notification> getNotification() {
		return Notification;
	}

	public void setNotification(ArrayList<Notification> notification) {
		Notification = notification;
	}
	
	public void addNotification(Notification notification) {
		Notification.add(notification);
	}
	
	public void removeNotification(Notification notification) {
		Notification.remove(notification);
	}
	
	public void removeFriend(Client Name) {Whitebox.remove(Name);}
	
	public void addFriend(Client Name) {Whitebox.add(Name);}
	
	public void removeBLMember(Client Name) {Personal_Blacklist.remove(Name);}
	
	public void addBLMember(Client Name) {Personal_Blacklist.add(Name);}
	
	public void addMessage(Message_Container MC) {Message_Content.add(MC);}
	
	public void removeMessage(Message_Container MC) {Message_Content.remove(MC);}
	
	public void addEmail(Email email) {Email_Content.add(email);}
	
	public void removeEmail(Email email) {Email_Content.remove(email);}
}
