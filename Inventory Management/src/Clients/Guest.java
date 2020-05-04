package Clients;

public class Guest extends Client{
	
	//Total number of deny < 2
	private int numRegister;
	
	//Check whether registration has been approved
	private boolean registration;
	
	//Contructor
	public Guest() {
		this.numRegister = 0;
		this.registration = false;
	}
	
	public Guest(String Name, String Interest, String recommender, String Email) {
		super(Name, Interest, recommender, Email);
		this.numRegister = 0;
		this.registration = false;
	}
	
	//-----------------------------------Getters-------------------------------------------  
	public String getName() {return super.getName();}
	
	public String Interest() {return super.getInterest();} 
	 
	public String recommender() {return super.getRecommender(); }
	
	public String Email() {return super.getEmail();}
	
	public String getID() {return super.ID;}
	
	public String getPassword() {return super.Password;}
	
	public boolean isRegistration() {return registration;}
	//**************************************************************************************
	
	//-----------------------------------Setters-----------------------------------------------
	public void setID(String ID) {super.setID(ID);}
	
	public void setPassword(String Password) {super.setPassword(Password);}
	
	public void setInterest(String Interest) {super.setInterest(Interest);}

	public void setEmail(String Email) {super.setEmail(Email);}
	
	public void setName(String Name) {super.setName(Name);}
	
	public void setRecommender(String Recommender) {super.setRecommender(Recommender);}
	
	public void setRegistration(boolean registration) {this.registration = registration;}
	//**************************************************************************************
	
	public int getNumRegister() {return numRegister;}

	public void setNumRegister(int numRegister) {this.numRegister = numRegister;}
	
	//Increase numRegister by one
	public void incNumRegister() {++numRegister;}
}
