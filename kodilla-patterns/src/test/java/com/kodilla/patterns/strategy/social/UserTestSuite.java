package com.kodilla.patterns.strategy.social;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTestSuite {
    @Test
    void testDefaultSharingStrategies() {
        //Given
        Millenials millenials = new Millenials("millenialUser");
        YGeneration yGeneration = new YGeneration("yGenerationUser");
        ZGeneration zGeneration = new ZGeneration("zGenerationUser");

        //When
        String millenialsShareResult = millenials.sharePost();
        String yGenerationShareResult = yGeneration.sharePost();
        String zGenerationShareResult = zGeneration.sharePost();

        //Then
        assertEquals("millenialUser shared post on Twitter", millenialsShareResult);
        assertEquals("yGenerationUser shared post on Facebook", yGenerationShareResult);
        assertEquals("zGenerationUser shared post on Snapchat", zGenerationShareResult);
    }

    @Test
    void testIndividualSharingStrategy() {
        //given
        Millenials millenials = new Millenials("millenialUser");

        //When
        millenials.setIndividualSocialPublisher(new SnapchatPublisher());
        String individualSharePublisherResult = millenials.sharePost();

        //Then
        assertEquals("millenialUser shared post on Snapchat", individualSharePublisherResult);
    }
}
