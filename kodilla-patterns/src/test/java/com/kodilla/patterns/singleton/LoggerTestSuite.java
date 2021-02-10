package com.kodilla.patterns.singleton;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoggerTestSuite {

    private static Logger logger;

    @BeforeAll
    public static void creatingLogs() {
        logger = Logger.INSTANCE;
        logger.log("first log");
        logger.log("second log");
    }

    @Test
    void testPreDefinedLastLog() {
        //Given
        //When
        String lastLog = logger.getLastLog();
        //Then
        assertEquals("second log", lastLog);
    }

    @Test
    void testLastLogLoadedManually() {
        //Given
        logger.log("third log");
        //When
        String lastLog = logger.getLastLog();
        //Then
        assertEquals("third log", lastLog);
    }
}
