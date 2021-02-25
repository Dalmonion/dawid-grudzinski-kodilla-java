package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuGame {

    private SudokuBoard board;

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public boolean resolveSudoku() {
        IOService.printBoard(board);
        IOService.welcomeMessage();
        List<Integer> list = new ArrayList<>(IOService.choiceInput());
        board.getBoard().get(list.get(1)-1).getRow().get(list.get(0)-1).setValue(list.get(2));
        IOService.printBoard(board);
        return false;
    }

    private void insertValue(int inserts []) {

    }
}
