package connect4;



/**
 * Abstract class for keeping a player in the ConnectFour.
 * assignment.
 * 
 * 
 * 
 * @version $Revision: 1 $
 */
public abstract class Player {

    // -- Instance variables -----------------------------------------

    private String name;
    private Mark mark;

    // -- Constructors -----------------------------------------------

    /*@
       requires name != null;
       requires mark == Mark.RED || mark== Mark.YELLOW;
       ensures this.getName() == name;
       ensures this.getMark() == mark;
     */
    /**
     * Creates a new Player object.
     * 
     */
    public Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    /*@ pure */ public String getName() {
        return name;
    }

    /**
     * Returns the mark of the player.
     */
    /*@ pure */ public Mark getMark() {
        return mark;
    }

    /*@
       requires board != null & !board.isFull();
       ensures board.isField(\result) & board.isEmptyField(\result);
     */
    /**
     * Determines the field for the next move.
     * 
     * @param board
     *            the current game board
     * @return the player's choice
     */
    public abstract int determineMove(Board board);

    // -- Commands ---------------------------------------------------

    /*@
       requires board != null & !board.isFull();;
     */
    /**
     * Makes a move on the board. <br>
     * 
     * @param board
     *            the current board
     */
    public void makeMove(Board board) {
        int choice = determineMove(board);
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
        for (int i = 0; i < temp.length(); i++)
        {
            newGuess[i] = temp.charAt(i) - '0';
        }
        board.setField(newGuess[0],newGuess[1], getMark());
    }

}