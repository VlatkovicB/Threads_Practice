package com.ReentrantReadWriteLock;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		RRWL rrwl = new RRWL();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 100; i++) {
					int nextInt = random.nextInt(101);
					rrwl.add(nextInt);
					System.out.println("Added number: " + nextInt);
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread() + "Max number: "
							+ rrwl.findMax());
				}
			}
		});

		Thread t3 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread() + "Max number: "
							+ rrwl.findMax());
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
