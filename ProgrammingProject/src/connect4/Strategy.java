package connect4;
import java.util.*;




public abstract class Strategy implements Observer{
	private Board board;
	public abstract String getName();
	public abstract int[] determineMove(Board board, Mark mark);
	public void update(Observable arg0, Object arg1) {
		board = (Board) arg0;
	}
	
	

}