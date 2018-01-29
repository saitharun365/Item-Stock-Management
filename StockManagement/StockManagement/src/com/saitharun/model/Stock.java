package com.saitharun.model;

import java.util.Date;

public class Stock {

	private int itemCode;
	private String itemName;
	private String unit;
	private int beginningInventory;
	private int qunatityOnHand;
	private double pricePerUnit;
	private Date dateOfManufacture;
	private Date dateOfExpiry;
	private String location;
	private String itemCategory;
	private String itemSubCategory;
	
	

	public Stock(String itemName, String unit, int beginningInventory, int qunatityOnHand, double pricePerUnit,
			Date dateOfManufacture, Date dateOfExpiry, String location, String itemCategory, String itemSubCategory) {
		this.itemName = itemName;
		this.unit = unit;
		this.beginningInventory = beginningInventory;
		this.qunatityOnHand = qunatityOnHand;
		this.pricePerUnit = pricePerUnit;
		this.dateOfManufacture = dateOfManufacture;
		this.dateOfExpiry = dateOfExpiry;
		this.location = location;
		this.itemCategory = itemCategory;
		this.itemSubCategory = itemSubCategory;
	}





public Stock(int itemCode, String itemName, String unit, int beginningInventory, int qunatityOnHand,
			double pricePerUnit, Date dateOfManufacture, Date dateOfExpiry, String location, String itemCategory,
			String itemSubCategory) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.unit = unit;
		this.beginningInventory = beginningInventory;
		this.qunatityOnHand = qunatityOnHand;
		this.pricePerUnit = pricePerUnit;
		this.dateOfManufacture = dateOfManufacture;
		this.dateOfExpiry = dateOfExpiry;
		this.location = location;
		this.itemCategory = itemCategory;
		this.itemSubCategory = itemSubCategory;
	}

public int getItemCode() {
	return itemCode;
}

public void setItemCode(int itemCode) {
	this.itemCode = itemCode;
}


public String getItemName() {
	return itemName;
}


public void setItemName(String itemName) {
	this.itemName = itemName;
}



public String getUnit() {
	return unit;
}



public void setUnit(String unit) {
	this.unit = unit;
}



public int getBeginningInventory() {
	return beginningInventory;
}


public void setBeginningInventory(int beginningInventory) {
	this.beginningInventory = beginningInventory;
}


public int getQunatityOnHand() {
	return qunatityOnHand;
}


public void setQunatityOnHand(int qunatityOnHand) {
	this.qunatityOnHand = qunatityOnHand;
}


public double getPricePerUnit() {
	return pricePerUnit;
}


public void setPricePerUnit(double pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
}


public Date getDateOfManufacture() {
	return dateOfManufacture;
}


public void setDateOfManufacture(Date dateOfManufacture) {
	this.dateOfManufacture = dateOfManufacture;
}


public Date getDateOfExpiry() {
	return dateOfExpiry;
}


public void setDateOfExpiry(Date dateOfExpiry) {
	this.dateOfExpiry = dateOfExpiry;
}


public String getLocation() {
	return location;
}


public void setLocation(String location) {
	this.location = location;
}


public String getItemCategory() {
	return itemCategory;
}


public void setItemCategory(String itemCategory) {
	this.itemCategory = itemCategory;
}


public String getItemSubCategory() {
	return itemSubCategory;
}


public void setItemSubCategory(String itemSubCategory) {
	this.itemSubCategory = itemSubCategory;
}


@Override
public String toString() {
	return "Stock [itemCode=" + itemCode + ", itemName=" + itemName + ", beginningInventory=" + beginningInventory
			+ ", qunatityOnHand=" + qunatityOnHand + ", pricePerUnit=" + pricePerUnit + ", dateOfManufacture="
			+ dateOfManufacture + ", dateOfExpiry=" + dateOfExpiry + ", location=" + location + ", itemCategory="
			+ itemCategory + ", itemSubCategory=" + itemSubCategory + "]";
}




}
