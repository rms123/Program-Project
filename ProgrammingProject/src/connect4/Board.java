package connect4;
import java.util.Observable;


public class Board extends Observable{
    public static final int DIM = 4;
    private static final String[] NUMBERING = {" 00 | 01 | 02 | 03 ", "___________________________________________________________________________________________________________________",
    		" 10 | 11 | 12 | 13 ", "_____________________________________________________________________________________________________________________________", " 20 | 21 | 22 | 23 ","_________________________________________________________________________________________________", " 30 | 31 | 32 | 33 ","__________________________________________________________________________________________________________________________________"};
    private static final String LINE = NUMBERING[1];
    private static final String DELIM = "     ";

    
    
    private Mark[][][] fields ;

    // -- Constructors -----------------------------------------------

    /**
     * Creates an empty student.
     */
    //@ ensures (\forall int i; 0 <= i & i < DIM * DIM*DIM; this.getField(i) == Mark.EMPTY);
    public Board() {
    	fields = new Mark[DIM][DIM][DIM];
    	reset();
    }

    /**
     * Creates a deep copy of this field.
     */
    /*@ ensures \result != this;
        ensures (\forall int i; 0 <= i & i < DIM * DIM;
                                \result.getField(i) == this.getField(i));
      @*/
    public Board deepCopy() {
    	Board newBoard = new Board();
    	for (int x=0; x<DIM; x++){
    		for (int y=0; y<DIM; y++){
    			for (int z=0; z<DIM; z++){
    				newBoard.fields[x][y][z] = this.fields[x][y][z];
    			}
    		}
    	}
    	return newBoard;
    }
    
    public int getDIM(){
    	return DIM;
    }
    
    public static void setDIM(int dim){
    	
    }

   

    /**
     * Returns true of the (x,y,z)  refers to a valid field on the student.
     *
     * @return true if 0 <= x < DIM && 0 <= y < DIM && 0<= z<DIM;
     */
    //@ ensures \result == (0<= x && x< DIM && 0<= y && y< DIM && 0<= z && z< DIM);
    /*@pure*/
    public Boolean isField(int x,int y) {
    	return (0<= x && x< DIM && 0<= y && y< DIM);
    }

   
    
    /**
     * Returns the content of the field i.
     *
     * @param i
     *            the number of the field (see NUMBERING)
     * @return the mark on the field
     */
    //@ requires this.isField(i);
    //@ ensures \result == Mark.EMPTY || \result == Mark.REDDD || \result == Mark.YELLO;
    /*@pure*/
    public Mark getField(int x, int y, int z) {
    	return (isField(x,y)) ? fields[x][y][z] : null;
    }

   

    /**
     * Returns true if the field i is empty.
     *
     * @param i
     *            the index of the field (see NUMBERING)
     * @return true if the field is empty
     */
    //@ requires this.isField(i);
    //@ ensures \result == (this.getField(i) == Mark.EMPTY);
    /*@pure*/
    public Boolean isEmptyField(int x, int y) {
    	for (int z=0; z<DIM; z++){
    		if (fields[x][y][z].equals(Mark.EMPTY)){
    			return true;
    		}
    	}
    	return false;
    }

    
    /**
     * Tests if the whole student is full.
     *
     * @return true if all fields are occupied
     */
    //@ ensures \result == (\forall int i; i <= 0 & i < DIM * DIM; this.getField(i) != Mark.EMPTY);
    /*@pure*/
    public Boolean isFull() {
    	for (int x=0; x<DIM; x++){
    		for (int y=0; y<DIM; y++){
    			for (int z=0; z<DIM; z++){
    				if (fields[x][y][z].equals(Mark.EMPTY)){
    	    			return false;
    	    		}
    			}
    		}
    	}
    	return true;
    }

    

   
    public Boolean hasRow(Mark m) {
    		for (int y=0; y<DIM; y++){
    			for (int z=0; z<DIM; z++){
    				if (fields[0][y][z].equals(m)&& fields[1][y][z].equals(m)&& fields[2][y][z].equals(m)&& fields[3][y][z].equals(m)){
    	    			
    					return true;
    	    			
    	    		}
    			}
    		}
    		return false;
    }

