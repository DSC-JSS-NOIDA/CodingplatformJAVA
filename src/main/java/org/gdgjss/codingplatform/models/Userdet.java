package org.gdgjss.codingplatform.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Userdet {
	
		 @Id
		private String emailid;
		private String team_name;
		private String participant1_name;
		private String participant1_roll;
		private String participant2_roll;
		private String participant2_name;
		private String contactno;           //used as a password
		
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public String getTeam_name() {
			return team_name;
		}
		public void setTeam_name(String team_name) {
			this.team_name = team_name;
		}
		public String getParticipant1_name() {
			return participant1_name;
		}
		public void setParticipant1_name(String participant1_name) {
			this.participant1_name = participant1_name;
		}
		public String getParticipant1_roll() {
			return participant1_roll;
		}
		public void setParticipant1_roll(String participant1_roll) {
			this.participant1_roll = participant1_roll;
		}
		public String getParticipant2_roll() {
			return participant2_roll;
		}
		public void setParticipant2_roll(String participant2_roll) {
			this.participant2_roll = participant2_roll;
		}
		public String getParticipant2_name() {
			return participant2_name;
		}
		public void setParticipant2_name(String participant2_name) {
			this.participant2_name = participant2_name;
		}
		public String getContactno() {
			return contactno;
		}
		public void setContactno(String contactno) {
			this.contactno = contactno;
		}
	
		
	 
		
}
