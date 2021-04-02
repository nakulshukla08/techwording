package com.techwording.general;

public class ThreadEvenOdd {

	static class NumberPrinter implements Runnable {

		Object shared;
		int limit;
		boolean even;
		int lastNumber = 0;

		public NumberPrinter(Object obj, int limit, boolean even) {
			this.shared = obj;
			this.limit = limit;
			this.even = even;
		}

		@Override
		public void run() {

			synchronized (shared) {
				int i = 0;
				try {
					while (limit / 2 > i) {
						i++;
						if (even) {
							lastNumber = lastNumber + 2;
							System.out.println("even thread : " + lastNumber);
						} else {
							if (lastNumber == 0) {
								lastNumber = 1;
							} else {
								lastNumber = lastNumber + 2;
							}
							System.out.println("Odd thread : " + lastNumber);
						}
						shared.notify();
						shared.wait();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	public static void main(String[] args) {

		Object obj = new Object();

		NumberPrinter printerOdd = new NumberPrinter(obj, 10, false);
		NumberPrinter printerEven = new NumberPrinter(obj, 10, true);
		
		Thread tE = new Thread(printerEven);
		Thread tO = new Thread(printerOdd);
		
		tO.start();
		tE.start();
	}

}
