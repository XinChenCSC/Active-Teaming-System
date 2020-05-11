package Group;

import java.util.ArrayList;

import Clients.Client;

public class Group_List {

	private ArrayList<Group> Group_List = new ArrayList<>();

	//Constructor
	public Group_List() {}
	
//	---------------------------------------Group List----------------------------------
	public ArrayList<Group> getGroup_List() {return Group_List;}

	public void setGroup_List(ArrayList<Group> group_List) {Group_List = group_List;}
	
//	---------------------------------------Remove a group----------------------------------
	public void removeGroup(String ID) {
		for(int i = 0; i < this.getGroup_List().size(); ++i) {
			if(this.getGroup_List().get(i).getGroup_ID().compareTo(ID) == 0)
				this.getGroup_List().remove(this.getGroup_List().get(i));
		}
	}
	
	public void removeGroup(Group group) {
		this.Group_List.remove(group);
	}
	
	public void addUserToGroup(String ID, Group_Status client) {
		for(int i = 0; i < this.Group_List.size(); ++i) {
			if(this.Group_List.get(i).getGroup_ID().compareTo(ID) == 0) {
				this.Group_List.get(i).addGroupMember(client);
			}
		}
	}
	
	public void addGroup(Group group) {
		this.Group_List.add(group);
	}
}
