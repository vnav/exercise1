package app.model;

import java.util.Date;

public class FundReturn {
	private String fundCode;	
	private Date date;	
	private double returnValue;

	/**
	 * @return the fundCode
	 */
	public String getFundCode() {
		return fundCode;
	}

	/**
	 * @param fundCode the fundCode to set
	 */
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the returnValue
	 */
	public double getReturnValue() {
		return returnValue;
	}

	/**
	 * @param returnValue the returnValue to set
	 */
	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}
}
