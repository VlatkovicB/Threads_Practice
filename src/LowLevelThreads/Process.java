package LowLevelThreads;

import java.util.LinkedList;
import java.util.Queue;

public class Process {
	private Queue<String> list = new LinkedList<String>();
	private boolean cond = true;
	private Object lock = new Object();
	private final int LIMIT = 1;

	// private boolean isEmpty;

	public void read() throws InterruptedException {

		while (cond) {
			synchronized (lock) {
				Thread.sleep(1000);
				System.out.println("Reader - Waiting for a writer.");
				if (list.size() == 0) {
					lock.wait();
				}

				// while (!isEmpty) {
				System.out.println("Reader - " + "\"" + list.poll() + "\""
						+ " was read.");

				// System.out.println(list.peek());
				lock.notify();
				// if (list.isEmpty()) {
				// isEmpty = true;
				// }
				// }
			}
		}
	}

	public void write(String data) throws InterruptedException {
		int count = 0;
		while (cond) {
			synchronized (lock) {
				Thread.sleep(1000);

				if (list.size() == LIMIT) {
					lock.wait();
				}
				System.out.println("Writer - Writing Data : " + data + count);
				list.offer(data + count);

				System.out.println("Writer - Notifying reader.");
				// Thread.sleep(500);

				lock.notify();
			}
			count++;
		}
	}
}
