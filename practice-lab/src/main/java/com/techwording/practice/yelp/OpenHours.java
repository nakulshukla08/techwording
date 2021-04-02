package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.List;

public class OpenHours {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<TimeRange> timeRanges = new ArrayList<TimeRange>();

		TimeRange tr1 = new TimeRange(0, 2);
		TimeRange tr2 = new TimeRange(20, 24);
		timeRanges.add(tr1);
		timeRanges.add(tr2);

		TimeRange input = new TimeRange(0, 24);

		System.out.println(openHoursRatio(input, timeRanges));

	}

	public static float openHoursRatio(TimeRange queryTimeRange, List<TimeRange> openHours) {

		float total = 24;
		float sum = 0;
		for (TimeRange tr : openHours) {
			sum = sum + (Math.min(tr.getEnd(), queryTimeRange.getEnd()) - Math.max(tr.getStart(), queryTimeRange.getStart()));
			System.out.println(sum);
		}

		System.out.println(sum);

		return sum / total;
	}
}

class TimeRange {

	float start;

	float end;

	public TimeRange(float start, float end) {

		super();
		this.start = start;
		this.end = end;
	}

	public float getStart() {

		return start;
	}

	public void setStart(float start) {

		this.start = start;
	}

	public float getEnd() {

		return end;
	}

	public void setEnd(float end) {

		this.end = end;
	}

}
