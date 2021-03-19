package com.project.calculation;

public abstract class CalculationAbstractFactoryTest {
	public CalculationAbstractFactoryTest instance = null;
	
	public CalculationAbstractFactoryTest instance() {
		if (instance == null) {
			instance = new CalculationConcreteFactoryTest();
		}
		return instance;
	}

}
