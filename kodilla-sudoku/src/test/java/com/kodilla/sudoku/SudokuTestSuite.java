package com.kodilla.sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SudokuTestSuite {

    @DisplayName("Testing the isInDifferentField method")
    @Test
    void testIsInDifferentField() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        IntStream.iterate(0, n -> n + 1)
                .limit(8)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));

        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));

        //When & Then
        assertFalse(processor.isInDifferentField(row, 9));
    }

    @DisplayName("Testing if algorithm adds last missing value on the end of the row")
    @Test
    void testRowLastNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();
        board.getRows().remove(0);
        SudokuRow row = new SudokuRow();
        IntStream.iterate(0, n -> n + 1)
                .limit(8)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));
//        for (int i = 0; i < row.getElements().size() - 1; i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }
        board.getRows().add(0, row);
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));

        //When
        try {
            processor.processRowNowe(board, row, 8, 0);
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }

        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(9, row.getElements().get(8).getValue());
        assertEquals(0, row.getElements().get(8).getRemainingChoices().size());

    }

    @DisplayName("Testing if algorithm adds last missing value on the beginning of the row")
    @Test
    void testRowFirstNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        SudokuBoard board = new SudokuBoard();
        board.getRows().remove(0);
        IntStream.iterate(1, n -> n + 1)
                .limit(8)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));
        board.getRows().add(0, row);
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
//        for (int i = 1; i < row.getElements().size(); i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }

        //When
        try {
            processor.processRowNowe(board, row, 0, 0);
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }
        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(1, row.getElements().get(0).getValue());

    }

    @DisplayName("Testing if algorithm adds last missing value on the middle of the row")
    @Test
    void testRowMiddleNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        SudokuBoard board = new SudokuBoard();
        board.getRows().remove(0);
        IntStream.iterate(0, n -> n + 1)
                .limit(9)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));

