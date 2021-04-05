package com.project.lookup;


public class LookupConcreteFactoryTest extends LookupAbstractFactoryTest {

	@Override
	public SearchTrainMock createSearchTrainMock() {
		return new SearchTrainMock();
	}

	@Override
	public SearchTrainDAOMock createSearchTrainDAOMock() {
		return new SearchTrainDAOMock();
	}
}

