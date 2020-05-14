package Clients;

import java.util.ArrayList;

public class SU extends Client{
	@SuppressWarnings("unused")
	private int count = 0;
	@SuppressWarnings("unused")
	private ArrayList<String> blacklist = new ArrayList<String>();
	//Constructor
	//Name - ID - Email - Position - Interest - Recommender - Password
	public SU(String Name, String ID, String Email, String Position, String Interest, String Recommender, String Password,
			int reputation_score, int total_project_completed, int total_group_engaged, int total_penalty_received,
			String status, String evaluation, String date_of_join) {
		super(Name, ID, Email, Position, Interest, Recommender, Password, reputation_score, total_project_completed, 
				total_group_engaged, total_penalty_received, status, evaluation, date_of_join);
	}
	
	public SU() {
		super("Steve Chan", "23333", "SteveChan@gmail.com", "SU", "Basketball", "Qichen You", "66666",
				50, 5, 5, 5, "Good", "Off", "01/01/2000");
	}
	
	public String Generate_Pass() {
	
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"; 
		StringBuilder password = new StringBuilder(5); 
		for (int i = 0; i < 5; i++) { 
            int index  = (int)(data.length() * Math.random()); 
            password.append(data .charAt(index)); 
        } 
		return password.toString();
	}
	//******************************************************************
}
