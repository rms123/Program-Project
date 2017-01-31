package connect4;
import java.io.BufferedReader;
import java.io.IOException;
import exceptions.*;




public class HumanNetPlayer extends Player{
	
	private BufferedReader reader;

	public HumanNetPlayer(Mark mark, String name, BufferedReader reader) {
		super(name,mark);
		this.reader = reader;
	}
	


	public int[] determineMove(Board board) {
		int[] coord = new int[2];
		System.out.println(getName() + "(" + getMark() + ")" + ", your turn!");
		//System.out.println("Do you want a hint? Y/N");
		/*boolean valid = false;
		while (!valid) {
			try {
				String input = reader.readLine();
				input = input.toLowerCase();
				if (input.equals("n")) {
					valid = true;
				} else if (input.equals("y")) {
					getHint();
					valid = true;
				} else { 
					throw new InputErrorException();
				}
			} catch (InputErrorException e) {
				System.out.println(e.getMessage() + " Please write either N or Y");
				valid = false;
			} catch (IOException e) {
				System.out.println("IO exception occured");
			}
		}*/
		System.out.println("Please enter a column number (starting at 0)");
		coord[0] = -1;
		while (coord[0] < 0) {
			try {
				String input = reader.readLine();
				coord[0] = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, please provide a valid column number");
				coord[0] = -1;
			} catch (IOException e) {
				System.out.println("IO exception occured");
			}
		}

		System.out.println("Please enter a row number (starting at 0)");
		coord[1] = -1;
		while (coord[1] < 0) {
			try {
				String input = reader.readLine();
				coord[1] = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, please provide a valid row number");
				coord[1] = -1;
			} catch (IOException e) {
				System.out.println("IO exception occured");
			}
		}
		return coord;
	}

	/*public void getHint() {
		Strategy smart = new SmartStrategy();
		int[] moves = new int[2];
		moves = smart.determineMove( board,Mark.EMPTY);
		System.out.println(
				"Have you considered the move" + " c:" + moves[0] + ", r:" + moves[1] + "?");
	}*/
}
