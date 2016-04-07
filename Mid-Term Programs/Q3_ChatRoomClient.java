// Seth Walz 700609721
// Chat Room Client

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Q3_ChatRoomClient extends Application {

	public static TextField usernameField = new TextField();
	public static TextField msgField = new TextField();
	public static TextArea chatBox = new TextArea();
	public static Button btnSend = new Button("Send");

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		primaryStage.setTitle("Chat Client");
		grid.setHgap(10);
		grid.setVgap(10);
		// Investment Amount
		Label username = new Label("Username:");
		grid.add(username, 0, 1);
		//send text area
		Label msg = new Label("Message:");
		grid.add(msg, 0, 3);
		chatBox.setEditable(false);
		grid.add(msgField, 1,3);
		// button to calculate value

		grid.add(btnSend, 2, 3);
		//TextField usernameField = new TextField();
		grid.add(usernameField, 1, 1);
		grid.add(chatBox,1,2);


		Scene scene = new Scene(grid, 600, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// The output stream
	private static DataOutputStream os = null;
	// The input stream
	private static DataInputStream is = null;

	private static BufferedReader inputLine = null;
  	private static boolean closed = false;

	public static void main(String[] args) throws Exception {


		Scanner input = new Scanner(System.in);
		System.out.println("Client: Connecting to server");
		Socket socket = new Socket("localhost",8000);
		System.out.println("Client: Connected to server");
		System.out.println("Client: Local chat ready");

		is = new DataInputStream(socket.getInputStream());
		os = new DataOutputStream(socket.getOutputStream());
		//if (usrName == null) {
		//	System.out.println("Username: ");
		//}

		//usrName = input.nextLine();
		new foo().start();

		btnSend.setOnAction(new EventHandler<ActionEvent>()  {
			@Override
			public void handle(ActionEvent e) {
				try {
					String usrName = usernameField.getText();
					String msg = msgField.getText();

					os.writeUTF(usrName);
					os.writeUTF(msg);

					//String msgOut = is.readUTF();
					//String nameOut = is.readUTF();

					//System.out.println(nameOut + ": " + msgOut);
				} catch (IOException p){
				}
			}
		});
		Application.launch(args);

	}
	// Thread to check new msgs
	static class  foo extends Thread {
		        public void run() {
		            try{
						// Read data
						while(true){
							String nameOut = is.readUTF();
							String msgOut = is.readUTF();

							chatBox.appendText(nameOut + ": " +  msgOut + "\n");
							//System.out.println(nameOut + ": " + msgOut);
						}
					}catch(IOException e){}
		        }
		}


}
