package com.techwording.general;

public class Deadlock {

	public static void main(String[] args) throws InterruptedException {

		Object resource1 = new Object();
		Object resource2 = new Object();

		Action action1 = new Action(resource2, resource1);
		Thread t1 = new Thread(action1);

		Action action2 = new Action(resource1, resource2);
		Thread t2 = new Thread(action2);

		t1.start();
		t2.start();

	}

}

class Action implements Runnable {

	Object obj1;

	Object obj2;

	String state;

	public Action(Object obj1, Object obj2) {

		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	@Override
	public void run() {

		synchronized (obj2) {

			System.out.println("Action : run , Thread : " + Thread.currentThread()
				.getName());

			synchronized (obj1) {
				obj1.toString();
			}

		}

	}

}
