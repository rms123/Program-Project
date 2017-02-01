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
	private OpponentPlayer opponent;
	private Game gameThread;

	public ServerHandler(BufferedReader reader, Client client) throws IOException {
		input = reader;
		this.client = client;
	}

	public void run() {
		running = true;

		while (running) {
			try {
				if (input.ready()) {
					String inputtt = input.readLine();
					String[] TEext = inputtt.split(" ");
					if (TEext.length >= 4 && inputtt.startsWith(Protocol.START)) {
						if (TEext[2].equals(client.getName())) {
							opponent = new OpponentPlayer(Mark.YELLO, TEext[3]);
							gameThread = new Game(client.getPlayer(), opponent, false);
							gameThread.setClient(client);
							client.setHasTurn(true);
							gameThread.start();
						} else {
							opponent = new OpponentPlayer(Mark.YELLO, TEext[2]);
							gameThread = new Game(opponent, client.getPlayer(), false);
							gameThread.setClient(client);
							gameThread.start();
						}
					} else if (TEext.length >= 4 && inputtt.startsWith(Protocol.SERVER_MOVE)) {
						if (TEext[2].equals(opponent.getName())) {
							int[] move = new int[2];
							move[0] = Integer.parseInt(TEext[3]);
							move[1] = Integer.parseInt(TEext[4]);
							opponent.setMoveBuffer(move);
							client.setHasTurn(true);
						} else {
							client.setHasTurn(false);
						}
					} else if (inputtt.startsWith(Protocol.END_WINNER) || inputtt.startsWith(Protocol.END_DRAW)) {
						
					}
					else {
						System.out.println(input);
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
