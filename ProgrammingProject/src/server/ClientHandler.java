package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


import exceptions.*;
import connect4.*;

public class ClientHandler extends Thread {
	private Server server;
	private Socket socket;
	private InputStream input;
	private OutputStream output;
	private BufferedReader reader;
	private PrintWriter writer;
	private String name;
	private Mark mark;
	private Integer[] moves;
	private ServerGameHandler servergame;

	public ClientHandler(Socket sock, Server server) throws IOException {
		this.socket = sock;
		this.server = server;
		input = socket.getInputStream();
		output = socket.getOutputStream();
		moves = null;
	}

	@Override
	public void run() {
		reader = new BufferedReader(new InputStreamReader(input));
		writer = new PrintWriter(new OutputStreamWriter(output));
		System.out.println("Connecting client...");
		boolean running = true;
		while (running) {
			try {
				String line = reader.readLine();
				if (line != null) {
					String[] text = line.split(" ");
					if (text.length >= 2 && text[0].equals(Protocol.CONNECT)) {
						connect(text);
					} else if (text.length == 1 && text[0].equals(Protocol.DISCONNECT)) {
						disconnect();
						running = false;
					} else if (text.length == 2 && line.equals(Protocol.READY)) {
						readyClient();
					} else if (text.length == 2 && line.equals(Protocol.UNREADY)) {
						cancelReadyClient();
					} else if (text.length == 2 && line.equals(Protocol.ASK_PLAYERS_ALL)) {
						getAllPlayersName();
					} else if (text.length == 4 && line.startsWith(Protocol.CLIENT_MOVE)) {
						if (servergame != null && servergame.determineTurn().equals(this)) {
							doMove(text);
						} else {
							throw new MethodErrorException(
									"error causes: maybe it's not your turn or not in a game" );
						}
					} else {
						throw new MethodUnknowException();
					} 
				} else {
					disconnect();
				}
			} catch (IOException e) {
				System.out.println(this.getName()+ "has diconnect");
				try {
					disconnect();
					running = false;
				} catch (MethodErrorException ee) {
				}
				running = false;
			} catch (UserNameExistException e) {
				System.out.println(e.getMessage());
				writeToClient(e.getMessage());
				running = false;
			} catch (MethodErrorException | MethodUnknowException e) {
				writeToClient(e.getMessage());
			} catch (NumberFormatException e) {
				try {
					moves = null;
					throw new FormatErrorException();
				} catch (FormatErrorException ee) {
					writeToClient(ee.getMessage());
				}
			}
		}

	}

	public void connect(String[] text) throws UserNameExistException {
		boolean userexist = false;
		for (ClientHandler clientThread : server.getConnectClients()) {
			if (clientThread.getClientName().equals(text[1])) {
				userexist = true;
			}
		}
		if (!userexist) {
			System.out.println(text[1] + " connected");
			writeToClient(Protocol.CONFIRM);
			name = text[1];
			server.getConnectClients().add(this);
		} else {
			throw new UserNameExistException();
		}
	}

	public void disconnect() throws MethodErrorException {
		if (server.getConnectClients().contains(this)) {
			if (servergame != null) {
				servergame.setDisconnect(true, this);
			}
			server.getConnectClients().remove(this);
			server.getReadyClients().remove(this);
			System.out.println(name + " has disconnected.");
			writeToClient("You have disconnected from server");
		} else {
			throw new MethodErrorException("You connection has error");
		}
	}

	public void readyClient() throws MethodErrorException {
		if (!server.getConnectClients().contains(this)) {
			throw new MethodErrorException("You connection has error");
		}
		if (server.getReadyClients().contains(this)) {
			System.out.println("You have already ready for game");
		} else {
			server.getReadyClients().add(this);
			System.out.println(name + " is ready for game");
			writeToClient("You are now ready ready a game");
		}
	}

	public void cancelReadyClient() throws MethodErrorException {
		if (server.getReadyClients().contains(this)) {
			server.getReadyClients().remove(this);
			System.out.println(name + " has cancel ready for game");
			writeToClient("You have cancel for a game");
		} else {
			throw new MethodErrorException("You have to ready for game to cancel");
		}
	}

	public void doMove(String[] text) throws NumberFormatException {
		moves = new Integer[2];
		moves[0] = Integer.parseInt(text[2]);
		moves[1] = Integer.parseInt(text[3]);
	}
	
	public String getClientName() {
		return name;
	}
	
	public Integer[] getMoves() {
		return moves;
	}
	
	public void setMoves(Integer[] moves) {
		this.moves = moves;
	}

	public void getAllPlayersName() {
		String playersall = Protocol.PLAYERS_ALL;
		for (ClientHandler client : server.getConnectClients()) {
			playersall = playersall + " " + client.getClientName();
		}
		writeToClient(playersall);
	}
	
	public void writeToClient(String msg) {
		writer.println(msg);
		writer.flush();
	}

	

	public Mark getMark() {
		return mark;
	}

	
	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public void setGameThread(ServerGameHandler gameThread) {
		this.servergame = gameThread;
	}

	
}
