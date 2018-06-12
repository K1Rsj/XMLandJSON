package parsing.com;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class GSONParserTest {

    private GSONParser gsonParser;

    @Before
    public void setUp() {
        gsonParser = mock(GSONParser.class);
    }

    @Test
    public void getJsonElementsTest() throws FileNotFoundException {
        File file = mock(File.class);
        JsonArray jsonArray = new JsonArray();
        Mockito.doReturn(jsonArray).when(gsonParser).getJsonElements(file);
        Assert.assertEquals(jsonArray, gsonParser.getJsonElements(file));
    }

    @Test
    public void getSpecificJsonTest() {
        JsonArray jsonArray = new JsonArray();
        List<JsonElement> list = new ArrayList<>();
        Mockito.doReturn(list).when(gsonParser).getSpecificJson(jsonArray);
        Assert.assertEquals(list, gsonParser.getSpecificJson(jsonArray));

    }

    @Test(expected=FileNotFoundException.class)
    public void getJsonElementsWithException() throws Exception {
        File file = mock(File.class);
        Mockito.doThrow(new FileNotFoundException()).when(gsonParser).getJsonElements(file);
        gsonParser.getJsonElements(file);
    }

}