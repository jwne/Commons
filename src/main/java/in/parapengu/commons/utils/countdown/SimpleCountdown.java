package in.parapengu.commons.utils.countdown;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * A simple countdown class used for creating countdowns.
 * The message for this countdown is shown if any of the conditions supplied by the constructor are met.
 */
public abstract class SimpleCountdown extends Countdown {

	private String[] conditions;

	/**
	 * Creates a new countdown.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param replace The string to replace with the time
	 * @param duration The duration of the countdown
	 * @param async If the countdown should be run asynchronously or not
	 * @param conditions A var args list of conditions required to show the message, defaults to {@code {"% 10", "<= 5"}} if none are supplied
	 */
	public SimpleCountdown(JavaPlugin plugin, String message, String replace, final int duration, boolean async, String... conditions) {
		super(plugin, message, replace, duration, async);

		if(conditions == null || conditions.length < 0) {
			conditions = new String[]{"% 10", "<= 5"};
		}

		this.conditions = conditions;
	}

	/**
	 * Creates a new countdown.
	 * By default, {@code {TIMING}} is replaced by the remaining time.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param duration The duration of the countdown
	 * @param async If the countdown should be run asynchronously or not
	 * @param conditions A var args list of conditions required to show the message, defaults to {@code {"% 10", "<= 5"}} if none are supplied
	 */
	public SimpleCountdown(JavaPlugin plugin, String message, int duration, boolean async, String... conditions) {
		this(plugin, message, "{TIMING}", duration, async, conditions);
	}

	/**
	 * Creates a new countdown.
	 * By default, the countdown will be run asynchronously.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param replace The string to replace with the time
	 * @param duration The duration of the countdown
	 * @param conditions A var args list of conditions required to show the message, defaults to {@code {"% 10", "<= 5"}} if none are supplied
	 */
	public SimpleCountdown(JavaPlugin plugin, String message, String replace, int duration, String... conditions) {
		this(plugin, message, replace, duration, true, conditions);
	}

	/**
	 * Creates a new countdown.
	 * By default, the countdown will be run asynchronously and {@code {TIMING}} is replaced by the remaining time.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param duration The duration of the countdown
	 * @param conditions A var args list of conditions required to show the message, defaults to {@code {"% 10", "<= 5"}} if none are supplied
	 */
	public SimpleCountdown(JavaPlugin plugin, String message, int duration, String... conditions) {
		this(plugin, message, duration, true, conditions);
	}

	@Override
	public boolean display() {
		for(String condition : conditions) {
			String[] split = condition.split(" ");
			String key = split[0];
			int value = Integer.parseInt(split[1]);
			if(matches(key, value)) {
				return true;
			}
		}

		return false;
	}

	public boolean matches(String key, int value) {
		int time = getRemainingTime();
		switch(key) {
			case ">":
				return time > value;
			case ">=":
				return time >= value;
			case "<":
				return time < value;
			case "<=":
				return time <= value;
			case "%":
				return time % value == 0;
		}

		return false;
	}

}
