package com.BlockingQueue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReadingThread implements Runnable {

	private File file = new File("input.txt");
	private BlockingQueue<String> queue = null;

	public ReadingThread(BlockingQueue<String> queue) {
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
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			System.out.println("BEFORE READING" + queue);
			String read = null;

			while ((read = reader.readLine()) != null) {

				queue.put(read);

				System.out.println("WHILE ADDING" + queue);

			}
			queue.put("End.");

			System.out.println("END OF READING" + queue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
