package Clients;

public class Guest extends Client{
	
	//Total number of deny < 2
	private int numRegister;
	
//	//Check whether Activate has been approved
	private boolean Activate;
	
	//Check whether the guest make the first login attempt
	private boolean isLogin;
	
	//Contructor
	public Guest() {
		this.numRegister = 0;
		this.setActivate(false);
		this.isLogin = false;
	}
	
	public Guest(String Name, String Interest, String recommender, String Email) {
		super(Name, Interest, recommender, Email);
		this.numRegister = 0;
		this.setActivate(false);
		this.isLogin = false;
	}
	
	
	//-----------------------------------Getters-------------------------------------------  
	
//	public boolean isActivated() {return Activate;}

	public int getNumRegister() {return numRegister;}

	public boolean isLogin() {return isLogin;}
	//**************************************************************************************
	
	//-----------------------------------Setters-----------------------------------------------
	

	public void setLogin(boolean isLogin) {this.isLogin = isLogin;}

	public void setNumRegister(int numRegister) {++this.numRegister;}
	//**************************************************************************************
	
	//Increase numRegister by one
	public void incNumRegister() {++numRegister;}

	public boolean isActivate() {
		return Activate;
	}

	public void setActivate(boolean activate) {
		Activate = activate;
	}


}
