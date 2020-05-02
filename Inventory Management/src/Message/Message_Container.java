package Message;

import java.util.HashMap;

public class Message_Container {

	private String target;
	
	private HashMap<Integer, String> container = new HashMap<Integer, String>();
	
	//Constructor
	public Message_Container(String target) {
		this.setTarget(target);
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	//Add message 
	public void addMessage(int key, String value) {
		container.put(key, value);
	}
	
	public HashMap<Integer, String> getContainer(){
		return this.container;
	}
}
