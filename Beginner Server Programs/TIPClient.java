import java.util.*;
import java.io.*;
import java.net.*;

public class TIPClient
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
			System.out.println("Enter a bill amount: ");
			double bill = input.nextDouble();

			System.out.println("Enter a tip %: ");
			double tip = input.nextDouble();


			outputStream.writeDouble(bill);
			outputStream.writeDouble(tip);

			double total = inputStream.readDouble();
			System.out.println("Total: " + total);

		}
	}
}