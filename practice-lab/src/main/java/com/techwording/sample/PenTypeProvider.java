package com.techwording.sample;

public class PenTypeProvider {

	public Pen getPenFromType(PenType type) {

		// map.get(type)

		if (type == null) {
			// throw new IllegalA
			return null;
		}

		if (FountainPen.TYPE.equalsIgnoreCase(type.getType())) {
			return new FountainPen();
		}
		else {
			// some dummy imple
		}
		// Return default pen type here
		return new Pen();

	}

}
