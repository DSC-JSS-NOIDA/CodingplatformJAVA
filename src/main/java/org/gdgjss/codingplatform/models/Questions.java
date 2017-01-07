package org.gdgjss.codingplatform.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Questions {
	@Id @GeneratedValue
	int quesid;
	public int getQuesid() {
		return quesid;
	
	}
	private String title;
	@Lob
	private String detail;
	@Lob
	private String constraints;
	private String inputformat;
	@Lob
	private String sampletestcase;
	@Lob
	private String inputtestcase;
	@Lob
	private String outputtestcase;
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
	public String getSampletestcase() {
		return sampletestcase;
	}
	public void setSampletestcase(String sampletestcase) {
		this.sampletestcase = sampletestcase;
	}
	public String getInputtestcase() {
		return inputtestcase;
	}
	public void setInputtestcase(String inputtestcase) {
		this.inputtestcase = inputtestcase;
	}
	public String getOutputtestcase() {
		return outputtestcase;
	}
	public void setOutputtestcase(String outputtestcase) {
		this.outputtestcase = outputtestcase;
	}
	
	
	
	
}
