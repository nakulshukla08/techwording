package com.techwording.sample;

public class PenServiceImpl implements PenService {

	private PenTypeProvider provider;

	public PenServiceImpl(PenTypeProvider provider) {

		this.provider = provider;
	}

	@Override
	public Pen getPen(String penType) {

		return provider.getPenFromType(createPenType(penType));

	}

	private PenType createPenType(String type) {

		PenType penType = new PenType();
		penType.setType(type);
		return penType;
	}

}
