package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class IOService {

    public static final Scanner scanner = new Scanner(System.in);

    public static void welcomeMessage() {
        System.out.println("Welcome to Sudoku Game!\n" +
                "Type numbers from 1 to 9 in specific order (This applies to columns, rows and values):\n" +
                "- example: 1,2,3 where 1 is column, 2 is row and 3 is specific number to put.\n" +
                "(Please remember about the comma between each number)\n" +
                "Then type \"SUDOKU\" to start game.");
    }

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

    public static List<Integer> choiceInput() {
        System.out.print("Enter your choice: ");
        String input = scanner.nextLine();
        List<Integer> insertValues = new ArrayList<>();
        if (input.toUpperCase().equals("SUDOKU")) {
            System.out.println("Processing");
            insertValues.add(9);
            insertValues.add(9);
            insertValues.add(9);
        } else if (input.charAt(1) != ',' || input.charAt(3) != ',' || input.length() > 5) {
            System.out.println("Wrong data");
            insertValues.add(6);
            insertValues.add(6);
            insertValues.add(6);
        } else {
            int column = Integer.parseInt(input.substring(0,1));
            int row = Integer.parseInt(input.substring(2,3));
            int value = Integer.parseInt(input.substring(4));

            insertValues.add(column);
            insertValues.add(row);
            insertValues.add(value);
        }
        return insertValues;
    }
}
