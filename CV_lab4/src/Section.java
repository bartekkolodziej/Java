import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.*;
import java.io.*;

public class Section {
    @XmlAttribute
    String title;
    @XmlElements(value= {
            @XmlElement(name = "paragraph", type = Paragraph.class),
            @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class)
    })
    List<Paragraph> paragraps = new ArrayList<>() ;

    Section(String Section_title){
        this.title = Section_title;
    }
    Section(){this.title = "";}


    Section setTitle(String title){
        this.title = title;
        return  this;
    }

    Section addParagraph(String paragraphText){
        this.paragraps.add(new Paragraph(paragraphText));
        return this;
    }

    Section addParagraph(Paragraph p){
        this.paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("\n<h2>" + this.title + "</h2>");
        for(Paragraph p : this.paragraps){
            p.writeHTML(out);
        }
    }

}
