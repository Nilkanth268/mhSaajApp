package com.jewellery.model;
import java.security.Timestamp;

import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

	private Integer pId;
	private String pCode;
	private String pName;
	private String pCategory;
	private String pDescription;
	private Double pAmount;
	private Timestamp pCrDate;
	
	//FileName
	@Transient
	private MultipartFile imageFile;
	
	//DBField
	private String imageName;

	private Timestamp pUpDate;
	private Integer pCrId;

	public String getPCode() {
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

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

}
