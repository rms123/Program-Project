package server;
import connect4.*;
import exceptions.*;


public class ServerGame extends Thread {
	private ClientHandler clientThread1;
	private ClientHandler clientThread2;
	private Board board;
	private int playerAmount;
	private int turn = 0;
	private boolean disconnect;
	private ClientHandler disconnectedThread;

	public ServerGame(ClientHandler client1, ClientHandler client2) {
		clientThread1 = client1;
		clientThread2 = client2;
		clientThread1.setMark(Mark.REDDD);
		clientThread2.setMark(Mark.YELLO);
		clientThread1.setGameThread(this);
		clientThread2.setGameThread(this);
		board = new Board();
		playerAmount = 2;
		disconnect = false;
	}

	public void run() {
		System.out.println(toString() + " started");
		broadcast(Protocol.START + " " + clientThread1.getClientName() + " " + clientThread2.getClientName());
		while (!board.gameOver() && !disconnect) {
			boolean moveMade = false;
			while (!moveMade) {
				try {
					moveMade = true;
					Integer[] coords = makeMove(this.determineTurn());
					String ctMadeMove = this.determineTurn().getClientName();
					turn++;
					String ctNextMove = this.determineTurn().getClientName();
					if (!board.gameOver()) {
						broadcast(Protocol.SERVER_MOVE + " " + ctMadeMove + " " + coords[0] + " " + coords[1] + " "
								+ ctNextMove);
					} else {
						broadcast(Protocol.SERVER_MOVE + " " + ctMadeMove + " " + coords[0] + " " + coords[1]);
					}

				} catch (NonvalidMoveException | InterruptedException e) {
					this.determineTurn().writeToClient(e.getMessage());
					moveMade = false;
				} catch (PlayerDisconnectException e) {
					if (disconnectedThread == clientThread1) {
						broadcast(Protocol.END_WINNER + " " + clientThread2.getClientName());
						System.out.println(toString() + " is won by " + clientThread2.getClientName());
					} else {
						broadcast(Protocol.END_WINNER + " " + clientThread1.getClientName());
						System.out.println(toString() + " is won by " + clientThread1.getClientName());
					}
				}
			}
		}
		if (!disconnect) {
			try {
				broadcast(Protocol.END_WINNER + " " + getWinner().getClientName());
				System.out.println(toString() + " won by " + getWinner().getClientName());
			} catch (ErrorException e) {
				broadcast(Protocol.END_DRAW);
				System.out.println(toString() + " ended in a draw");
			}
		}

	}

	public ClientHandler determineTurn() {
		turn = turn % playerAmount;
		if (turn == 0) {
			return clientThread1;
		} else {
			return clientThread2;
		}
	}

	public Integer[] makeMove(ClientHandler client)throws NonvalidMoveException, InterruptedException, PlayerDisconnectException {
		while (client.getMoves() == null && !disconnect) {
			sleep(1);
		}
		if (disconnect) {
			throw new PlayerDisconnectException();
		}
		Integer[] coords = client.getMoves();
		for (Integer coord : coords) {
			if (coord >= board.getDIM() || coord < 0) {
				throw new NonvalidMoveException();
			}
		}
		board.setField(coords[0], coords[1], client.getMark());
		client.setMoves(null);
		return coords;
	}

	public ClientHandler getWinner() throws ErrorException {
		if (board.isWinner(clientThread1.getMark())) {
			return clientThread1;
		}
		if (board.isWinner(clientThread2.getMark())) {
			return clientThread2;
		} else {
			throw new ErrorException();
		}
	}

	public void broadcast(String msg) {
		clientThread1.writeToClient(msg);
		clientThread2.writeToClient(msg);
	}

	public String toString() {
		return "game " + clientThread1.getClientName() + " " + clientThread2.getClientName();
	}

	public void setDisconnect(boolean disc, ClientHandler ct) {
		this.disconnect = disc;
		this.disconnectedThread = ct;

	}
}
