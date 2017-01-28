package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//import exceptions.InvalidInputException;

public class Server {
	private int port;
	private ServerSocket serversocket;
	private List<ClientThread> connectCli;
	private List<ClientThread> readyCli;
	
	public Server() {
		this.connectCli = new ArrayList<ClientThread>();
		this.readyCli = new ArrayList<ClientThread>();
	}
	
	public List<ClientThread> getConnectClients() {
		return connectCli;
	}
	
	public List<ClientThread> getReadyClients() {
		return readyCli;
	}

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.checkArguments(args);
			server.setPort(args[0]);
			server.serversocket = new ServerSocket(server.port);
			server.serversocket.setSoTimeout(500);
		} catch (IOException e) {
			System.out.println("port is already in use, please alter run configuration");
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		boolean running = true;
		while (running) {
			try {
				Socket socket = server.serversocket.accept();
				Thread clientThread = new ClientThread(socket, server);
				clientThread.start();
			} catch (IOException e) {
				//this is for the time out
			}
			if (server.getReadyClients().size() >= 2) {
				server.startGame();
			}
		}
	}

	public void startGame() {
		Thread game = new ServerGameThread(getReadyClients().get(0), getReadyClients().get(1));
		getReadyClients().remove(1);
		getReadyClients().remove(0);
		game.start();
	}
	
	public void checkArguments(String[] args) throws Exception {
		if (args.length != 1) {
			throw new Exception("INput a valid port number:");
		}
	}

	public void setPort(String arg) {
		try {
			port = Integer.parseInt(arg);
		} catch (NumberFormatException e) {
			System.out.println(
					"PLEASE input integer port number!");
			System.exit(0);
		}
	}

}