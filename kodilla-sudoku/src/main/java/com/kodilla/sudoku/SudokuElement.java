package com.kodilla.sudoku;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuElement {
    public static final int EMPTY =  -1;
    private int value;
    private final Integer choiceArray [] = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private List<Integer> remainingChoices = new ArrayList<>(Arrays.asList(choiceArray));


    public SudokuElement() {
        value = EMPTY;
    }

    public SudokuElement(int value) {
        this.value = value;
        remainingChoices.remove(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getRemainingChoices() {
        return new ArrayList<>(remainingChoices);
    }

    public void removeChoice(int choice) {
        remainingChoices.remove(choice);
    }
}
