package com.kodilla.sudoku;

public class Application {
    public static void main(String[] args) {

        SudokuGame theGame = new SudokuGame();
        theGame.resolveSudoku();

//        boolean gameFinished = false;
//        while (!gameFinished) {
//            SudokuGame theGame = new SudokuGame();
//            gameFinished = theGame.resolveSudoku();
//        }
    }
}
