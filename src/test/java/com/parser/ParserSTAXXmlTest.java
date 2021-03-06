package com.parser;

import com.domain.City;
import com.domain.User;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class ParserSTAXXmlTest {

    private ParserSTAXXml parseInputSTAX = new ParserSTAXXml();

    @Test
    public void shouldParseDOMXmlTest() {
        List<User> expectedUsers = Arrays.asList(
                new User("Oleg", 19, City.LVIV, "Epam"),
                new User("Andriy", 50, City.DNIPRO, "Voitovucj"),
                new User("Yura", 130, City.KUIV, "Bahlay"));
        try {
            List<User> users = parseInputSTAX.parseSTAXUser("input.xml");
            Assert.assertEquals(expectedUsers, users);
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
