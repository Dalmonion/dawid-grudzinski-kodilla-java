package com.kodilla.sudoku;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SudokuTestSuite {

    @DisplayName("Testing if algorithm adds last missing value on the end of the row")
    @Test
    void testRowLast() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        for (int i = 0; i < row.getRow().size() - 1; i++) {
            row.getRow().get(i).setValue(1 + i);
        }

        //When
        processor.processRow(row);

        //Then
        assertEquals(9, row.getRow().get(8).getValue());

    }

    @DisplayName("Testing if algorithm adds last missing value on the beginning of the row")
    @Test
    void testRowFirst() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        for (int i = 1; i < row.getRow().size(); i++) {
            row.getRow().get(i).setValue(1 + i);
        }

        //When
        processor.processRow(row);

        //Then
        assertEquals(1, row.getRow().get(0).getValue());

    }

    @DisplayName("Testing if algorithm adds last missing value on the middle of the row")
    @Test
    void testRowMiddle() {
        //Given
        GameProcessor processor = new GameProcessor();
        SudokuRow row = new SudokuRow();
        for (int i = 0; i < row.getRow().size(); i++) {
            row.getRow().get(i).setValue(1 + i);
        }
        row.getRow().get(4).setValue(-1);

        //When
        processor.processRow(row);

        //Then
        assertEquals(5, row.getRow().get(4).getValue());

    }
}
