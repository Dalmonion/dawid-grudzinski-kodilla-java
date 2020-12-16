package com.kodilla.testing.statistics;

public class StatisticsAnalyzer {

    private Statistics statistics;
    private double usersAmount;
    private double postsAmount;
    private double commentsAmount;
    private double averagePostsPerUser = -1;
    private double averageCommentsPerUser = -1;
    private double averageCommentsPerPost = -1;

    public StatisticsAnalyzer(Statistics statistics) {
        this.statistics = statistics;
    }

    public double getUsersAmount() {
        return usersAmount;
    }

    public double getPostsAmount() {
        return postsAmount;
    }

    public double getCommentsAmount() {
        return commentsAmount;
    }

    public double getAveragePostsPerUser() {
        return averagePostsPerUser;
    }

    public double getAverageCommentsPerUser() {
        return averageCommentsPerUser;
    }

    public double getAverageCommentsPerPost() {
        return averageCommentsPerPost;
    }

    public void setUsersAmount(int usersAmount) {
        this.usersAmount = usersAmount;
    }

    public void setPostsAmount(int postsAmount) {
        this.postsAmount = postsAmount;
    }

    public void setCommentsAmount(int commentsAmount) {
        this.commentsAmount = commentsAmount;
    }

    public void calculateAdvStatistic(Statistics statistics) {
        usersAmount = statistics.userNames().size();
        postsAmount = statistics.postsCount();
        commentsAmount = statistics.commentsCount();

        if (postsAmount == 0 && usersAmount != 0 && commentsAmount != 0) {
            averagePostsPerUser = 0;
            averageCommentsPerUser = commentsAmount / usersAmount;
            averageCommentsPerPost = 0;
        } else if (postsAmount != 0 && usersAmount == 0 && commentsAmount != 0) {
            averagePostsPerUser = 0;
            averageCommentsPerUser = 0;
            averageCommentsPerPost = commentsAmount / postsAmount;
        } else if (postsAmount != 0 && usersAmount != 0 && commentsAmount == 0){
            averagePostsPerUser = postsAmount / usersAmount;
            averageCommentsPerUser = 0;
            averageCommentsPerPost = 0;
        } else {
            averagePostsPerUser = postsAmount / usersAmount;
            averageCommentsPerUser = commentsAmount / usersAmount;
            averageCommentsPerPost = commentsAmount / postsAmount;
        }
    }

    public void showStatistics() {
        System.out.println("usersAmount= " + getUsersAmount() + "\npostsAmount= " + getPostsAmount() +
                "\ncommentsAmount= " + getCommentsAmount() + "\naveragePostsPerUser= " + getAveragePostsPerUser() +
                "\naverageCommentsPerUser= " + getAverageCommentsPerUser() + "\naverageCommentsPerPost= " + getAverageCommentsPerPost());
    }
}
