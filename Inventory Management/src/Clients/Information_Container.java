package Clients;

import java.util.ArrayList;

import Appointment.Appointment;
import Email.Email;
import Message.Message_Container;
import Notice.Notice;

public class Information_Container {
		
	//Messages
	private ArrayList<Message_Container> Message_Content = new ArrayList<Message_Container>(); 
	
	//Emails
	private ArrayList<Email> Email_Content = new ArrayList<Email>();
	
	//blacklist 
	private ArrayList<Client> Personal_Blacklist = new ArrayList<Client>();
	
	//whitebox
	private ArrayList<Client> Whitebox = new ArrayList<Client>();
	
	//Notice
	private ArrayList<Notice> Notice = new ArrayList<Notice>();
	
	//Appointment
	private ArrayList<Appointment> Appointment = new ArrayList<Appointment>();
	
	private String ID;
	
//	-----------------------------------Contructors----------------------------------------
	public Information_Container() {}
	
	public Information_Container(String ID) {this.setID(ID);}

//	-----------------------------------ID getter and setter----------------------------------------
	public String getID() {return ID;}

	public void setID(String ID) {this.ID = ID;}
	
//	-----------------------------------Notice----------------------------------------
	public ArrayList<Notice> getNotice() {return this.Notice;}

	public void setNotice(ArrayList<Notice> notice) {this.Notice = notice;}
	
	public void addNotice(Notice notice) {this.Notice.add(notice);}
	
	public void removeNotice(Notice notice) {this.Notice.remove(notice);}
	
//	-----------------------------------Whitebox----------------------------------------
	public ArrayList<Client> getWhitebox() {return Whitebox;}
	
	public void setWhitebox(ArrayList<Client> whitebox) {Whitebox = whitebox;}
	
	public void removeFriend(Client Name) {Whitebox.remove(Name);}
	
	public void addFriend(Client Name) {Whitebox.add(Name);}
	
	public Client findFriend(String name) {
		Client client = null;
		for(int i = 0; i < this.Whitebox.size(); ++i) {
			if(this.Whitebox.get(i).getName().compareTo(name) == 0) {
				client = this.Whitebox.get(i);
			}
		}
		return client;
	}
	
//	-----------------------------------Personal blacklist----------------------------------------
	public void removeBLMember(Client Name) {Personal_Blacklist.remove(Name);}
	
	public void addBLMember(Client Name) {Personal_Blacklist.add(Name);}
	
	public ArrayList<Client> getPersonal_Blacklist() {return Personal_Blacklist;}

	public void setPersonal_Blacklist(ArrayList<Client> personal_Blacklist) {Personal_Blacklist = personal_Blacklist;}
	
//	-----------------------------------Message Content----------------------------------------
	public void addMessage(Message_Container MC) {Message_Content.add(MC);}
	
	public void removeMessage(Message_Container MC) {Message_Content.remove(MC);}
	
	public ArrayList<Message_Container> getMessage_Content() {return Message_Content;}
	
	public void setMessage_Content(ArrayList<Message_Container> message_Content) {Message_Content = message_Content;}	
	
//	-----------------------------------Email Content----------------------------------------
	public void addEmail(Email email) {Email_Content.add(email);}
	
	public void removeEmail(Email email) {Email_Content.remove(email);}
	
	public ArrayList<Email> getEmail_Content() {return Email_Content;}

	public void setEmail_Content(ArrayList<Email> email_Content) {Email_Content = email_Content;}
	
	
//	-----------------------------------Appointment----------------------------------------
	public ArrayList<Appointment> getAppointment() {return Appointment;}

	public void setAppointment(ArrayList<Appointment> appointment) {Appointment = appointment;}
	
	public void addAppointment(Appointment appointment) {this.Appointment.add(appointment);}
	
	public void removeAppointment(Appointment appointment) {this.Appointment.remove(appointment);}
}
