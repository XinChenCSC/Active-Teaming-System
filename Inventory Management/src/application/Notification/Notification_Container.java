package application.Notification;

public class Notification_Container {

	private boolean NewIcon;
	
	private String date;
	
	private String message;
	
	//Constructor
	public Notification_Container(boolean newIcon, String date, String message) {
		this.NewIcon = newIcon;
		this.date = date;
		this.message = message;
	}

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
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	//--------------------------------------
	//Message
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
