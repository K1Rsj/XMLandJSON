package parsing.com;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            JDOMParser jdomParser = new JDOMParser(new SAXBuilder()
                    .build(new File("src/main/resources/catalog.xml")));
            jdomParser.runProcess();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        DOM4JParser dom4JParser = new DOM4JParser();
        dom4JParser.runProcess();

        GSONParser gsonParser = new GSONParser();
        gsonParser.runProcess();
    }
}
