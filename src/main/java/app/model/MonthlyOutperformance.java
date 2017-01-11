package app.model;

import java.util.Date;
import java.util.Objects;

/**
 * The Class MonthlyOutperformance.
 */
public class MonthlyOutperformance {
	private String fundName;
	private Date date;
	private double excess;
	private String outperformance;
	private double returnValue;
	private int rank;
	
	/**
	 * @return the fundName
	 */
	public String getFundName() {
		return fundName;
	}
	/**
	 * @param fundName the fundName to set
	 */
	public void setFundName(String fundName) {
		this.fundName = fundName;
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
	 * @return the excess
	 */
	public double getExcess() {
		return excess;
	}
	/**
	 * @param excess the excess to set
	 */
	public void setExcess(double excess) {
		this.excess = excess;
	}
	/**
	 * @return the outperformance
	 */
	public String getOutperformance() {
		return outperformance;
	}
	/**
	 * @param outperformance the outperformance to set
	 */
	public void setOutperformance(String outperformance) {
		this.outperformance = outperformance;
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
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) { 
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof MonthlyOutperformance)) {
            return false;
        }
         
        MonthlyOutperformance mo = (MonthlyOutperformance) o;

        return  Objects.equals(fundName, mo.fundName) &&
                Objects.equals(date, mo.date) &&
        		excess == mo.excess &&
        		Objects.equals(outperformance, mo.outperformance) &&
        		returnValue == mo.returnValue &&
        		rank == mo.rank;        
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(fundName, date, excess, outperformance, returnValue, rank);
    }
    
}