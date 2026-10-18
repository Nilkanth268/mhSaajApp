package com.jewellery.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Integer oId;
	private String custName;
	private String whatsappNo;
	private String altPhoneNo;
	private Double totalAmt;
	private String oCrId;
	private String orderStatus;

	// Holds the individual products for com_order_dtl
	private List<Product> itemList = new ArrayList<>();

	public Integer getOId() {
		return oId;
	}

	public void setOId(Integer oId) {
		this.oId = oId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getWhatsappNo() {
		return whatsappNo;
	}

	public void setWhatsappNo(String whatsappNo) {
		this.whatsappNo = whatsappNo;
	}

	public String getAltPhoneNo() {
		return altPhoneNo;
	}

	public void setAltPhoneNo(String altPhoneNo) {
		this.altPhoneNo = altPhoneNo;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getOCrId() {
		return oCrId;
	}

	public void setOCrId(String oCrId) {
		this.oCrId = oCrId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Product> getItemList() {
		return itemList;
	}

	public void setItemList(List<Product> itemList) {
		this.itemList = itemList;
	}
}