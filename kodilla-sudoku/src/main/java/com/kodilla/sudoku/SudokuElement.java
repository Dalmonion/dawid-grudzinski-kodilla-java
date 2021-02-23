package com.kodilla.sudoku;


public class SudokuElement {
    public static final int EMPTY =  -1;
    private int value;

    public SudokuElement() {
        value = EMPTY;
    }

    public SudokuElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
