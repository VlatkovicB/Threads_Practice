package LowLevelThreadsFILE;

public class App {
	public static void main(String[] args) {
		ReadWrite rw = new ReadWrite();
		String data = "Some data";

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					rw.write(data);
				} catch (Exception e) {
				}
			}

		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					rw.read();
				} catch (Exception e) {
				}
			}
		});

		t1.start();
		t2.start();
	}
}
