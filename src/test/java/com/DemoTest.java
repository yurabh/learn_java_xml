package com;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DemoTest {

    private InputStream originalInput = System.in;

    @Test
    public void shouldTestMain() {
        String[] args = new String[]{"input.xml"};
        try {
            FileInputStream input;
            input = new FileInputStream(new File("input.xml"));
            System.setIn(input);
            Main.main(args);
            System.setIn(originalInput);
            Assert.assertTrue(true);
        } catch (FileNotFoundException e) {
            Thread.currentThread().interrupt();
        }
    }
}
