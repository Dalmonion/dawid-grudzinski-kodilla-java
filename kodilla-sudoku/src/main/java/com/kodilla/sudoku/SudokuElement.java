package com.kodilla.sudoku;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SudokuElement {
    private static final int EMPTY =  -1;

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


    public List<Integer> getRemainingChoices() {
        return new ArrayList<>(remainingChoices);
    }

    public boolean removeChoice(int choice) {
        int size = remainingChoices.size();
        for (int i = 0; i < remainingChoices.size(); i++) {
            if (choice == remainingChoices.get(i)) remainingChoices.remove(i);
        }

        return size > remainingChoices.size();
    }
}
