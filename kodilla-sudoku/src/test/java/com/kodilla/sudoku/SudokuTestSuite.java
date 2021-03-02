package com.kodilla.sudoku;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SudokuTestSuite {

    @DisplayName("Testing if algorithm adds last missing value on the end of the row")
    @Test
    void testRowLast() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        IntStream.iterate(0, n -> n + 1)
                .limit(8)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));
//        for (int i = 0; i < row.getElements().size() - 1; i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));

        //When
        processor.processRow(row);

        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(9, row.getElements().get(8).getValue());

    }

    @DisplayName("Testing if algorithm adds last missing value on the beginning of the row")
    @Test
    void testRowFirst() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        IntStream.iterate(1, n -> n + 1)
                .limit(8)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
//        for (int i = 1; i < row.getElements().size(); i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }

        //When
        processor.processRow(row);
        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(1, row.getElements().get(0).getValue());

    }

    @DisplayName("Testing if algorithm adds last missing value on the middle of the row")
    @Test
    void testRowMiddle() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        IntStream.iterate(0, n -> n + 1)
                .limit(9)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));

//        for (int i = 0; i < row.getElements().size(); i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }
        row.getElements().get(4).setValue(-1);
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));

        //When
        processor.processRow(row);
        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(5, row.getElements().get(4).getValue());

    }

    @DisplayName("Testing if algorithm adds first value from remaining choices array where there is no " +
            "use of such value neither in all remaining fields or theirs arrays")
    @Test
    void testSecondOption() {
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
        processor.processRow(row);
        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
        System.out.println();

        //Then
        assertEquals(4, row.getElements().get(2).getValue());
    }

    @DisplayName("Testing if board is cloned correctly")
    @Test
    void testBoardClone() {
        //Given
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i ++) {
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
        board.getRows().clear();
        System.out.println(board.toString());
        System.out.println(clonedBoard.toString());
        //Then
        assertNotEquals(clonedBoard.getRows().size(), board.getRows().size());

    }

    @DisplayName("Testing if method extracts columns in correct way")
    @Test
    void testColumnsExtraction() {
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
        List<SudokuRow> result = processor.extractColumns(board);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).getElements().size(); j++) {
                System.out.print(result.get(i).getElements().get(j).getValue() + ", ");
            }
            System.out.println();
        }
        boolean containsOnlyOne = result.get(0).getElements().stream()
                .map(e -> e.getValue())
                .allMatch(e -> e == 1);

        boolean containsOnlyFive = result.get(4).getElements().stream()
                .map(e -> e.getValue())
                .allMatch(e -> e == 5);

        //Then
        assertEquals(9, result.size());
        assertFalse(result.get(0).getElements().contains(2));
        assertTrue( containsOnlyOne);
        assertTrue(containsOnlyFive);

    }

    @DisplayName("Testing if method extracts sections in correct way")
    @Test
    void testSectionsExtraction() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();
        int counter = 1;

        for (int i = 0; i < 9; i++) {
            board.getRows().get(i).getElements().clear();
            for (int j = 0; j < 9; j++) {
                board.getRows().get(i).getElements().add(new SudokuElement(counter));
                counter++;
            }
        }
        System.out.println(board.toString());

        //When
        List<SudokuRow> result = processor.extractBlocks(board);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).getElements().size(); j++) {
                System.out.print(result.get(i).getElements().get(j).getValue() + ", ");
            }
            System.out.println();
        }

        List<Integer> firstSectionValues = new ArrayList<>(Arrays.asList(1, 2, 3, 10, 11, 12, 19, 20, 21));
        List<Integer> middleSectionValues = new ArrayList<>(Arrays.asList(31, 32, 33, 40, 41, 42, 49, 50, 51));
        List<Integer> lastSectionValues = new ArrayList<>(Arrays.asList(61, 62, 63, 70, 71, 72, 79, 80, 81));

        //Then
        assertEquals(firstSectionValues, result.get(0).getElements().stream().map(SudokuElement::getValue).collect(Collectors.toList()));
        assertEquals(middleSectionValues, result.get(4).getElements().stream().map(SudokuElement::getValue).collect(Collectors.toList()));
        assertEquals(lastSectionValues, result.get(8).getElements().stream().map(SudokuElement::getValue).collect(Collectors.toList()));


    }

    @DisplayName("Testing if algorithm throws \"OutOfChoicesException\" when field has only one option in remainingChoices array" +
            "which has been used in different place in row, column or section")
    @Test
    void testThrowOutOfChoicesException() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        row.getElements().clear();

        for (int i = 0; i < 9; i++) {
            row.getElements().add(new SudokuElement(i));
        }

        row.getElements().get(0).setValue(-1);
        row.getElements().get(0).getRemainingChoices().clear();
        row.getElements().get(0).getRemainingChoices().add(8);

        row.getElements().stream()
                .map(e -> e.getValue())
                .forEach(System.out::print);

        //When & Then
        assertThrows(OutOfChoicesException.class, () -> processor.thirdOption(row));

    }

    @DisplayName("Testing if processColumn method moves values to main board columns in correct order")
    @Test
    void testUpdateColumns() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < board.getRows().size(); i++) {
            board.getRows().get(i).getElements().get(0).setValue(1 + i);
        }
        board.getRows().get(3).getElements().get(0).setValue(-1);
        board.getRows().get(6).getElements().get(0).setValue(-1);
        board.getRows().get(8).getElements().get(0).setValue(-1);

        //When
        processor.processColumn(board);

        //Then
        assertEquals(4, board.getRows().get(3).getElements().get(0).getValue());
        assertEquals(7, board.getRows().get(6).getElements().get(0).getValue());
        assertEquals(9, board.getRows().get(8).getElements().get(0).getValue());
    }


}
