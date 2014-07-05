package in.parapengu.commons.utils.countdown;

import in.parapengu.commons.utils.OtherUtil;
import in.parapengu.commons.utils.Schedule;
import in.parapengu.commons.utils.Duration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * A countdown class used for creating countdowns
 */
public abstract class Countdown {

	private String replace;
	private String message;
	private Schedule schedule;

	private boolean stopped;
	private int duration;
	private int run_time;

	/**
	 * Creates a new countdown.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param replace The string to replace with the time
	 * @param duration The duration of the countdown
	 * @param async If the countdown should be run asynchronously or not
	 */
	public Countdown(JavaPlugin plugin, String message, String replace, int duration, boolean async) {
		this.replace = replace;
		this.message = message;
		this.duration = duration;

		this.stopped = true;
		this.run_time = 0;
		restart(plugin, async);
	}

	/**
	 * Creates a new countdown.
	 * By default, {@code {TIMING}} is replaced by the remaining time.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param duration The duration of the countdown
	 * @param async If the countdown should be run asynchronously or not
	 */
	public Countdown(JavaPlugin plugin, String message, int duration, boolean async) {
		this(plugin, message, "{TIMING}", duration, async);
	}

	/**
	 * Creates a new countdown.
	 * By default, the countdown will be run asynchronously.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param replace The string to replace with the time
	 * @param duration The duration of the countdown
	 */
	public Countdown(JavaPlugin plugin, String message, String replace, int duration) {
		this(plugin, message, replace, duration, true);
	}

	/**
	 * Creates a new countdown.
	 * By default, the countdown will be run asynchronously and {@code {TIMING}} is replaced by the remaining time.
	 *
	 * @param plugin The owner of the schedule
	 * @param message The message to be displayed on intervals specified by the display() method
	 * @param duration The duration of the countdown
	 */
	public Countdown(JavaPlugin plugin, String message, int duration) {
		this(plugin, message, duration, true);
	}

	private void restart(JavaPlugin plugin, boolean async) {
		this.schedule = new Schedule(plugin, new Runnable() {
			@Override
			public void run() {
				interval();
				if(stopped) {
					return;
				}

				if(run_time == duration) {
					complete();
					stop();
					return;
				}

				if(display()) {
					Bukkit.broadcastMessage(message());
				}

				run_time++;
			}
		}, async);
	}

	/**
	 * Starts the countdown.
	 */
	public void start() {
		stopped = false;
		schedule.repeat(Duration.SECONDS, 1);
	}

	/**
	 * Stops the countdown.
	 */
	public void stop() {
		stopped = true;
		schedule.stop();
	}

	/**
	 * Run once every second to see if the message should be displayed or not.
	 *
	 * @return If the message should be displayed
	 */
	public abstract boolean display();

	/**
	 * The method to be run once the countdown is complete.
	 */
	public abstract void complete();

	/**
	 * The method to be run every second in the countdown
	 */
	public void interval() { /* nothing */ }

	/**
	 * The message to be shown if display() is true.
	 * Replaces the replace key with the correct value.
	 *
	 * @return The message to be shown
	 */
	public String message() {
		int display = duration - run_time;

		System.out.println("display: " + display);
		List<String> timings = new ArrayList<>();
		if(display / 60 / 60 / 24 / 7 >= 1) {
			int weeks = (display - (display % (60 * 60 * 24 * 7))) / 60 / 60 / 24 / 7;
			String string = weeks + " week" + (weeks == 1 ? "" : "s");
			timings.add(string);
			display = display % (60 * 60 * 24 * 7);
		}

		if(display / 60 / 60 / 24 >= 1) {
			int days = (display - (display % (60 * 60 * 24))) / 60 / 60 / 24;
			String string = days + " day" + (days == 1 ? "" : "s");
			timings.add(string);
			display = display % (60 * 60 * 24);
		}

		if(display / 60 / 60 >= 1) {
			int hours = (display - (display % (60 * 60))) / 60 / 60;
			String string = hours + " hour" + (hours == 1 ? "" : "s");
			timings.add(string);
			display = display % (60 * 60);
		}

		if(display / 60 >= 1) {
			int minutes = (display - (display % 60)) / 60;
			String string = minutes + " minute" + (minutes == 1 ? "" : "s");
			timings.add(string);
			display = display % 60;
		}

		if(display > 0) {
			String string = display + " second" + (display == 1 ? "" : "s");
			timings.add(string);
		}

		return message.replace(replace, OtherUtil.listToEnglishCompound(timings));
	}

	/**
	 * Get the amount of seconds left until the time is complete
	 *
	 * @return The amount of seconds left until the time is complete
	 */
	public int getRemainingTime() {
		return duration - run_time;
	}

	public void setRunTime(int runTime) {
		this.run_time = run_time;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void restart() {
		stop();
		setRunTime(0);
		start();
	}

	public void restart(int duration) {
		stop();
		setRunTime(0);
		setDuration(duration);
		start();
	}

}
