package Clients;

import java.util.ArrayList;

import Group.Group;


public class VIP extends Client{
	
	private ArrayList<Guest> presentee = new ArrayList<Guest>();
	
	private boolean group_evaluation;
	
	private Group target_group;
	
	private final static int Evaluation_Score_Range = 20;
	
	private int numCompliment = 0;
	
	private boolean needAppeal = false;
	
	private boolean inGroup = false;
	
	private boolean CreatingGroup = false;
	
	//Constructor
	public VIP(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password,
			int reputation_score, int total_project_completed, int total_group_engaged, int total_penalty_received,
			String status, String evaluation, String date_of_join) {
		super(Name, ID, Email, Position, Interest, Recommender, Password, reputation_score, total_project_completed, 
				total_group_engaged, total_penalty_received, status, evaluation, date_of_join);
	}
	
	public VIP() {
		super("Liam Lee", "77777", "LiamLee@gmail.com", "VIP", "Game", "Qichen You", "66666",
				35, 5, 5, 0, "Excellent", "On", "03/24/2016");
	}

	//****************************************************************************************	
	
	//---------------------------------Presentee-----------------------------------------
	//Remove presentee if you have filled up the initial evaluation for your presentee
	public void removePresentee(Guest Name) {presentee.remove(Name);}
	
	//Add presentee if your presentee needs you to provide them an initial scores
	public void addPresentee(Guest Name) {presentee.add(Name);}
	
	public void setPresentee(ArrayList<Guest> guest) {this.presentee = guest;}
	
	public ArrayList<Guest> getPresenteeList() {return presentee;}
	//***************************************************************

	//Return Evaluation score Range
	public static int getEvaluationScoreRange() {return Evaluation_Score_Range;}
	
	//------------------------------Group evaluation-------------------------------------
	public boolean isGroup_evaluation() {return group_evaluation;}

	public void setGroup_evaluation(boolean group_evaluation) {this.group_evaluation = group_evaluation;}
	//****************************************************************
	
	//--------------------------------Target group---------------------------------------
	public Group getTarget_group() {return target_group;}

	public void setTarget_group(Group target_group) {this.target_group = target_group;}
	//***************************************************************
	
//--------------------------------Number of Compliments------------------------------------
	public void incNumCompliment() {++this.numCompliment;}
		
	public int getNumCompliment() {return numCompliment;}

	public void setNumCompliment(int numCompliment) {this.numCompliment = numCompliment;}

//	----------------------------------Need appeal getter and setter------------------------------

	public boolean isNeedAppeal() {return needAppeal;}

	public void setNeedAppeal(boolean needAppeal) {this.needAppeal = needAppeal;}
	
//	----------------------------------Need appeal getter and setter------------------------------
	public boolean isInGroup() {return inGroup;}

	public void setInGroup(boolean inGroup) {this.inGroup = inGroup;}	
	
//	----------------------------------Creating group getter and setter------------------------------
	public boolean isCreatingGroup() {return this.CreatingGroup;}
	
	public void setCreatingGroup(boolean creatingGroup) {this.CreatingGroup = creatingGroup;}

}
