/*
Michael Walz | 700609721
Follow the directions in the textbook. Display how long it took to add the two matrices sequentially and how long
it took to add them in parallel. Since a 2000x2000 matrix is too big to display on the screen, display the sum of all
the elements in each of the result matrices, this should help you verify that your add methods are working correctly.
*/

import java.util.*;
import java.util.concurrent.*;



public class Exercise30_16 {

	public static void main(String[] args) {
		int size = 2000;
		double[][] a = new double[size][size];
		double[][] b = new double[size][size];
		double[][] finishedMatrix = new double[size][size];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				a[i][j] = Math.random();
				b[i][j] = Math.random();
			}
		}
		long time = System.currentTimeMillis();
		finishedMatrix = parallelAddMatrix(a, b);
		double sum = 0;
		for (int i = 0; i < finishedMatrix.length; i++) {
			for (int j = 0; j < finishedMatrix.length; j++) {
				sum += finishedMatrix[i][j];
			}
		}

		System.out.println((System.currentTimeMillis() - time)	+ " msec - parallelAddMatrix() | Sum: " + sum);
		time = System.currentTimeMillis();
		finishedMatrix = addMatrix(a, b);
		System.out.println((System.currentTimeMillis() - time) + " msec - addMatrix() | Sum: " + sum);
	}

	public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
		double[][] c = new double[a.length][a.length];
		RecursiveAction mainTask = new AddMatrix(a, b, c, 0, a.length, 0, a.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
		return c;
	}

	private static class AddMatrix extends RecursiveAction {
		private final static int THRESHOLD = 100;
		private double[][] a;
		private double[][] b;
		private double[][] c;
		private int x1;
		private int x2;
		private int y1;
		private int y2;

		public AddMatrix(double[][] a, double[][] b, double[][] c, int x1, int x2, int y1, int y2) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}

		@Override
		protected void compute() {
			if (((x2 - x1) < THRESHOLD) || ((y2 - y1) < THRESHOLD)) {
				for (int i = x1; i < x2; i++) {
					for (int j = y1; j < y2; j++) {
						c[i][j] = a[i][j] + b[i][j];
					}
				}
			} else {
				int midX = (x1 + x2) / 2;
				int midY = (y1 + y2) / 2;

				invokeAll(
						new AddMatrix(a, b, c, x1, midX, y1, midY),
						new AddMatrix(a, b, c, midX, x2, y1, midY),
						new AddMatrix(a, b, c, x1, midX, midY, y2),
						new AddMatrix(a, b, c, midX, x2, midY, y2));
			}
		}
	}

	public static double[][] addMatrix(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[i][j] + b[i][j];
			}
		}
		return result;
	}
}