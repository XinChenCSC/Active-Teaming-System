package Clients;

import java.util.ArrayList;
import application.Notification.Notification;
import Email.Email;
import Message.Message_Container;

public class Client {
	
	protected String Name;
	
	protected String Email;
	
	protected String Recommender;
	
	protected String Interest;
	
	protected String Position;
	
	protected String ID;
	
	protected String Password;
	
	protected String Comment;
	
	//Messages
	protected ArrayList<Message_Container> Message_Content = new ArrayList<Message_Container>(); 
	
	//Emails
	protected ArrayList<Email> Email_Content = new ArrayList<Email>();
	
	//blacklist 
	protected ArrayList<Client> Personal_Blacklist = new ArrayList<Client>();
	
	//whitebox
	protected ArrayList<Client> Whitebox = new ArrayList<Client>();
	
	//Notification
	protected ArrayList<Notification> Notification = new ArrayList<Notification>();
	
	//constructor
	public Client() {}
	
	//SU / OU / VIP
	public Client(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password) {
		this.Name = Name;
		this.Email = Email;
		this.ID = ID;
		this.Position = Position;
		this.Interest = Interest;
		this.Recommender = Recommender;
		this.Password = Password;
	}
	
	//White box 
	public Client(String Name, String Interest, String Recommender, String Email) {
		this.Name = Name;
		this.Email = Email;
		this.Recommender = Recommender;
		this.Email = Email;
	}
	
	//Blacklist
	public Client(String Name, String ID) {
		this.Name = Name;
		this.ID = ID;
	}
	
	//System blacklist
	public Client(String Name, String Email, String ID) {
		this.Name = Name;
		this.Email = Email;
		this.ID = ID;
	}
	
	//****************************************************************************
	//------------------Email container and message container---------------------
	protected void addMessage(Message_Container MC) {Message_Content.add(MC);}
	
	protected void removeMessage(Message_Container MC) {Message_Content.remove(MC);}
	
	protected ArrayList<Message_Container> getMessageList() {return Message_Content;}
	
	protected void addEmail(Email email) {Email_Content.add(email);}
	
	protected void removeEmail(Email email) {Email_Content.remove(email);}
	
	protected ArrayList<Email> getEmailList() {return Email_Content;}	
	//**************************************************************************
	
	//---------------------------Whitebox & blacklist------------------------------------
	protected void removeFriend(Client Name) {Whitebox.remove(Name);}
	
	protected void addFriend(Client Name) {Whitebox.add(Name);}
	
	protected ArrayList<Client> getWhitebox() {return Whitebox;}
	
	protected void removeBLMember(Client Name) {Personal_Blacklist.remove(Name);}
	
	protected ArrayList<Client> getBL() {return Personal_Blacklist;}
	
	protected void addBLMember(Client Name) {Personal_Blacklist.add(Name);}
	//******************************************************************************
	
	//-------------------------------Notifications-------------------------------------
	protected void addNotification(Notification notification) {
		Notification.add(notification);
	}
	
	protected void removeNotification(Notification notification) {
		Notification.remove(notification);
	}
	
	protected ArrayList<Notification> getNotification(){
		return Notification;
	}
	//******************************************************************************
	
	//Getters and setters
	protected String getComment() {
		return Comment;
	}

	protected void setComment(String comment) {
		Comment = comment;
	}

	protected String getPassword() {
		return Password;
	}

	protected void setPassword(String password) {
		Password = password;
	}

	protected String getID() {
		return ID;
	}

	protected void setID(String iD) {
		ID = iD;
	}

	protected String getPosition() {
		return Position;
	}

	protected void setPosition(String position) {
		Position = position;
	}

	protected String getInterest() {
		return Interest;
	}

	protected void setInterest(String interest) {
		Interest = interest;
	}

	protected String getRecommender() {
		return Recommender;
	}

	protected void setRecommender(String recommender) {
		Recommender = recommender;
	}

	protected String getEmail() {
		return Email;
	}

	protected void setEmail(String email) {
		Email = email;
	}

	protected String getName() {
		return Name;
	}

	protected void setName(String name) {
		Name = name;
	}
	
	//*************************************************
}
