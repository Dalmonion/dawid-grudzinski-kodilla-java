package com.kodilla.exception.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SecondChallengeTestSuite {

    @DisplayName(
            "ProbablyIWillThrowException Method with X equals 1 and Y not equals 1.5"
    )
    @Test
    void testProbablyIWillThrowExceptionXEquals1() {
        //given
        SecondChallenge secondChallenge = new SecondChallenge();

        //when & then
        assertDoesNotThrow( () -> secondChallenge.probablyIWillThrowException(1.0, 1.6));
    }

    @DisplayName(
            "ProbablyIWillThrowException Method with X >= 2 and Y not equals 1.5"
    )
    @Test
    void testProbablyIWillThrowExceptionXIsBiggerOrEquals2() {
        //given
        SecondChallenge secondChallenge = new SecondChallenge();

        //when & then
        assertThrows(Exception.class, () -> secondChallenge.probablyIWillThrowException(2.0, 1.6));
    }

    @DisplayName(
            "ProbablyIWillThrowException Method with X < 1 and Y not equals 1.5"
    )
    @Test
    void testProbablyIWillThrowExceptionXIsSmallerThan1() {
        //given
        SecondChallenge secondChallenge = new SecondChallenge();

        //when & then
        assertThrows(Exception.class, () -> secondChallenge.probablyIWillThrowException(0.9, 1.6));
    }

    @DisplayName(
            "ProbablyIWillThrowException Method with X equals 1 and Y equals 1.5"
    )
    @Test
    void testProbablyIWillThrowExceptionYisDifferentThanCondition() {
        //given
        SecondChallenge secondChallenge = new SecondChallenge();

        //when & then
        assertThrows(Exception.class, () -> secondChallenge.probablyIWillThrowException(1.5, 1.5));
    }
}
