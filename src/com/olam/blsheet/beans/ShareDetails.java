package com.olam.blsheet.beans;

import java.io.Serializable;

public class ShareDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -705436540641819218L;
	String name;
	String description;
	float amount;
	float perHead;
	String selectedUsers;
	int userId;
	String date;
	int spendId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getPerHead() {
		return perHead;
	}
	public void setPerHead(float perHead) {
		this.perHead = perHead;
	}
	public String getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(String selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSpendId() {
		return spendId;
	}
	public void setSpendId(int spendId) {
		this.spendId = spendId;
	}

}
