package connect4;
import java.util.*;




public class TUI implements Observer{
	private String sBoard;
	private Board board;
	public TUI(Board board){
		this.board=board;
		sBoard = ToString(board);
	}
	
	public String ToString(Board boa){
		return boa.toString();
	}
	
	public void update(Observable arg0, Object arg1) {
		String boardString = this.ToString(board);
		System.out.println(boardString);
	}
	
	public Board getBoard() {
		return board;
	}

}
