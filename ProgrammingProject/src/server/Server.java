package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
	private int port;
	private ServerSocket serversocket;
	private List<ClientHandler> connectCli;
	private List<ClientHandler> readyCli;
	
	public Server() {
		this.connectCli = new ArrayList<ClientHandler>();
		this.readyCli = new ArrayList<ClientHandler>();
	}
	
	public List<ClientHandler> getConnectClients() {
		return connectCli;
	}
	
	public List<ClientHandler> getReadyClients() {
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
				Thread clientThread = new ClientHandler(socket, server);
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
		Thread game = new ServerGameHandler(getReadyClients().get(0), getReadyClients().get(1));
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