package com.kodilla.sudoku;


import java.util.*;

public final class SudokuElement {
    private static final int EMPTY =  -1;

    private int value;
    private final Integer choiceArray [] = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
//    private List<Integer> remainingChoices1 = new ArrayList<>(Arrays.asList(choiceArray));
    private Set<Integer> remainingChoices = new HashSet<>(Arrays.asList(choiceArray));


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
//        for (int i = 0; i < remainingChoices.size(); i++) {
//            if (choice == remainingChoices.get(i)) remainingChoices.remove(i);
//        }
        remainingChoices.remove(choice);

        return size > remainingChoices.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SudokuElement element = (SudokuElement) o;

        if (value != element.value) return false;
        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + remainingChoices.hashCode();
        return result;
    }
}
