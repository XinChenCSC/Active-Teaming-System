package Appointment;

public class Appointment {

	private String Date = "";
		
	private String Duration = " ";
	
	private boolean status = false;
	
	
//	-------------------------------Contructors--------------------------------
	public Appointment() {}
	
	public Appointment(String date, String duration) {
		this.setDate(date);
		this.setDuration(duration);
	}
	
//	-------------------------------Date getter and setter--------------------------------
	public String getDate() {return Date;}

	public void setDate(String date) {Date = date;}
	
//	-------------------------------Duration getter and setter--------------------------------
	public String getDuration() {return Duration;}

	public void setDuration(String duration) {Duration = duration;}
	
//	-------------------------------Duration getter and setter--------------------------------
	public boolean isStatus() {return status;}

	public void setStatus(boolean status) {this.status = status;}
}
