import java.io.*;
import java.net.*;

public class MultiThreadedAreaServer{

	public static void main(String[] args) throws Exception{

		int port = 8000;
		ServerSocket serverSocket = new ServerSocket(port);
		int count = 0;

		while(true){
			Socket socket = serverSocket.accept();
			count++;
			InetAddress inetAddress = socket.getInetAddress();

			System.out.println("Client " + count + "IP address" + inetAddress);

			HandleClientTask task = new HandleClientTask(socket);
			Thread thread = new Thread(task);
			thread.start();
		}




	}
}

class HandleClientTask implements Runnable{
	private Socket socket;

	public HandleClientTask(Socket socket){
		this.socket = socket;
	}
	public void run(){
		try{
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

			while(true){
				double radius = inputStream.readDouble();
				System.out.println("Server received radius: " + radius);
				double area = radius * radius * Math.PI;
				outputStream.writeDouble(area);
				System.out.println("Server sent area: " + area);
			}
		} catch(Exception e){}
	}
}