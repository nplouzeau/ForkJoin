package fr.istic.nplouzeau.forkjoin;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class SummerTest {


	double data[];

	@Before
	public void setUp() throws Exception {

		final int DATA_SIZE = 10001;
		data = new double[DATA_SIZE];
		for (int i = 0; i < data.length; i++) {
			data[i] = data.length - i;
		}

	}

	@Test
	public void testCompute() throws Exception {
		Logger.getGlobal().setLevel(Level.WARNING);
		// Create the action
		Summer summer = new Summer(data, 0, data.length - 1);
		// Launch a parallel recursive action
		ForkJoinPool pool = new ForkJoinPool();

		pool.execute(summer);
		Double sum = summer.join();
		Logger.getGlobal().info(sum.toString());
		assertEquals(data.length * (data.length + 1) / 2, sum, 1.0d - 12) ;

	}
}