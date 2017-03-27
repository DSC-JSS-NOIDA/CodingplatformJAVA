package org.gdgjss.codingplatform.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		   name = "Codesave", 
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
		)
public class Codesave {
	
	@Id
	private String email;
	private String ques1_C_code;
	private String ques1_JAVA_code;
	private String ques1_PYTHON_code;
	
	private String ques2_C_code;
	private String ques2_JAVA_code;
	private String ques2_PYTHON_code;
	
	private String ques3_C_code;
	private String ques3_JAVA_code;
	private String ques3_PYTHON_code;
	
	private String ques4_C_code;
	private String ques4_JAVA_code;
	private String ques4_PYTHON_code;
	
	private String ques5_C_code;
	private String ques5_JAVA_code;
	private String ques5_PYTHON_code;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQues1_C_code() {
		return ques1_C_code;
	}
	public void setQues1_C_code(String ques1_C_code) {
		this.ques1_C_code = ques1_C_code;
	}
	public String getQues1_JAVA_code() {
		return ques1_JAVA_code;
	}
	public void setQues1_JAVA_code(String ques1_JAVA_code) {
		this.ques1_JAVA_code = ques1_JAVA_code;
	}
	public String getQues1_PYTHON_code() {
		return ques1_PYTHON_code;
	}
	public void setQues1_PYTHON_code(String ques1_PYTHON_code) {
		this.ques1_PYTHON_code = ques1_PYTHON_code;
	}
	public String getQues2_C_code() {
		return ques2_C_code;
	}
	public void setQues2_C_code(String ques2_C_code) {
		this.ques2_C_code = ques2_C_code;
	}
	public String getQues2_JAVA_code() {
		return ques2_JAVA_code;
	}
	public void setQues2_JAVA_code(String ques2_JAVA_code) {
		this.ques2_JAVA_code = ques2_JAVA_code;
	}
	public String getQues2_PYTHON_code() {
		return ques2_PYTHON_code;
	}
	public void setQues2_PYTHON_code(String ques2_PYTHON_code) {
		this.ques2_PYTHON_code = ques2_PYTHON_code;
	}
	public String getQues3_C_code() {
		return ques3_C_code;
	}
	public void setQues3_C_code(String ques3_C_code) {
		this.ques3_C_code = ques3_C_code;
	}
	public String getQues3_JAVA_code() {
		return ques3_JAVA_code;
	}
	public void setQues3_JAVA_code(String ques3_JAVA_code) {
		this.ques3_JAVA_code = ques3_JAVA_code;
	}
	public String getQues3_PYTHON_code() {
		return ques3_PYTHON_code;
	}
	public void setQues3_PYTHON_code(String ques3_PYTHON_code) {
		this.ques3_PYTHON_code = ques3_PYTHON_code;
	}
	public String getQues4_C_code() {
		return ques4_C_code;
	}
	public void setQues4_C_code(String ques4_C_code) {
		this.ques4_C_code = ques4_C_code;
	}
	public String getQues4_JAVA_code() {
		return ques4_JAVA_code;
	}
	public void setQues4_JAVA_code(String ques4_JAVA_code) {
		this.ques4_JAVA_code = ques4_JAVA_code;
	}
	public String getQues4_PYTHON_code() {
		return ques4_PYTHON_code;
	}
	public void setQues4_PYTHON_code(String ques4_PYTHON_code) {
		this.ques4_PYTHON_code = ques4_PYTHON_code;
	}
	public String getQues5_C_code() {
		return ques5_C_code;
	}
	public void setQues5_C_code(String ques5_C_code) {
		this.ques5_C_code = ques5_C_code;
	}
	public String getQues5_JAVA_code() {
		return ques5_JAVA_code;
	}
	public void setQues5_JAVA_code(String ques5_JAVA_code) {
		this.ques5_JAVA_code = ques5_JAVA_code;
	}
	public String getQues5_PYTHON_code() {
		return ques5_PYTHON_code;
	}
	public void setQues5_PYTHON_code(String ques5_PYTHON_code) {
		this.ques5_PYTHON_code = ques5_PYTHON_code;
	}
	
	
	public Codesave(String email) {
		super();
		this.email = email;
		this.ques1_C_code = "NA";
		this.ques1_JAVA_code = "NA";
		this.ques1_PYTHON_code = "NA";
		this.ques2_C_code = "NA";
		this.ques2_JAVA_code = "NA";
		this.ques2_PYTHON_code = "NA";
		this.ques3_C_code = "NA";
		this.ques3_JAVA_code = "NA";
		this.ques3_PYTHON_code = "NA";
		this.ques4_C_code = "NA";
		this.ques4_JAVA_code = "NA";
		this.ques4_PYTHON_code = "NA";
		this.ques5_C_code = "NA";
		this.ques5_JAVA_code = "NA";
		this.ques5_PYTHON_code = "NA";
	}
	public Codesave() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
		
	
	
	
	
}
