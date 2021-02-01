package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneInformationService implements InformationService {
    @Override
    public void inform(User user) {
        log.info("Sending text message to user: " + user.getUserName() + ", with confirmation of buying an item.");

    }
}
