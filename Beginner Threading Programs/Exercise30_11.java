/*
Michael Walz | 700609721
Use explicit locks (or semaphores) to cause a deadlock. Do not use the synchronized keyword.
Print messages to indicate when a lock is locked or unlocked to give some indication of what the program is doing.
Using Thread.sleep() might be helpful to make a deadlock more likely.
*/

import java.util.*;
import java.util.concurrent.Semaphore;


public class Exercise30_11
{


	public static Semaphore sem1 = new Semaphore(1);
	public static Semaphore sem2 = new Semaphore(1);

	private static class deadlock1 implements Runnable
	{
	    public void run()
	    {
			try
        	{
				sem1.acquire();
				System.out.println("Holding lock1");
				Thread.sleep(1000);
				sem2.acquire();
				System.out.println("Holding lock2");


			}catch (Exception e)
        	{
				System.out.println("I am deadlocked");
			}
	    }
	}

	private static class deadlock2 implements Runnable
	{
	    public void run()
	    {
			try
        	{
	        sem2.acquire();
	        System.out.println("Holding lock2");
	        Thread.sleep(1000);
	        sem1.acquire();
	        System.out.println("Holding lock1");

	        }catch (Exception e)
			{
				System.out.println("I am deadlocked");
			}
	    }
	}

	public static void main(String[] args)
	{
		deadlock1 lock1 = new deadlock1();
		deadlock2 lock2 = new deadlock2();

		lock1.run();
		lock2.run();
	}

}