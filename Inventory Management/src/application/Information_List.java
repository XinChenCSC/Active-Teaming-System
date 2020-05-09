package application;

import java.util.ArrayList;

import Email.Email;
import Message.Message_Container;
import application.Notification.Notification;

public class Information_List {
	private ArrayList<Information_Container> Info_Con = new ArrayList<Information_Container>();
	
	public Information_List() {}
	
	public void CreateEmail(String id, Email email) {
		boolean b = false;
		for(int i = 0; i < Info_Con.size(); ++i) {
			if(id.compareTo(Info_Con.get(i).getID()) == 0) {
				Info_Con.get(i).addEmail(email);
				b = true;
				break;
			}
		}
		if(!b) {
			Information_Container ic = new Information_Container(id);
			ic.addEmail(email);
			this.Info_Con.add(ic);			
		}
	}
	
	public void removeEmail(String id, Email email) {
		for(int i = 0; i < Info_Con.size(); ++i) {
			if(id.compareTo(Info_Con.get(i).getID()) == 0) {
				Info_Con.get(i).removeEmail(email);
				break;
			}
		}
	}
	
	public void CreateMessage(String id, Message_Container message) {
		boolean b = false;
		for(int i = 0; i < Info_Con.size(); ++i) {
			if(id.compareTo(Info_Con.get(i).getID()) == 0) {
				this.Info_Con.get(i).addMessage(message);
				b = true;
				break;
			}
		}
		if(!b) {
			Information_Container ic = new Information_Container(id);
			ic.addMessage(message);
			this.Info_Con.add(ic);			
		}
	}
	
	public void removeMessage(String id, Message_Container message) {
		for(int i = 0; i < Info_Con.size(); ++i) {
			if(id.compareTo(Info_Con.get(i).getID()) == 0) {
				Info_Con.get(i).removeMessage(message);
				break;
			}
		}
	}
	
	public void CreateNotification(String id, Notification notification) {
		boolean b = false;
		for(int i = 0; i < Info_Con.size(); ++i) {
			if(id.compareTo(Info_Con.get(i).getID()) == 0) {
				Info_Con.get(i).addNotification(notification);
				b = true;
			}
		}
		if(!b) {
			Information_Container ic = new Information_Container(id);
			ic.addNotification(notification);;
			this.Info_Con.add(ic);			
		}
	}
	
	public ArrayList<Information_Container> getInfo_Con(){
		return this.Info_Con;
	}
	
	public void removeNotification(String id) {
		for(int i = 0 ;i < Info_Con.size(); ++i) {
			if(Info_Con.get(i).getID().compareTo(id) == 0)
				Info_Con.remove(i);
		}
	}
	
	public void setInfo_Con(ArrayList<Information_Container> info_con) {
		this.Info_Con = info_con;
	}
}
