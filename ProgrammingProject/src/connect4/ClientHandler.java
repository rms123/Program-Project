package connect4;

import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException;


/**
 * Peer for a simple client-server application 
 * @author  Theo Ruys 
 * @version 2005.02.21 
 */ 
public class ClientHandler implements Runnable { 
	public static final String EXIT = "exit"; 

	protected String name; 
	protected Socket sock; 
	protected BufferedReader in; 
	protected BufferedWriter out; 


	/*@
    requires (nameArg != null) && (sockArg != null); 
	 */ 
	/**
	 * Constructor. creates a peer object based in the given parameters. 
	 * @param   nameArg name of the Peer-proces 
	 * @param   sockArg Socket of the Peer-proces 
	 */ 
	public ClientHandler(String nameArg, Socket sockArg) throws IOException { 
		sock = sockArg; 
		name = nameArg; 
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())); 
		in = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
	} 

	/**
	 * Reads strings of the stream of the socket-connection and writes the characters to the default output 
	 */ 
	public void run() { 
		String line = null; 
		try { 
			while ((line = in.readLine()) != null) { 
				System.out.println(line); 
			} 
		} catch (IOException e) {  
			shutDown(); 
		} 
	} 


	/**
	 * Reads a string from the console and sends this string over the socket-connection to the Peer proces. On Peer.EXIT the method ends 
	 */ 
	public void handleTerminalInput() { 
		boolean running = true;
		while (running) {
			String command = ClientHandler.readString("Please type command: " + "\n");
			if (sock.isClosed() || command.equals("Leave")){
				running = false;
				System.out.println("You left the server");
			} else {
				String[] commandArray;
				commandArray = command.split(" ");
				switch (commandArray[0]) {
				case "hoi":
					System.out.println("Server says: Ook hoi :D");
					break;

				case "StartGame":
					try {
						send(commandArray[0] + " " + commandArray[1] + " " + commandArray[2]);
					} catch (IOException e1) {
						e1.printStackTrace();
					ConnectFour.main(commandArray);
					}
					break;
					
				case "Move":
					try {
						send(command);
					} catch (IOException e) {
						e.printStackTrace();
					}
					int x = Integer.parseInt(commandArray[1]);
					int z = Integer.parseInt(commandArray[2]);
					//.board.dropColumn(x, z, Mark.EE);
					break;
					//				break;
					//				case 4:  commandArray[0].equals("y");
					//				break;
					//				case 5:  commandArray[0].equals("z");
					//				break;
				default:
					try {
						send(command);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void send(String msg) throws IOException { 
		out.write(name + ": " + msg); 
		out.newLine(); 
		out.flush(); 
	} 

	/**
	 * Closes the connection, the sockets will be terminated 
	 */ 
	public void shutDown() { 
		try { 
			sock.close(); 
		} catch (IOException e) {} 
	} 

	/**  returns name of the peer object*/ 
	public String getName() { 
		return name; 
	} 

	/** read a line from the default input */ 
	/** read a line from the default input */
	static public String readString(String tekst) {
		System.out.print(tekst);
		String antw = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			antw = in.readLine();
		} catch (IOException e) {
		}

		return (antw == null) ? "" : antw;
	}
}