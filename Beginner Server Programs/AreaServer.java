import java.io.*;
import java.net.*;

public class AreaServer
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
			double radius = inputStream.readDouble();
			System.out.println("Server: Recieved radius " + radius);

			double area = radius * radius * Math.PI;

			outputStream.writeDouble(area);
			System.out.println(" Server: Sent area " + area);
		}

	}
}