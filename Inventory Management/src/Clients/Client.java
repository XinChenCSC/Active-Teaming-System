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
	
	protected String imageURL = "";
	
//	-------------------------------------------All Status in the Account------------------------------
	protected int Reputation_Score = 0;
	
	protected int Total_Project_Completed = 0;
	
	protected int Total_Group_Engaged = 0;
	
	protected int Total_Penalty_Received = 0;
	
	protected String Status = "";
	
	protected String Evaluation = "";
	
	protected String Date_Of_Join = "";
	
//	----------------------------------------------Contructors--------------------------------------------------------------
	public Client() {}
	
	//SU / OU / VIP
	public Client(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password,
			int reputation_score, int total_project_completed, int total_group_engaged, int total_penalty_received,
			String status, String evaluation, String date_of_join) {
		this.Name = Name;
		this.ID = ID;
		this.Email = Email;
		this.Position = Position;
		this.Interest = Interest;
		this.Recommender = Recommender;
		this.Password = Password;
		this.Reputation_Score = reputation_score;
		this.Total_Project_Completed = total_project_completed;
		this.Total_Group_Engaged = total_group_engaged;
		this.Total_Penalty_Received = total_penalty_received;
		this.Status = status;
		this.Evaluation = evaluation;
		this.Date_Of_Join = date_of_join;
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
		
//	------------------------------------Comment getter and setter---------------------------------------
	public String getComment() {return Comment;}

	public void setComment(String comment) {Comment = comment;}
	
//	------------------------------------Password getter and setter---------------------------------------
	public String getPassword() {return Password;}

	public void setPassword(String password) {Password = password;}
	
//	------------------------------------ID getter and setter---------------------------------------
	public String getID() {return ID;}

	public void setID(String iD) {ID = iD;}
	
//	------------------------------------Position getter and setter---------------------------------------
	public String getPosition() {return Position;}

	public void setPosition(String position) {Position = position;}
	
//	------------------------------------Interest getter and setter---------------------------------------
	public String getInterest() {return Interest;}

	public void setInterest(String interest) {Interest = interest;}
	
//	------------------------------------Recommender getter and setter---------------------------------------
	public String getRecommender() {return Recommender;}

	public void setRecommender(String recommender) {Recommender = recommender;}
	
//	------------------------------------Email getter and setter---------------------------------------
	public String getEmail() {return Email;}

	public void setEmail(String email) {Email = email;}
	
//	------------------------------------Name getter and setter---------------------------------------
	public String getName() {return Name;}

	public void setName(String name) {Name = name;}
	
//	------------------------------------Reputation Score---------------------------------------
	public void setReputationScore(int i) {this.Reputation_Score = i;}
	
	public int getReputationScore() {return this.Reputation_Score;}
	
	public void addReputationScore(int i) {this.Reputation_Score += i;}
	
	public void subReputationScore(int i ) {this.Reputation_Score -= i;}
	
//	------------------------------------Image URL String Format---------------------------------------
	public void setImageURL(String str) {this.imageURL = str;}
	
	public String getImageURL() {return this.imageURL;}
	
//	------------------------------------Total Project Completed---------------------------------------
	public int getTotal_Project_Completed() {return Total_Project_Completed;}

	public void setTotal_Project_Completed(int total_Project_Completed) {Total_Project_Completed = total_Project_Completed;}
	
	public void addTotal_Project() {++this.Total_Project_Completed;}
	
//	-----------------------------------Total project engaged----------------------------------------
	public int getTotal_Group_Engaged() {return Total_Group_Engaged;}

	public void setTotal_Group_Engaged(int total_Group_Engaged) {Total_Group_Engaged = total_Group_Engaged;}
	
//	------------------------------------Total Penalty Received---------------------------------------
	public void addTotal_Group() {++this.Total_Group_Engaged; }

	public int getTotal_Penalty_Received() {return Total_Penalty_Received;}

	public void setTotal_Penalty_Received(int total_Penalty_Received) {Total_Penalty_Received = total_Penalty_Received;}
	
//	------------------------------------Status---------------------------------------
	public String getStatus() {return Status;}

	public void setStatus(String Status) {this.Status = Status;}
	
//	------------------------------------Evaluation---------------------------------------
	public String getEvaluation() {return Evaluation;}

	public void setEvaluation(String evaluation) {Evaluation = evaluation;}

//	------------------------------------Date Of Join---------------------------------------
	public String getDate_Of_Join() {return Date_Of_Join;}

	public void setDate_Of_Join(String date_Of_Join) {Date_Of_Join = date_Of_Join;}
}
