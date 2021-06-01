package com.sprint.uedfeid.model;

public class ExcelData {
	private String locationId;
	private String manfName;
	private String networkType;
	private String lockcode1;
	private String lockcode2;
	private String productType;
	private String imeiDec;
	private String csnOrEid;
	private String softwareNo;
	private String modelNo;
	private String modelName;
	
	public ExcelData() {
		locationId = "";
		manfName = "";
		networkType = "";
		lockcode1 = "";
		lockcode2 = "";
		productType = "";
		imeiDec = "";
		csnOrEid = "";
		softwareNo = "12345";
		modelNo = "";
		modelName = "";
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getManfName() {
		return manfName;
	}

	public void setManfName(String manfName) {
		this.manfName = manfName;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getLockcode1() {
		return lockcode1;
	}

	public void setLockcode1(String lockcode1) {
		this.lockcode1 = lockcode1;
	}

	public String getLockcode2() {
		return lockcode2;
	}

	public void setLockcode2(String lockcode2) {
		this.lockcode2 = lockcode2;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getImeiDec() {
		return imeiDec;
	}

	public void setImeiDec(String imeiDec) {
		this.imeiDec = imeiDec;
	}

	public String getCsnOrEid() {
		return csnOrEid;
	}

	public void setCsnOrEid(String csnOrEid) {
		this.csnOrEid = csnOrEid;
	}

	public String getSoftwareNo() {
		return softwareNo;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
