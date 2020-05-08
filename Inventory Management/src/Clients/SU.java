package Clients;

public class SU extends Client{
	
	//Constructor
	//Name - ID - Email - Position - Interest - Recommender - Password
	public SU() {
		super("Steve Chan", "23333", "SteveChan@gmail.com", "SU", "Basketball", "Qichen You", "66666");
	}
	
	public SU(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password) {
		super(Name, ID, Email, Position, Interest, Recommender, Password);
	}
	
	//Getters and setters
	public void setPassword(String Password) {super.setPassword(Password);}
	
	public String getPassword() {return super.Password;}
	
	public void setComment(String Comment) {super.setComment(Comment);}
	
	public String getComment() {return super.Comment;}
	
	public void setPosition(String Position) {super.setPosition(Position);}
	
	public String getPosition() {return super.Position;}
	
	public void setID(String ID) {super.setID(ID);}
	
	public String getID() {return super.ID;}
	
	public void setName(String Name) {super.setName(Name);}
	
	public String getName() {return super.Name;}
	
	public void setEmail(String Email) {super.setEmail(Email);}
	
	public String getEmail() {return super.Email;}
	
	public void setInterest(String Interest) {super.setInterest(Interest);}
	
	public String getInterest() {return super.Interest;}
	
	public void setRecommender(String Recommender) {super.setRecommender(Recommender);}
	
	public String getRecommender() {return super.Recommender;}
	
	//******************************************************************
}
