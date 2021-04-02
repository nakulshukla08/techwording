package com.techwording.practice.array;

import java.util.Scanner;

public class Tester extends Parent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		String one = scan.next();
		String two = scan.next();

		System.out.println(one);
		System.out.println(two);

		for (String str : args) {

		}

		/*
		 * System.out.println();
		 *
		 * System.out.println(businessesResult);
		 *
		 * System.out.println(business.getDistance() + " | " + business.getPrice() + " | " + business.getId() + " | " + business
		 * .getVeganFriendly() + " | " + business.getRating());
		 */

	}

}

class Parent {

}
