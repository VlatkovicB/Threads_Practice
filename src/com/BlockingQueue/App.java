package com.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	public static void main(String[] args) {
		// LOW LEVEL synchronized TODO
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

		ReadingThread reader = new ReadingThread(queue);
		WritingThread writer = new WritingThread(queue);

		Thread t1 = new Thread(reader);
		Thread t2 = new Thread(writer);

		t1.start();
		t2.start();

		System.out.println("IN APP" + queue);

	}
}
