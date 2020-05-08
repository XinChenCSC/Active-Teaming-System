package Clients;

import java.util.ArrayList;

import application.Group;

public class VIP extends Client{
	
	private ArrayList<Guest> presentee = new ArrayList<Guest>();
	
	private boolean group_evaluation;
	
	private Group target_group;
	
	private final static int Evaluation_Score_Range = 20;
	
	private int numCompliment = 0;
	
	//Constructor
	public VIP(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password) {
		super(Name, ID, Email, Position, Interest, Recommender, Password);
	}
	
	public VIP() {
		super("Liam Lee", "77777", "LiamLee@gmail.com", "VIP", "Game", "Qichen You", "66666");
	}

	//****************************************************************************************	
	
	//---------------------------------Presentee-----------------------------------------
	//Remove presentee if you have filled up the initial evaluation for your presentee
	public void removePresentee(Guest Name) {
		presentee.remove(Name);
	}
	
	//Add presentee if your presentee needs you to provide them an initial scores
	public void addPresentee(Guest Name) {
		presentee.add(Name);
	}
	
	public ArrayList<Guest> getPresenteeList() {
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
	
//--------------------------------Number of Compliments------------------------------------
	public void incNumCompliment() {
		++this.numCompliment;
	}
		
	public int getNumCompliment() {
		return numCompliment;
	}

	public void setNumCompliment(int numCompliment) {
		this.numCompliment = numCompliment;
	}
//******************************************************************************************
}
