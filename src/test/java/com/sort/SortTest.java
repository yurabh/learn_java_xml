package com.sort;

import com.domain.City;
import com.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTest {

    private List<User> users;
    private List<User> expectedSortedUsers;

    @Before
    public void beforeTest() {
        users = new ArrayList<>();
        users.add(new User("Oleg", 19, City.LVIV, "Epam"));
        users.add(new User("Andriy", 50, City.DNIPRO, "Voitovucj"));
        users.add(new User("Yura", 130, City.KUIV, "Bahlay"));
        expectedSortedUsers = new ArrayList<>();
        expectedSortedUsers.add(new User("Andriy", 50, City.DNIPRO, "Voitovucj"));
        expectedSortedUsers.add(new User("Oleg", 19, City.LVIV, "Epam"));
        expectedSortedUsers.add(new User("Yura", 130, City.KUIV, "Bahlay"));
    }

    @Test
    public void shouldSortByNameDOM() {
        Collections.sort(users, Sort.sortByNameDOM());
        Assert.assertEquals(expectedSortedUsers, users);
    }


    @Test
    public void shouldSortByNameSAX() {
        Collections.sort(users, Sort.sortByNameSAX());
        Assert.assertEquals(expectedSortedUsers, users);
    }

    @Test
    public void shouldSortByNameSTAX() {
        Collections.sort(users, Sort.sortByNameSTAX());
        Assert.assertEquals(expectedSortedUsers, users);
    }

    @After
    public void afterTest() {
        users.clear();
        expectedSortedUsers.clear();
    }
}
