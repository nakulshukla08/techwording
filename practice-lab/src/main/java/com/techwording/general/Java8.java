package com.techwording.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8 {

	public static void main(String[] args) {

		Java8 solution = new Java8();
		// TODO Auto-generated method stub

		Employee emp1 = solution.new Employee(1, "Ajay");

		Employee emp2 = solution.new Employee(2, "Pawan");

		List<Employee> empList = new ArrayList<>();
		empList.add(emp1);
		empList.add(emp2);

		Map<Integer, Employee> collect = empList.stream()
			.collect(Collectors.toMap(Employee::getId, x -> x));

	}

	class Employee {

		private int id;

		public Employee(int id) {

			super();
			this.id = id;
		}

		private String name;

		public Employee(int id, String name) {

			super();
			this.id = id;
			this.name = name;
		}

		public int getId() {

			return id;
		}

		public void setId(int id) {

			this.id = id;
		}

		public String getName() {

			return name;
		}

		public void setName(String name) {

			this.name = name;
		}

	}

}
