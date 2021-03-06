package com.sort;

import com.domain.User;

import java.util.Comparator;

public class Sort {

    private Sort() {
    }

    public static Comparator<User> sortByNameDOM() {
        return (user, user2) -> {
            String userName1 = user.getUserName().toUpperCase();
            String userName2 = user2.getUserName().toUpperCase();
            return userName1.compareTo(userName2);
        };
    }

    public static Comparator<User> sortByNameSAX() {
        return (user, user2) -> {
            String userName = user.getUserName().toUpperCase();
            String userName2 = user2.getUserName().toUpperCase();
            return userName.compareTo(userName2);
        };
    }

    public static Comparator<User> sortByNameSTAX() {
        return (user, user2) -> {
            String userName1 = user.getUserName().toUpperCase();
            String userName2 = user2.getUserName().toUpperCase();
            return userName1.compareTo(userName2);
        };
    }
}
