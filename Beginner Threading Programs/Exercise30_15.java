/*
Michael Walz | 700609721
(Parallel sum) Implement the following method using Fork/Join to find the sum of a list.
Naming is a little wierd because I based off of the example in book.
*/

import java.util.*;
import java.util.concurrent.*;

public class Exercise30_15
{
	public static void main(String[] args)
	{
		//Creating the list
		final int N = 9000000;
		double[] list = new double[N];
		Random random = new Random();
		for (int i = 0; i < list.length; i++)
		{
			list[i] = 1 + (15 - 2) * random.nextDouble();
		}
		long startTime = System.currentTimeMillis();
		System.out.println("\nThe sum for everything is: " + max(list));
		long endTime = System.currentTimeMillis();
		System.out.println("\nThe number of processors is " + Runtime.getRuntime().availableProcessors());
		System.out.println("\nTime is " + (endTime - startTime) + " milliseconds\n");

	}
	public static double max(double[] list)
	{
		RecursiveTask<Double> task = new MaxTask(list, 0, list.length);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);
	}
	private static class MaxTask extends RecursiveTask<Double>
	{
		private final static int THRESHOLD = 1000000;
		private double[] list;
		private int low;
		private int high;
		private double sum;

		public MaxTask(double[] list, int low, int high)
		{
			this.list = list;
			this.low = low;
			this.high = high;
		}
		 @Override
		 public Double compute()
			 {
				 if (high - low < THRESHOLD)
				 {
					long startTime = System.currentTimeMillis();
					for(int i = low; i < high; i++){
						sum += list[i];
					}
					long endTime = System.currentTimeMillis();
					long seconds = endTime - startTime;
					System.out.println("Sum method took " + seconds + " milliseconds " + " Result= " + sum);
					return new Double(sum);
				 }
				 else {
					int mid = (low + high) / 2;
 					RecursiveTask<Double> left = new MaxTask(list, low, mid);
 					RecursiveTask<Double> right = new MaxTask(list, mid, high);

 					right.fork();
 					left.fork();
 					return new Double(left.join().doubleValue() + right.join().doubleValue());
				}
			}
		}
		public static double parallelSum(double[] list)
		{
			RecursiveTask<Double> task = new MaxTask(list, 0, list.length);
			ForkJoinPool pool = new  ForkJoinPool();
			return pool.invoke(task);
		}
}
