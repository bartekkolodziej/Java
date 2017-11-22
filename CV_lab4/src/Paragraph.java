import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;


public class Paragraph {
    @XmlValue
    String content;

    Paragraph(){this.content = "";}
    Paragraph(String content){
        this.content = content;
    }


    Paragraph setContent(String content){
        this.content = content;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("\n<p>" + this.content + "</p>");
    }


}
