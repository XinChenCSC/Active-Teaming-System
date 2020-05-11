package Group;

import java.util.ArrayList;

public class Group {

	private ArrayList<Group_Status> A_Group = new ArrayList<>();
		
	private boolean voteTypes[] = {false, false, false, false, false, false};
	
	private int pollStatus[][];
	
	private int totalMember = 0;
	
	private String Group_ID = "";
	
	private String Title = "";
	
	private String Description = "";
	
	private boolean Close = false;
	
	//Constructor
	public Group() {}
	
	public Group(ArrayList<Group_Status> group,
			int totalMember,
			String ID,
			String title,
			String description) {
		this.A_Group = group;
		setSize(totalMember);
		this.Group_ID = ID;
		this.Description = description;
		this.Title = title;
	}

//	--------------------------------------------Group-----------------------------------------
	public ArrayList<Group_Status> getA_Group() {return A_Group;}

	public void setA_Group(ArrayList<Group_Status> a_Group) {A_Group = a_Group;}
	
	public void addGroupMember(Group_Status client) {this.A_Group.add(client);}
	
	public void removeGroupMember(Group_Status client) {this.A_Group.remove(client);}

//	--------------------------------------------Buffer-----------------------------------------
	public void removeBufferMember(Group_Status client) {
		--this.totalMember;
	}


//	--------------------------------------------Total member-----------------------------------------
	public int size() {return totalMember;}

	public void setSize(int totalMember) {
		this.totalMember = totalMember;
		this.pollStatus = new int[6][this.totalMember];
		for(int i = 0; i < 6; ++i) {
			for(int j = 0; j < this.totalMember; ++j) {
				this.pollStatus[i][j] = -1;
			}
		}
	}

//	--------------------------------------------ID-----------------------------------------
	public String getGroup_ID() {return Group_ID;}

	public void setGroup_ID(String group_ID) {Group_ID = group_ID;}

//	--------------------------------------------Vote type-----------------------------------------
	public void DeactivateVote(int i) {this.voteTypes[i] = false;}
	
	public void VoteTurnOn(int i) {this.voteTypes[i] = true;}

//	--------------------------------------------Polling status-----------------------------------------
	public void addAVote(int vote, int votetype, int index) {this.pollStatus[votetype][index] = vote;}
	
	public void PollInitialization(int reset) {
		for(int i = 0; i < this.totalMember; ++i) {
			this.pollStatus[reset][i] = -1;
		}
	}
	
//	--------------------------------------------Title-----------------------------------------
	public String getTitle() {return Title;}

	
	public void setTitle(String title) {Title = title;}

	public String getDescription() {return Description;}

	public void setDescription(String description) {Description = description;}

//	--------------------------------------------Close-----------------------------------------
	public boolean isClose() {return Close;}

	public void setClose(boolean close) {Close = close;}
	
	public boolean isGroupMember(String ID) {
		for(int i = 0; i < this.totalMember; ++i) {
			if(A_Group.get(i).getUser().getID().compareTo(ID) == 0)
				return true;
		}
		return false;
	}
}
