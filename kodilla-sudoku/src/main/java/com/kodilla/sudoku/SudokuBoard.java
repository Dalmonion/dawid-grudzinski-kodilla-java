package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class SudokuBoard {

    private List<SudokuRow> board = new ArrayList<>();

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            board.add(new SudokuRow());
        }
    }

    public List<SudokuRow> getBoard() {
        return board;
    }

    public void updateBoard(String updateValues) {
        int row = Integer.parseInt(updateValues.substring(1,2)) - 1;
        int column = Integer.parseInt(updateValues.substring(0,1)) - 1;
        int value = Integer.parseInt(updateValues.substring(2,3));

        board.get(row).getRow().get(column).setValue(value);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < board.size(); i++) {

            result.append("| ");
            if (i == 3 || i == 6) result.append("- - - + - - - + - - - |\n| ");

            for (int j = 0; j < board.get(i).getRow().size(); j++) {
                if (board.get(i).getRow().get(j).getValue() == -1) {
                    result.append("0 ");
                } else {
                    result.append(board.get(i).getRow().get(j).getValue() + " ");
                }
                if (j == 2 || j == 5) result.append("| ");
            }
            result.append("|\n");
        }
        return result.toString();
    }
}
