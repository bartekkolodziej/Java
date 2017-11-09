import java.util.*;
import java.io.*;

public class Section {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;

    Section(String Section_title){
        this.title = Section_title;
    }


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
