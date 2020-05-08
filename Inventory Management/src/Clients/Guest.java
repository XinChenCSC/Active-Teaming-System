package Clients;

public class Guest extends Client{
	
	//Total number of deny < 2
	private int numRegister;
	
	//Check whether Activate has been approved
	private boolean Activate;
	
	//Check whether the guest make the first login attempt
	private boolean isLogin;
	
	//Contructor
	public Guest() {
		this.numRegister = 0;
		this.Activate = false;
		this.setLogin(false);
	}
	
	public Guest(String Name, String Interest, String recommender, String Email) {
		super(Name, Interest, recommender, Email);
		this.numRegister = 0;
		this.Activate = false;
		this.setLogin(false);
	}
	
	
	//-----------------------------------Getters-------------------------------------------  
	public String getName() {return super.getName();}
	
	public String Interest() {return super.getInterest();} 
	 
	public String recommender() {return super.getRecommender(); }
	
	public String Email() {return super.getEmail();}
	
	public String getID() {return super.ID;}
	
	public String getPassword() {return super.Password;}
	
	public boolean isActivated() {return Activate;}

	public int getNumRegister() {return numRegister;}

	public boolean isLogin() {return isLogin;}
	//**************************************************************************************
	
	//-----------------------------------Setters-----------------------------------------------
	public void setID(String ID) {super.setID(ID);}
	
	public void setPassword(String Password) {super.setPassword(Password);}
	
	public void setInterest(String Interest) {super.setInterest(Interest);}

	public void setEmail(String Email) {super.setEmail(Email);}
	
	public void setName(String Name) {super.setName(Name);}
	
	public void setRecommender(String Recommender) {super.setRecommender(Recommender);}
	
	public void setActivate(boolean Activate) {this.Activate = Activate;}

	public void setLogin(boolean isLogin) {this.isLogin = isLogin;}

	public void setNumRegister(int numRegister) {this.numRegister = numRegister;}
	//**************************************************************************************
	

	
	//Increase numRegister by one
	public void incNumRegister() {++numRegister;}


}
