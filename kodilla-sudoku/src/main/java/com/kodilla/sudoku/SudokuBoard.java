package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    private List<SudokuRow> board = new ArrayList<>();

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            board.add(new SudokuRow());
        }
    }

    public List<SudokuRow> getBoard() {
        return board;
    }
}
