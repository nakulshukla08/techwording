package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class FilterSpam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Payload> createPayloads = createPayloads();

		sortUsersWithMessageCount(createPayloads);

	}

	private static void sortUsersWithMessageCount(List<Payload> createPayloads) {

		Map<Integer, Set<User>> map = new TreeMap<>(Comparator.reverseOrder());

		Map<User, Integer> map2 = new HashMap<User, Integer>();

		for (Payload msg : createPayloads) {

			Integer previous = null;
			Integer count = map2.get(msg.getTo());

			if (count != null) {
				previous = count;
				count = count + 1;
				map2.put(msg.getTo(), count);
			}
			else {
				count = 1;
				map2.put(msg.getTo(), 1);
			}

			if (previous != null) {
				Set<User> old = map.get(previous);

				if (old != null) {
					old.remove(msg.getTo());
				}
			}

			if (count != null) {
				Set<User> set = map.get(count);
				if (set != null) {
					set.add(msg.getTo());
				}
				else {
					Set<User> users = new HashSet<>();
					users.add(msg.getTo());
					map.put(count, users);
				}
			}

		}

		System.out.println(map);
		System.out.println(map2);

	}

	private static List<Payload> filterSpam(List<Payload> input) {

		return input;

	}

	private static List<Payload> createPayloads() {

		User user1 = new User("user-1");
		User user2 = new User("user-2");
		User user3 = new User("user-3");
		User user4 = new User("user-4");
		User user5 = new User("user-5");
		User user6 = new User("user-6");

		Payload payload1 = new Payload(user1, user2, "hello");
		Payload payload2 = new Payload(user1, user2, "hello");
		Payload payload3 = new Payload(user1, user2, "hello");
		Payload payload4 = new Payload(user2, user3, "hello");
		Payload payload5 = new Payload(user3, user4, "hello");
		Payload payload6 = new Payload(user6, user5, "hello");
		Payload payload7 = new Payload(user6, user4, "hello");
		Payload payload8 = new Payload(user6, user3, "hello");
		Payload payload9 = new Payload(user3, user6, "hello");
		Payload payload10 = new Payload(user2, user1, "hello");

		List<Payload> payloads = new ArrayList<Payload>();

		payloads.add(payload1);
		payloads.add(payload2);
		payloads.add(payload3);
		payloads.add(payload4);
		payloads.add(payload5);
		payloads.add(payload6);
		payloads.add(payload7);
		payloads.add(payload8);
		payloads.add(payload9);
		payloads.add(payload10);

		return payloads;

	}

}

class Payload {

	private User from;

	private User to;

	private String text;

	public Payload(User from, User to, String text) {

		super();
		this.from = from;
		this.to = to;
		this.text = text;
	}

	public User getFrom() {

		return from;
	}

	public void setFrom(User from) {

		this.from = from;
	}

	public User getTo() {

		return to;
	}

	public void setTo(User to) {

		this.to = to;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

}

class User {

	private String id;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public User(String id) {

		super();
		this.id = id;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {

		return "User [id=" + id + "]";
	}

}

class UserMessageCount implements Comparable<UserMessageCount> {

	private User user;

	private Integer messageCount;

	public UserMessageCount(User user, Integer messageCount) {

		super();
		this.user = user;
		this.messageCount = messageCount;
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	public Integer getMessageCount() {

		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {

		this.messageCount = messageCount;
	}

	@Override
	public int hashCode() {

		return Objects.hash(user);
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
		UserMessageCount other = (UserMessageCount) obj;
		return Objects.equals(user, other.user);
	}

	@Override
	public int compareTo(UserMessageCount o) {

		if (o == null) {
			return -1;
		}

		if (messageCount > o.getMessageCount()) {
			return 1;
		}
		else if (messageCount < o.getMessageCount()) {
			return -1;
		}
		else {
			return Objects.compare(user.getId(), o.getUser()
				.getId(), Comparator.<String>naturalOrder());
		}

	}

}
