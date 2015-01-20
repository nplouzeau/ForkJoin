package fr.istic.nplouzeau.forkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

/**
 * Created by plouzeau on 2015-01-20.
 */
public class Summer extends RecursiveTask<Double> {

	private int firstItemIndex;
	private int lastItemIndex;
	private double data[];

	public Summer(double[] data, int firstItemIndex, int lastItemIndex) {
		this.data = data;
		this.firstItemIndex = firstItemIndex;
		this.lastItemIndex = lastItemIndex;
	}

	/**
	 * The main computation performed by this task.
	 */
	@Override
	protected Double compute() {
		if (firstItemIndex < lastItemIndex) {
			int middleIndex = (firstItemIndex + lastItemIndex) / 2;
			Summer summer1 = new Summer(data, firstItemIndex, middleIndex);
			Summer summer2 = new Summer(data, middleIndex+1, lastItemIndex);

			summer1.fork();
			summer2.fork();
			return summer1.join() + summer2.join();
		}
		else {
			Logger.getGlobal().info(Double.toString(data[firstItemIndex]));
			return data[firstItemIndex];
		}
	}
}
