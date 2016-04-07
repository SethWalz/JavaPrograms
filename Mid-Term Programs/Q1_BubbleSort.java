// Seth Walz 700609721
// Bubble Sort

import java.util.Random;
import java.util.ArrayList;

public abstract class Q1_BubbleSort<E> {

	public static <E> void bubbleSort(E[] CompArray){
		for(int iter =1; iter< CompArray.length; iter++){
			for(int inner = 0; inner < (CompArray.length - iter); inner ++){
		    	if((((Comparable) (CompArray[inner])).compareTo(CompArray[inner+1])) > 0){
					E tmp = CompArray[inner];
					CompArray[inner] = CompArray[inner + 1];
					CompArray[inner + 1] = tmp;
				}
			}
        }
	}
	public static void main(String[] args){

		//create array and fill with random ints
		Integer intArray[] = new Integer[999];
		String[] stringList = new String[999];

		// creating random stringList Array
		for (int i=0; i< stringList.length; i++)
		{
			stringList[i] = generateString();
        }

		Random rand = new Random();
		for(int i = 0; i < intArray.length; i++){
			intArray[i] = rand.nextInt(99) + 1;
		}

		//Print Starting Integer Array
		System.out.println("Starting Integer Array: ");
		for(int i = 0; i < intArray.length; i++){
				System.out.print(intArray[i] + " ");
		}

		bubbleSort(intArray);

		//Print Ending Integer Array
		System.out.println("\nEnding Integer Array: ");
		for(int i = 0; i < intArray.length; i++){
				System.out.print(intArray[i] + "  ");
		}

		// Printing starting stringList Array
		System.out.println("\n---------------------------------------------\nStarting String Array:");
		for (int i = 0; i < stringList.length; i++)
		{
			System.out.print(stringList[i]  + "	 ");
			if(((i + 1)%10) == 0){
				System.out.println();
            }
		}

		bubbleSort(stringList);

		// Printing starting stringList Array
		System.out.println("\n---------------------------------------------\nEnding String Array:");
		for (int i = 0; i < stringList.length; i++)
		{
			System.out.print(stringList[i] + "	");
			if(((i + 1)%10) == 0){
				System.out.println();
            }
		}


		}

		public static String generateString()
		{
				 Random rng = new Random();
				 int length = (rng.nextInt(10) + 1);
				 String characters = "abcdefghijklmnopqrstuvwxyz";
			     char[] text = new char[length];
			     for (int i = 0; i < length; i++)
			     {
			         text[i] = characters.charAt(rng.nextInt(characters.length()));
			     }
			     return new String(text);
		}

}