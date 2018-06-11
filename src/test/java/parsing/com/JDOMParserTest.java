package parsing.com;

import org.jdom2.Document;
import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Unit test for simple JDOMParser.
 */
public class JDOMParserTest {

    @Test
    public void getListOfPersonsTest() {

        Document document = mock(Document.class);
        Element rootElement = mock(Element.class);
        Element element = mock(Element.class);
        List list = mock(List.class);


        JDOMParser jdomParser = new JDOMParser(document);
        when(document.getRootElement()).thenReturn(rootElement);
        when(rootElement.getChild("notebook")).thenReturn(element);
        when(element.getChildren()).thenReturn(list);
        //when(jdomParser.getListOfPersons()).thenReturn(list);

        verify(jdomParser.getListOfPersons());
    }

    @Test
    public void parseXMLTest() {
        List list = mock(List.class);
        List<Element> result = new ArrayList<>();
        JDOMParser jdomParser = mock(JDOMParser.class);

        doReturn(list).when(jdomParser).parseXML(result);
        verify(jdomParser.parseXML(result));
        Assert.assertEquals(list, jdomParser.parseXML(result));

    }
}
