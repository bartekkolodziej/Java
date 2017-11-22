import javax.xml.bind.annotation.XmlAttribute;
import java.io.*;

public class Photo {
    @XmlAttribute
    String url;

    Photo(String url){
        this.url = url;
    }
    Photo(){this.url = "";};

    void ChangePhoto(String url){
        this.url = url;
    }



    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" >\n",url);
    }
}