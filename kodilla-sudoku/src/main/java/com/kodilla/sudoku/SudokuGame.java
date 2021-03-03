package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuGame {

    private final SudokuBoard board;
    private final InputChoiceInterpreter inputChoiceInterpreter = new InputChoiceInterpreter();
    private final GameProcessor processor = new GameProcessor();

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public boolean resolveSudoku() {
        System.out.println(board.toString());
        IOService.welcomeMessage();
        String input = "";
        while (true) {
            input = inputChoiceInterpreter.input(IOService.choiceInput());
            if (input.equalsIgnoreCase("sudoku")) {
                break;
            }
            if (input.length() == 3) {
                board.updateBoard(input);
                System.out.println(board.toString());
            }
        }

        System.out.println("Processing with the game");
        processor.process(board);
        System.out.println(board.toString());

        return false;
    }


}
