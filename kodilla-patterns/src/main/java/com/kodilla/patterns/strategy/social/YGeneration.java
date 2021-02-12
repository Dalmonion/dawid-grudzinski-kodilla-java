package com.kodilla.patterns.strategy.social;

public class YGeneration extends User {
    public YGeneration(String username) {
        super(username);
        socialPublisher = new FacebookPublisher();
    }
}
