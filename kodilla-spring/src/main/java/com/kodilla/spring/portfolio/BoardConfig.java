package com.kodilla.spring.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BoardConfig {

    @Autowired
    TaskList taskList;

    @Bean
    public Board getBoard() {
        return new Board(taskList, taskList, taskList);
    }

    @Bean
    @Scope("prototype")
    public TaskList getTaskList() {
        return new TaskList();
    }

}
