import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {
    @XmlElement(name = "list")
    UnorderedList items = new UnorderedList();
    ParagraphWithList(){}

    ParagraphWithList setContent(String content){
        this.content = content;
        return this;
    }

    ParagraphWithList addListItem(String c) {
        items.addItem(c);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("\n<p>" + this.content + "</p>");
        items.writeHTML(out);
    }


}
