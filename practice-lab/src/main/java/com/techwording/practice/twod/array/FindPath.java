package com.techwording.practice.twod.array;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class FindPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 0 means path exists
		int[][] arr = { { 0, 0, 0, -1, 0 }, { -1, 0, 0, -1, -1 }, { 0, 0, 0, -1, 0 }, { -1, 0, -1, 0, -1 }, { 0, 0, -1, 0, 0 } };

		System.out.println(isPath(arr));

	}

	private static boolean isPath(int[][] arr) {

		int num = arr[0][0];
		int col = arr.length;
		int row = arr[0].length;

		Set<Pair> pairSet = new LinkedHashSet<>();

		Pair pair = new Pair(0, 0, num);

		Queue<Pair> queue = new LinkedList<>();

		queue.add(pair);

		pairSet.add(pair);

		int[] x_arr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] y_arr = { -1, 0, 1, -1, 1, -1, 0, 1 };

		boolean found = false;

		while (queue.size() > 0) {

			Pair poll = queue.poll();

			int x = poll.getX();
			int y = poll.getY();

			for (int i = 0; i < 8; i++) {

				int a = x_arr[i];
				int b = y_arr[i];

				if (a + x >= arr.length || a + x < 0) {
					continue;
				}

				if (b + y >= arr[0].length || b + y < 0) {
					continue;
				}

				if (arr[a + x][b + y] == 0) {
					if (x == row - 1 && y == col - 1) {
						System.out.println("found");
						found = true;
						break;
					}
					else {

						Pair temp = new Pair(a + x, b + y, arr[a + x][b + y]);
						if (!pairSet.contains(temp)) {
							pairSet.add(temp);
							queue.add(temp);
						}

					}
				}
			}

		}

		System.out.println(pairSet);

		return found;

	}

}

class Pair {
	int x;
	int y;

	int value;

	public Pair(int x, int y, int value) {

		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public int getX() {

		return x;
	}

	public void setX(int x) {

		this.x = x;
	}

	public int getY() {

		return y;
	}

	public void setY(int y) {

		this.y = y;
	}

	public int getValue() {

		return value;
	}

	public void setValue(int value) {

		this.value = value;
	}

	@Override
	public int hashCode() {

		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {

		return "Pair [x=" + x + ", y=" + y + ", value=" + value + "]";
	}

}
