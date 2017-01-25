package connect4;


public class NaiveStrategy implements Strategy{
	public String getName(){
		return "Naive";
	}
	
	public int determineMove(Board board, Mark mark){
		int[] empty = new int[Board.DIM*Board.DIM];
		int positions = 0;
		for (int x =0; x<Board.DIM; x++){
			for (int y =0; y<Board.DIM; y++){
				if (board.isEmptyField(x,y)){
					String str = "" + x + y;
					int k = Integer.parseInt(str);
					empty[positions]= k;
					positions++;
				}
			}
		}
		int index = (int) (positions *Math.random());
		return empty[index];
		       		
	}
}
