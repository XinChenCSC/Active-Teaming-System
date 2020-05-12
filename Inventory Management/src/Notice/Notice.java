package Notice;

public class Notice {
	
	private boolean NewIcon;
	
	private String Date;
	
	private String Message;
	
	private int index;
		
	//Constructor
	public Notice(int index, String date, String message, boolean newIcon) {
		this.NewIcon = newIcon;
		this.Date = date;
		this.Message = message;
		this.setIndex(index);
	}

	public Notice(boolean newIcon, String date, String message) {
		this.NewIcon = newIcon;
		this.Date = date;
		this.Message = message;
		this.setIndex(-1);
	}

	public Notice() {}
	
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
