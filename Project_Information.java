package application;

public class Project_Information {

	private String project_name;
	private String public_info;
	private String private_info;
	
	public Project_Information() {
		this(" ", " ", " ");
	}

	public Project_Information(String project_name, String public_info, String private_info) {
		// TODO Auto-generated constructor stub
		this.project_name = project_name;
		this.public_info = public_info;
		this.private_info = private_info;
	}

	
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getPublic_info() {
		return public_info;
	}
	public void setPublic_info(String public_info) {
		this.public_info = public_info;
	}
	public String getPrivate_info() {
		return private_info;
	}
	public void setPrivate_info(String private_info) {
		this.private_info = private_info;
	}
	

}
