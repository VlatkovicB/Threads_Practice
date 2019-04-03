package LowLevelThreads;

public class App {
	public static void main(String[] args) {
		Process proc = new Process();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					// for (int i = 0; i < 5; i++) {
					proc.read();
					// }
				} catch (InterruptedException e) {
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					// for (int i = 0; i < 5; i++) {
					proc.write("Test ");
					// }
				} catch (InterruptedException e) {
				}
			}
		});

		t1.start();
		t2.start();
	}
}
