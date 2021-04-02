package com.techwording.general;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumer {

	private static final int SIZE = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Producer {

		private Queue<Integer> sharedQueue;

		public Producer(Queue sharedQueue) {

			super();
			this.sharedQueue = sharedQueue;
		}

		public Queue getSharedQueue() {

			return sharedQueue;
		}

		public void setSharedQueue(Queue sharedQueue) {

			this.sharedQueue = sharedQueue;
		}

		public void produce() {

			while (sharedQueue.size() < SIZE) {
				sharedQueue.add(ThreadLocalRandom.current()
					.nextInt());
			}
		}

	}

	class Consumer {
		private Queue sharedQueue;

		public Consumer(Queue sharedQueue) {

			super();
			this.sharedQueue = sharedQueue;
		}

		public Queue getSharedQueue() {

			return sharedQueue;
		}

		public void setSharedQueue(Queue sharedQueue) {

			this.sharedQueue = sharedQueue;
		}

	}

}
