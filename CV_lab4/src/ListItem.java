import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class ListItem {
    @XmlValue
    String content;

    ListItem(){this.content = "";}
    ListItem(String content){this.content = content;}

    void writeHTML(PrintStream out){
        out.printf("<li>" + content + "</li>\n");
    }
}
