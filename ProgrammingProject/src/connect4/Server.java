package connect4;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server. 
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server {
	private static final String USAGE
	= "usage: " + Server.class.getName() + " <name> <port>";

	
	public static void main(String[] args) throws IOException { 
		
		int port = Integer.parseInt(args[1]); 
		if (args.length != 2) { 
			System.out.println("Could not read port"); 
			System.out.println(Server.USAGE); 
			System.exit(1); 
		} 

		ServerSocket serv = null; 
		try { 
			serv = new ServerSocket(port); 
		} catch (IOException e1) { 
			System.out.println("Cannot open socket"); 
			System.err.println(e1);
			System.exit(1); 
		} 
		while(!serv.isClosed()){
			Socket sock = null;
			try {
				sock = serv.accept() ;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try{
				ClientHandler clientHandler = new ClientHandler(args[0], sock);
				Thread thread = new Thread(clientHandler);
				thread.start();
				//			peer.handleTerminalInput();
				//			peer.shutDown();
				//			sock.close();
			} catch (IOException e1){
				e1.printStackTrace();
			}
		}
	}
}