package Group;

import java.util.ArrayList;

import Clients.Client;

public class Group {

	private ArrayList<Group_Status> A_Group = new ArrayList<>();
		
	//Dismissal - Warning - Praise - Closure -  Close evaluation - schedule
	private boolean[] voteTypes = {false, false, false, false, false, false};
	
	private Client[] target = {null, null, null, null, null, null};
	
	private int[][] pollStatus = new int[6][7];
		
	private String Group_ID = "";
	
	private String Title = "";
	
	private String Description = "";
	
	private boolean Open = false;
	
	private boolean Close = false;
	
	private int EvaluationScore = 0;
	
	
	//Constructor
	public Group() {}
	
	public Group(ArrayList<Group_Status> group,
			String ID,
			String title,
			String description) {
		this.A_Group = group;
		this.Group_ID = ID;
		this.Description = description;
		this.Title = title;
	}

//	--------------------------------------------Group-----------------------------------------
	public ArrayList<Group_Status> getA_Group() {return A_Group;}

	public void setA_Group(ArrayList<Group_Status> a_Group) {A_Group = a_Group;}
	
	public void addGroupMember(Group_Status client) {
		this.A_Group.add(client);
	}
	
	public int getTotalMember() {return this.A_Group.size();}
	
	public void removeGroupMember(Group_Status client) {this.A_Group.remove(client);}

//	--------------------------------------------Total member-----------------------------------------

//	--------------------------------------------ID-----------------------------------------
	public String getGroup_ID() {return Group_ID;}

	public void setGroup_ID(String group_ID) {Group_ID = group_ID;}

//	--------------------------------------------Vote type-----------------------------------------
	public boolean[] getVoteTypes() {return voteTypes;}

	public void setVoteTypes(boolean[] voteTypes) {this.voteTypes = voteTypes;}
	
	public void addVoteTypes(int i, boolean value) {this.voteTypes[i] = value;}
	
	public boolean getVT(int i) {return this.voteTypes[i]; }

//	--------------------------------------------Polling status-----------------------------------------
	public void addAVote(int vote, int votetype, int index) {this.pollStatus[votetype][index] = vote;}
	
	public void PollInitialization(int reset) {
		for(int i = 0; i < 7; ++i) {
			this.pollStatus[reset][i] = -1;
		}
	}
	
	public int[][] getPollStatus() {return pollStatus;}

	public void setPollStatus(int[][] pollStatus) {this.pollStatus = pollStatus;}
	
	public int findPollStatus(int votetype, int index) {return this.pollStatus[votetype][index];}
	
//	--------------------------------------------Title-----------------------------------------
	public String getTitle() {return Title;}
	
	public void setTitle(String title) {Title = title;}

	public String getDescription() {return Description;}

	public void setDescription(String description) {Description = description;}

//	--------------------------------------------Close-----------------------------------------
	public boolean isClose() {return Close;}

	public void setClose(boolean close) {Close = close;}
	
	public boolean isGroupMember(String ID) {
		for(int i = 0; i < this.A_Group.size(); ++i) {
			if(A_Group.get(i).getUser().getID().compareTo(ID) == 0)
				return true;
		}
		return false;
	}
	
//	--------------------------------------------Open-----------------------------------------
	public boolean isOpen() {return Open;}

	public void setOpen(boolean open) {Open = open;}


//	--------------------------------------------target-----------------------------------------
	public Client[] getTarget() {return target;}

	public String findTarget(int index) {
		if(this.target[index] == null)
			return "";
		return this.target[index].getName();
	}
	
	public String getTargetID(int index) {
		if(this.target[index] == null)
			return "";
		return this.target[index].getID();
	}
	
	public void removeTarget(int i) {this.target[i] = null;}
	
	public void clearTarget()	{
		for(int i = 0; i < 6; ++i)
			this.target[i] = null;
	}

	public void setTarget(Client[] target) {this.target = target;}
	
	public void addTarget(int index, Client target) {this.target[index] = target;}

	public int getEvaluationScore() {return EvaluationScore;}

	public void setEvaluationScore(int evaluationScore) {EvaluationScore = evaluationScore;}

	
	public void addEvaluationScore(int i) { ++this.EvaluationScore;}

	
}
