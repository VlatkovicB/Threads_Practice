package Semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphores {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2, true);

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 20; i++) {
			executor.submit(new Runnable() {
				public void run() {
					try {
						semaphore.acquire();
						Thread.sleep(500);
						System.out.println("Thread name : "
								+ Thread.currentThread().getName());

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaphore.release();
					}
				}
			});
		}

	}
}
