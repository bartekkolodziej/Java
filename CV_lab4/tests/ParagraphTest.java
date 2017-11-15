import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ParagraphTest {


    @Test
    public void setContent() throws Exception {
        Paragraph tmp = new Paragraph();
        tmp.setContent("cont");
        assertTrue(tmp.content.contains("cont"));
    }

    @Test
    public void writeHTML() throws Exception {
        Paragraph tmp = new Paragraph("content");
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
        assertTrue(result.contains("content"));
    }

}