package com.kodilla.stream.forumuser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Forum {

    private final List<ForumUser> forumUsers = new ArrayList<>();

    public Forum() {
        forumUsers.add(new ForumUser(970612, "kerry1", 'M', LocalDate.of(1997, 6,12), 55));
        forumUsers.add(new ForumUser(920108, "dalmo", 'M', LocalDate.of(1992, 1,8), 10));
        forumUsers.add(new ForumUser(881101, "mack12", 'F', LocalDate.of(1988, 11,1), 33));
        forumUsers.add(new ForumUser(800722, "merry3", 'F', LocalDate.of(1980, 7,22), 5));
        forumUsers.add(new ForumUser(850213, "garry5", 'M', LocalDate.of(1985, 2,13), 110));
        forumUsers.add(new ForumUser(2000313, "louise", 'F', LocalDate.of(2000, 3,13), 77));
        forumUsers.add(new ForumUser(20070507, "loumos", 'M', LocalDate.of(2007, 5,7), 3));
        forumUsers.add(new ForumUser(980507, "loumos", 'M', LocalDate.of(1998, 5,7), 0));
    }

    public List<ForumUser> getUsersList() {
        return new ArrayList<>(forumUsers);
    }
}
