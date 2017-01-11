package app.model;

public class Benchmark {
	private String benchmarkCode;	
	private String name;

	/**
	 * @return the code
	 */
	public String getBenchmarkCode() {
		return benchmarkCode;
	}

	/**
	 * @param code the code to set
	 */
	public void setBenchmarkCode(String code) {
		this.benchmarkCode = code;
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
}
