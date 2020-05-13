	package application;


import java.util.LinkedList;

public class Personal_Information {

		private LinkedList<String> firstname;
		private LinkedList<String> lastname;
		private LinkedList<String> id;
		private LinkedList<String> password;
		private LinkedList<Integer> score;
		
		public Personal_Information() {
			firstname = new LinkedList<String>();
			lastname = new LinkedList<String>();
			id = new LinkedList<String>();
			password = new LinkedList<String>();
			score = new LinkedList<Integer>();
			
		}

		public Personal_Information(String firstname, String lastname, String id, String password,int score ) {
			this.firstname.add(firstname);
			this.lastname.add(lastname);
			this.id.add(id);
			this.password.add(password);
			this.score.add(score);
		}

		public LinkedList<String> getFirstname() {
			return firstname;
		}

		public void setFirstname(LinkedList<String> firstname) {
			this.firstname = firstname;
		}

		public LinkedList<String> getLastname() {
			return lastname;
		}

		public void setLastname(LinkedList<String> lastname) {
			this.lastname = lastname;
		}

		public LinkedList<String> getId() {
			return id;
		}

		public void setId(LinkedList<String> id) {
			this.id = id;
		}

		public LinkedList<String> getPassword() {
			return password;
		}

		public void setPassword(LinkedList<String> password) {
			this.password = password;
		}

		public LinkedList<Integer> getScore() {
			return score;
		}

		public void setScore(LinkedList<Integer> score) {
			this.score = score;
		}
}

		