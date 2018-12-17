package com.cmad.sandboxers.model;

/**
 * 
 * @author pkrishne
 *
 */
public class EventCounters {

	private int error_count;
	private int warning_count;
	private int notification_count;

	public int getError_count() {
		return error_count;
	}

	public void setError_count(int error_count) {
		this.error_count = error_count;
	}

	public int getWarning_count() {
		return warning_count;
	}

	public void setWarning_count(int warning_count) {
		this.warning_count = warning_count;
	}

	public int getNotification_count() {
		return notification_count;
	}

	public void setNotification_count(int notification_count) {
		this.notification_count = notification_count;
	}

	@Override
	public String toString() {
		return "EventCounters [error_count=" + error_count + ", warning_count=" + warning_count
				+ ", notification_count=" + notification_count + "]";
	}

}
