package in.parapengu.commons.utils;

/**
 * Duration enum to differentiate between different times.
 */
public enum Duration {

	/**
	 * The duration in ticks.
	 */
	TICKS(),

	/**
	 * The duration in seconds.
	 * Multiplies the value by 20.
	 */
	SECONDS(20),

	/**
	 * The duration in minutes.
	 * Multiplies the value by 1,200. (60 * 20)
	 */
	MINUTES(60 * 20),

	/**
	 * The duration in seconds.
	 * Multiplies the value by 72,000. (60 * 60 * 20)
	 */
	HOURS(60 * 60 * 20),

	/**
	 * The duration in seconds.
	 * Multiplies the value by 1,728,000. (24 * 60 * 60 * 20)
	 */
	DAYS(24 * 60 * 60 * 20);

	private long multiplier;

	Duration(long multiplier) {
		this.multiplier = multiplier;
	}

	Duration() {
		this(1);
	}

	/**
	 * Gets the time duration as a {@link Long} in the form of ticks
	 * The "base enum format" refers to the time duration, for example SECONDS' would mean that 10 would return 200 (10 * 20) when converted to ticks
	 *
	 * @param value The duration of time in it's base enum format
	 * @return The duration of time in ticks
	 */
	public long getTicks(long value) {
		return value * multiplier;
	}

}
