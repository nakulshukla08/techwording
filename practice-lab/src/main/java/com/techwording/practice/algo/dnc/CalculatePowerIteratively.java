package com.techwording.practice.algo.dnc;

import java.util.HashMap;
import java.util.Map;

public class CalculatePowerIteratively {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		int base = 5;
		int exponent = 10;

		Map<Integer, Integer> calculation = new HashMap<Integer, Integer>();

		int result = power(base, exponent, calculation);

		System.out.println(result);

	}

	public static int power(int base, int exponent, Map<Integer, Integer> calculation) {

		System.out.println("exponent : " + exponent);
		if (exponent == 0) {
			return 1;
		}
		int counter = 0;
		int iterationCounter = 2;
		int temp = 1;
		int result = 5;
		while (iterationCounter < exponent) {
			result = result * result; // 2, 4 , 8
			temp = iterationCounter;
			iterationCounter = iterationCounter * iterationCounter;
			calculation.put(temp, result);

		}

		int diff = exponent - temp;

		return result * power(base, diff, calculation);

	}

}
