package com.kodilla.sudoku;

public class IOService {

    public static void printBoard(SudokuBoard board) {
        for (int i = 0; i < board.getBoard().size(); i++) {
            System.out.print("| ");
            if (i == 3 || i == 6) {
                System.out.print("- - - + - - - + - - - |\n| ");
            }
            for (int j = 0; j < board.getBoard().get(i).getRow().size(); j++) {
                if (board.getBoard().get(i).getRow().get(j).getValue() == -1) {
                    System.out.print("0 ");
                } else {
                    System.out.print(board.getBoard().get(i).getRow().get(j).getValue() + " ");
                }
                if (j == 2 || j == 5) System.out.print("| ");
            }
            System.out.print("|\n");
        }
    }
}
