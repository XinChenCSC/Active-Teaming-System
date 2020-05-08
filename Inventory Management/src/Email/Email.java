package Email;

public class Email {

	private String Target ;
	
	private String Subject;
	
	private String Content;
	
	//ConstrucTor
	public Email() {
		this.Target = "";
		this.Subject = "";
		this.Content = "";
	}

	public Email(String Subject, String Content, String Target) {
		this.Target = Target;
		this.Subject = Subject;
		this.Content = Content;
	}

	public String getTarget() {return this.Target;}

	public void setTarget(String Target) {this.Target = Target;}

	public String getSubject() {return this.Subject;}

	public void setSubject(String Subject) {this.Subject = Subject;}
	
	public String getContent() {return this.Content;}

	public void setContent(String Content) {this.Content = Content;}
	
	public String toString() {
		return this.Target + "\n" + this.Content;
	}
}
