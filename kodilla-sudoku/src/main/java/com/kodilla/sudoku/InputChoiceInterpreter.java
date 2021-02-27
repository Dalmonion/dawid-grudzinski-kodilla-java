package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class InputChoiceInterpreter {

    public String input(String input) {
        String insertValues;
        if (input.equalsIgnoreCase("sudoku")) {
            insertValues = "SUDOKU";
        } else if (input.length() != 5 || input.charAt(1) != ',' || input.charAt(3) != ',') {
            IOService.typeMistake();
            insertValues = "-1";
        } else {
            insertValues = input.replace(",","");
        }
        return insertValues;
    }
}
