package connect4;
public class SmartStrategy extends Strategy{
	public String getName() { 
		return "Smart"; 
	}


	public int[] determineMove(Board board, Mark mark) { 
		/*//Check for guaranteed win 

		int[] empty = new int[Board.DIM*Board.DIM];
		int num = 0;
		for (int x =0; x<Board.DIM; x++){
			for (int y =0; y<Board.DIM; y++){
				if (board.isEmptyField(x,y)){
					String str = "" + x + y;
					int k = Integer.parseInt(str);
					empty[num]= k;
					num++;
				}
			}
		}
		// Check own win
		for (int i=0; i<num; i++){
			Board board1 = board.deepCopy();
			int choice = empty[i];
			String temp="";
			if (choice == 0){
				temp = "00" ;

			}else if (choice == 1){
				temp = "01" ;

			}else if (choice == 2){
				temp = "02" ;

			}else if (choice == 3){
				temp = "03" ;
			}else{ 
				temp = Integer.toString(choice);
			}

			int[] newGuess = new int[temp.length()];

			for (int k = 0; k < temp.length(); k++){
				newGuess[k] = temp.charAt(k) - '0';
			}
			board1.setField(newGuess[0],newGuess[1], mark);
			if (board1.isWinner(mark)){
				return choice;
			}
			board1.setField(newGuess[0],newGuess[1], Mark.EMPTY);
		}
		// Check opponent win
		for (int i=0; i<num; i++){
			Board board1 = board.deepCopy();
			int choice = empty[i];
			String temp="";
			if (choice == 0){
				temp = "00" ;

			}else if (choice == 1){
				temp = "01" ;

			}else if (choice == 2){
				temp = "02" ;

			}else if (choice == 3){
				temp = "03" ;
			}else{ 
				temp = Integer.toString(choice);
			}

			int[] newGuess = new int[temp.length()];

			for (int k = 0; k < temp.length(); k++){
				newGuess[k] = temp.charAt(k) - '0';
			}
			Mark other = mark.other();
			board1.setField(newGuess[0],newGuess[1], other);
			if (board1.isWinner(other)){
				return choice;
			}
			board1.setField(newGuess[0],newGuess[1], Mark.EMPTY);
		}

		//random move
		int choice = (int) (num * Math.random());
		return empty[choice];
	}*/
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
