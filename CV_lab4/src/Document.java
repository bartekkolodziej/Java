import javax.print.Doc;
import java.util.*;
import java.io.*;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo.ChangePhoto(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section tmp_section = new Section(sectionTitle);
        this.sections.add(tmp_section);
        return tmp_section;
    }

    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }

    void addPhoto(String url){
        this.photo = new Photo(url);
    }


    void writeHTML(PrintStream out){
       out.printf("<?xml version=\"1.0\"?>\n" +
               "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
               "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
               "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + "\n<head>"
       + "\n<title>CV</title>" + "\n<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;\n" +
               "charset=UTF-8\" />\n" +
               "</head>\n" + "<body> \n <h1>" + this.title + "</h1>");

       this.photo.writeHTML(out);
       for(Section s: this.sections) s.writeHTML(out);
       out.printf("\n</body> \n</html>");
    }


}
