package app.util.enums;

/**
 * The Enum OutPerformanceEnum.
 */
public enum PerformanceMessageEnum {
	
	/** The out perform. */
	OUT_PERFORM("outPerformed"),
	
	/** The under perform. */
	UNDER_PERFORM("underPerformed"),
	
	/** The equal. */
	EQUAL("equal");
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new out performance enum.
	 *
	 * @param message the message
	 */
	private PerformanceMessageEnum(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
