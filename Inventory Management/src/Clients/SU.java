package Clients;

import java.util.ArrayList;

import Message.Message_Container;
import application.Notification.Notification;
import Email.Email;

public class SU extends Client{
	
	//Constructor
	//Name - ID - Email - Position - Interest - Recommender - Password
	public SU() {
		super("Steve", "23333", "123456@gmail.com", "SU", "Basketball", "Qichen You", "66666");
	}
	
	public SU(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password) {
		super(Name, ID, Email, Position, Interest, Recommender, Password);
	}
	
	//Getters and setters
	public void setPassword(String Password) {super.setPassword(Password);}
	
	public String getPassword() {return super.Password;}
	
	public void setComment(String Comment) {super.setComment(Comment);}
	
	public String getComment() {return super.Comment;}
	
	public void setPosition(String Position) {super.setPosition(Position);}
	
	public String getPosition() {return super.Position;}
	
	public void setID(String ID) {super.setID(ID);}
	
	public String getID() {return super.ID;}
	
	public void setName(String Name) {super.setName(Name);}
	
	public String getName() {return super.Name;}
	
	public void setEmail(String Email) {super.setEmail(Email);}
	
	public String getEmail() {return super.Email;}
	
	public void setInterest(String Interest) {super.setInterest(Interest);}
	
	public String getInterest() {return super.Interest;}
	
	public void setRecommender(String Recommender) {super.setRecommender(Recommender);}
	
	public String getRecommender() {return super.Recommender;}
	
	//******************************************************************
	
	//Email container and message container
	public void addMessage(Message_Container MC) {
		super.addMessage(MC);
	}
		
	public ArrayList<Message_Container> getMessageList() {
		return super.Message_Content;
	}
	
	public void removeMessage(Message_Container MC) {
		super.removeMessage(MC);
	}
		
	public void addEmail(Email email) {
		super.addEmail(email);
	}
		
	public ArrayList<Email> getEmailList() {
		return super.Email_Content;
	}	
		
	public void removeEmail(Email email) {
		super.removeEmail(email);
	}	
	//**************************************************************************
	
	//-------------------------------Notifications-------------------------------------
	public void addNotification(Notification notification) {
		super.addNotification(notification);
	}
		
	public void removeNotification(Notification notification) {
		super.removeNotification(notification);
	}
		
	public ArrayList<Notification> getNotification(){
		return super.getNotification();
	}
	//******************************************************************************
	
	//---------------------------Whitebox & blacklist------------------------------------
	public void removeFriend(Client Name) {super.removeFriend(Name);}
	
	public void addFriend(Client Name) {super.addFriend(Name);}
	
	public ArrayList<Client> getWhitebox() {return super.getWhitebox();}
	
	public void removeBLMember(Client Name) {super.removeBLMember(Name);}
	
	public ArrayList<Client> getBL() {return super.Personal_Blacklist;}
	
	public void addBLMember(Client Name) {super.addBLMember(Name);}
	//******************************************************************************
}
