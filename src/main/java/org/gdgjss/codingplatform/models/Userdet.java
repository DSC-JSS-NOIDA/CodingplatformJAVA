package org.gdgjss.codingplatform.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Userdet {
	 @GeneratedValue
		int id;
		 @Id
		private String emailid;
		private String avatar;
		private String year;
		private String branch;
		private String name;
		private String admno;
		public Userdet(){}
		public Userdet(String emailid,String avatar,String year,String branch,String name,String admno)
		        { 
			super();
			this.emailid=emailid;
			this.avatar=avatar;
			this.year=year;
			this.branch=branch;
			this.name=name;
			this.admno=admno;
			
			
		         }
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAdmno() {
			return admno;
		}
		public void setAdmno(String admno) {
			this.admno = admno;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		
		
}
