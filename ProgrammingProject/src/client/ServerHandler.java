package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedWriter;
import java.net.Socket;

import connect4.*;

public class ServerHandler extends Thread {
	private BufferedReader input;
	private boolean running;
	private Client client;
	private OnlinePlayer opponent;
	private Game gameThread;

	public ServerHandler(BufferedReader reader, Client clt) throws IOException {
		input = reader;
		client = clt;
	}

	public void run() {
		running = true;

		while (running) {
			try {
				if (input.ready()) {
					String rawText = input.readLine();
					String[] parsedText = rawText.split(" ");
					if (parsedText.length >= 4 && rawText.startsWith(Protocol.START)) {
						if (parsedText[2].equals(client.getName())) {
							opponent = new OnlinePlayer(Mark.YELLO, parsedText[3]);
							gameThread = new Game(client.getPlayer(), opponent, false);
							gameThread.setClient(client);
							client.setHasTurn(true);
							gameThread.start();
						} else {
							opponent = new OnlinePlayer(Mark.YELLO, parsedText[2]);
							gameThread = new Game(opponent, client.getPlayer(), false);
							gameThread.setClient(client);
							gameThread.start();
						}
					} else if (parsedText.length >= 4 && rawText.startsWith(Protocol.SERVER_MOVE)) {
						if (parsedText[2].equals(opponent.getName())) {
							int[] move = new int[2];
							move[0] = Integer.parseInt(parsedText[3]);
							move[1] = Integer.parseInt(parsedText[4]);
							opponent.setMoveBuffer(move);
							client.setHasTurn(true);
						} else {
							client.setHasTurn(false);
						}
					} else if (rawText.startsWith(Protocol.END_WINNER) || rawText.startsWith(Protocol.END_DRAW)) {
						
					}
					else {
						System.out.println(rawText);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void stopRunning() {
		running = false;
	}
}
