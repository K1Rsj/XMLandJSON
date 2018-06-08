package parsing.com;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JDOMParser {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/catalog.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            Element child = classElement.getChild("notebook");
            List<Element> personsList = child.getChildren();

            List<Person> resultList = parsing(personsList);
            printAllPersons(resultList);
            writeIntoFile(resultList);

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeIntoFile(List<Person> resultList) {
        File outputFile = new File("src/main/persons.txt");
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            resultList.stream().filter(person -> person.getCash() >= 10000).forEach(s -> {
                String g = String.valueOf(s);
                try {
                    fileWriter.write(g);
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printAllPersons(List<Person> resultList) {
        resultList.stream().filter((person -> person.getCash() >= 10000)).forEach(System.out::println);
    }

    private static List<Person> parsing(List<Element> personsList) {
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
