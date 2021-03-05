package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class GameProcessor {

    private static List<Backtrack> backtracks = new ArrayList<>();

    public int numberGuesser(SudokuBoard board, int rowIndex, int columnIndex) {
        int pickedValue = -1;
        pickedValue = board.getRows().get(rowIndex).getElements().get(columnIndex).getRemainingChoices().get(0);
        return pickedValue;
    }

    public boolean processNowe(SudokuBoard board) {
        for (int i = 0; i < board.getRows().size(); i++) {
            for (int j = 0; j < board.getRows().get(i).getElements().size(); j++) {
                if (board.getRows().get(i).getElements().get(j).getValue() != -1) continue;
                boolean anyActionOnRow = false;
                boolean anyActionOnColumn = false;
                boolean anyActionOnSection = false;
                try {
                    anyActionOnRow = processRowNowe(board.getRows().get(i), j);
                    anyActionOnColumn = processColumnNowe(board, j, i);
                    anyActionOnSection = processSectionNowe(board, j, i);
                } catch (OutOfChoicesException e) {
                    if (backtracks.size() > 0) {
                        board = backtracks.get(backtracks.size() - 1).getSavedBoard();
                        i = backtracks.get(backtracks.size() - 1).getRowValue();
                        j = backtracks.get(backtracks.size() - 1).getColumnValue();
                        board.getRows().get(i).getElements().get(j).removeChoice(backtracks.get(backtracks.size() - 1).getGuessedValue());
                        backtracks.remove(backtracks.size() - 1);
                    } else {
                        System.out.println("Entered Sudoku is incorrect!");
                        System.exit(0);
                    }
                }


                if (!anyActionOnRow && !anyActionOnColumn && !anyActionOnSection) {
                    int pickedValue = numberGuesser(board, i, j);
                    Backtrack backtrack = new Backtrack(i, j, pickedValue, board);
                    backtracks.add(backtrack);
                    board.getRows().get(i).getElements().get(j).setValue(pickedValue);
                }
            }
        }
        return true;
    }

    public boolean processRowNowe(SudokuRow row, int index) throws OutOfChoicesException {
        boolean anyAction = false;
        for (Integer valueFromTable : row.getElements().get(index).getRemainingChoices()) {
            boolean isInDifferentField = isInDifferentField(row, valueFromTable);
            if (isInDifferentField) row.getElements().get(index).removeChoice(valueFromTable);
            thirdOptionNowe(row, index);
            if (row.getElements().get(index).getRemainingChoices().size() == 1) {
                row.getElements().get(index).setValue(row.getElements().get(index).getRemainingChoices().get(0));
                anyAction = true;
                break;
            }

            boolean isInDifferentArray = isInDifferentArray(row, valueFromTable, row.getElements().get(index));
            if (!isInDifferentField && !isInDifferentArray) {
                row.getElements().get(index).setValue(valueFromTable);
                anyAction = true;
                break;
            }
//            thirdOptionNowe(row, index);

        }
        return anyAction;
    }

    public void secondOptionNowe(SudokuRow row, int index) {
        for (Integer valueFromTable : row.getElements().get(index).getRemainingChoices()) {
            boolean isInDifferentField = isInDifferentField(row, valueFromTable);
            boolean isInDifferentArray = isInDifferentArray(row, valueFromTable, row.getElements().get(index));
            if (!isInDifferentField && !isInDifferentArray) {
                row.getElements().get(index).setValue(valueFromTable);
                break;
            }
        }
    }

    public void thirdOptionNowe(SudokuRow row, int index) throws OutOfChoicesException {
        if (row.getElements().get(index).getRemainingChoices().size() == 1 && isInDifferentField(row, row.getElements().get(index).getValue()))
            throw new OutOfChoicesException();

    }

    public SudokuRow extractColumnNowe(SudokuBoard board, int columnNumber) {

        SudokuRow column = new SudokuRow();
        column.getElements().clear();
        for (int i = 0; i < board.getRows().size(); i++) {
            column.getElements().add(board.getRows().get(i).getElements().get(columnNumber));
        }
        return column;
    }

    public void injectColumnNowe(SudokuRow row, SudokuBoard board, int index) {

        for (int i = 0; i < board.getRows().size(); i++) {
            board.getRows().get(i).getElements().add(index, row.getElements().get(i));
            board.getRows().get(i).getElements().remove(index + 1);
        }
    }

    public boolean processColumnNowe(SudokuBoard board, int columnIndex, int valueIndex) throws OutOfChoicesException {
        SudokuRow column = extractColumnNowe(board, columnIndex);
        boolean anyAction = processRowNowe(column, valueIndex);
        injectColumnNowe(column, board, columnIndex);
        return anyAction;
    }

    public int[] sectionGuesser(int row, int column) {
        int sectionColumnNumber = -1;
        int sectionRowNumber = -1;
        if (row < 3) {
            if (column < 3) {
                sectionColumnNumber = 0;
                sectionRowNumber = 0;
            } else if (column > 2 && column < 6) {
                sectionColumnNumber = 1;
                sectionRowNumber = 0;
            } else {
                sectionColumnNumber = 2;
                sectionRowNumber = 0;
            }
        } else if (row > 2 && row < 6) {
            if (column < 3) {
                sectionColumnNumber = 0;
                sectionRowNumber = 1;
            } else if (column > 2 && column < 6) {
                sectionColumnNumber = 1;
                sectionRowNumber = 1;
            } else {
                sectionColumnNumber = 2;
                sectionRowNumber = 1;
            }
        } else {
            if (column < 3) {
                sectionColumnNumber = 0;
                sectionRowNumber = 2;
            } else if (column > 2 && column < 6) {
                sectionColumnNumber = 1;
                sectionRowNumber = 2;
            } else {
                sectionColumnNumber = 2;
                sectionRowNumber = 2;
            }
        }
        return new int[]{sectionColumnNumber, sectionRowNumber};
    }

    public SudokuRow extractSectionNowe(SudokuBoard board, int row, int column) {
        SudokuRow section = new SudokuRow();
        section.getElements().clear();
        int[] guesser = sectionGuesser(row, column);
        int sectionColumnNumber = guesser[0];
        int sectionRowNumber = guesser[1];

        for (int i = 3 * sectionRowNumber; i < 3 + (3 * sectionRowNumber); i++) {
            for (int j = 3 * sectionColumnNumber; j < 3 + (3 * sectionColumnNumber); j++) {
                section.getElements().add(board.getRows().get(i).getElements().get(j));
            }
        }
        return section;
    }

    public void injectSectionNowe(SudokuRow row, SudokuBoard board, int rowIndex, int columnIndex) {
        int[] guesser = sectionGuesser(rowIndex, columnIndex);
        int sectionColumnNumber = guesser[0];
        int sectionRowNumber = guesser[1];

        for (int i = 3 * sectionRowNumber; i < 3 + (3 * sectionRowNumber); i++) {
            for (int j = 3 * sectionColumnNumber; j < 3 + (3 * sectionColumnNumber); j++) {
                board.getRows().get(i).getElements().add(j, row.getElements().get(0));
                row.getElements().remove(0);
                board.getRows().get(i).getElements().remove(j + 1);
            }
        }
    }

    public boolean processSectionNowe(SudokuBoard board, int columnIndex, int rowIndex) throws OutOfChoicesException {
        boolean anyAction = false;
        SudokuRow section = extractSectionNowe(board, rowIndex, columnIndex);
        for (int i = 0; i < section.getElements().size(); i++) {
            if (section.getElements().get(i).getValue() == -1) {
                anyAction = processRowNowe(section, i);
                break;
            }

        }
        injectSectionNowe(section, board, rowIndex, columnIndex);
        return anyAction;
    }

    public void processRow(SudokuRow row) {
        for (int i = 0; i < row.getElements().size(); i++) {

            if (row.getElements().get(i).getValue() != -1) continue;

            for (Integer valueFromTable : row.getElements().get(i).getRemainingChoices()) {
                for (int j = 0; j < row.getElements().size(); j++) {
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
                .filter(e -> e.getValue() == value)
                .anyMatch(e -> e.getValue() == value);
    }

    public boolean isInDifferentArray(SudokuRow row, int value, SudokuElement element) {
        return row.getElements().stream()
                .filter(e -> e != element && e.getValue() == -1)
                .flatMap(e -> e.getRemainingChoices().stream())
                .anyMatch(e -> e == value);
    }

    public void secondOption(SudokuRow row) {
        for (int i = 0; i < row.getElements().size(); i++) {
            if (row.getElements().get(i).getValue() != -1) continue;

            for (Integer valueFromTable : row.getElements().get(i).getRemainingChoices()) {

                boolean isInDifferentField = isInDifferentField(row, valueFromTable);
                boolean isInDifferentArray = isInDifferentArray(row, valueFromTable, row.getElements().get(i));

                if (!isInDifferentField && !isInDifferentArray) {
                    row.getElements().get(i).setValue(valueFromTable);
                    break;
                }
            }
        }
    }

    public void thirdOption(SudokuRow row) throws OutOfChoicesException {
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

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                for (int k = 3 * i; k < 3 + (3 * i); k++) {
                    for (int l = 3 * j; l < 3 + (3 * j); l++) {
                        board.getRows().get(k).getElements().add(l, sections.get(0).getElements().get(0));
                        sections.get(0).getElements().remove(0);
                        board.getRows().get(k).getElements().remove(l + 1);
                    }
                }
                sections.remove(0);
            }
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
        for (int i = 0; i < board.getRows().size(); i++) {
            processRow(board.getRows().get(i));
        }

        processColumn(board);
        processSection(board);
        return true;
    }


}
