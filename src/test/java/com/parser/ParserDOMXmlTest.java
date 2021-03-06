package com.parser;

import com.domain.City;
import com.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ParserDOMXmlTest {

    private ParserDOMXml parserDOMXML = new ParserDOMXml();

    @Test
    public void shouldParseDOMXmlTest() {
        List<User> expectedUsers = Arrays.asList(
                new User("Oleg", 19, City.LVIV, "Epam"),
                new User("Andriy", 50, City.DNIPRO, "Voitovucj"),
                new User("Yura", 130, City.KUIV, "Bahlay"));
        try {
            List<User> usersDOMXml = parserDOMXML.parseInputByDom("input.xml");
            Assert.assertEquals(expectedUsers, usersDOMXml);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException | SAXException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void shoutWriteIntoFilesXml() {
        List<User> expectedUsers = Arrays.asList(
                new User("Andriy", 50, City.DNIPRO, "Voitovucj"),
                new User("Oleg", 19, City.LVIV, "Epam"),
                new User("Yura", 130, City.KUIV, "Bahlay"));
        Assert.assertTrue(parserDOMXML.writeParseDataIntoXML(expectedUsers, "output.dom.xml"));

    }

    @After
    public void clean() {
        Path pathDom = Paths.get("output.dom.xml");
        Path pathSax = Paths.get("output.sax.xml");
        Path pathStax = Paths.get("output.stax.xml");
        try {
            Files.deleteIfExists(pathDom);
            Files.deleteIfExists(pathSax);
            Files.deleteIfExists(pathStax);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
