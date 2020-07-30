package com.company;

import java.util.Scanner;

public class MainTicTacToe {

    public static final int SIZE = 3;

    public static void welcomeMessage () {
        System.out.println("Welcome to TicTacToe! Start playing.\n");
    }


    public static void main (String[] args) {
        com.company.TicTacToe ticTacToe = new com.company.TicTacToe(SIZE);
        Scanner scanner = new Scanner(System.in);
        welcomeMessage();

        ticTacToe.ticTacToeGameWinnerDeclaration(scanner);
        ticTacToe.continuePlayingOrNot(scanner);

    }
}

