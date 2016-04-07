import java.io.*;
import java.net.*;

public class TIPServer
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
			double bill = inputStream.readDouble();
			System.out.println("Server: Recieved bill amount " + bill);
			double tip = inputStream.readDouble();
			System.out.println("Server: Recieved tip % " + tip);

			double total = bill + (bill*tip);

			outputStream.writeDouble(total);
			System.out.println(" Server: Sent total " + total);
		}

	}
}