package com.kodilla.sudoku;

public class SudokuGame {

    private SudokuBoard board;

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public boolean resolveSudoku() {
        IOService.printBoard(board);
        return false;
    }
}
