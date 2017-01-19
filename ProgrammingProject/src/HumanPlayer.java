

import java.util.Scanner;



/**
 * Class for maintaining a human player in ConnectFour
 * 
 * 
 * @version $Revision: 1 $
 */
public class HumanPlayer extends Player {

    // -- Constructors -----------------------------------------------

    /*@
       requires name != null;
       requires mark == Mark.RED || mark == Mark.YELLOW;
       ensures this.getName() == name;
       ensures this.getMark() == mark;
    */
    /**
     * Creates a new human player object.
     * 
     */
    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    // -- Commands ---------------------------------------------------

    /*@
       requires board != null;
       ensures board.isField(\result) && board.isEmptyField(\result);
     */
    /**
     * Asks the user to input the field where to place the next mark. This is
     * done using the standard input/output. \
     * 
     * @param board
     *            the game board
     * @return the player's chosen field
     */
    
    public int determineMove(Board board) {
        String prompt = "> " + getName() + " (" + getMark().toString() + ")"
                + ", what is your choice for x? ";
        int choiceX = readInt(prompt);
        String prompt1 = "> " + getName() + " (" + getMark().toString() + ")"
                + ", what is your choice for Y? ";
        int choiceY = readInt(prompt1);
        String prompt2 = "> " + getName() + " (" + getMark().toString() + ")"
                + ", what is your choice for Z? ";
        int choiceZ = readInt(prompt2);
        boolean valid = board.isField(choiceX,choiceY,choiceZ) && board.isEmptyField(choiceX,choiceY,choiceZ) && board.IsChoiceValid(choiceX,choiceY,choiceZ);
        while (!valid) {
            System.out.println("ERROR: field " + choiceX + choiceY + choiceZ
                    + " is no valid choice.");
            choiceX = readInt(prompt);
            choiceY = readInt(prompt1);
            choiceZ = readInt(prompt2);
            valid = board.isField(choiceX,choiceY,choiceZ) && board.isEmptyField(choiceX,choiceY,choiceZ) && board.IsChoiceValid(choiceX,choiceY,choiceZ);
        }
        int choice[] = {choiceX, choiceY, choiceZ};

        StringBuilder strNum = new StringBuilder();

        for (int num : choice) 
        {
             strNum.append(num);
        }
        int finalInt = Integer.parseInt(strNum.toString());
        return finalInt;
    }

    /**
     * Writes a prompt to standard out and tries to read an int value from
     * standard in. This is repeated until an int value is entered.
     * 
     * @param prompt
     *            the question to prompt the user
     * @return the first int value which is entered by the user
     */
    private int readInt(String prompt) {
        int value = 0;
        boolean intRead = false;
        @SuppressWarnings("resource")
        Scanner line = new Scanner(System.in);
        do {
            System.out.print(prompt);
            try (Scanner scannerLine = new Scanner(line.nextLine());) {
                if (scannerLine.hasNextInt()) {
                    intRead = true;
                    value = scannerLine.nextInt();
                }
            }
        } while (!intRead);
        return value;
    }//

}
