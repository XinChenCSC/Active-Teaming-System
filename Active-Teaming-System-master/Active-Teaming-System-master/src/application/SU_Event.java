package application;

import java.util.LinkedList;

public class SU_Event {
	private LinkedList<String> fname;
	private LinkedList<String>  lname;
	private LinkedList<String> account;
	private LinkedList<String>  password;
	private LinkedList<String> email;
	private LinkedList<String> interest;
	private LinkedList<String> referer;
	
	
	public SU_Event() {
		
		this.fname = new LinkedList<String>();
		this.lname = new LinkedList<String>();
		this.account = new LinkedList<String>();
		this.password = new LinkedList<String>();
		this.email = new LinkedList<String>();
		this.interest = new LinkedList<String>();
		this.referer = new LinkedList<String>();
		
		
		
	}
	public SU_Event(String fname,String lname,String interest, String referer, String email) {
		
		this.fname.add(fname);
		this.lname.add(lname);
		this.interest.add(interest);
		this.referer.add(referer);
		this.email.add(email);
		
		
		
	}
	public String generate_acc() {
		
		
		
		
		return " ";
		
		
	}
	public String generate_pass() {
		
		
		
		
		return " ";
		
		
	}
	public void setFname(LinkedList<String> fname) {
		this.fname = fname;
	}
	public LinkedList<String> getLname() {
		return lname;
	}
	public void setLname(LinkedList<String> lname) {
		this.lname = lname;
	}
	public LinkedList<String> getInterest() {
		return interest;
	}
	
	public LinkedList<String> getAccount() {
		return account;
	}
	public void setAccount(LinkedList<String> account) {
		this.account = account;
	}
	public LinkedList<String> getPassword() {
		return password;
	}
	public void setPassword(LinkedList<String> password) {
		this.password = password;
	}
	public LinkedList<String> getEmail() {
		return email;
	}
	public void setEmail(LinkedList<String> email) {
		this.email = email;
	}
	public LinkedList<String> getFname() {
		return fname;
	}

	public void setInterest(LinkedList<String> interest) {
		this.interest = interest;
	}
	public LinkedList<String> getReferer() {
		return referer;
	}
	public void setReferer(LinkedList<String> referer) {
		this.referer = referer;
	}


}
