package com.ArrayListRunnable;

import java.util.ArrayList;
import java.util.List;

public class ArrayListRunnable implements Runnable {
	// shared by all threads
	private List<Integer> list = new ArrayList<>();

	public ArrayListRunnable() {
		// add some elements
		for (int i = 0; i < 100000; i++) {
			list.add(i);
		}
	}

	// might run concurrently, you cannot be sure
	// to be safe you must assume it does
	public void run() {
		String tName = Thread.currentThread().getName();
		while (!list.isEmpty()) {
			System.out.println(tName + " removed " + list.remove(0));
		}
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public static void main(String[] args) {
		ArrayListRunnable alr = new ArrayListRunnable();
		Thread t1 = new Thread(alr);
		Thread t2 = new Thread(alr); // shared Runnable
		t1.start();
		t2.start();
		// try {
		// t1.join();
		// t2.join();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(getList().size());
	}
}
