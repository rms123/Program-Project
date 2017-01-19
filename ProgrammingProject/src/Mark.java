

/**
 * Represents a mark in the Tic Tac Toe game. There three possible values:
 * Mark.RED, Mark.YELLOW and Mark.EMPTY.
 * Module 2 lab assignment
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public enum Mark {
    
    EMPTY, RED, YELLOW;

    /*@
       ensures this == Mark.RED ==> \result == Mark.YELLOW;
       ensures this == Mark.YELLOW ==> \result == Mark.RED;
       ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
     */
    /**
     * Returns the other mark.
     * 
     * @return the other mark is this mark is not EMPTY or EMPTY
     */
    public Mark other() {
        if (this == RED) {
            return YELLOW;
        } else if (this == YELLOW) {
            return RED;
        } else {
            return EMPTY;
        }
    }
}
