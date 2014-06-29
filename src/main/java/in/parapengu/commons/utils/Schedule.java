package in.parapengu.commons.utils;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * A Schedule class for handling (a)synchronous tasks.
 */
public class Schedule {

	/**
	 * The {@link org.bukkit.plugin.java.JavaPlugin} which will act as the owner of the task
	 */
	protected JavaPlugin plugin;
	
	/**
	 * The {@link org.bukkit.scheduler.BukkitTask} associated with this {@link in.parapengu.commons.utils.Schedule}.
	 */
	protected BukkitTask task;

	/**
	 * If the {@link in.parapengu.commons.utils.Schedule} is currently running or not.
	 */
	protected boolean running;

	/**
	 * The {@link Runnable} used to show the {@link in.parapengu.commons.utils.Schedule} is no longer running.
	 */
	private Runnable stopRunnable = new Runnable() {

		@Override
		public void run() {
			running = false;
		}

	};

	/**
	 * The {@link Runnable} associated with this {@link in.parapengu.commons.utils.Schedule}.
	 */
	protected Runnable run;

	/**
	 * If the {@link in.parapengu.commons.utils.Schedule} should be run asynchronously or not.
	 */
	protected boolean async;

	/**
	 * Creates a new {@link in.parapengu.commons.utils.Schedule} using the specified Runnable.
	 *
	 * @param run The {@link Runnable} to be used.
	 * @param async If the Schedule should be run asynchronously or not.
	 */
	public Schedule(JavaPlugin plugin, Runnable run, boolean async) {
		this.plugin = plugin;
		this.run = run;
		this.running = false;
		this.async = async;
	}

	/**
	 * Get is the {@link in.parapengu.commons.utils.Schedule} is running or not.
	 *
	 * @return true if the task is running, false if it isn't (or doesn't exist).
	 */
	public boolean isRunning() {
		return running || task != null;
	}

	/**
	 * Get the {@link Runnable} which will be used by the {@link in.parapengu.commons.utils.Schedule}.
	 *
	 * @return The {@link Runnable} which will be used.
	 */
	public Runnable getRunnable() {
		return run;
	}

	/**
	 * Runs the task after a specified delay.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param delay The amount of time to wait before starting the task in {@link Duration}.TICKS.
	 * @return If the task could be started or not.
	 */
	public boolean delay(long delay) {
		return delay(Duration.TICKS, delay);
	}

	/**
	 * Runs the task after a specified delay.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param duration The duration enum to multiply the delay by.
	 * @param delay The amount of time to wait before starting the task, multiplied by {@link Duration}.getTicks().
	 * @return If the task could be started or not.
	 */
	public boolean delay(Duration duration, long delay) {
		if(running) {
			return false;
		}

		if(async) {
			this.task = plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, run, delay);
		} else {
			this.task = plugin.getServer().getScheduler().runTaskLater(plugin, run, delay);
		}

		this.running = true;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, stopRunnable, delay);
		return true;
	}

	/**
	 * Creates a repeating task to run between a period.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param period The amount of time to wait between occurrences in {@link Duration}.TICKS.
	 * @return If the task could be started or not.
	 */
	public boolean repeat(long period) {
		return repeat(Duration.TICKS, 0, period);
	}

	/**
	 * Creates a repeating task to run between a period.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param duration The duration enum to multiply the delay by.
	 * @param period The amount of time to wait between occurrences, multiplied by {@link Duration}.getTicks().
	 * @return If the task could be started or not.
	 */
	public boolean repeat(Duration duration, long period) {
		return repeat(0, duration.getTicks(period));
	}

	/**
	 * Creates a repeating task to run between a period.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param delay The amount of time to wait before starting the task in {@link Duration}.TICKS.
	 * @param period The amount of time to wait between occurrences in {@link Duration}.TICKS.
	 * @return If the task could be started or not.
	 */
	public boolean repeat(long delay, long period) {
		return repeat(Duration.TICKS, delay, period, -1);
	}

	/**
	 * Creates a repeating task to run between a period.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param duration The duration enum to multiply the delay by.
	 * @param delay The amount of time to wait before starting the task, multiplied by {@link Duration}.getTicks().
	 * @param period The amount of time to wait between occurrences, multiplied by {@link Duration}.getTicks().
	 * @return If the task could be started or not.
	 */
	public boolean repeat(Duration duration, long delay, long period) {
		return repeat(duration.getTicks(delay), duration.getTicks(period), -1);
	}

	/**
	 * Creates a repeating task to run between a period.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param delay The amount of time to wait before starting the task in {@link Duration}.TICKS.
	 * @param period The amount of time to wait between occurrences in {@link Duration}.TICKS.
	 * @param cancel The amount of time to wait before cancelling the task in {@link Duration}.TICKS.
	 * @return If the task could be started or not.
	 */
	public boolean repeat(long delay, long period, long cancel) {
		return repeat(Duration.TICKS, delay, period, cancel);
	}

	/**
	 * Creates a repeating task to run between a period.
	 * The task will not start if this {@link in.parapengu.commons.utils.Schedule} is already running.
	 *
	 * @param duration The duration enum to multiply the delay by.
	 * @param delay The amount of time to wait before starting the task, multiplied by {@link Duration}.getTicks().
	 * @param period The amount of time to wait between occurrences, multiplied by {@link Duration}.getTicks().
	 * @param cancel The amount of time to wait before cancelling the task, multiplied by {@link Duration}.getTicks().
	 * @return If the task could be started or not.
	 */
	public boolean repeat(Duration duration, long delay, long period, long cancel) {
		if(running) {
			return false;
		}

		if(async) {
			this.task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, run, duration.getTicks(delay), duration.getTicks(period));
		} else {
			this.task = plugin.getServer().getScheduler().runTaskTimer(plugin, run, duration.getTicks(delay), duration.getTicks(period));
		}

		this.running = true;

		if(cancel > 0) {
			plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
				@Override
				public void run() {
					stop();
				}
			}, duration.getTicks(delay));
		}

		return true;
	}

	/**
	 * Stops the currently running task.
	 *
	 * @return If there was a task to stop.
	 */
	public boolean stop() {
		if(!running) {
			return false;
		}

		plugin.getServer().getScheduler().cancelTask(this.task.getTaskId());
		this.running = false;
		this.task = null;
		return true;
	}

	/**
	 * Runs the task once.
	 */
	public void run() {
		if(async) {
			plugin.getServer().getScheduler().runTaskAsynchronously(plugin, run);
		} else {
			plugin.getServer().getScheduler().runTask(plugin, run);
		}
	}

}
