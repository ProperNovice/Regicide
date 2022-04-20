package utils;

import javafx.application.Platform;

public enum Lock {

	INSTANCE;

	private Object lockObject = new Object();
	private Runnable runnable = null;

	private Lock() {

	}

	public void lock() {
		lock(null);
	}

	public void lock(Runnable runnable) {

		this.runnable = runnable;
		Logger.INSTANCE.log("lock");
		Platform.enterNestedEventLoop(this.lockObject);

	}

	public void unlock() {

		Logger.INSTANCE.logNewLine("unlock");
		Platform.exitNestedEventLoop(this.lockObject, null);

		if (this.runnable == null)
			return;

		Logger.INSTANCE.logNewLine("executing runnable");

		this.runnable.run();

	}

}