//        for (int i = 0; i < row.getElements().size(); i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }
        row.getElements().get(4).setValue(-1);
        board.getRows().add(0, row);
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));

        //When
        try {
            processor.processRowNowe(board, board.getRows().get(0), 4, 0);
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }
        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(5, row.getElements().get(4).getValue());

    }

    @DisplayName("Testing if injectColumn method injects column in correct way")
    @Test
    void testColumnInjectionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();
        SudokuRow row = new SudokuRow();

        for (int i = 0; i < 9; i++) {
            board.getRows().get(i).getElements().clear();
            for (int j = 0; j < 9; j++) {
                board.getRows().get(i).getElements().add(new SudokuElement(j + 1));
            }
        }
        System.out.println(board.toString());

        for (int i = 0; i < 9; i++) {
            row.getElements().get(i).setValue(i + 1);
            System.out.print(row.getElements().get(i).getValue() + " ");
        }
        System.out.println();

        //When
        processor.injectColumnNowe(row, board, 4);
        System.out.println(board.toString());

        //Then
        assertEquals(1, board.getRows().get(0).getElements().get(4).getValue());
        assertEquals(9, board.getRows().get(8).getElements().get(4).getValue());
        assertEquals(9, board.getRows().get(0).getElements().size());
    }

    @DisplayName("Testing if extractColumn method extracts column in correct way")
    @Test
    void testColumnExtractionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            board.getRows().get(i).getElements().clear();
            for (int j = 0; j < 9; j++) {
                board.getRows().get(i).getElements().add(new SudokuElement(j + 1));
            }
        }
        System.out.println(board.toString());

        //When
        SudokuRow resultAtIndexZero = processor.extractColumnNowe(board, 0);
        SudokuRow resultAtIndexFour = processor.extractColumnNowe(board, 4);

        for (int i = 0; i < resultAtIndexZero.getElements().size(); i++) {
            System.out.print(resultAtIndexZero.getElements().get(i).getValue() + " ");
        }
        System.out.println();

        for (int i = 0; i < resultAtIndexFour.getElements().size(); i++) {
            System.out.print(resultAtIndexFour.getElements().get(i).getValue() + " ");
        }
        System.out.println();


        boolean containsOnlyOneValues = resultAtIndexZero.getElements().stream()
                .map(e -> e.getValue())
                .allMatch(e -> e == 1);

        boolean containsOnlyFiveValues = resultAtIndexFour.getElements().stream()
                .map(e -> e.getValue())
                .allMatch(e -> e == 5);
        System.out.println(board.toString());
        //Then
        assertEquals(9, resultAtIndexZero.getElements().size());
        assertFalse(resultAtIndexZero.getElements().contains(1));
        assertTrue(containsOnlyOneValues);
        assertTrue(containsOnlyFiveValues);

    }

    @DisplayName("Testing if processColumn method moves values to main board column in correct order")
    @Test
    void testUpdateColumnNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < board.getRows().size(); i++) {
            board.getRows().get(i).getElements().get(0).setValue(1 + i);
        }
        board.getRows().get(3).getElements().get(0).setValue(-1);
        System.out.println(board.toString());
        //When
        try {
            processor.processColumnNowe(board, 0, 3);
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }
        System.out.println(board.toString());
        //Then
        assertEquals(4, board.getRows().get(3).getElements().get(0).getValue());
    }

    @DisplayName("Testing if extractSection method extracts section in correct way")
    @Test
    void testExtractSectionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            board.getRows().get(i).getElements().clear();
            for (int j = 0; j < 9; j++) {
                board.getRows().get(i).getElements().add(new SudokuElement(j + 1 + 9 * i));
            }
        }
        System.out.println(board.toString());

        //When
        SudokuRow resultAtSectionTopLeft = processor.extractSectionNowe(board, 0, 0);
        SudokuRow resultAtSectionMiddle = processor.extractSectionNowe(board, 4, 4);
        SudokuRow resultAtSectionBottomRight = processor.extractSectionNowe(board, 8, 8);


        for (int i = 0; i < resultAtSectionTopLeft.getElements().size(); i++) {
            System.out.print(resultAtSectionTopLeft.getElements().get(i).getValue() + " ");
        }
        System.out.println();

        for (int i = 0; i < resultAtSectionMiddle.getElements().size(); i++) {
            System.out.print(resultAtSectionMiddle.getElements().get(i).getValue() + " ");
        }
        System.out.println();

        for (int i = 0; i < resultAtSectionBottomRight.getElements().size(); i++) {
            System.out.print(resultAtSectionBottomRight.getElements().get(i).getValue() + " ");
        }
        System.out.println();

        System.out.println(board.toString());
        //Then
        assertEquals(9, resultAtSectionTopLeft.getElements().size());
        assertEquals(1, resultAtSectionTopLeft.getElements().get(0).getValue());
        assertEquals(41, resultAtSectionMiddle.getElements().get(4).getValue());
        assertEquals(81, resultAtSectionBottomRight.getElements().get(8).getValue());
    }

    @DisplayName("Testing if injectSection method injects section in correct way")
    @Test
    void testSectionjectionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();
        SudokuRow row = new SudokuRow();

        for (int i = 0; i < 9; i++) {
            board.getRows().get(i).getElements().clear();
            for (int j = 0; j < 9; j++) {
                board.getRows().get(i).getElements().add(new SudokuElement(j + 1));
            }
        }
        System.out.println(board.toString());

        for (int i = 0; i < 9; i++) {
            row.getElements().get(i).setValue(i + 9);
            System.out.print(row.getElements().get(i).getValue() + " ");
        }
        System.out.println();

        //When
        processor.injectSectionNowe(row, board, 8, 8);
        System.out.println(board.toString());

        //Then
        assertEquals(9, board.getRows().get(6).getElements().get(6).getValue());
        assertEquals(13, board.getRows().get(7).getElements().get(7).getValue());
        assertEquals(17, board.getRows().get(8).getElements().get(8).getValue());
    }

    @DisplayName("Testing if processSection method moves values to main board section in correct order")
    @Test
    void testUpdateSectionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();
        SudokuRow row = new SudokuRow();
        for (int i = 0; i < 9; i++) {
            row.getElements().get(i).setValue(i + 1);
        }
        processor.injectSectionNowe(row, board, 4, 4);

        board.getRows().get(4).getElements().get(4).setValue(-1);

        System.out.println(board.toString());
        //When
        try {
            processor.processSectionNowe(board, 4, 4);
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }
        System.out.println(board.toString());
        //Then
        assertEquals(5, board.getRows().get(4).getElements().get(4).getValue());
    }

    @DisplayName("Testing if algorithm adds first value from remaining choices array where there is no " +
            "use of such value neither in all remaining fields or theirs remainingChoice arrays")
    @Test
    void testSecondOptionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        row.getElements().get(0).setValue(1);
        row.getElements().get(1).setValue(2);
        row.getElements().get(3).setValue(3);
        row.getElements().get(4).setValue(5);
        row.getElements().get(5).setValue(6);
        row.getElements().get(6).setValue(7);
        row.getElements().get(7).setValue(8);
        row.getElements().get(8).setValue(8);

        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));

        //When
        System.out.println();
        processor.secondOptionNowe(row, 2);
