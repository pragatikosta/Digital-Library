package com.geek.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Penalty {
	@Id
	@GeneratedValue
private int penaltyId;
private float amount;
private String remarks;
private int noOfDays;
public int getPenaltyId() {
	return penaltyId;
}
public void setPenaltyId(int penaltyId) {
	this.penaltyId = penaltyId;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public int getNoOfDays() {
	return noOfDays;
}
public void setNoOfDays(int noOfDays) {
	this.noOfDays = noOfDays;
}



}
