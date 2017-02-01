package connect4;
	

import java.util.Scanner;
import client.*;

/**
 * Class for maintaining the Tic Tac Toe game. Lab assignment Module 2
 * 
 * @author Theo Ruys en Arend Rensink
 * @version $Revision: 1.4 $
 */
public class Game extends Thread{

    // -- Instance variables -----------------------------------------

    public static final int NUMBER_PLAYERS = 2;
    private Player player1;
    private Player player2;
    private int playerAmount;
    private Client client;
    private boolean local;
    private int turn;
    private TUI tui;

    /*@
       private invariant board != null;
     */
    /**
     * The board.
     */
    private Board board;

    /*@
       private invariant players.length == NUMBER_PLAYERS;
       private invariant (\forall int i; 0 <= i && i < NUMBER_PLAYERS; players[i] != null); 
     */
    /**
     * The 2 players of the game.
     */
    private Player[] players;

    /*@
       private invariant 0 <= current  && current < NUMBER_PLAYERS;
     */
    /**
     * Index of the current player.
     *//*
    private int current;*/

    // -- Constructors -----------------------------------------------

    /*@
      requires s0 != null;
      requires s1 != null;
     */
    /**
     * Creates a new Game object.
     * 
     * @param s0
     *            the first player
     * @param s1
     *            the second player
     */
    public Game(Player s0, Player s1, boolean local) {
        board = new Board();
        player1 = s0;
        player2 = s1;
        tui = new TUI(board);
        board.addObserver(tui);
        /*players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        board.toString();*/
        if (s0 instanceof ComputerPlayer){
        	board.addObserver(((ComputerPlayer) s0).getStrategy());
        }
        if (s1 instanceof ComputerPlayer){
        	board.addObserver(((ComputerPlayer) s1).getStrategy());
        }
        this.local= local;
        playerAmount = 2;
        turn = -1;
        
    }
    
    public void run(){
    	System.out.println(tui.ToString(board));
    	while(!board.gameOver()){
    		boolean madeMove = false;
    		while(!madeMove){
    			try{
    				madeMove = true;
    				this.determineTurn().makeMove(board);
    			}catch(Exception e){
    				System.out.println(e.getMessage());
    				madeMove =false;
    			}
    		}
    	}
    	this.printResult();
    	if(local == false){
    		System.out.println("Game is finish, try another game type GAME READY");
    	}
    }
    
    public void MakeMove(Player player){
    	
    	player.makeMove(board);
    	turn++;
    	
    	if (player instanceof OnlinePlayer) {
			int[] reset = new int[2];
			reset[0] = -1;
			OnlinePlayer onlinePlayer = (OnlinePlayer) player;
			onlinePlayer.setMoveBuffer(reset);
		} else if (!local) {
			client.writeToServer(Protocol.CLIENT_MOVE); //" " + coords[0] + " " + coords[1]);
		}
    }
    
    public Player determineTurn(){
    	turn = (turn +1)%playerAmount;
    	if(turn==0){
    		return player1;
    	}else{
    		return player2;
    	}
    }
    
    public Player getWinner(){
    	if (board.isWinner(player1.getMark())){
    		return player1;
    	}
    	if (board.isWinner(player2.getMark())){
    		return player2;
    	}else{System.out.println("it's a tie");
    			return null;}
    }
    
    public void setClient(Client client){
    	this.client=client;
    }

    // -- Commands ---------------------------------------------------

    /**
     * Starts the Tic Tac Toe game. <br>
     * Asks after each ended game if the user want to continue. Continues until
     * the user does not want to play anymore.
     */
    /*public void start() {
        boolean doorgaan = true;
        while (doorgaan) {
            reset();
            play();
            doorgaan = readBoolean("\n> Play another time? (y/n)?", "y", "n");
        }
    }*/

    /**
     * Prints a question which can be answered by yes (true) or no (false).
     * After prompting the question on standard out, this method reads a String
     * from standard in and compares it to the parameters for yes and no. If the
     * user inputs a different value, the prompt is repeated and te method reads
     * input again.
     * 
     * @parom prompt the question to print
     * @param yes
     *            the String corresponding to a yes answer
     * @param no
     *            the String corresponding to a no answer
     * @return true is the yes answer is typed, false if the no answer is typed
     */
    private boolean readBoolean(String prompt, String yes, String no) {
        String answer;
        do {
            System.out.print(prompt);
            try (Scanner in = new Scanner(System.in)) {
                answer = in.hasNextLine() ? in.nextLine() : null;
            }
        } while (answer == null || (!answer.equals(yes) && !answer.equals(no)));
        return answer.equals(yes);
    }

    /**
     * Resets the game. <br>
     * The board is emptied and player[0] becomes the current player.
     */
   /* private void reset() {
        current = 0;
        board.reset();
    }*/

    /**
     * Plays the Tic Tac Toe game. <br>
     * First the (still empty) board is shown. Then the game is played until it
     * is over. Players can make a move one after the other. After each move,
     * the changed game situation is printed.
     */
    /*private void play() {
    	
        while (!board.gameOver()){
        	
        	players[current].makeMove(board);
        	current = (current +1) % NUMBER_PLAYERS;
        	update();
        }
        printResult();
    }*/

    /**
     * Prints the game situation.
     */
    private void update() {
        System.out.println("\ncurrent game situation: \n\n" + board.toString()
                + "\n");
    }

    /*@
       requires this.board.gameOver();
     */

    /**
     * Prints the result of the last game. <br>
     */
    private void printResult() {
        if (board.hasWinner()) {
            Player winner = board.isWinner(player1.getMark()) ? player1
                    : player2;
            System.out.println("Speler " + winner.getName() + " ("
                    + winner.getMark().toString() + ") has won!");
        } else {
            System.out.println("Draw. There is no winner!");
        }
    }
}