//        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));


        //Then
        assertEquals(4, row.getElements().get(2).getValue());
    }

    @DisplayName("Testing if algorithm throws \"OutOfChoicesException\" when field has only one option in remainingChoices array" +
            "which has been used in different place in row, column or section")
    @Test
    void testThrowOutOfChoicesExceptionNowe() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();

        for (int i = 0; i < row.getElements().size(); i++) {
            row.getElements().get(i).setValue(1 + i);
        }


        row.getElements().get(3).setValue(-1);


        for (int i = 1; i < 10; i++) {
            if (i == 4) continue;
            row.getElements().get(3).removeChoice(i);
        }
        row.getElements().get(8).setValue(4);


        row.getElements().stream()
                .map(e -> e.getValue())
                .forEach(System.out::print);

        //When & Then
//        assertThrows(OutOfChoicesException.class, () -> processor.thirdOptionNowe(row, 3, false, false));
    }

    @DisplayName("Testing if board is cloned correctly")
    @Test
    void testBoardClone() {
        //Given
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.getRows().get(i).getElements().get(j).setValue(j + 1);
            }
        }
        //When
        SudokuBoard clonedBoard = null;
        try {
            clonedBoard = board.deepCopy();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        board.getRows().remove(0);
        for (int i = 0; i < board.getRows().size(); i++) {
            board.getRows().get(i).getElements().remove(8);
        }
        System.out.println(board.toString());

        System.out.println(clonedBoard.toString());
        //Then
        assertNotEquals(clonedBoard.getRows().size(), board.getRows().size());
    }

    @DisplayName("Testing process method for solving basic sudoku board2")
    @Test
    void testProcessTwo() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        board.getRows().get(0).getElements().get(3).setValue(2);
        board.getRows().get(0).getElements().get(4).setValue(6);
        board.getRows().get(0).getElements().get(6).setValue(7);
        board.getRows().get(0).getElements().get(8).setValue(1);

        board.getRows().get(1).getElements().get(0).setValue(6);
        board.getRows().get(1).getElements().get(1).setValue(8);
        board.getRows().get(1).getElements().get(4).setValue(7);
        board.getRows().get(1).getElements().get(7).setValue(9);

        board.getRows().get(2).getElements().get(0).setValue(1);
        board.getRows().get(2).getElements().get(1).setValue(9);
        board.getRows().get(2).getElements().get(5).setValue(4);
        board.getRows().get(2).getElements().get(6).setValue(5);

        board.getRows().get(3).getElements().get(0).setValue(8);
        board.getRows().get(3).getElements().get(1).setValue(2);
        board.getRows().get(3).getElements().get(3).setValue(1);
        board.getRows().get(3).getElements().get(7).setValue(4);

        board.getRows().get(4).getElements().get(2).setValue(4);
        board.getRows().get(4).getElements().get(3).setValue(6);
        board.getRows().get(4).getElements().get(5).setValue(2);
        board.getRows().get(4).getElements().get(6).setValue(9);

        board.getRows().get(5).getElements().get(1).setValue(5);
        board.getRows().get(5).getElements().get(5).setValue(3);
        board.getRows().get(5).getElements().get(7).setValue(2);
        board.getRows().get(5).getElements().get(8).setValue(8);

        board.getRows().get(6).getElements().get(2).setValue(9);
        board.getRows().get(6).getElements().get(3).setValue(3);
        board.getRows().get(6).getElements().get(7).setValue(7);
        board.getRows().get(6).getElements().get(8).setValue(4);

        board.getRows().get(7).getElements().get(1).setValue(4);
        board.getRows().get(7).getElements().get(4).setValue(5);
        board.getRows().get(7).getElements().get(7).setValue(3);
        board.getRows().get(7).getElements().get(8).setValue(6);

        board.getRows().get(8).getElements().get(0).setValue(7);
        board.getRows().get(8).getElements().get(2).setValue(3);
        board.getRows().get(8).getElements().get(4).setValue(1);
        board.getRows().get(8).getElements().get(5).setValue(8);

        System.out.println(board.toString());

        //When
        SudokuResult result = processor.processNowe(board);
        System.out.println(result.getBoard().toString());

        //Then
    }

    @DisplayName("Testing process method for solving basic sudoku board3")
    @Test
    void testProcessThree() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        board.getRows().get(0).getElements().get(1).setValue(2);
        board.getRows().get(0).getElements().get(3).setValue(6);
        board.getRows().get(0).getElements().get(5).setValue(8);

        board.getRows().get(1).getElements().get(0).setValue(5);
        board.getRows().get(1).getElements().get(1).setValue(8);
        board.getRows().get(1).getElements().get(5).setValue(9);
        board.getRows().get(1).getElements().get(6).setValue(7);

        board.getRows().get(2).getElements().get(4).setValue(4);

        board.getRows().get(3).getElements().get(0).setValue(3);
        board.getRows().get(3).getElements().get(1).setValue(7);
        board.getRows().get(3).getElements().get(6).setValue(5);

        board.getRows().get(4).getElements().get(0).setValue(6);
        board.getRows().get(4).getElements().get(8).setValue(4);

        board.getRows().get(5).getElements().get(2).setValue(8);
        board.getRows().get(5).getElements().get(7).setValue(1);
        board.getRows().get(5).getElements().get(8).setValue(3);

        board.getRows().get(6).getElements().get(4).setValue(2);


        board.getRows().get(7).getElements().get(2).setValue(9);
        board.getRows().get(7).getElements().get(3).setValue(8);
        board.getRows().get(7).getElements().get(7).setValue(3);
        board.getRows().get(7).getElements().get(8).setValue(6);

        board.getRows().get(8).getElements().get(3).setValue(3);
        board.getRows().get(8).getElements().get(5).setValue(6);
        board.getRows().get(8).getElements().get(7).setValue(9);

        System.out.println(board.toString());

        //When
        SudokuResult result = processor.processNowe(board);
        System.out.println(result.getBoard().toString());

        //Then
        List<Integer> testRow = new ArrayList<>();
        for (int k = 1; k < 10; k++) {
            testRow.add(k);
        }
        for (int i = 0; i < result.getBoard().getRows().size(); i++) {
            SudokuRow row = result.getBoard().getRows().get(i);
            List<Integer> rowValues = new ArrayList<>();
            for (int j = 0; j < result.getBoard().getRows().get(i).getElements().size(); j++) {
                SudokuRow section = processor.extractSectionNowe(result.getBoard(), i, j);
                SudokuRow column = processor.extractColumnNowe(result.getBoard(), j);

                List<Integer> columnValues = new ArrayList<>();
                List<Integer> sectionValues = new ArrayList<>();

                for (int k = 0; k < 9; k++) {
                    columnValues.add(column.getElements().get(k).getValue());
                    rowValues.add(row.getElements().get(k).getValue());
                    sectionValues.add(section.getElements().get(k).getValue());
                }

                for (int k = 0; k < 9;  k++) {
                    if (rowValues.contains(testRow.get(k))) rowValues.remove(Integer.valueOf(k + 1));
                    if (columnValues.contains(testRow.get(k))) columnValues.remove(Integer.valueOf(k + 1));
                    if (sectionValues.contains(testRow.get(k))) sectionValues.remove(Integer.valueOf(k + 1));
                }

                assertEquals(0, rowValues.size());
                assertEquals(0, columnValues.size());
                assertEquals(0, sectionValues.size());
            }
        }
    }

    @DisplayName("Testing process method for solving basic sudoku board4")
    @Test
    void testProcessFour() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        board.getRows().get(0).getElements().get(3).setValue(6);
        board.getRows().get(0).getElements().get(6).setValue(4);

        board.getRows().get(1).getElements().get(0).setValue(7);
        board.getRows().get(1).getElements().get(5).setValue(3);
        board.getRows().get(1).getElements().get(6).setValue(6);

        board.getRows().get(2).getElements().get(4).setValue(9);
        board.getRows().get(2).getElements().get(5).setValue(1);
        board.getRows().get(2).getElements().get(7).setValue(8);

        board.getRows().get(4).getElements().get(1).setValue(5);
        board.getRows().get(4).getElements().get(3).setValue(1);
        board.getRows().get(4).getElements().get(4).setValue(8);
        board.getRows().get(4).getElements().get(8).setValue(3);

        board.getRows().get(5).getElements().get(3).setValue(3);
        board.getRows().get(5).getElements().get(5).setValue(6);
        board.getRows().get(5).getElements().get(7).setValue(4);
        board.getRows().get(5).getElements().get(8).setValue(5);

        board.getRows().get(6).getElements().get(1).setValue(4);
        board.getRows().get(6).getElements().get(3).setValue(2);
        board.getRows().get(6).getElements().get(7).setValue(6);

        board.getRows().get(7).getElements().get(0).setValue(9);
        board.getRows().get(7).getElements().get(2).setValue(3);

        board.getRows().get(8).getElements().get(1).setValue(2);
        board.getRows().get(8).getElements().get(6).setValue(1);


        System.out.println(board.toString());

        //When
        SudokuResult result = processor.processNowe(board);
        System.out.println(result.getBoard().toString());

        //Then
        List<Integer> testRow = new ArrayList<>();
        for (int k = 1; k < 10; k++) {
            testRow.add(k);
        }
        for (int i = 0; i < result.getBoard().getRows().size(); i++) {
            SudokuRow row = result.getBoard().getRows().get(i);
            List<Integer> rowValues = new ArrayList<>();
            for (int j = 0; j < result.getBoard().getRows().get(i).getElements().size(); j++) {
                SudokuRow section = processor.extractSectionNowe(result.getBoard(), i, j);
                SudokuRow column = processor.extractColumnNowe(result.getBoard(), j);

                List<Integer> columnValues = new ArrayList<>();
                List<Integer> sectionValues = new ArrayList<>();

                for (int k = 0; k < 9; k++) {
                    columnValues.add(column.getElements().get(k).getValue());
                    rowValues.add(row.getElements().get(k).getValue());
                    sectionValues.add(section.getElements().get(k).getValue());
                }

                for (int k = 0; k < 9;  k++) {
                    if (rowValues.contains(testRow.get(k))) rowValues.remove(Integer.valueOf(k + 1));
                    if (columnValues.contains(testRow.get(k))) columnValues.remove(Integer.valueOf(k + 1));
                    if (sectionValues.contains(testRow.get(k))) sectionValues.remove(Integer.valueOf(k + 1));
                }
                if (rowValues.size() != 0) System.out.println("Rząd: " + i );
                if (columnValues.size() != 0) System.out.println("Kolumna: " + j );
                if (sectionValues.size() != 0) System.out.println("Kolumna: " + j + ", rząd: " + i);
                assertEquals(0, rowValues.size());
                assertEquals(0, columnValues.size());
                assertEquals(0, sectionValues.size());
            }
        }
    }

    @DisplayName("Testing process method for solving basic sudoku board5")
    @Test
    void testProcessFive() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        board.getRows().get(0).getElements().get(1).setValue(2);

        board.getRows().get(1).getElements().get(3).setValue(6);
        board.getRows().get(1).getElements().get(8).setValue(3);


        board.getRows().get(2).getElements().get(1).setValue(7);
        board.getRows().get(2).getElements().get(2).setValue(4);
        board.getRows().get(2).getElements().get(4).setValue(8);

        board.getRows().get(3).getElements().get(5).setValue(3);
        board.getRows().get(3).getElements().get(8).setValue(2);

        board.getRows().get(4).getElements().get(1).setValue(8);
        board.getRows().get(4).getElements().get(4).setValue(4);
        board.getRows().get(4).getElements().get(7).setValue(1);

        board.getRows().get(5).getElements().get(0).setValue(6);
        board.getRows().get(5).getElements().get(3).setValue(5);


        board.getRows().get(6).getElements().get(4).setValue(1);
        board.getRows().get(6).getElements().get(6).setValue(7);
        board.getRows().get(6).getElements().get(7).setValue(8);

        board.getRows().get(7).getElements().get(0).setValue(5);
        board.getRows().get(7).getElements().get(5).setValue(9);

        board.getRows().get(8).getElements().get(7).setValue(4);

        System.out.println(board.toString());

        //When
        SudokuResult result = processor.processNowe(board);
        System.out.println(result.getBoard().toString());

        //Then
        List<Integer> testRow = new ArrayList<>();
        for (int k = 1; k < 10; k++) {
            testRow.add(k);
        }
        for (int i = 0; i < result.getBoard().getRows().size(); i++) {
            SudokuRow row = result.getBoard().getRows().get(i);
            List<Integer> rowValues = new ArrayList<>();
            for (int j = 0; j < result.getBoard().getRows().get(i).getElements().size(); j++) {
                SudokuRow section = processor.extractSectionNowe(result.getBoard(), i, j);
                SudokuRow column = processor.extractColumnNowe(result.getBoard(), j);

                List<Integer> columnValues = new ArrayList<>();
                List<Integer> sectionValues = new ArrayList<>();

                for (int k = 0; k < 9; k++) {
                    columnValues.add(column.getElements().get(k).getValue());
                    rowValues.add(row.getElements().get(k).getValue());
                    sectionValues.add(section.getElements().get(k).getValue());
                }

                for (int k = 0; k < 9;  k++) {
                    if (rowValues.contains(testRow.get(k))) rowValues.remove(Integer.valueOf(k + 1));
                    if (columnValues.contains(testRow.get(k))) columnValues.remove(Integer.valueOf(k + 1));
                    if (sectionValues.contains(testRow.get(k))) sectionValues.remove(Integer.valueOf(k + 1));
                }
                if (rowValues.size() != 0) System.out.println("Rząd: " + i );
                if (columnValues.size() != 0) System.out.println("Kolumna: " + j );
                if (sectionValues.size() != 0) System.out.println("Kolumna: " + j + ", rząd: " + i);
                assertEquals(0, rowValues.size());
                assertEquals(0, columnValues.size());
                assertEquals(0, sectionValues.size());
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "data.csv")
    void testCosTam(String values) {
        System.out.println(values);
    }


}
