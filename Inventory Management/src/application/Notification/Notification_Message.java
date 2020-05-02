package application.Notification;

public class Notification_Message {

	@SuppressWarnings("unused")
	private int value = 0;
	
	@SuppressWarnings("unused")
	private String reason = "";
	
	//Constructor
	public Notification_Message() {
	}
	
	private void setVariables(int value, String target) {
		this.value = value;
		this.reason = target;		
	}
	
	//Score reduction message
	public String Score_Decrease_Message(int value, String target) {
		setVariables(value, target);
		String str = "You received " + value + " scores reduction for " + target + ".";
		return str;
	}
	
	//Score reduction message
	public String Score_Increase_Message(int value, String target) {
		setVariables(value, target);
		String str = "You received " + value + " scores increase for " + target + ".";
		return str;
	}
	
	//Voting notification
	//Warning
	public String Group_Warning_Message(String target) {
		setVariables(0, target);
		String str = "You're the target of " + target + " vote.";
		return str;
	}
	
	//Praise
	public String Group_Praise_Message(String target) {
		setVariables(0, target);
		String str = "You're the target of " + target + " vote.";
		return str;
	}
	
	//Evaluation exit
	public String Group_Exit_Evaluation_Message(String target) {
		setVariables(0, target);
		String str = "You're the target of " + target + " vote.";
		return str;
	}
	
	//Closure
	public String Group_Closure_Message() {
		setVariables(0, "");
		String str = "Your groupmates are polling for closure the group.";
		return str;
	}
	
	//Kick from the group
	public String Group_Kickout_Message(String target) {
		setVariables(0, target);
		String str = "You're the target of " + target + " vote.";
		return str;
	}
	//*****************************************************************************
	
	//Notification from SU
	//Kick from the system
	public String System_Kick_Message(String target) {
		setVariables(0, target);
		String str = "You have been kicked out from the system by " + target;
		return str;
	}
	
	//Evaluation a group
	public String Group_Evaluation_Message() {
		setVariables(0, "");
		String str = "SU assigns you to evaluation a group.";
		return str;
	}
	
	//Member revise
	public String Member_Revise_Message() {
		setVariables(0, "");
		String str = "SU approved your appeal, your group identity has been recovered.";
		return str;
	}
	//********************************************************************************
	
	//Group confirmation notifications
	//Kick from the group
	public String Confirmed_Kickout_Message() {
		setVariables(0, "");
		String str = "You have been kicked out from the group.";
		return str;
	}
	
	//Voting failed.
	public String Confirmed_Voting_Message() {
		setVariables(0, "");
		String str = "Nothing has changed as group poll failed.";
		return str;
	}
	
	//Confirm evaluation exit
	public String Confirmed_Evaluation_Message() {
		setVariables(0, "");
		String str = "Your evaluation board has been removed.";
		return str;
	}
	
	//Promotion
	public String Confirmed_Promotion_Message(String target) {
		setVariables(0, target);
		String str = "You were promoted to " + target + "." ;
		return str;
	}
	
	//Demotion
	public String Confirmed_Demotion_Message(String target) {
		setVariables(0, target);
		String str = "You were demoted to " + target + ".";
		return str;
	}
}
