package com.ems.practice.bean;

public class Fee {
	
	private int feeId;
	private double paidAmunt;
	private double balanceAmount;
	
	public Fee(){}

	public Fee(int feeId, double paidAmunt, double balanceAmount) {
		super();
		this.feeId = feeId;
		this.paidAmunt = paidAmunt;
		this.balanceAmount = balanceAmount;
	}

	public int getFeeId() {
		return feeId;
	}

	public double getPaidAmunt() {
		return paidAmunt;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public void setPaidAmunt(double paidAmunt) {
		this.paidAmunt = paidAmunt;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	@Override
	public String toString(){
		return feeId + "\t" + paidAmunt + "\t" +  balanceAmount;
	}

}
