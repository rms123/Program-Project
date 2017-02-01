package connect4;
	

import java.util.Scanner;
import client.*;


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
    	
    	if (player instanceof OpponentPlayer) {
			int[] reset = new int[2];
			reset[0] = -1;
			OpponentPlayer onlinePlayer = (OpponentPlayer) player;
			onlinePlayer.setMoveBuffer(reset);
		} else if (!local) {
			client.writeToServer(Protocol.CLIENT_MOVE); 
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
