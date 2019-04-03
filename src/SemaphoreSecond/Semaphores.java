package SemaphoreSecond;

import java.util.concurrent.Semaphore;

public class Semaphores {
	private static Semaphores instance = null;
	private Semaphore semaphore = new Semaphore(5, true);
	// private AtomicInteger number = new AtomicInteger(1);
	private volatile int number = 0;

	private Semaphores() {
	}

	public static Semaphores getInstance() {
		if (instance == null) {
			instance = new Semaphores();
		}

		return instance;
	}

	public void work() {
		try {
			semaphore.acquire();
			System.out.println("Acquired permit."
					+ Thread.currentThread().getName());
		} catch (InterruptedException ex) {
		}

		try {
			doWork();
		} finally {
			semaphore.release();
			System.out.println("Released permit."
					+ Thread.currentThread().getName());
		}
	}

	private void doWork() {
		synchronized (this) {
			try {
				Thread.sleep(500);

				// System.out.println("Some important work: "
				// + number.incrementAndGet());

				System.out.println("Some important work: " + ++number);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
