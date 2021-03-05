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
        try {
            processor.processRowNowe(row, 8);
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
        IntStream.iterate(1, n -> n + 1)
                .limit(8)
                .forEach(n -> row.getElements().get(n).setValue(1 + n));
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));
//        for (int i = 1; i < row.getElements().size(); i++) {
//            row.getElements().get(i).setValue(1 + i);
//        }

        //When
        try {
            processor.processRowNowe(row, 0);
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
        try {
            processor.processRowNowe(row, 4);
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
        assertThrows(OutOfChoicesException.class, () -> processor.thirdOptionNowe(row, 3));

    }

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
            "use of such value neither in all remaining fields or theirs remainingChoice arrays")
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
        System.out.println();
        processor.secondOption(row);
//        System.out.println();
        IntStream.range(0, row.getElements().size())
                .forEach(n -> System.out.print(row.getElements().get(n).getValue()));


        //Then
        assertEquals(4, row.getElements().get(2).getValue());
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
        assertTrue(containsOnlyOne);
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
        System.out.println(board.toString());
        //When
        processor.processColumn(board);
        System.out.println(board.toString());
        //Then
        assertEquals(4, board.getRows().get(3).getElements().get(0).getValue());
        assertEquals(7, board.getRows().get(6).getElements().get(0).getValue());
        assertEquals(9, board.getRows().get(8).getElements().get(0).getValue());
    }

    @DisplayName("Testing if processSections method moves values to main board columns in correct order")
    @Test
    void testUpdateSections() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        board.getRows().get(0).getElements().get(0).setValue(1);
        board.getRows().get(0).getElements().get(1).setValue(-1);
        board.getRows().get(0).getElements().get(2).setValue(-1);
        board.getRows().get(1).getElements().get(0).setValue(5);
        board.getRows().get(1).getElements().get(1).setValue(-1);
        board.getRows().get(1).getElements().get(2).setValue(2);
        board.getRows().get(2).getElements().get(0).setValue(8);
        board.getRows().get(2).getElements().get(1).setValue(-1);
        board.getRows().get(2).getElements().get(2).setValue(4);

        board.getRows().get(6).getElements().get(6).setValue(1);
        board.getRows().get(6).getElements().get(7).setValue(-1);
        board.getRows().get(6).getElements().get(8).setValue(-1);
        board.getRows().get(7).getElements().get(6).setValue(5);
        board.getRows().get(7).getElements().get(7).setValue(-1);
        board.getRows().get(7).getElements().get(8).setValue(2);
        board.getRows().get(8).getElements().get(6).setValue(8);
        board.getRows().get(8).getElements().get(7).setValue(-1);
        board.getRows().get(8).getElements().get(8).setValue(4);
        System.out.println(board.toString());

        //When
        processor.processSection(board);
        System.out.println(board.toString());

        //Then
        assertEquals(3, board.getRows().get(0).getElements().get(1).getValue());
        assertEquals(6, board.getRows().get(0).getElements().get(2).getValue());
        assertEquals(7, board.getRows().get(1).getElements().get(1).getValue());
        assertEquals(9, board.getRows().get(2).getElements().get(1).getValue());
        assertEquals(3, board.getRows().get(6).getElements().get(7).getValue());
        assertEquals(6, board.getRows().get(6).getElements().get(8).getValue());
        assertEquals(7, board.getRows().get(7).getElements().get(7).getValue());
        assertEquals(9, board.getRows().get(8).getElements().get(7).getValue());

    }

    @DisplayName("Testing process method for solving basic sudoku board")
    @Test
    void testProcessNowy() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            board.getRows().get(0).getElements().get(i).setValue(i + 1);
        }

        board.getRows().get(1).getElements().get(0).setValue(5);
        board.getRows().get(3).getElements().get(0).setValue(2);
        board.getRows().get(4).getElements().get(0).setValue(3);
        board.getRows().get(5).getElements().get(0).setValue(6);
        board.getRows().get(6).getElements().get(0).setValue(7);
        board.getRows().get(7).getElements().get(0).setValue(8);
        board.getRows().get(8).getElements().get(0).setValue(9);
        board.getRows().get(1).getElements().get(1).setValue(6);
        board.getRows().get(2).getElements().get(1).setValue(8);
        board.getRows().get(2).getElements().get(2).setValue(9);
        board.getRows().get(0).getElements().get(2).setValue(-1);

        System.out.println(board.toString());

        //When
//        processor.process(board);
        try {
            processor.processRowNowe(board.getRows().get(0), 2);
            processor.processColumnNowe(board, 0, 2);
            System.out.println(board.toString());
            processor.processSectionNowe(board, 2, 1);
            System.out.println(board.toString());
        } catch (OutOfChoicesException e) {
            System.out.println(e);
        }


        //Then
        assertEquals(3, board.getRows().get(0).getElements().get(2).getValue());
        assertEquals(7, board.getRows().get(1).getElements().get(2).getValue());
        assertEquals(4, board.getRows().get(2).getElements().get(0).getValue());

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
        processor.processNowe(board);
        System.out.println(board.toString());

        //Then
        SudokuRow checkRow = new SudokuRow();
        for (int i = 0; i < 9; i++) {
            checkRow.getElements().get(i).setValue(i + 1);
        }
        for (SudokuElement element : checkRow.getElements()) {
            for (int i = 0; i < board.getRows().size(); i++) {
                assertTrue(board.getRows().get(i).getElements().contains(element));
            }
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "data.csv")
    void testCosTam(String values) {
        System.out.println(values);
    }


}
