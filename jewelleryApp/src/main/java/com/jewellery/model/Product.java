package com.jewellery.model;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
	
	private Integer pId;
	private String pCode;
	private String pName;
	private String pCategory;
	private String pDescription;
	private Double pAmount;
	private Timestamp pCrDate;
	
	private Timestamp pUpDate;
	private Integer pCrId;
	
	
	public String getPCode(){
		return pCode;
	}
	
	public void setPCode(String pCode) {
		this.pCode = pCode;
	}
	
	public String getPName() {
		return pName;
	}
	
	public void setPName(String pName) {
		this.pName = pName;
	}
	
	public String getPCategory() {
		return pCategory;
	}
	
	public void setPCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	
	
	public String getPDescription() {
	    return pDescription;
	}

	public void setPDescription(String pDescription) {
	    this.pDescription = pDescription;
	}
	
	public Double getPAmount() {
		return pAmount;
	}
	
	public void setPAmount(Double pAmount) {
		this.pAmount = pAmount;
	}
	

	public Timestamp getpCrDate() {
		return pCrDate;
	}

	public void setpCrDate(Timestamp pCrDate) {
		this.pCrDate = pCrDate;
	}

	public Timestamp getpUpDate() {
		return pUpDate;
	}

	public void setpUpDate(Timestamp pUpDate) {
		this.pUpDate = pUpDate;
	}

	public Integer getpCrId() {
		return pCrId;
	}

	public void setpCrId(Integer pCrId) {
		this.pCrId = pCrId;
	}


}
