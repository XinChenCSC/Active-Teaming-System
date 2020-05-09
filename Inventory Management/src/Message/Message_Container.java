package Message;


public class Message_Container {

	private String ID;
	
	//Message container
	private String[][] content = new String[100][2];
	
	private int position = 0;
	
	//Constructor
	public Message_Container(String ID) {
		this.setID(ID);
	}
	
	public Message_Container() {
		this.ID = "";
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	

	public String[][] getContent() {
		return content;
	}

	public void setContent(String[][] content) {
		this.content = content;
	}

	public void addContent(String key, String value) {
		this.content[this.position][0] = key;
		this.content[this.position][1] = value;
		++this.position;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
