package LowLevelThreadsFILE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

enum Access {
	ReadyToRead, ReadyToWrite
}

public class ReadWrite {
	private File file = new File("read_write.txt");
	private Object lock = new Object();
	private boolean condition = true;
	private Access access = Access.ReadyToWrite;

	public ReadWrite() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
	}

	public void write(String data) throws Exception {
		int counter = 1;
		while (condition) {
			synchronized (lock) {
				Thread.sleep(500);
				if (access != Access.ReadyToWrite) {
					lock.wait();
				}
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(
						file))) {
					writer.write(data + " " + counter + ".");
					writer.flush();
				}

				access = Access.ReadyToRead;
				counter++;

				if (counter > 5) {
					condition = false;
				}
				lock.notify();

			}

		}
	}

	public void read() throws Exception {
		while (condition) {
			synchronized (lock) {
				Thread.sleep(500);

				if (access != Access.ReadyToRead) {
					lock.wait();
				}

				try (BufferedReader reader = new BufferedReader(new FileReader(
						file))) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						System.out.println("Read from file: " + line);
					}
				}

				access = Access.ReadyToWrite;
				lock.notify();
			}
		}
	}

}
