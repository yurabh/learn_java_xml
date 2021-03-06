package com;

import com.domain.User;
import com.parser.ParserDOMXml;
import com.parser.ParserSAXXml;
import com.parser.ParserSTAXXml;
import com.sort.Sort;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class Main {
    public static void main(final String[] args) {
        ParserDOMXml parserDOMXml = new ParserDOMXml();
        try {
            List<User> users = parserDOMXml.parseInputByDom(args[0]);
            Collections.sort(users, Sort.sortByNameDOM());
            parserDOMXml.writeParseDataIntoXML(users, "output.dom.xml");
        } catch (SAXException | ParserConfigurationException | IOException e) {
            Thread.currentThread().interrupt();
        }

        ParserSAXXml parseSax = new ParserSAXXml();
        try {
            List<User> usersTwo = parseSax.parseInputSax(args[0]);
            Collections.sort(usersTwo, Sort.sortByNameSAX());
            parserDOMXml.writeParseDataIntoXML(usersTwo, "output.sax.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Thread.currentThread().interrupt();
        }

        ParserSTAXXml parserStaxXml = new ParserSTAXXml();
        try {
            List<User> usersThree = parserStaxXml.parseSTAXUser(args[0]);
            Collections.sort(usersThree, Sort.sortByNameSTAX());
            parserDOMXml.writeParseDataIntoXML(usersThree, "output.stax.xml");
        } catch (FileNotFoundException | XMLStreamException e) {
            Thread.currentThread().interrupt();
        }
    }
}
