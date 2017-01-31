package connect4;


public class NaiveStrategy extends Strategy{
	public String getName(){
		return "Naive";
	}
	
	public int[] determineMove(Board board, Mark mark){
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
		
		String temp="";
        if (empty[index] == 0){
        	temp = "00" ;
        	
        }else if (empty[index] == 1){
        	temp = "01" ;
        	
        }else if (empty[index] == 2){
        	temp = "02" ;

        }else if (empty[index] == 3){
        	temp = "03" ;
        }else{ 
        	temp = Integer.toString(empty[index]);
        }
		
		   	
		int[] newGuess = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
            newGuess[i] = temp.charAt(i) - '0';
        }
		return newGuess;
	}
}