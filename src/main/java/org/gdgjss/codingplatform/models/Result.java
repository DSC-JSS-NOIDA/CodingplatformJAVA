package org.gdgjss.codingplatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		   name = "Result", 
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
		)
public class Result {
	
	@Id
	private String email;
	private String team;
	
	
	private int ques1_C=0;
	
	
	
	private int ques1_JAVA=0;
	
	private int ques1_PYTHON=0;
	
	
	private int ques1_C_l;

	
	private int ques1_JAVA_l;
	
	private int ques1_PYTHON_l;
	
	
	private int ques2_C=0;
	

	
	private int ques2_JAVA=0;
	
	private int ques2_PYTHON=0;
	
	
	private int ques2_C_l;
	
	
	private int ques2_JAVA_l;
	
	private int ques2_PYTHON_l;
	
	
	
	private int ques3_C=0;
	

	
	private int ques3_JAVA=0;
	
	private int ques3_PYTHON=0;
	
	
	private int ques3_C_l;
	

	
	private int ques3_JAVA_l;
	
	private int ques3_PYTHON_l;
	
	
	
	private int ques4_C=0;
	

	
	private int ques4_JAVA=0;
	
	private int ques4_PYTHON=0;
	
	
	private int ques4_C_l;
	

	
	private int ques4_JAVA_l;
	
	private int ques4_PYTHON_l;
	
	
	
	private int ques5_C=0;
	

	
	private int ques5_JAVA=0;
	
	private int ques5_PYTHON=0;
	
	
	private int ques5_C_l;
	
	private int ques5_JAVA_l;
	
	private int ques5_PYTHON_l;
	
	private int total;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getQues1_C() {
		return ques1_C;
	}
	public void setQues1_C(int ques1_C) {
		this.ques1_C = ques1_C;
	}

	public int getQues1_JAVA() {
		return ques1_JAVA;
	}
	public void setQues1_JAVA(int ques1_JAVA) {
		this.ques1_JAVA = ques1_JAVA;
	}
	public int getQues1_PYTHON() {
		return ques1_PYTHON;
	}
	public void setQues1_PYTHON(int ques1_PYTHON) {
		this.ques1_PYTHON = ques1_PYTHON;
	}
	public int getQues1_C_l() {
		return ques1_C_l;
	}
	public void setQues1_C_l(int ques1_C_l) {
		this.ques1_C_l = ques1_C_l;
	}
	
	public int getQues1_JAVA_l() {
		return ques1_JAVA_l;
	}
	public void setQues1_JAVA_l(int ques1_JAVA_l) {
		this.ques1_JAVA_l = ques1_JAVA_l;
	}
	public int getQues1_PYTHON_l() {
		return ques1_PYTHON_l;
	}
	public void setQues1_PYTHON_l(int ques1_PYTHON_l) {
		this.ques1_PYTHON_l = ques1_PYTHON_l;
	}
	public int getQues2_C() {
		return ques2_C;
	}
	public void setQues2_C(int ques2_C) {
		this.ques2_C = ques2_C;
	}
	
	public int getQues2_JAVA() {
		return ques2_JAVA;
	}
	public void setQues2_JAVA(int ques2_JAVA) {
		this.ques2_JAVA = ques2_JAVA;
	}
	public int getQues2_PYTHON() {
		return ques2_PYTHON;
	}
	public void setQues2_PYTHON(int ques2_PYTHON) {
		this.ques2_PYTHON = ques2_PYTHON;
	}
	public int getQues2_C_l() {
		return ques2_C_l;
	}
	public void setQues2_C_l(int ques2_C_l) {
		this.ques2_C_l = ques2_C_l;
	}

	public int getQues2_JAVA_l() {
		return ques2_JAVA_l;
	}
	public void setQues2_JAVA_l(int ques2_JAVA_l) {
		this.ques2_JAVA_l = ques2_JAVA_l;
	}
	public int getQues2_PYTHON_l() {
		return ques2_PYTHON_l;
	}
	public void setQues2_PYTHON_l(int ques2_PYTHON_l) {
		this.ques2_PYTHON_l = ques2_PYTHON_l;
	}
	public int getQues3_C() {
		return ques3_C;
	}
	public void setQues3_C(int ques3_C) {
		this.ques3_C = ques3_C;
	}
	
	public int getQues3_JAVA() {
		return ques3_JAVA;
	}
	public void setQues3_JAVA(int ques3_JAVA) {
		this.ques3_JAVA = ques3_JAVA;
	}
	public int getQues3_PYTHON() {
		return ques3_PYTHON;
	}
	public void setQues3_PYTHON(int ques3_PYTHON) {
		this.ques3_PYTHON = ques3_PYTHON;
	}
	public int getQues3_C_l() {
		return ques3_C_l;
	}
	public void setQues3_C_l(int ques3_C_l) {
		this.ques3_C_l = ques3_C_l;
	}
	