    /**
     * Checks whether there is a column which is full and only contains the mark
     * m.
     *
     * @param m
     *            the mark of interest
     * @return true if there is a column controlled by m
     */
    /*@ pure */
    public Boolean hasColumn(Mark m) {
		for (int x=0; x<DIM; x++){
			for (int z=0; z<DIM; z++){
				if (fields[x][0][z].equals(m)&& fields[x][1][z].equals(m)&& fields[x][2][z].equals(m)&& fields[x][3][z].equals(m)){
					
					return true;
	    		}
			}
		}
		return false;
}
    
    public Boolean hasPillar(Mark m) {
		for (int x=0; x<DIM; x++){
			for (int y=0; y<DIM; y++){
				if (fields[x][y][0].equals(m)&& fields[x][y][1].equals(m)&& fields[x][y][2].equals(m)&& fields[x][y][3].equals(m)){
					
					return true;
	    		}
			}
		}
		return false;
}

   
    public Boolean hasDiagonal(Mark m) {
		// Diagonal for X-Y plane
    	for (int z=0; z<DIM; z++){
    		if (fields[0][0][z].equals(m)&& fields[1][1][z].equals(m)&& fields[2][2][z].equals(m)&& fields[3][3][z].equals(m)){
    			
    			return true;
    		}
    		else if (fields[0][3][z].equals(m)&& fields[1][2][z].equals(m)&& fields[2][1][z].equals(m)&& fields[3][0][z].equals(m)){
    			
    			return true;
    		}
    	}
    	// Diagonal for X-Z Plane
    	for (int y=0; y<DIM; y++){
    		if (fields[0][y][0].equals(m)&& fields[1][y][1].equals(m)&& fields[2][y][2].equals(m)&& fields[3][y][3].equals(m)){
    		
    			return true;
    		}
    		else if (fields[0][y][3].equals(m)&& fields[1][y][2].equals(m)&& fields[2][y][1].equals(m)&& fields[3][y][0].equals(m)){
    			
    			return true;
    		}
    	}
    	// Diagonal for Y-Z Plane
    	for (int x=0; x<DIM; x++){
    		if (fields[x][0][0].equals(m)&& fields[x][1][1].equals(m)&& fields[x][2][2].equals(m)&& fields[x][3][3].equals(m)){
    		
    			return true;
    		}
    		else if (fields[x][0][3].equals(m)&& fields[x][1][2].equals(m)&& fields[x][2][1].equals(m)&& fields[x][3][0].equals(m)){
    			
    			return true;
    		}
    	}
    	// Diagonal for Cube
    		if (fields[0][0][0].equals(m)&& fields[1][1][1].equals(m)&& fields[2][2][2].equals(m)&& fields[3][3][3].equals(m)){
    			
    			return true;
    		}
    		else if (fields[0][0][3].equals(m)&& fields[1][1][2].equals(m)&& fields[2][2][1].equals(m)&& fields[3][3][0].equals(m)){
    			
    			return true;
    		}
    		if (fields[3][0][0].equals(m)&& fields[2][1][1].equals(m)&& fields[1][2][2].equals(m)&& fields[0][3][3].equals(m)){
    			
    			return true;
    		}
    		else if (fields[3][0][3].equals(m)&& fields[2][1][2].equals(m)&& fields[1][2][1].equals(m)&& fields[0][3][0].equals(m)){
    			
    			return true;
    		}
    	
		return false;
}


