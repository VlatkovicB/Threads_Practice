package ThirteenTwo;

public class SynchronizationBlockOfCode extends Thread {

	private StringBuffer sb;

	public SynchronizationBlockOfCode(StringBuffer sb) {
		this.sb = sb;
	}

	public void run() {
		synchronized (sb) {
			System.out.println(Thread.currentThread().getName());
			for (int i = 1; i <= 100; i++)
				System.out.print(sb);

			System.out.println();
			sb.setCharAt(0, (char) (sb.charAt(0) + 1));
		}
	}

	public static void main(String[] args) {
		StringBuffer sba = new StringBuffer("A");

		SynchronizationBlockOfCode t1 = new SynchronizationBlockOfCode(sba);
		SynchronizationBlockOfCode t2 = new SynchronizationBlockOfCode(sba);
		SynchronizationBlockOfCode t3 = new SynchronizationBlockOfCode(sba);

		t1.start();
		t2.start();
		t3.start();
	}
}
