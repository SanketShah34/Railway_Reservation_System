package com.project.setup;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class CancelTrainTest {

	SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
	
	@Test
	void testGetTrainCode() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		cancelTrain.setTrainCode(1);
		assertEquals(cancelTrain.getTrainCode(), 1);
	}

	@Test
	void testSetTrainCode() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		cancelTrain.setTrainCode(1);
		assertEquals(cancelTrain.getTrainCode(), 1);
	}

	@Test
	void testGetCancellationDate() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		Date currentDate = Date.valueOf("2021-04-08");
		cancelTrain.setCancellationDate(currentDate);
		assertEquals(cancelTrain.getCancellationDate(), currentDate);
	}

	@Test
	void testSetCancellationDate() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		Date currentDate = Date.valueOf("2021-04-08");
		cancelTrain.setCancellationDate(currentDate);
		assertEquals(cancelTrain.getCancellationDate(), currentDate);
	}

}
