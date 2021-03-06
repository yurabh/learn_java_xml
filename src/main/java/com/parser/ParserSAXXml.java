package com.parser;

import com.domain.City;
import com.domain.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSAXXml {

    private List<User> users;

    public ParserSAXXml() {
        users = new ArrayList<>();
    }

    public List<User> parseInputSax(String input)
            throws ParserConfigurationException, SAXException, IOException {

        class SAXHandler extends DefaultHandler {
            private User user = null;
            private String content = null;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equals("User")) {
                    user = new User();
                    users.add(user);
                }
            }

            @Override
            public void endElement(String uri, String localName,
                                   String qName) {
                switch (qName) {
                    case "Name":
                        user.setUserName(content);
                        break;
                    case "Age":
                        user.setAge(Integer.valueOf(content));
                        break;
                    case "City":
                        user.setCity(City.valueOf(content));
                        break;
                    case "Surname":
                        user.setSurName(content);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                content = String.copyValueOf(ch, start, length).trim();
            }
        }

        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        parserFactor.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(new FileInputStream(input), handler);
        return users;
    }
}
