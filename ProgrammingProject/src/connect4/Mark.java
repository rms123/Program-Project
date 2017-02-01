package connect4;


/**
 * Represents a mark in the connect4 game. There three possible values:
 * Mark.REDDD, Mark.YELLO and Mark.EMPTY.
 * Module 2 lab assignment
 * 
 * 
 * @version $Revision: 1.4 $
 */
public enum Mark {
    
    EMPTY, REDDD, YELLO;

    /*@
       ensures this == Mark.REDDD ==> \result == Mark.YELLO;
       ensures this == Mark.YELLO ==> \result == Mark.REDDD;
       ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
     */
    /**
     * Returns the other mark.
     * 
     * @return the other mark is this mark is not EMPTY or EMPTY
     */
    public Mark other() {
        if (this == REDDD) {
            return YELLO;
        } else if (this == YELLO) {
            return REDDD;
        } else {
            return EMPTY;
        }
    }
}
