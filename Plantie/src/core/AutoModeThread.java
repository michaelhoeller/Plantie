package core;

import java.util.concurrent.atomic.AtomicBoolean;

import gui.MainPage;
import utils.messenger.Notification;

public class AutoModeThread implements Runnable {

	private Thread worker;
	private int interval;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private final AtomicBoolean finished = new AtomicBoolean(false);

	MainPage mp;

	public void start() {
		worker = new Thread(this);
		running.set(true);
		finished.set(false);
		worker.start();
	}

	public void ControlSubThread(int sleepInterval) {
		interval = sleepInterval;
	}

	public void stop() {
		running.set(false);
	}

	public void run() {
		while (running.get()) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				new Notification("Thread was interrupted, Failed to complete operation");
			}
			// Thred code here:
			for (Integer i = 0; i < 10000000; i++) {
				System.out.println(i);
			}
			// End of thread code
			finished.set(true);
		}
	}

	public AtomicBoolean getFinished() {
		return finished;
	}

}