    /**
     * Checks if the mark m has won. A mark wins if it controls at
     * least one row, column or diagonal.
     *
     * @param m
     *            the mark of interest
     * @return true if the mark has won
     */
    //@requires m == Mark.REDDD | m == Mark.YELLO;
    //@ ensures \result == this.hasRow(m) || this.hasColumn(m) | this.hasDiagonal(m);
    /*@ pure */
    public Boolean isWinner(Mark m) {
    	return (hasRow(m) || hasColumn(m) || hasDiagonal(m) || hasPillar(m));
    }

    /**
     * Returns true if the game has a winner. This is the case when one of the
     * marks controls at least one row, column or diagonal.
     *
     * @return true if the student has a winner.
     */
    //@ ensures \result == isWinner(Mark.REDDD) | \result == isWinner(Mark.YELLO);
    /*@pure*/
    public boolean hasWinner() {
    	return (isWinner(Mark.REDDD) || isWinner(Mark.YELLO));
    }
    
    /**
     * Returns true if the game is over. The game is over when there is a winner
     * or the whole student is full.
     *
     * @return true if the game is over
     */
    //@ ensures \result == this.isFull() || this.hasWinner();
    /*@pure*/
    public Boolean gameOver() {
    	return this.isFull() || this.hasWinner();
    }
    /**
     * Returns a String representation of this student. In addition to the current
     * situation, the String also shows the numbering of the fields.
     *
     * @return the game situation as String
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < DIM; i++) {
            String row = "";
            for (int j = 0; j < DIM; j++) {
                row = row + " " + getField(i, j, 0).toString() + " ";
                row = row + " " + getField(i, j, 1).toString() + " ";
                row = row + " " + getField(i, j, 2).toString() + " ";
                row = row + " " + getField(i, j, 3).toString() + " ";
                if (j < DIM - 1) {
                    row = row + "|";
                }
            }
            s = s + row + DELIM + NUMBERING[i * 2];
            if (i < DIM - 1) {
                s = s + "\n" + LINE + DELIM + NUMBERING[i * 2 + 1] + "\n";
            }
        }
        return s;
    }

    /**
     * Empties all fields of this student (i.e., let them refer to the value
     * Mark.EMPTY).
     */
    /*@ ensures (\forall int i; 0 <= i & i < DIM * DIM;
                                this.getField(i) == Mark.EMPTY); @*/
    public void reset() {
    	for (int x=0; x<DIM; x++){
    		for (int y=0; y<DIM; y++){
    			for (int z=0; z<DIM; z++){
    				fields[x][y][z] = Mark.EMPTY;	
    			}
    		}
    	}
    	this.setChanged();
    	this.notifyObservers();
    }

    /**
     * Sets the content of field i to the mark m.
     *
     * @param i
     *            the field number (see NUMBERING)
     * @param m
     *            the mark to be placed
     */
    //@ requires this.isField(i);
    //@ ensures this.getField(i) == m;
    public void setField(int x, int y, Mark m) {
    	for (int z=0; z<DIM; z++){
    		if (fields[x][y][0].equals(Mark.EMPTY)){
    			fields[x][y][0] = m;
    			break;
    		}else if (fields[x][y][1].equals(Mark.EMPTY)){
    			fields[x][y][1] = m;
    			break;
    		}else if (fields[x][y][2].equals(Mark.EMPTY)){
    			fields[x][y][2] = m;
    			break;
    		}else if (fields[x][y][3].equals(Mark.EMPTY)){
    			fields[x][y][3] = m;
    			break;
    		}else break;
    	}
    	this.setChanged();
    	this.notifyObservers();
    }

//
    
    public boolean IsChoiceValid(int x, int y, int z){
    	// Checks if mark is in the air
    	if (z == 0){
    		return true;
    	}
    	if (z == 1){
    		return (fields[x][y][0].equals(Mark.EMPTY))?  false : true;
    	}
    	else if (z == 2){
    		return (fields[x][y][1].equals(Mark.EMPTY))?  false : true;
    	}
    	else if (z == 3){
    		return (fields[x][y][2].equals(Mark.EMPTY))?  false : true;
    	}
    	// Not valid if Z>3
    	return false;
    }
}
//