import java.io.*;
import java.net.*;

public class MultiThreadedBMIServer{

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
				double height = inputStream.readDouble();
				System.out.println("Server: Recieved height " + height);
				double weight = inputStream.readDouble();
				System.out.println("Server: Recieved weight " + weight);

				double BMI = ((weight * 703)/(height * height));

				outputStream.writeDouble(BMI);
				System.out.println(" Server: Sent BMI " + BMI);
			}
		} catch(Exception e){}
	}
}