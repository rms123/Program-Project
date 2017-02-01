package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import connect4.*;
import exceptions.*;

public class Client {

	public static final String COMMAND = "COMMAND";
	public static final String GIVECOMMAND = "List of commands:\n" + "DISCONNECT: disconnect from server and exit\n"
			+ "PLAYERS ALL: get list of players that are connected to server\n"
			+ "GAME READY: notify server you are ready to play a game\n"
			+ "GAME UNREADY: notify server that you are not ready anymore to play a game\n";

	private String name;
	private int port;
	private InetAddress ipAddress;
	private Socket sock;
	private BufferedReader reader;
	private PrintWriter writer;
	private BufferedReader terminalReader;
	private ServerHandler ServerHandler;
	private Player player;
	private boolean hasTurn;

	public Client() {
		terminalReader = new BufferedReader(new InputStreamReader(System.in));
		hasTurn = false;
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.reader = null;
		client.writer = null;
		boolean infoReady = false;
		while (!infoReady) {
			try {
				client.getConnectionInfo();
				client.getPlayerInfo();
				client.sock = new Socket(client.ipAddress, client.port);
				client.reader = new BufferedReader(new InputStreamReader(client.sock.getInputStream()));
				client.writer = new PrintWriter(new OutputStreamWriter(client.sock.getOutputStream()));
				client.connect();
				if (!client.reader.readLine().startsWith(Protocol.CONFIRM)) {
					throw new UserHasConnectException();
				} else {
					System.out.println("For commands, type " + COMMAND);
					infoReady = true;
				}

			} catch (IOException | NumberFormatException e){
				System.out.println("An IO-Exception/NumberFormatException Occured, please enter information again. " + "Possible causes:\n"
						+ "- incorrect ip address\n" + "- incorrect port number\n");
			} catch (UserHasConnectException e) {
				System.out.println(e.getMessage() + ". Please choose a different username");
			} catch (InputErrorException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			client.ServerHandler = new ServerHandler(client.reader, client);
			client.ServerHandler.start();
			client.handleTerminalInput();
		} catch (IOException e) {
			System.out.println("IO exception in main client");
		}

	}

	public void getConnectionInfo() throws IOException, NumberFormatException {
		System.out.println("What is your name?");
		String[] nameparts = terminalReader.readLine().split(" ");
		name = "";
		for (int i = 0; i < nameparts.length; i++) {
			name = name + nameparts[i];
		}
		System.out.println("What's the ip address to connect?");
		ipAddress = InetAddress.getByName(terminalReader.readLine());
		System.out.println("What's the port number to connect?");
		port = Integer.parseInt(terminalReader.readLine());
	}

	public void getPlayerInfo() throws IOException, InputErrorException {
		System.out.println("Do want this client as AI palyer(1) or play this game yourself(2)?");
		String playChoice = terminalReader.readLine();
		if (playChoice.equals("2")) {
			player = new HumanNetPlayer(Mark.REDDD, name, terminalReader);
		} else if (playChoice.equals("1")) {
			System.out.println("Do you want to play with a naive AI (1) or smart AI (2)?");
			playChoice = terminalReader.readLine();
			if (playChoice.equals("1")) {
				player = new ComputerPlayer(Mark.REDDD, new NaiveStrategy());
			} else if (playChoice.equals("2")) {
				player = new ComputerPlayer(Mark.REDDD, new SmartStrategy());
			} else {
				throw new InputErrorException("Invalid input, please provide a 1 or a 2 as answer");
			}
		} else {
			throw new InputErrorException("Invalid input, please provide a 1 or a 2 as answer");
		}
	}

	public void handleTerminalInput() throws IOException {
		boolean running = true;
		while (running) {
			while (!hasTurn) {
				if (terminalReader.ready()) {
					String input = terminalReader.readLine();
					String[] parsedInput = input.split(" ");
					if (parsedInput.length >= 1 && parsedInput[0].equals(Protocol.DISCONNECT)) {
						writeToServer(input);
						running = false;
						disconnect();
					} else if (parsedInput.length >= 1 && parsedInput[0].equals(COMMAND)) {
						System.out.println(GIVECOMMAND);
					} else {
						writeToServer(input);
					}
				}
			}
		}
	}

	public void disconnect() throws IOException {
		ServerHandler.stopRunning();
		writer.close();
		terminalReader.close();
		reader.close();
	}

	public void connect() {
		writeToServer(Protocol.CONNECT + " " + name);
	}

	public void writeToServer(String msg) {
		writer.println(msg);
		writer.flush();
	}

	public Player getPlayer() {
		return player;
	}

	public String getName() {
		return name;
	}

	public boolean isHasTurn() {
		return hasTurn;
	}

	public void setHasTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}
}
