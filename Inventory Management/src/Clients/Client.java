package Clients;


public class Client {
	
	protected String Name;
	
	protected String Email;
	
	protected String Recommender;
	
	protected String Interest;
	
	protected String Position;
	
	protected String ID;
	
	protected String Password;
	
	protected String Comment;
	
	protected int Reputation_Score = 0;
	
	protected String imageURL = "";
	
	//constructor
	public Client() {}
	
//	-----------------------------------Contructors----------------------------------------
	//SU / OU / VIP
	public Client(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password) {
		this.Name = Name;
		this.ID = ID;
		this.Email = Email;
		this.Position = Position;
		this.Interest = Interest;
		this.Recommender = Recommender;
		this.Password = Password;
	}
	
	//White box 
	public Client(String Name, String Interest, String Recommender, String Email) {
		this.Name = Name;
		this.Email = Email;
		this.Recommender = Recommender;
		this.Email = Email;
	}
	
	//Blacklist
	public Client(String Name, String ID) {
		this.Name = Name;
		this.ID = ID;
	}
	
	//System blacklist
	public Client(String Name, String Email, String ID) {
		this.Name = Name;
		this.Email = Email;
		this.ID = ID;
	}
		
	
	//Getters and setters
	public String getComment() {return Comment;}

	public void setComment(String comment) {Comment = comment;}

	public String getPassword() {return Password;}

	public void setPassword(String password) {Password = password;}

	public String getID() {return ID;}

	public void setID(String iD) {ID = iD;}

	public String getPosition() {return Position;}

	public void setPosition(String position) {Position = position;}

	public String getInterest() {return Interest;}

	public void setInterest(String interest) {Interest = interest;}

	public String getRecommender() {return Recommender;}

	public void setRecommender(String recommender) {Recommender = recommender;}

	public String getEmail() {return Email;}

	public void setEmail(String email) {Email = email;}

	public String getName() {return Name;}

	public void setName(String name) {Name = name;}
	
	public void setReputationScore(int i) {this.Reputation_Score = i;}
	
	public int getReputationScore() {return this.Reputation_Score;}
	
	public void addReputationScore(int i) {this.Reputation_Score += i;}
	
	public void subReputationScore(int i ) {this.Reputation_Score -= i;}
	
	public void setImageURL(String str) {this.imageURL = str;}
	
	public String getImageURL() {return this.imageURL;}
	//*************************************************
}
