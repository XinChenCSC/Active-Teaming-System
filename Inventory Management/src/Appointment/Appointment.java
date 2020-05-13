package Appointment;

public class Appointment {

	private String Date = "";
		
	private double Duration = 0.0;
	
	
//	-------------------------------Contructors--------------------------------
	public Appointment() {}
	
	public Appointment(String date, double duration) {
		this.setDate(date);
		this.setDuration(duration);
	}
	
//	-------------------------------Date getter and setter--------------------------------
	public String getDate() {return Date;}

	public void setDate(String date) {Date = date;}
	
//	-------------------------------Duration getter and setter--------------------------------
	public double getDuration() {return Duration;}

	public void setDuration(double duration) {Duration = duration;}
}
