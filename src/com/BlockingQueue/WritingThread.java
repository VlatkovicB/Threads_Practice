package com.BlockingQueue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class WritingThread implements Runnable {
	private File file = new File("output.txt");
	private BlockingQueue<String> queue = null;

	public WritingThread(BlockingQueue<String> queue) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.queue = queue;
	}

	public void run() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,
				true))) {
			// Thread.currentThread().sleep(1000);

			System.out.println("BEFORE WRITING" + queue);
			while (true) {
				String next = queue.take();
				if (next.equals("End.")) {
					break;
				}
				writer.newLine();
				writer.write(next);
			}
			writer.flush();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
