package com.techwording.general;

import java.util.ArrayList;
import java.util.List;

public class GenericsPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GenericsPractice practice = new GenericsPractice();

		List<Flyable> list = new ArrayList<GenericsPractice.Flyable>();

		Bird bird = practice.new Bird();
		Aeroplane plane = practice.new Aeroplane();

		list.add(bird);
		list.add(plane);

		practice.executeFly(list);

	}

	private <T> T executeFly(List<? extends Flyable> flaybleList) {

		T t = null;
		if (flaybleList != null && !flaybleList.isEmpty()) {

			for (Flyable obj : flaybleList) {
				obj.fly();
			}

		}

		return t;
	}

	interface Flyable {

		void fly();
	}

	class Bird implements Flyable {

		@Override
		public void fly() {

			System.out.println("bird is flying!");

		}

	}

	class Aeroplane implements Flyable {

		@Override
		public void fly() {

			System.out.println("plane is flying!");

		}

	}

}
