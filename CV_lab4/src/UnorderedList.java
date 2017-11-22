import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    @XmlElement(name = "item")
    List<ListItem> items = new ArrayList<>();
    UnorderedList(){}

    void addItem(String item){this.items.add(new ListItem(item));}

    void writeHTML(PrintStream out){
        out.printf("<ul>");
        for(ListItem l: items) l.writeHTML(out);
        out.printf("</ul>\n");
    }
}
