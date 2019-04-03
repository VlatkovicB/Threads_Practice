package ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Rentrant {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();

		if (lock.tryLock()) {
			try {

			} finally {
				lock.unlock();
			}
		}

	}
}
