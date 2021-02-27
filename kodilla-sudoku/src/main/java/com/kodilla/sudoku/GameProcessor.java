package com.kodilla.sudoku;

public final class GameProcessor {

    public boolean processRow(SudokuRow row) {
        for (int i = 0; i < row.getRow().size(); i++) {
            for (Integer valueFromTable : row.getRow().get(i).getRemainingChoices()) {
                for (int k = 0; k < row.getRow().size(); k++) {
                    if (i == k) continue;
                    if (valueFromTable == row.getRow().get(k).getValue()) {
                        row.getRow().get(i).removeChoice(valueFromTable);
                        if (row.getRow().get(i).getRemainingChoices().size() == 1) {
                            row.getRow().get(i).setValue(row.getRow().get(i).getRemainingChoices().get(0));
                        } else if {

                        } else {

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
