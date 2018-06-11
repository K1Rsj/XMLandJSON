package parsing.com;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOM4JParser {

    public void runProcess() {
        try {
            Document document = getDocumentFromFile();
            List<Node> nodes = getNodes(document);
            List<Person> persons = parseXML(nodes);
            XmlUtil.printPersons(persons);
            XmlUtil.writePersonsIntoFile(persons);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private List<Person> parseXML(List<Node> nodes) {
        List<Person> persons = new ArrayList<>();
        for (Node node : nodes) {
            String name = node.selectSingleNode("name").getText();
            String address = node.selectSingleNode("address").getText();
            int cash = Integer.valueOf(node.selectSingleNode("cash").getText());
            String education = node.selectSingleNode("education").getText();
            Person person = new Person(name, address, cash, education);
            persons.add(person);
        }
        return persons;
    }

    private List<Node> getNodes(Document document) {
        return document.selectNodes("/catalog/notebook/person");
    }


    private Document getDocumentFromFile() throws DocumentException {
        File inputFile = new File("src/main/resources/catalog.xml");
        SAXReader reader = new SAXReader();
        return reader.read(inputFile);
    }
}