package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class GameProcessor {
    private static List<Backtrack> backtracks = new ArrayList<>();
    static int counter = 0;

    public int numberGuesser(SudokuBoard board, int rowIndex, int columnIndex) {
        int pickedValue = -1;
        pickedValue = board.getRows().get(rowIndex).getElements().get(columnIndex).getRemainingChoices().get(0);
        return pickedValue;
    }

    public SudokuResult processNowe(SudokuBoard board) {
        for (int i = 0; i < board.getRows().size(); i++) {
            for (int j = 0; j < board.getRows().get(i).getElements().size(); j++) {
                if (board.getRows().get(i).getElements().get(j).getValue() != -1) continue;
                counter++;
                boolean anyActionOnRow = false;
                boolean anyActionOnColumn = false;
                boolean anyActionOnSection = false;
                try {

                    anyActionOnRow = processRowNowe(board, board.getRows().get(i), j, i);
                    if (!anyActionOnRow) {
                        anyActionOnColumn = processColumnnnnnnnnnnnnnnnnnnnNowe(board, j, i);
                        if (!anyActionOnColumn && !board.getRows().get(i).getElements().get(j).getRemainingChoices().isEmpty())
                            anyActionOnSection = processSectionnnnnnnnnnnnnnnnNowe(board, j, i);

                    }


                } catch (OutOfChoicesException e) {
                    if (!backtracks.isEmpty()) {
                        board = backtracks.get(backtracks.size() - 1).getSavedBoard();
                        i = backtracks.get(backtracks.size() - 1).getRowValue();
                        j = backtracks.get(backtracks.size() - 1).getColumnValue() - 1;
                        board.getRows().get(i).getElements().get(j + 1).removeChoice(backtracks.get(backtracks.size() - 1).getGuessedValue());
                        backtracks.remove(backtracks.size() - 1);
                        continue;
                    } else {
                        System.out.println("Entered Sudoku is incorrect!");
                        SudokuResult result = new SudokuResult(board, false);
                        return result;
                    }
                }


                if (!anyActionOnRow && !anyActionOnColumn && !anyActionOnSection) {
//                    if (board.getRows().get(i).getElements().get(j).getRemainingChoices().isEmpty()) {
//                        board = backtracks.get(backtracks.size() - 1).getSavedBoard();
//                        i = backtracks.get(backtracks.size() - 1).getRowValue();
//                        j = backtracks.get(backtracks.size() - 1).getColumnValue() - 1;
//                        board.getRows().get(i).getElements().get(j + 1).removeChoice(backtracks.get(backtracks.size() - 1).getGuessedValue());
//                        backtracks.remove(backtracks.size() - 1);
//                    } else {
                    int pickedValue = numberGuesser(board, i, j);
                    Backtrack backtrack = new Backtrack(i, j, pickedValue, board);
                    backtracks.add(backtrack);
                    board.getRows().get(i).getElements().get(j).setValue(pickedValue);
//                    }
                }
            }
        }
        SudokuResult result = null;
        try {
            result = new SudokuResult(board.deepCopy(), true);
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean processRowNowe(SudokuBoard board, SudokuRow row, int columnIndex, int rowIndex) throws OutOfChoicesException {
        boolean anyAction = false;

        for (Integer valueFromTable : row.getElements().get(columnIndex).getRemainingChoices()) {
            boolean isInDifferentField = isInDifferentField(row, valueFromTable);
            if (isInDifferentField) row.getElements().get(columnIndex).removeChoice(valueFromTable);
            if (row.getElements().get(columnIndex).getRemainingChoices().size() == 1) {
                boolean isInDifferentColumnField = isInDifferentColumn(board, columnIndex,
                        row.getElements().get(columnIndex).getRemainingChoices().get(0));
                boolean isInDifferentSectionField = isInDifferentSection(board, rowIndex, columnIndex,
                        row.getElements().get(columnIndex).getRemainingChoices().get(0));
                thirdOptionNowe(row, columnIndex, isInDifferentColumnField, isInDifferentSectionField);
                row.getElements().get(columnIndex).setValue(row.getElements().get(columnIndex).getRemainingChoices().get(0));
                anyAction = true;
                break;
            }
            boolean isInDifferentArray = isInDifferentArray(row, valueFromTable, row.getElements().get(columnIndex));
            if (!isInDifferentField && !isInDifferentArray /*&& !isInDifferentColumnField && !isInDifferentSectionField*/) {
                boolean isInDifferentColumnField = isInDifferentColumn(board, columnIndex,
                        row.getElements().get(columnIndex).getRemainingChoices().get(0));
                boolean isInDifferentSectionField = isInDifferentSection(board, rowIndex, columnIndex,
                        row.getElements().get(columnIndex).getRemainingChoices().get(0));
                thirdOptionNowe(row, columnIndex, isInDifferentColumnField, isInDifferentSectionField);
                row.getElements().get(columnIndex).setValue(valueFromTable);
                anyAction = true;
                break;
            }

        }
        return anyAction;
    }

    public boolean processColumnnnnnnnnnnnnnnnnnnnNowe(SudokuBoard board, int columnIndex, int rowIndex) throws OutOfChoicesException {
        boolean anyAction = false;
        SudokuRow column = extractColumnNowe(board, columnIndex);

        for (Integer valueFromTable : column.getElements().get(rowIndex).getRemainingChoices()) {
            boolean isInDifferentField = isInDifferentField(column, valueFromTable);

            if (isInDifferentField) column.getElements().get(rowIndex).removeChoice(valueFromTable);

            if (column.getElements().get(rowIndex).getRemainingChoices().size() == 1) {
                boolean isInDifferentRowField = isInDifferentField(board.getRows().get(rowIndex),
                        column.getElements().get(rowIndex).getRemainingChoices().get(0));
                boolean isInDifferentSectionField = isInDifferentSection(board, rowIndex, columnIndex,
                        column.getElements().get(rowIndex).getRemainingChoices().get(0));
                thirdOptionNowe(column, rowIndex, isInDifferentRowField, isInDifferentSectionField);
                column.getElements().get(rowIndex).setValue(column.getElements().get(rowIndex).getRemainingChoices().get(0));
                anyAction = true;
                injectColumnNowe(column, board, columnIndex);
                break;
            }
            boolean isInDifferentArray = isInDifferentArray(column, valueFromTable, column.getElements().get(rowIndex));
            if (!isInDifferentField && !isInDifferentArray /*&& !isInDifferentColumnField && !isInDifferentSectionField*/) {
                column.getElements().get(rowIndex).setValue(valueFromTable);
                anyAction = true;
                injectColumnNowe(column, board, columnIndex);
                break;
            }
        }
        return anyAction;
    }

    public int sectionValueIndex(SudokuRow section) {
        int result = -1;
        for (int i = 0; i < section.getElements().size(); i++) {
            if (section.getElements().get(i).getValue() == -1) {
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean processSectionnnnnnnnnnnnnnnnNowe(SudokuBoard board, int columnIndex, int rowIndex) throws OutOfChoicesException {
        boolean anyAction = false;
        SudokuRow section = extractSectionNowe(board, rowIndex, columnIndex);
        int valueIndex = sectionValueIndex(section);

        for (Integer valueFromTable : section.getElements().get(valueIndex).getRemainingChoices()) {
            boolean isInDifferentField = isInDifferentField(section, valueFromTable);

            if (isInDifferentField) section.getElements().get(valueIndex).removeChoice(valueFromTable);

            if (section.getElements().get(valueIndex).getRemainingChoices().size() == 1) {
                boolean isInDifferentColumnField = isInDifferentColumn(board, columnIndex,
                        valueFromTable);
                boolean isInDifferentRowField = isInDifferentField(board.getRows().get(rowIndex),
                        valueFromTable);
                thirdOptionNowe(section, valueIndex, isInDifferentColumnField, isInDifferentRowField);
                section.getElements().get(valueIndex).setValue(section.getElements().get(valueIndex).getRemainingChoices().get(0));
                injectSectionNowe(section, board, rowIndex, columnIndex);
                anyAction = true;
                break;
            }
            boolean isInDifferentArray = isInDifferentArray(section, valueFromTable, section.getElements().get(columnIndex));
            if (!isInDifferentField && !isInDifferentArray /*&& !isInDifferentColumnField && !isInDifferentSectionField*/) {

                section.getElements().get(columnIndex).setValue(valueFromTable);
                injectSectionNowe(section, board, rowIndex, columnIndex);
                anyAction = true;
                break;
            }
        }
        return anyAction;
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

    public boolean isInDifferentColumn(SudokuBoard board, int columnIndex, int value) {
        SudokuRow column = extractColumnNowe(board, columnIndex);
        return isInDifferentField(column, value);
    }

    public boolean isInDifferentSection(SudokuBoard board, int rowIndex, int columnIndex, int value) {
        SudokuRow section = extractSectionNowe(board, rowIndex, columnIndex);
        return isInDifferentField(section, value);
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

    public void thirdOptionNowe(SudokuRow row, int index, boolean isInDifferentColumnField, boolean isInDifferentSectionField) throws OutOfChoicesException {
        if (/*row.getElements().get(index).getRemainingChoices().size() == 1 && */
                (isInDifferentField(row, row.getElements().get(index).getRemainingChoices().get(0))
                        || isInDifferentColumnField || isInDifferentSectionField)) {
            throw new OutOfChoicesException();
        }


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

    public boolean processColumnNowe(SudokuBoard board, int columnIndex, int rowIndex) throws OutOfChoicesException {
        SudokuRow column = extractColumnNowe(board, columnIndex);
        boolean anyAction = processRowNowe(board, column, rowIndex, columnIndex);
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
                anyAction = processRowNowe(board, section, columnIndex, rowIndex);
                break;
            }
        }
        injectSectionNowe(section, board, rowIndex, columnIndex);
        return anyAction;
    }
}
