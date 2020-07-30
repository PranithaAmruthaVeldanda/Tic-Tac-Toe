package com.company;


import java.util.Scanner;  //required for scanner class

/**
 * This is a java program which allows 2 users to play a Tic Tac Toe board
 * game by taking turns between each other. Once a user fills up a complete
 * row, or a column or a diagonal then the game is over and user is declared
 * as winner. This program allows users to play this game as many times as
 * they wish and stops only when they wish to end.
 */

public class TicTacToe {

    int size;          //to hold the size of the board
    String[][] board;  //array to hold user input values ob board
    String userX;      //first user
    String userO;      //second user
    int row;           //to hold the row number given by users
    int column;        //to hold the column number given by users

    /**
     * Constructor TicTacToe, initializes the size of the board, and a blank
     * board using the initialBoard function and initializes the user1 and
     * user2 values to X and O.
     * @param size is the integer value to create the board
     */

    public TicTacToe (int size) {
        this.size = size;   //initializes size of the array
        board = initialBoard(size);  //initializes empty board
        userX = "X";  //initializes user1
        userO = "O";  //initializes user 2
    }

    /**
     * Method initialBoard is used to create the board with empty string values
     * so that we can display an empty board the beginning of our program.
     * @param num is the size of the board.
     * @return returns a 2d array of empty strings
     */

