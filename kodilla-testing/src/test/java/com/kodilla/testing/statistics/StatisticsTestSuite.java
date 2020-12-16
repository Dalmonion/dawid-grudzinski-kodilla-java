package com.kodilla.testing.statistics;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StatisticsTestSuite {

    public List<String> usersListCreator(int number) {
        List<String> usersList = new ArrayList<>();
        for (int i = 1; i <= number; i ++) {
            usersList.add("user" + i);
        }
        return usersList;
    }

    @Mock
    private Statistics statisticsMock;

    @BeforeEach
    public void beforeEachTest() {
        List<String> usersList = usersListCreator(3);
        int posts = 1;
        int comments = 1;

        when(statisticsMock.userNames()).thenReturn(usersList);
        when(statisticsMock.postsCount()).thenReturn(posts);
        when(statisticsMock.commentsCount()).thenReturn(comments);
    }

    @DisplayName("Test when posts counts equals 0")
    @Test
    void testStatisticsWith0Posts() {
        // Given
        int posts = 0;
        when(statisticsMock.postsCount()).thenReturn(posts);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(posts, statisticsMock.postsCount());
        assertEquals(1, statisticsMock.commentsCount());
        assertEquals(3, statisticsMock.userNames().size());
        assertEquals(0, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(1/3.0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(0, statisticsAnalyzer.getAveragePostsPerUser());

    }

    @DisplayName("Test when posts count equals 1000")
    @Test
    void testStatisticsWith1000Posts() {
        // Given
        int posts = 1000;
        when(statisticsMock.postsCount()).thenReturn(posts);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(posts, statisticsMock.postsCount());
        assertEquals(1, statisticsMock.commentsCount());
        assertEquals(3, statisticsMock.userNames().size());
        assertEquals(1/1000.0, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(1/3.0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(1000/3.0, statisticsAnalyzer.getAveragePostsPerUser());
    }

    @DisplayName("Test when comments count equals 0")
    @Test
    void testStatisticsWith0Comments() {
        // Given
        int comments = 0;
        when(statisticsMock.commentsCount()).thenReturn(comments);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(comments, statisticsMock.commentsCount());
        assertEquals(1, statisticsMock.postsCount());
        assertEquals(3, statisticsMock.userNames().size());
        assertEquals(0, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(1/3.0, statisticsAnalyzer.getAveragePostsPerUser());
    }

    @DisplayName("Test when amount of the posts is greater than amount of the comments")
    @Test
    void testStatisticsWithGreaterAmountOfPostsThanComments() {
        // Given
        int posts = 1000;
        int comments = 100;
        when(statisticsMock.postsCount()).thenReturn(posts);
        when(statisticsMock.commentsCount()).thenReturn(comments);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(posts, statisticsMock.postsCount());
        assertEquals(comments, statisticsMock.commentsCount());
        assertEquals(3, statisticsMock.userNames().size());
        assertEquals(0.1, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(100/3.0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(1000/3.0, statisticsAnalyzer.getAveragePostsPerUser());
    }

    @DisplayName("Test when amount of the comments is greater than amount of the posts")
    @Test
    void testStatisticsWithGreaterAmountOfCommentsThanPosts() {
        // Given
        int posts = 100;
        int comments = 1000;
        when(statisticsMock.postsCount()).thenReturn(posts);
        when(statisticsMock.commentsCount()).thenReturn(comments);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(posts, statisticsMock.postsCount());
        assertEquals(comments, statisticsMock.commentsCount());
        assertEquals(3, statisticsMock.userNames().size());
        assertEquals(10, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(1000/3.0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(100/3.0, statisticsAnalyzer.getAveragePostsPerUser());
    }

    @DisplayName("Test when users count equals 0")
    @Test
    void testStatisticsWith0Users() {
        // Given
        List<String> users = usersListCreator(0);
        when(statisticsMock.userNames()).thenReturn(users);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(users, statisticsMock.userNames());
        assertEquals(1, statisticsMock.postsCount());
        assertEquals(1, statisticsMock.commentsCount());
        assertEquals(1, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(0, statisticsAnalyzer.getAveragePostsPerUser());
    }

    @DisplayName("Test when users count equals 100")
    @Test
    void testStatisticsWith100Users() {
        // Given
        List<String> users = usersListCreator(100);
        when(statisticsMock.userNames()).thenReturn(users);
        StatisticsAnalyzer statisticsAnalyzer = new StatisticsAnalyzer(statisticsMock);

        // When
        statisticsAnalyzer.calculateAdvStatistic(statisticsMock);

        // Then
        assertEquals(users, statisticsMock.userNames());
        assertEquals(1, statisticsMock.postsCount());
        assertEquals(1, statisticsMock.commentsCount());
        assertEquals(1, statisticsAnalyzer.getAverageCommentsPerPost());
        assertEquals(1/100.0, statisticsAnalyzer.getAverageCommentsPerUser());
        assertEquals(1/100.0, statisticsAnalyzer.getAveragePostsPerUser());
    }
}
