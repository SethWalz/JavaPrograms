import java.io.*;
import java.net.*;

public class BMIServer
{
	public static void main(String[] args)throws Exception
	{
		int port = 8000;

		ServerSocket serverSocket = new ServerSocket(port);

		System.out.println("Server waiting for client");
		Socket socket = serverSocket.accept();
		System.out.println("Client connected");

		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

		while (true)
		{
			double height = inputStream.readDouble();
			System.out.println("Server: Recieved height " + height);
			double weight = inputStream.readDouble();
			System.out.println("Server: Recieved weight " + weight);

			double BMI = ((weight * 703)/(height * height));

			outputStream.writeDouble(BMI);
			System.out.println(" Server: Sent BMI " + BMI);
		}

	}
}