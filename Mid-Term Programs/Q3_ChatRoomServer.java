// Seth Walz 700609721
// Chat Room Server


import java.io.*;
import java.net.*;
import java.util.Vector;
import java.lang.*;



public class Q3_ChatRoomServer {
	Vector<chatHandler> clientSocket = new Vector<chatHandler>();
 	private static final int maxClientsCount = 10;
 	private static final chatHandler[] threads = new chatHandler[maxClientsCount];

	public void startProc() throws Exception{
		int port = 8000;
		ServerSocket serverSocket = new ServerSocket(port);

		while(true){
			Socket socket = serverSocket.accept();
			InetAddress address = socket.getInetAddress();
			chatHandler task = new chatHandler(socket);
			clientSocket.add(task);
			Thread thread = new Thread(task);
			thread.start();
		}
	}
	public static void main(String[] args) throws Exception{
		new Q3_ChatRoomServer().startProc();
	}

	public void sendMessageAll(String msg,String usrName,Socket socket){
			 for ( chatHandler task : clientSocket )
			 	if ( ! task.returnSocket().equals(socket) )
			    	task.sendMessage(msg,usrName, task.returnSocket());

	}


	class chatHandler extends  Thread{
			private Socket socket;
			private DataInputStream is = null;
	 		private DataOutputStream os = null;
			private Socket clientSocket[] = new Socket[1000];

			public chatHandler(Socket socket){
					this.socket = socket;

			}
			public Socket returnSocket(){
				return socket;
			}
			public void sendMessage(String msg,String usrName,Socket socket){
				String user;
				String message;

				try{
					DataOutputStream os2 = new DataOutputStream(socket.getOutputStream());

					os2.writeUTF(usrName);
					os2.writeUTF(msg);
				}catch(IOException e){}

			}
			public void run(){
				try {

	     			is = new DataInputStream(socket.getInputStream());
	      			os = new DataOutputStream(socket.getOutputStream());

					while (true){

						String usrName = is.readUTF();
						String msg = is.readUTF();
						System.out.println("Server: Recieved msg from: " + usrName + " - " + msg);

						String msgOut = msg;
						String nameOut = usrName;
						os.writeUTF(usrName);
						os.writeUTF(msg);

						sendMessageAll(msg,usrName,socket);

					}
				}
				catch(Exception e){
				}

			}

	}
}