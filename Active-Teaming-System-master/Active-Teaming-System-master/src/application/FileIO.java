package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class FileIO {

	// variable for to get or set the information from the input/output file
	private File file;
	private PrintWriter output;
	private Scanner input; 
	

	
	
	public void User_Info_File_Input(LinkedList<String> fname,LinkedList<String> lname,LinkedList<String> id,LinkedList<String> pass,LinkedList<Integer> score) {
		
		try {
			input = new Scanner(file);
			while(input.hasNextLine()) {
				fname.add(input.nextLine());
				lname.add(input.nextLine());
				id.add(input.nextLine());
				pass.add(input.nextLine());
				score.add(Integer.parseInt(input.nextLine()));
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void User_Info_Output(LinkedList<String> fname,LinkedList<String> lname,LinkedList<String> id,LinkedList<String> pass,LinkedList<Integer> score) {
		file = new File("User_Info.txt");
		
		try {
			
			output = new PrintWriter(file);
			
			for(int i = 0; i< fname.size(); i++) {
				output.println(fname.get(i));
				output.println(lname.get(i));
				output.println(id.get(i));
				output.println(pass.get(i));
				output.println(score.get(i));
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.printf("Error: %s\n",e);
		}
		
	}
	
	public void Project_Info_File_Input(LinkedList<String> project_name,LinkedList<String> public_info,LinkedList<String> private_info) {
		file = new File("Project_Info.txt");
		try {
			input = new Scanner(file);
			while(input.hasNextLine()) {
				project_name.add(input.nextLine());
				public_info.add(input.nextLine());
				private_info.add(input.nextLine());
			
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void Project_Info_Output(LinkedList<String> project_name,LinkedList<String> public_info,LinkedList<String> private_info) {
		
		file = new File("Project_Info.txt");
		try {
			
			output = new PrintWriter(file);
			
			for(int i = 0; i< project_name.size(); i++) {
				output.println(project_name.get(i));
				output.println(public_info.get(i));
				output.println(private_info.get(i));
				
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.printf("Error: %s\n",e);
		}
	}
	
	public void Register_Info_File_Input(LinkedList<String> fname,LinkedList<String> lname,LinkedList<String> account,LinkedList<String> password,LinkedList<String> email,LinkedList<String> interest) {
		
		file = new File("Register_Info.txt");
		try {
			input = new Scanner(file);
			while(input.hasNextLine()) {
				fname.add(input.nextLine());
				lname.add(input.nextLine());
				account.add(input.nextLine());
				password.add(input.nextLine());
				email.add(input.nextLine());
				interest.add(input.nextLine());
			
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void Register_Info_Output(LinkedList<String> fname,LinkedList<String> lname,LinkedList<String> account,LinkedList<String> password,LinkedList<String> email,LinkedList<String> interest) {
		file = new File("Register_Info.txt");
		try {
			
			output = new PrintWriter(file);
			
			for(int i = 0; i< account.size(); i++) {
				output.println(fname.get(i));
				output.println(lname.get(i));
				output.println(account.get(i));
				output.println(password.get(i));
				output.println(email.get(i));
				output.println(interest.get(i));
				
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.printf("Error: %s\n",e);
		}
	}
	
}
