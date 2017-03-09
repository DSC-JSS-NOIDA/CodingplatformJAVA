package org.gdgjss.codingplatform.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(
		   name = "Questions", 
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"quesid"})}
		)
public class Questions {
	@Id 
	int quesid;
	 
	private String title;
	@Lob
	private String detail;
	@Lob
	private String constraints;
	@Lob
	private String inputformat;
	@Lob
	private String outputformat;
	@Lob
	private String sampletestcase;
	
	private String outputfilepath;
	private String inputfilepath;
	
	
	
	public int getQuesid() {
		return quesid;
	}
	public void setQuesid(int quesid) {
		this.quesid = quesid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	public String getInputformat() {
		return inputformat;
	}
	public void setInputformat(String inputformat) {
		this.inputformat = inputformat;
	}
	public String getOutputformat() {
		return outputformat;
	}
	public void setOutputformat(String outputformat) {
		this.outputformat = outputformat;
	}
	public String getSampletestcase() {
		return sampletestcase;
	}
	public void setSampletestcase(String sampletestcase) {
		this.sampletestcase = sampletestcase;
	}
	public String getOutputfilepath() {
		return outputfilepath;
	}
	public void setOutputfilepath(String outputfilepath) {
		this.outputfilepath = outputfilepath;
	}
	public String getInputfilepath() {
		return inputfilepath;
	}
	public void setInputfilepath(String inputfilepath) {
		this.inputfilepath = inputfilepath;
	}
	

	


}