	public int getQues3_JAVA_l() {
		return ques3_JAVA_l;
	}
	public void setQues3_JAVA_l(int ques3_JAVA_l) {
		this.ques3_JAVA_l = ques3_JAVA_l;
	}
	public int getQues3_PYTHON_l() {
		return ques3_PYTHON_l;
	}
	public void setQues3_PYTHON_l(int ques3_PYTHON_l) {
		this.ques3_PYTHON_l = ques3_PYTHON_l;
	}
	public int getQues4_C() {
		return ques4_C;
	}
	public void setQues4_C(int ques4_C) {
		this.ques4_C = ques4_C;
	}
	
	public int getQues4_JAVA() {
		return ques4_JAVA;
	}
	public void setQues4_JAVA(int ques4_JAVA) {
		this.ques4_JAVA = ques4_JAVA;
	}
	public int getQues4_PYTHON() {
		return ques4_PYTHON;
	}
	public void setQues4_PYTHON(int ques4_PYTHON) {
		this.ques4_PYTHON = ques4_PYTHON;
	}
	public int getQues4_C_l() {
		return ques4_C_l;
	}
	public void setQues4_C_l(int ques4_C_l) {
		this.ques4_C_l = ques4_C_l;
	}
	
	public int getQues4_JAVA_l() {
		return ques4_JAVA_l;
	}
	public void setQues4_JAVA_l(int ques4_JAVA_l) {
		this.ques4_JAVA_l = ques4_JAVA_l;
	}
	public int getQues4_PYTHON_l() {
		return ques4_PYTHON_l;
	}
	public void setQues4_PYTHON_l(int ques4_PYTHON_l) {
		this.ques4_PYTHON_l = ques4_PYTHON_l;
	}
	public int getQues5_C() {
		return ques5_C;
	}
	public void setQues5_C(int ques5_C) {
		this.ques5_C = ques5_C;
	}
	
	public int getQues5_JAVA() {
		return ques5_JAVA;
	}
	public void setQues5_JAVA(int ques5_JAVA) {
		this.ques5_JAVA = ques5_JAVA;
	}
	public int getQues5_PYTHON() {
		return ques5_PYTHON;
	}
	public void setQues5_PYTHON(int ques5_PYTHON) {
		this.ques5_PYTHON = ques5_PYTHON;
	}
	public int getQues5_C_l() {
		return ques5_C_l;
	}
	public void setQues5_C_l(int ques5_C_l) {
		this.ques5_C_l = ques5_C_l;
	}
	
	public int getQues5_JAVA_l() {
		return ques5_JAVA_l;
	}
	public void setQues5_JAVA_l(int ques5_JAVA_l) {
		this.ques5_JAVA_l = ques5_JAVA_l;
	}
	public int getQues5_PYTHON_l() {
		return ques5_PYTHON_l;
	}
	public void setQues5_PYTHON_l(int ques5_PYTHON_l) {
		this.ques5_PYTHON_l = ques5_PYTHON_l;
	}
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(String email, String team) {
		super();
		this.email = email;
		this.team = team;
		
		this.ques1_C = 0;
		this.ques1_JAVA =0;
		this.ques1_PYTHON = 0;
		
		this.ques1_C_l = 1000000;
		this.ques1_JAVA_l = 1000000;
		this.ques1_PYTHON_l = 1000000;
		
		this.ques2_C = 0;
		this.ques2_JAVA =0;
		this.ques2_PYTHON =0;
		
		this.ques2_C_l = 1000000;
		this.ques2_JAVA_l = 1000000;
		this.ques2_PYTHON_l = 1000000;
		
		this.ques3_C = 0;
		this.ques3_JAVA = 0;
		this.ques3_PYTHON =0;
		
		this.ques3_C_l = 1000000;
		this.ques3_JAVA_l = 1000000;
		this.ques3_PYTHON_l = 1000000;
		
		this.ques4_C = 0;
		this.ques4_JAVA = 0;
		this.ques4_PYTHON =0;
		
		this.ques4_C_l = 1000000;
		this.ques4_JAVA_l = 1000000;
		this.ques4_PYTHON_l = 1000000;
		
		this.ques5_C = 0;
		this.ques5_JAVA = 0;
		this.ques5_PYTHON = 0;
		
		this.ques5_C_l = 1000000;
		this.ques5_JAVA_l = 1000000;
		this.ques5_PYTHON_l = 1000000;
		
		this.total=0;
	}
	
	
}
