

public class SmartStrategy implements Strategy{
	public String getName() { 
		return "Smart"; 
	}
	
	
	public int determineMove(Board board, Mark mark) { 
		//check the middle one
		int mid = (Board.DIM*Board.DIM)/2;
		if (board.isEmptyField(mid)){
			return mid;
		}
		
		//Check for garantueed win 
		int num=0;
		int[] empty = new int[Board.DIM * Board.DIM];
		for (int i=0; i<Board.DIM*Board.DIM;i++){
			if (board.isEmptyField(i)){
				empty[num] = i;
				num++;
			}
		}
		
		for (int i=0; i<num; i++){
			int posi = empty[i];
			board.setField(posi, mark);
			if (board.isWinner(mark)){
				return posi;
			}
			board.setField(posi, Mark.EMPTY);
		}
		
		//check opponent win
		for (int i=0; i<num; i++){
			int posi = empty[i];
			Mark other = mark.other();
			board.setField(posi, other);
			if (board.isWinner(other)){
				return posi;
			}
			board.setField(posi, Mark.EMPTY);
		}
		
		//random move
		int posi = (int) (num * Math.random());
		return empty[posi];
		
	}
}
