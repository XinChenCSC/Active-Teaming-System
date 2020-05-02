package Email;

public class Email {

	private String from ;
	
	private String subject;
	
	private String content;
	
	private String to;
	
	//Constructor
	public Email(String from, String subject, String content, String to) {
		this.from = from;
		this.subject = subject;
		this.content = content;
		this.to = to;
	}

	public String getfrom() {return from;}

	public void setfrom(String from) {this.from = from;}

	public String getSubject() {return subject;}

	public void setSubject(String subject) {this.subject = subject;}
	
	public String getContent() {return content;}

	public void setContent(String content) {this.content = content;}

	public String getTo() {return to;}

	public void setTo(String to) {this.to = to;}
}
