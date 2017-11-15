import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class SectionTest {
    @Test
    public void setTitle() throws Exception {
        Section tmp = new Section();
        tmp.setTitle("title");
        assertTrue(tmp.title.contains("title"));
    }

    @Test
    public void addParagraph() throws Exception {
        Section tmp = new Section();
        tmp.addParagraph("asd");
        assertTrue(tmp.paragraps.get(0).content.contains("asd"));
    }

    @Test
    public void addParagraph1() throws Exception {
        Section tmp = new Section();
        Paragraph tmp1 = new Paragraph("qwerty");
        tmp.addParagraph(tmp1);
        assertTrue(tmp.paragraps.get(0).content.contains("qwerty"));
    }

    @Test
    public void writeHTML() throws Exception {
        Section tmp = new Section();
        tmp.setTitle("title");
        tmp.addParagraph("asd");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream html_output = new PrintStream(os);
        tmp.writeHTML(html_output);

        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains("asd"));
        assertTrue(result.contains("<h2>"));
        assertTrue(result.contains("</h2>"));
        assertTrue(result.contains("title"));
    }

}