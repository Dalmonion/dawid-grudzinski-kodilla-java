package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class GameProcessor {

    public void processRow(SudokuRow row) {
        for (int i = 0; i < row.getElements().size(); i++) {

            if (row.getElements().get(i).getValue() != -1) continue;

            for (Integer valueFromTable : row.getElements().get(i).getRemainingChoices()) {
                for (int j = 0; j  < row.getElements().size(); j++) {
                    if (i == j) continue;
                    if (valueFromTable == row.getElements().get(j).getValue()) {
                        row.getElements().get(i).removeChoice(valueFromTable);
                        if (row.getElements().get(i).getRemainingChoices().size() == 1) {
                            row.getElements().get(i).setValue(row.getElements().get(i).getRemainingChoices().get(0));
                        }
                    }
                }
            }
        }
        secondOption(row);
        try {
            thirdOption(row);
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }
    }

    public boolean isInDifferentField(SudokuRow row, int value) {
        return row.getElements().stream()
                .anyMatch(e -> e.getValue() == value);
    }

    public boolean isInDifferentArray(SudokuRow row, int value) {
        return row.getElements().stream()
                .flatMap(e -> e.getRemainingChoices().stream())
                .anyMatch(e -> e == value);
    }

    public void secondOption(SudokuRow row) {
        for (int i = 0; i < row.getElements().size(); i++) {
            if (row.getElements().get(i).getValue() != -1) continue;

            for (Integer valueFromTable : row.getElements().get(i).getRemainingChoices()) {

                boolean isInDifferentField = isInDifferentField(row, valueFromTable);
                boolean isInDifferentArray = isInDifferentArray(row, valueFromTable);

                if (!isInDifferentField || !isInDifferentArray) {
                    row.getElements().get(i).setValue(valueFromTable);
                    break;
                }
            }
        }
    }

    public void thirdOption(SudokuRow row) throws OutOfChoicesException{
        for (int i = 0; i < row.getElements().size(); i++) {
            if (row.getElements().get(i).getValue() != -1) continue;

            if (row.getElements().size() == 1 || isInDifferentField(row, row.getElements().get(0).getValue()))
                    throw new OutOfChoicesException();
        }
    }

    public void processColumn(SudokuBoard board) {
        List<SudokuRow> columns = extractColumns(board);

        for (int i = 0; i < columns.size(); i++) {
            processRow(columns.get(i));
        }

        for (int i = 0; i < board.getRows().size(); i++) {
            for (int j = 0; j < board.getRows().size(); j++) {
                board.getRows().get(j).getElements().add(i, columns.get(i).getElements().get(j));
                board.getRows().get(j).getElements().remove(j + 1);
            }
        }
    }

    public List<SudokuRow> extractColumns(SudokuBoard board) {
        List<SudokuRow> columns = new ArrayList<>();

        for (int i = 0; i < board.getRows().size(); i++) {
            SudokuRow column = new SudokuRow();
            column.getElements().clear();
            for (int j = 0; j < board.getRows().size(); j++) {
                column.getElements().add(board.getRows().get(j).getElements().get(i));
            }
            columns.add(column);
        }
        return columns;
    }

    public void processSection(SudokuBoard board) {
       List<SudokuRow> sections = extractBlocks(board);

       for (int i = 0; i < sections.size(); i++) {
           processRow(sections.get(i));
       }
    }

    public List<SudokuRow> extractBlocks(SudokuBoard board) {
        List<SudokuRow> sections = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SudokuRow section = new SudokuRow();
                section.getElements().clear();
                for (int k = 3 * i; k < 3 + (3 * i); k++) {
                    for (int l = 3 * j; l < 3 + (3 * j); l++) {
                        section.getElements().add(board.getRows().get(k).getElements().get(l));
                    }
                }
                sections.add(section);
            }
        }
        return sections;
    }

    public boolean process(SudokuBoard board) {

        List<SudokuRow> sections = extractBlocks(board);

        for (int i = 0; i < board.getRows().size(); i++) {
            processRow(board.getRows().get(i));
        }

        processColumn(board);



        for (int i = 0; i < sections.size(); i++) {
            processRow(sections.get(i));
        }



        return true;
    }


}
