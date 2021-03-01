package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class GameProcessor {

    private void secondOption(SudokuRow row) {
        for (int i = 0; i < row.getRow().size(); i++) {

            if (row.getRow().get(i).getValue() != -1) continue;

            for (Integer valueFromTable : row.getRow().get(i).getRemainingChoices()) {

                boolean wroteSomewhereElse = false;
                boolean possibleSomewhereElse = false;

                for (int j = 0; j < row.getRow().size(); j++) {
                    if (row.getRow().get(j).getValue() == valueFromTable) wroteSomewhereElse = true;

                    for (Integer remainingChoice : row.getRow().get(j).getRemainingChoices()) {
                        if (remainingChoice == valueFromTable) {
                            possibleSomewhereElse = true;
                            break;
                        }

                    }
                }


            }
        }
    }

    public boolean processRow(SudokuRow row) {
        for (int i = 0; i < row.getRow().size(); i++) {

            if (row.getRow().get(i).getValue() != -1) continue;

            for (Integer valueFromTable : row.getRow().get(i).getRemainingChoices()) {
                for (int k = 0; k < row.getRow().size(); k++) {
                    if (i == k) continue;
                    if (valueFromTable == row.getRow().get(k).getValue()) {
                        row.getRow().get(i).removeChoice(valueFromTable);
                        if (row.getRow().get(i).getRemainingChoices().size() == 1) {
                            row.getRow().get(i).setValue(row.getRow().get(i).getRemainingChoices().get(0));
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean processColumn() {

        return true;
    }

    public boolean processBlock() {

        return true;
    }

    public boolean process(SudokuBoard board) {
        for (int i = 0; i < board.getBoard().size(); i++) {
            processRow(board.getBoard().get(i));
        }
        processColumn();
        processBlock();

        return true;
    }


}
