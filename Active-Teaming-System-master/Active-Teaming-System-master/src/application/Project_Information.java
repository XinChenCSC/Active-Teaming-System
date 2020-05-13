package application;

import java.util.LinkedList;

public class Project_Information {

	private LinkedList<String> project_name;
	private LinkedList<String> public_info;
	private LinkedList<String> private_info;
	
	public Project_Information() {
		project_name = new LinkedList<String>();
		public_info = new LinkedList<String>();
		private_info = new LinkedList<String>();
	}

	public Project_Information(String project_name, String public_info, String private_info) {
		// TODO Auto-generated constructor stub
		this.project_name.add(project_name);
		this.public_info.add(public_info);
		this.private_info.add(private_info);
	}

	public LinkedList<String> getProject_name() {
		return project_name;
	}

	public void setProject_name(LinkedList<String> project_name) {
		this.project_name = project_name;
	}

	public LinkedList<String> getPublic_info() {
		return public_info;
	}

	public void setPublic_info(LinkedList<String> public_info) {
		this.public_info = public_info;
	}

	public LinkedList<String> getPrivate_info() {
		return private_info;
	}

	public void setPrivate_info(LinkedList<String> private_info) {
		this.private_info = private_info;
	}

	

}
