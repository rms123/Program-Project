	
import java.io.*;
/**
 * Executable class for the game Tic Tac Toe. The game can be played against the
 * computer. Lab assignment Module 2
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public class ConnectFour {
	private static class UncloseableInputStream extends FilterInputStream {

        /**
         * Creates a wrapper around {@link System.in}.
         */
        UncloseableInputStream() {
            this(System.in);
        }

        /**
         * Creates a wrapper around an arbitrary {@link InputStream}.
         * @param stream The stream to wrap.
         */
        UncloseableInputStream(InputStream stream) {
            super(stream);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void close() throws IOException {
            // Don't do anything
        }
    }
	
    public static void main(String[] args) {
    	System.setIn(new UncloseableInputStream());
    	Player p1 = new HumanPlayer(args[0].toString(), Mark.YELLO);
    	//Player p2 = new HumanPlayer(args[0].toString(), Mark.REDDD);
    	Player p2 = new ComputerPlayer(Mark.REDDD, new NaiveStrategy());
    	Game game = new Game(p1,p2);
    	game.start();
    }
    
    
}