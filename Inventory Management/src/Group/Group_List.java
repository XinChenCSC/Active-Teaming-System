package Group;

import java.util.ArrayList;

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
	
}
