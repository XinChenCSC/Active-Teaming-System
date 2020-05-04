package Clients;

import java.util.ArrayList;

import Email.Email;
import Message.Message_Container;
import application.Group;

public class VIP extends Client{
	
	private ArrayList<String> presentee = new ArrayList<String>();
	
	private boolean group_evaluation;
	
	private Group target_group;
	
	private final static int Evaluation_Score_Range = 10;
	
	//Constructor
	public VIP() {}
	
	public VIP(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password) {
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
	
	public ArrayList<String> getPresentee() {return presentee;}
	
	public void setPresentee(ArrayList<String> presentee) {this.presentee = presentee;}
	//*****************************************************************************************

	//------------------------Email container and message container-----------------------------
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
	
	
	//---------------------------------Presentee-----------------------------------------
	//Remove presentee if you have filled up the initial evaluation for your presentee
	public void removePresentee(String Name) {
		presentee.remove(Name);
	}
	
	//Add presentee if your presentee needs you to provide them an initial scores
	public void addPresentee(String Name) {
		presentee.add(Name);
	}
	
	public ArrayList<String> getPresenteeList() {
		return presentee;
	}
	//***************************************************************

	//Return Evaluation score Range
	public static int getEvaluationScoreRange() {
		return Evaluation_Score_Range;
	}
	
	//------------------------------Group evaluation-------------------------------------
	public boolean isGroup_evaluation() {
		return group_evaluation;
	}

	public void setGroup_evaluation(boolean group_evaluation) {
		this.group_evaluation = group_evaluation;
	}
	//****************************************************************
	
	//--------------------------------Target group---------------------------------------
	public Group getTarget_group() {
		return target_group;
	}

	public void setTarget_group(Group target_group) {
		this.target_group = target_group;
	}
	//***************************************************************
	
	//---------------------------Whitebox & blacklist------------------------------------
	public void removeFriend(Client Name) {super.removeFriend(Name);}
	
	public void addFriend(Client Name) {super.addFriend(Name);}
	
	public ArrayList<Client> getWhitebox() {return super.getWhitebox();}
	
	public void removeBLMember(Client Name) {super.removeBLMember(Name);}
	
	public ArrayList<Client> getBL() {return super.Personal_Blacklist;}
	
	public void addBLMember(Client Name) {super.addBLMember(Name);}
	//******************************************************************************
}
