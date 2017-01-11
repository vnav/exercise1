package app.model;

import java.util.Date;

public class BenchmarkReturn {
	private String benchmarkCode;
	private Date date;	
	private double returnValue;

	/**
	 * @return the benchmarkCode
	 */
	public String getBenchmarkCode() {
		return benchmarkCode;
	}

	/**
	 * @param benchmarkCode the benchmarkCode to set
	 */
	public void setBenchmarkCode(String benchmarkCode) {
		this.benchmarkCode = benchmarkCode;
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
