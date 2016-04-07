import java.util.*;
import java.io.*;
import java.net.*;

public class BMIClient
{
	public static void main(String[] args) throws Exception
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Client: Connecting to server");
		Socket socket = new Socket("localhost",8000);
		System.out.println("Client: Connected to server");

		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

		while(true)
		{
			System.out.println("Enter a height: ");
			double height = input.nextDouble();

			System.out.println("Enter a weight: ");
			double weight = input.nextDouble();


			outputStream.writeDouble(height);
			outputStream.writeDouble(weight);

			double BMI = inputStream.readDouble();
			System.out.println("BMI: " + BMI);

		}
	}
}