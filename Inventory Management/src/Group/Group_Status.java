package Group;

import Clients.Client;

public class Group_Status {
	
	private Client user = new Client();
	
	private int Attendances = 0;
	
	private int Warnings = 0;
	
	private int Praises = 0;
	
	private int Assignments = 0;
	
	private boolean isEvaluation = true;
	
	private boolean Condition = true;
	
	private String Evaluation = ""; 
	
	private boolean Kick = false;
	
	//Constructor
	public Group_Status() {}
	
	public Group_Status(Client client) { this.user = client;}

//	------------------------------------------User-------------------------------------------
	public Client getUser() {return user;}

	public void setUser(Client user) {this.user = user;}

//	------------------------------------------Attendances-------------------------------------------
	public int getAttendances() {return Attendances;}
	
	public void setAttendances(int attendances) {Attendances = attendances;}

	public void addAttendances() {++this.Attendances;}

//	------------------------------------------Warnings-------------------------------------------
	public int getWarnings() {return Warnings;}

	public void setWarnings(int warnings) {Warnings = warnings;}
	
	public void addWarnings() {++this.Warnings;}

//	------------------------------------------Praises-------------------------------------------
	public int getPraises() {return Praises;}

	public void setPraises(int praises) {Praises = praises;}

	public void addPraise() {++this.Praises;}

//	------------------------------------------Assignments-------------------------------------------
	public int getAssignments() {return Assignments;}

	public void setAssignments(int assignments) {Assignments = assignments;}

	public void addAssignments() {++this.Assignments;}

//	------------------------------------------is Evalutaion-------------------------------------------
	public boolean isEvaluation() {return isEvaluation;}

	public void setisEvaluation(boolean isEvaluation) {this.isEvaluation = isEvaluation;}

//	------------------------------------------Condition-------------------------------------------
	public boolean isCondition() {return Condition;}

	public void setCondition(boolean condition) {Condition = condition;}

//	------------------------------------------Evaluation-------------------------------------------
	public String getEvaluation() {return Evaluation;}

	public void setEvaluation(String evaluation) {Evaluation = evaluation;}

//	------------------------------------------Kick-------------------------------------------
	public boolean isKick() {return Kick;}

	public void setKick(boolean kick) {Kick = kick;}
}
