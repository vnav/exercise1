package app.model;

public class Fund {
	private String fundCode;
	private String name;	
	private String benchmarkCode;

	/**
	 * @return the code
	 */
	public String getFundCode() {
		return fundCode;
	}

	/**
	 * @param code the code to set
	 */
	public void setFundCode(String code) {
		this.fundCode = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

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
}