    public String[][] initialBoard (int num) {
        String[][] initialBoard = new String[num][num];  //creates a 2d board

        //fills the board value with empty strings using for loop
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                initialBoard[i][j] = "";
            }
        }
        return initialBoard;  //returns the board with empty strings
    }

    /**
     * Method printBoard is used to print the board before the start of the
     * game and every time a user inputs his values to the board so that user
     * gets an idea of empty locations on the board
     */

    public void printBoard() {

        //prints the column header
        for (int i =0; i<board.length; i++) {
            System.out.print("   "+i);
        }
        System.out.println();

        //prints the row header, board array values and other highlights
        //as given in sample output

        for (int i =0; i<board.length; i++) {
            System.out.print(+i +" ");  //prints the row number beside each row

            //prints the array values giving tab spaces between them
            for (int j =0; j<board.length;j++) {
                System.out.print(board[i][j]);
                System.out.print("\t| ");
            }

            //print the line separator after each row.
            System.out.println("\n  -----------");
        }
    }

    /**
     * Method ticTacToeGameWinnerDeclaration gives turns to both users one
     * after the other and check if the game is over or not after each turns
     * and declares a winner if the game is over, or declares the game as tie
     * if there is no clear winner.
     * @param keyboard takes inputs from keyboard given by users
     */

    public void ticTacToeGameWinnerDeclaration(Scanner keyboard) {
        board = initialBoard(size);  //initializes the empty board
        printBoard();           //prints the initial board with empty values
        int counter =0;        //counter to keep a count of total no of turns

        //gridSize is the total number of turns,
        // which is n * n turns, where n is size of the array
        int gridSize = size*size;

        //checks if the game is over or not by calling the gameOverOrNot
        // method and also checks if the no of turns or exceeded or not.
        // while game is not over and total count is less than total turns
        // then while loop executes
        while (!isGameOverOrNot() && counter < gridSize) {
            takingTurnsBetweenPlayers(userX, keyboard);  //calling
            // userGameChance for
            // user X

            //condition to check if game is over or not after userX input, if
            // condition satisfies, declares user X as winner
            if(isGameOverOrNot()) {
                System.out.println("Game Over! Winner is " +userX);
            }

            //if condition fails, increments the counter, and calls the
            // function userGameChance with user O.
            else {
                counter++;   //increments counter

                //checks if counter is less or not after first user, if this
                // fails the game ends and doesn't execute this if conditions
                if (counter<gridSize) {
                    takingTurnsBetweenPlayers(userO, keyboard);  //calling the
                    // user O to play

                    //check if game is over or not after user O, if yes
                    // declares user O as winner
                    if (isGameOverOrNot()) {
                        System.out.println("Game Over! Winner is " + userO);
                    }
                    //else increments the counter to keep the turn count and
                    // goes back to while loop
                    else {
                        counter++;
                    }
                }
            }
        }

        //if number of counts exceed the total size but if still game is not
        // over, declares the game as tie
        if (!isGameOverOrNot()) {
            System.out.println("Game over! It's a tie");
        }

    }

    /**
     * Method takingTurnsBetweenPlayers gives each user chance to input
     * row number and column number as per their choice and fills that
     * location with user value then prints the board after each input.
     * @param user string parameter ( user X or O )
     */

    public void takingTurnsBetweenPlayers(String user, Scanner keyboard) {
        System.out.println("\n"+ user +" , it is your chance");
        System.out.print("Which row? ");  //asks for a row number
        row = keyboard.nextInt();
        System.out.print("Which column? ");  //asks for a column number
        column = keyboard.nextInt();

        //checks the conditions if a row or column is already filled or if
        // their values exceeds the array length limits ans asks users to
        // input new values if true.
        while (row > board.length-1 || row <0 || column > board.length-1 ||
                column< 0 || !board[row][column].equals("")) {
            System.out.print("\nInput not in range or bad " +
                    "location\nPlease input other numbers" +
                    " between 0 and ");
            System.out.println(board.length-1);
            System.out.print("\nWhich row? "); //asks for a new row
            row = keyboard.nextInt();
            System.out.print("Which column? ");  //asks for a new column
            column = keyboard.nextInt();
        }

        //places the user value in the given row and column
        board[row][column] = user;

        //prints the status of the board after each input
        printBoard();
    }

    /**
     * Method checkRows, checks column elements of the row given by user and
     * keeps a count of no of equal values. If the count is equal to length
     * of board minus 1, then returns true. else returns false.
     * @param row the row value provided by user
     * @return a true if all row elements are equal, else return false
     */

    public boolean checkRows (int row) {
        int count = 0;  //to hold the count value

        //checking if all row elements are equal for that particular row
        // using for loop
        for (int j =0; j< board.length-1; j++) {

            //checks if adjacent column values are equal or not, if they are
            // equal, increments the count
            if (!board[row][j].equals("") &&
                    board[row][j].equals(board[row][j+1])) {
                count++; //increments count

            }
        }

        //returns true if count is equal to given length; else return false
        return count==board[0].length-1;
    }

    /**
     * Method checkColumns, checks row elements of the column given by user and
     * keeps a count of no of equal values. If the count is equal to length
     * of board minus 1, then returns true. else returns false.
     * @param column value given by user.
     * @return a true if all the value in that column are equal else returns
     * false
     */

    public boolean checkColumns(int column) {
        int count = 0;  //to hold the no of counts

        //iterates through row values for a particular column to check if equal
        for (int i =0; i<board.length-1; i++) {

            //condition to check if adjacent values are equal of not, if
            // equal, increments the count
            if (!board[i][column].equals("") &&
                    board[i][column].equals(board[i+1][column])) {
                count++;
            }
        }

        //returns true if the count is equal to given length
        return count==board.length-1;
    }

    /**
     * Method checkDiagonalLeftToRight checks the diagonal values from left
     * to right. The condition will be checked only when both row and column
     * values given by user are equal. else condition is not executed and it
     * directly returns a false.
     * @param row value given by user
     * @param column value given by user
     * @return a true if all the diagonal elements are equal, else false
     */
    public boolean checkDiagonalLeftToRight(int row, int column) {
        int count = 0;  //to hold the count

        //checks if given row and column are diagonal locations, if yes,
        //then check if all the diagonal elements are equal, else returns false

        if (row == column) {
            int i = 0;  //required to iterate rows
            int j = 0;  //required to iterate columns

            //iterates when i and j values are less than the length of board
            // .length-1
            while (i < board.length-1 && j < board[0].length-1) {

                //checks if the adjacent values on a diagonal or equal or not
                if (!board[i][j].equals("") &&
                        board[i][j].equals(board[i + 1][j + 1])) {

                    count++;  //if equal, increments the count
                }
                i++;  //increments the row value
                j++;  //increments column value
            }
        }

        // returns true if count is given to board.length -1, else false
        return count==board.length-1;
    }

    /**
     * Method checkDiagonalsRightToLeft checks the diagonal values from right
     * to left. The condition will be checked only when given column is equal
     * to (board.length-row) value. Else condition is not executed and it
     * directly returns a false.
     * @param row input value given by user
     * @param column input value given by user
     * @return a true if all the diagonal elements are equal, else false
     */

    public boolean checkDiagonalsRightToLeft(int row, int column) {
        int count = 0;  //to hold the count

        //checks if given row and column are diagonal locations, if yes,
        //then check if all the diagonal elements are equal, else returns false
        if (column == (board.length-1-row)) {
            int i = 0;  //required to iterate rows
            int j = board[0].length-1;  //required to iterate columns

            //iterates when i less than board length and j greater than 0
            while (i < board.length-1 && j > 0) {

                //checks the condition to see if adjacent values on diagonal
                // are equal
                if (!board[i][j].equals("") &&
                        board[i][j].equals(board[i + 1][j -1])) {

                    count++;  //if condition satisfies, increments count

                }
                i++;  //increments row value,
                j--;  //decrements column value
            }
        }

        //returns a true if count is equal to given length, else false
        return count==board.length-1;
    }

    /**
     * Method isGameOverOrNot checks if any of the check methods (checkRows,
     * checkColumns, checkDiagonalLeftToRight, checkDiagonalsRightToLeft) are
     * true or not. If either of them is true, then this method returns true
     * @return a true when other called functions are true, else false
     */

    public boolean isGameOverOrNot() {

        //returns a true if at least one functions is true, else false
        return checkRows(row) || checkColumns(column) ||
                checkDiagonalLeftToRight(row, column) ||
                checkDiagonalsRightToLeft(row,column);
    }

    /**
     * Method continuePlayingOrNot checks if user wants to continue playing
     * ticTacToe game after each game based on user input. Users will
     * continue playing the game until they wish to stop.
     * @param keyboard takes input from users to continue or not.
     */

    public void continuePlayingOrNot(Scanner keyboard){

        //asks the users if they wish to continue or not
        System.out.print("\nDo you want to continue playing the game? \n " +
                "(press yes to continue, no to quit) ");
        String yesOrNO = keyboard.next();  //required to hold user input

        //if yes, iterates the game until user wishes to stop
        while (yesOrNO.equalsIgnoreCase("yes")) {

            //resets earlier board and initializes empty board
            board = initialBoard(size);

            //continues with playing game and declares winners
            ticTacToeGameWinnerDeclaration(keyboard);

            //asks again for user input if they wish to continue or not
            System.out.print("\nDo you want to continue playing the game? \n " +
                    "(press yes to continue, no to quit) ");
            yesOrNO = keyboard.next();
        }

        //end the game and prints goodbye message if users wish to stop
        System.out.println("\nThanks for playing Tic-Tac-Toe!");
    }
}
