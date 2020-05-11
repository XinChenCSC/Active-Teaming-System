package application.Notification;

public class Notification {

	private boolean NewIcon;
	
	private String Date;
	
	private String Message;
	
	private boolean Invitation = false;
	
	//Constructor
	public Notification(boolean newIcon, String date, String message) {
		this.NewIcon = newIcon;
		this.Date = date;
		this.Message = message;
	}
	
	public Notification(boolean newIcon, String date, String message, boolean invitation) {
		this.NewIcon = newIcon;
		this.Date = date;
		this.Message = message;
		this.Invitation = invitation;
	}

	public Notification() {}
	
	//Icon 
	public boolean isNewIcon() {
		return NewIcon;
	}

	public void setNewIcon(boolean newIcon) {
		NewIcon = newIcon;
	}
	//-------------------------------------
	//Date 
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		this.Date = date;
	}
	//--------------------------------------
	//Message
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		this.Message = message;
	}

	//Invitation
	public boolean isInvitation() {
		return Invitation;
	}

	public void setInvitation(boolean invitation) {
		this.Invitation = invitation;
	}
}
