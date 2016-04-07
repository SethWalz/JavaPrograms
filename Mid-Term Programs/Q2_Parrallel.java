// Seth Walz 700609721
// Parrallize


//sequentailAdd()
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Q2_Parrallel {

	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();
		double sum1 = sequentialAdd();
		long endTime = System.currentTimeMillis();
		long duration = ((endTime - startTime));
		System.out.println("sequentialAdd took: " + duration + " milliseconds with a result of: " + sum1);
		startTime = System.currentTimeMillis();
		double sum2 = parallelSum();
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		System.out.println("ParallelAdd took: " + duration + " milliseconds with a result of: " + sum2);

	}

	public static double sequentialAdd() {
		boolean done = false;
		double number = 0.0;
		System.out.println("Calculating: sequentialAdd...");
		while(done == false){
			if(number <= 1000000.0){
				number += .0001;
			}
			else
				done = true;
		}
		return number;


	}

	public static double parallelSum(){
		System.out.println("Calculating: parallelAdd...");
		RecursiveTask<Double> task = new sumTask(0.0001, 1000000);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);
	}

	private static class sumTask extends RecursiveTask<Double>{
			private double sum;
			private double low;
			private double high;
			public sumTask(double low, double high){
				this.low = low;
				this.high = high;
			}

			@Override
			public Double compute(){
				if(high - low < 1000){
					for(double i = low; i < high; i+=.0001){
									sum += 0.0001;
					}
					return sum;
				}
				else{
					double mid = (low + high) / 2;
					RecursiveTask<Double> left = new sumTask(low, mid);
					RecursiveTask<Double> right = new sumTask(mid, high);

					right.fork();
					left.fork();
					return new Double(left.join().doubleValue() + right.join().doubleValue());

				}
		}
	}

}
