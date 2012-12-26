package com.olam.blsheet.beans;

import java.io.Serializable;
import java.util.List;

public class SummaryDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1140774610978499156L;

	String userId;
	String startDate;
	String endDate;
	float amountSpend;
	float selfExpense;
	float creditOrDebit;
	float otherExpense;
	List<SummaryDetails> summarDetailsList;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public float getAmountSpend() {
		return amountSpend;
	}
	public void setAmountSpend(float amountSpend) {
		this.amountSpend = amountSpend;
	}
	public float getSelfExpense() {
		return selfExpense;
	}
	public void setSelfExpense(float selfExpense) {
		this.selfExpense = selfExpense;
	}
	public float getCreditOrDebit() {
		return creditOrDebit;
	}
	public void setCreditOrDebit(float creditOrDebit) {
		this.creditOrDebit = creditOrDebit;
	}
	public List<SummaryDetails> getSummarDetailsList() {
		return summarDetailsList;
	}
	public void setSummarDetailsList(List<SummaryDetails> summarDetailsList) {
		this.summarDetailsList = summarDetailsList;
	}
	public float getOtherExpense() {
		return otherExpense;
	}
	public void setOtherExpense(float otherExpense) {
		this.otherExpense = otherExpense;
	}
}
