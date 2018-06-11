package parsing.com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XmlUtil {
    static void printPersons(List<Person> resultList) {
        resultList.stream().filter((person -> person.getCash() >= 10000)).forEach(System.out::println);
    }

    static void writePersonsIntoFile(List<Person> resultList) {
        File outputFile = new File("src/main/resources/persons.txt");
        try {
            FileWriter fileWriter = new FileWriter(outputFile, true);
            for (Person person : resultList) {
                if (person.getCash() >= 10000) {
                    fileWriter.write(person.toString() + "\n");
                    fileWriter.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
