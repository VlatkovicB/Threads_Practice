package com.ReentrantReadWriteLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RRWL {
	private List<Integer> integers = new ArrayList<>();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void add(Integer number) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// System.out.println("locking");
		lock.writeLock().lock();
		// System.out.println("entered");
		try {

			integers.add(number);
		} finally {
			// System.out.println("unlocking");
			lock.writeLock().unlock();
		}
	}

	public int findMax() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread() + "locking");
		lock.readLock().lock();
		System.out.println(Thread.currentThread() + "entered");
		try {
			if (integers.size() != 0)
				return Collections.max(integers);
		} finally {
			System.out.println(Thread.currentThread() + "unlocking");
			lock.readLock().unlock();
		}
		return 0;
	}
}
