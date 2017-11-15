import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class UnorderedListTest {
    @Test
    public void addItem() throws Exception {
        UnorderedList tmp = new UnorderedList();
        tmp.addItem("item");
        assertTrue(tmp.items.get(0).content.contains("item"));
    }

    @Test
    public void writeHTML() throws Exception {
        UnorderedList tmp = new UnorderedList();
        tmp.addItem("item1");
        tmp.addItem("item2");
        tmp.addItem("item3");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream html_output = new PrintStream(os);
        tmp.writeHTML(html_output);

        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li>"));
        assertTrue(result.contains("item1"));
        assertTrue(result.contains("item2"));
        assertTrue(result.contains("item3"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains("</ul>"));

    }

}