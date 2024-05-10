/*
* Nishika Solanki | B00953260
* CSCI 1100
* Assignment 2 | October 09, 2023
* This problem's about the game of battleship between two players with 
* special effect of area of damage when the shots are fired causing bomb like explosion on the board
*/

import java.util.*;

public class Problem2 {
    public static void main(String[] args) {

        //Creation of Scanner class to take input via console
        Scanner in = new Scanner(System.in);

        //inputs
        int number = in.nextInt();
        String[][] playerBoard1 = new String[number][number];
        String[][] playerBoard2 = new String[number][number];

        //initializing the battleship board of the two player with dashes
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                playerBoard1[i][j] = "-";
                playerBoard2[i][j] = "-";
            }
        }
    
        //positioning of the boats on the battleship board of the two player
        int numberOfTurns = in.nextInt();  
        for(int i = 0; i < numberOfTurns; i++){
            int positionX = in.nextInt();
            int positionY = in.nextInt();
            int size = in.nextInt();
            int orientation = in.nextInt();
            if (orientation == 0) {
                for (int n = 0; n < size; n++) {
                    if(positionY + n == number)
                        break;
                    playerBoard1[positionX][positionY + n] = "B";
                }
            } else if (orientation == 1) {
                for (int n = 0; n < size; n++) {
                    if(positionX+n == number)
                        break;
                    playerBoard1[positionX + n][positionY] = "B";
                }
            }

            positionX = in.nextInt();
            positionY = in.nextInt();
            size = in.nextInt();
            orientation = in.nextInt();
            if (orientation == 0) {
                for (int n = 0; n < size; n++) {
                    if(positionY + n == number) {
                        break;
                    }
                    playerBoard2[positionX][positionY + n] = "B";
                }
            }else if (orientation == 1) {
                for (int n = 0; n < size; n++) {
                    if(positionX+n == number) {
                        break;
                    }
                    playerBoard2[positionX + n][positionY] = "B";
                }

            }
        }

        //calling the method to print the player's board after the boats are placed
        printBattleship(number, playerBoard1, playerBoard2);

     
      int shotsFired = in.nextInt(); // represents how many shots will be fired by each player


        //Placing X at the targeted shots on the boards for the respective players
        for(int i = 0; i < shotsFired; i++){
            int xCoordinate = in.nextInt();
            int yCoordinate = in.nextInt();

            /* Using method- isSafe, placing last shot of player 2 which causes damage on radius of 2 in 
             * the horizontal and vertical directions and of radius 1 in the diagonal directions
            */
            if (i == (shotsFired - 1)) {
                if (playerBoard2[xCoordinate][yCoordinate] == "B" || playerBoard2[xCoordinate][yCoordinate] == "-") {
                    playerBoard2[xCoordinate][yCoordinate] = "X";
                    for (int k = xCoordinate - 2; k <= xCoordinate + 2; k++) {
                        if (isSafe(number,k,yCoordinate)) {
                            playerBoard2[k][yCoordinate] = "X";
                        }
                    }
                    for (int k = yCoordinate - 2; k <= yCoordinate + 2; k++) {
                        if (isSafe(number,xCoordinate,k)) {
                            playerBoard2[xCoordinate][k] = "X";
                        }
                    }
                    if(isSafe(number,xCoordinate-1,yCoordinate-1))
                      playerBoard2[xCoordinate-1][yCoordinate-1] = "X";
                    if(isSafe(number,xCoordinate+1,yCoordinate+1))
                      playerBoard2[xCoordinate+1][yCoordinate+1] = "X";
                    if(isSafe(number,xCoordinate-1,yCoordinate+1))
                      playerBoard2[xCoordinate-1][yCoordinate+1] = "X";
                    if(isSafe(number,xCoordinate+1,yCoordinate-1))
                      playerBoard2[xCoordinate+1][yCoordinate-1] = "X";
                }
            } else {
                if (playerBoard2[xCoordinate][yCoordinate] == "B" || playerBoard2[xCoordinate][yCoordinate] == "-") {
                    playerBoard2[xCoordinate][yCoordinate] = "X";
                }
            }



            xCoordinate = in.nextInt();
            yCoordinate = in.nextInt();

            /*Using method- isSafe, placing last shot of player 1 which causes damage on radius of 2 in 
             * the horizontal and vertical directions and of radius 1 in the diagonal directions
            */
            if (i == (shotsFired - 1)) {
                if (playerBoard1[xCoordinate][yCoordinate] == "B" || playerBoard1[xCoordinate][yCoordinate] == "-") {
                    playerBoard1[xCoordinate][yCoordinate] = "X";
                    for (int k = xCoordinate - 2; k <= xCoordinate + 2; k++) {
                        if (isSafe(number,k,yCoordinate)) {
                            playerBoard1[k][yCoordinate] = "X";
                        }
                    }
                    for (int k = yCoordinate - 2; k <= yCoordinate + 2; k++) {
                        if (isSafe(number,xCoordinate,k)) {
                            playerBoard1[xCoordinate][k] = "X";
                        }
                    }
                    if(isSafe(number,xCoordinate-1,yCoordinate-1))
                      playerBoard1[xCoordinate-1][yCoordinate-1] = "X";
                    if(isSafe(number,xCoordinate+1,yCoordinate+1))
                      playerBoard1[xCoordinate+1][yCoordinate+1] = "X";
                    if(isSafe(number,xCoordinate-1,yCoordinate+1))
                      playerBoard1[xCoordinate-1][yCoordinate+1] = "X";
                    if(isSafe(number,xCoordinate+1,yCoordinate-1))
                      playerBoard1[xCoordinate+1][yCoordinate-1] = "X";

                    

                }
            }else {
                if (playerBoard1[xCoordinate][yCoordinate] == "B" || playerBoard1[xCoordinate][yCoordinate] == "-") {
                    playerBoard1[xCoordinate][yCoordinate] = "X";
                }
            }

        }
        //Calls the method and prints the player's board after the shots are fired
        printBattleship(number, playerBoard1, playerBoard2);

        //prints the output based on the decision of the game
        System.out.println(gameDecision(number, playerBoard1, playerBoard2));
    }
    
    /**
	 * printBattleship(int,String[][], String[][]) -> String
	 * prints both the player's battleship board
	 * 
	 * @param numbers tells the size of the board
     * @param playerOneBoard 2D Array containing player one battleship board 
     * @param playerTwoBoard 2D Array containing player two battleship board 
	 * prints the battleship board of the players
	 */
    public static void printBattleship(int numbers, String[][] playerOneBoard, String[][] playerTwoBoard){

        for(int i = 0; i < numbers; i++) {
            for (int j = 0; j < numbers; j++) {
                System.out.print(playerOneBoard[i][j]);
            }
            System.out.print("\t");
            for (int j = 0; j < numbers; j++) {
                System.out.print(playerTwoBoard[i][j]);
            }
            System.out.print("\n");

        }
        System.out.println();
    }

    /**
	 * gameDecision(int,String[][], String[][]) -> String
	 * Traverse through the array and finds whether player 1 or player 2 won or
	 * whether the game was a draw or both player's had no boats left on the board i.e. all destroyed
	 * 
     * @param numbers tells the size of the board
     * @param playerOneBoard 2D Array containing player one battleship board 
     * @param playerTwoBoard 2D Array containing player two battleship board 
	 * @return String containing the decision of the game
	 */
    public static String gameDecision(int numbers, String[][] playerOneBoard, String[][] playerTwoBoard ){
        int count1 = 0;
        int count2 = 0;
        String gameState = "";
        for(int i = 0; i< numbers; i++){
            for(int j = 0; j< numbers; j++){
                if(playerOneBoard[i][j] == "B"){
                    count1++;
                }
            }
            for(int j = 0; j< numbers; j++){
                if(playerTwoBoard[i][j] == "B"){
                    count2++;
                }
            }
        }

        if(count1 > 0 && count2 > 0){
            gameState = "Draw!";
        } else if(count1 == 0 && count2 > 0){
            gameState = "P2 Won!";
        } else if(count2 == 0 && count1 > 0){
            gameState = "P1 Won!";
        } else{
            gameState = "All destroyed";
        }

        return gameState;

    }
    /**
	 * isSafe(int, int, int) -> boolean
	 * Checking if it is safe to place X without crossing the board's boundaries when players take their last shot 
	 * 
     * @param numbers tells the size of the board
     * @param coordinateX tells the x-coordinate on the board
     * @param coordinateY tells the x-coordinate on the board
	 * @return boolean, true if safe to place, and false if not safe
	 */
    public  static boolean isSafe(int number,int coordinateX, int coordinateY){
        if(coordinateX < number && coordinateY < number && coordinateX >= 0 && coordinateY >= 0 ){
            return true;
        }
        return false;
    }
}