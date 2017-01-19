

public class NaiveStrategy implements Strategy{
	public String getName(){
		return "Naive";
	}
	
	public int determineMove(Board board, Mark mark){
		int[] empty = new int[Board.DIM * Board.DIM];
		int positions = 0;
		for (int i =0; i<Board.DIM*Board.DIM;i++){
			if (board.isEmptyField(i)){
				empty[positions]=i;
				positions++;
			}
		}
		int index = (int) (positions *Math.random());
		return empty[index];
	}
}
