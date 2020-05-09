package Clients;

public class Personal_Information {

		private String firstname;
		private String lastname;
		private String id;
		private String password;
		
		public Personal_Information() {
			this(" ", " ", " ", " ");
		}

		public Personal_Information(String firstname, String lastname, String id, String password) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.id = id;
			this.password = password;
		}
		
		//setters
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public void setPassword(String password) {
			this.password = password;	
		}
		
		//getters
		public String getFirstname() {
			return this.firstname;
		}
		
		public String getLastname() {
			return this.lastname;
		}
		
		public String getId() {
			return this.id;
		}
		
		public String getPassword() {
			return this.password;
		}
		
		
}
