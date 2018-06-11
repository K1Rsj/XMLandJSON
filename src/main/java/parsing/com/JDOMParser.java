package parsing.com;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class JDOMParser {


    private Document document;

    JDOMParser(Document document) {
        this.document = document;
    }

    public void runProcess() {
        List<Element> personsList = getListOfPersons();
        List<Person> resultList = parseXML(personsList);
        XmlUtil.printPersons(resultList);
        XmlUtil.writePersonsIntoFile(resultList);
    }

    List<Element> getListOfPersons() {
        Element classElement = document.getRootElement();
        Element child = classElement.getChild("notebook");
        return child.getChildren();
    }

    List<Person> parseXML(List<Element> personsList) {
        List<Person> resultList = new ArrayList<>();
        for (Element elementPerson : personsList) {
            String name = elementPerson.getChild("name").getText();
            String address = elementPerson.getChild("address").getText();
            int cash = Integer.parseInt(elementPerson.getChild("cash").getText());
            String education = elementPerson.getChild("education").getText();
            Person person = new Person(name, address, cash, education);
            resultList.add(person);
        }
        return resultList;
    }
}
