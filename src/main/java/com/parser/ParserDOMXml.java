package com.parser;

import com.domain.City;
import com.domain.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserDOMXml {

    private List<User> users;

    public ParserDOMXml() {
        users = new ArrayList<>();
    }

    public List<User> parseInputByDom(String input)
            throws ParserConfigurationException, IOException, SAXException {
        User user;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(input));
        NodeList nList = document.getElementsByTagName("User");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                user = new User();
                user.setUserName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                user.setAge(Integer.valueOf(eElement.getElementsByTagName("Age").item(0).getTextContent()));
                user.setCity(City.valueOf(String.valueOf(eElement.getElementsByTagName("City").item(0).getTextContent())));
                user.setSurName(eElement.getElementsByTagName("Surname").item(0).getTextContent());
                users.add(user);
            }
        }
        return users;
    }

    public boolean writeParseDataIntoXML(List<User> users, String outPutPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setAttribute(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Users");
            document.appendChild(root);

            for (User userTmp : users) {
                Element user = document.createElement("User");
                root.appendChild(user);
                Element name = document.createElement("Name");
                name.appendChild(document.createTextNode(String.valueOf(userTmp.getUserName())));
                user.appendChild(name);
                Element age = document.createElement("Age");
                age.appendChild(document.createTextNode(String.valueOf(userTmp.getAge())));
                user.appendChild(age);
                Element enumCity = document.createElement("City");
                enumCity.appendChild(document.createTextNode(String.valueOf(userTmp.getCity())));
                user.appendChild(enumCity);
                Element surName = document.createElement("Surname");
                surName.appendChild(document.createTextNode(String.valueOf(userTmp.getSurName())));
                user.appendChild(surName);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(outPutPath);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            writer.close();
        } catch (TransformerException | ParserConfigurationException | IOException ex) {
            Thread.currentThread().interrupt();
        }
        return true;
    }
}
