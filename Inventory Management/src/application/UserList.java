package application;

import java.util.ArrayList;

import Clients.Client;
import Clients.Guest;
import Clients.OU;
import Clients.SU;
import Clients.VIP;


public class UserList {

	private Guest guest = new Guest();
	
	private ArrayList<OU> OU_User = new ArrayList<OU>();
	
	private ArrayList<VIP> VIP_User = new ArrayList<VIP>();
	
	private ArrayList<SU> SU_User = new ArrayList<SU>();
	
	private ArrayList<Client> All_User = new ArrayList<Client>();
	
	public UserList() {}
	
	//Return size 
	public int getOU_Size() {
		return this.OU_User.size();
	}
	
	public int getVIP_Size() {
		return this.VIP_User.size();
	}
	
	public int getSU_Size() {
		return this.SU_User.size();
	}
	
	public int getAll_Size() {
		return this.All_User.size();
	}
	//************************************************************
	
	//---------------------------OU Users-------------------------------
	public void addOU_User(OU ou) {
		this.OU_User.add(ou);
		this.All_User.add(ou);
	}
	
	public void removeOU_User(OU ou) {
		this.OU_User.remove(ou);
		this.All_User.remove(ou);
	}
	
	public ArrayList<OU> getOU_User() {
		return this.OU_User;
	}
	//******************************************************************
	
	//---------------------------VIP Users-------------------------------
	public void addVIP_User(VIP vip) {
		this.VIP_User.add(vip);
		this.All_User.add(vip);
	}
	
	public void removeVIP_User(VIP vip) {
		this.VIP_User.remove(vip);
		this.All_User.remove(vip);
	}
	
	public ArrayList<VIP> getVip_User() {
		return this.VIP_User;
	}
	//******************************************************************
	
	//---------------------------SU Users-------------------------------
	public void addSU_User(SU su) {
		this.SU_User.add(su);
		this.All_User.add(su);
	}
	
	public void removeSU_User(SU su) {
		this.SU_User.remove(su);
		this.All_User.remove(su);
	}
	
	public ArrayList<SU> getSU_User() {
		return this.SU_User;
	}
	
	public void setSU(ArrayList<SU> s) {
		this.SU_User = s;
	}
	//******************************************************************
	
	public ArrayList<Client> getAll_User(){
		return this.All_User;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public Guest getGuest() {
		return this.guest;
	}
}
