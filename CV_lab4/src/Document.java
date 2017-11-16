import javax.print.Doc;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import java.io.*;

@XmlRootElement
public class Document {
    @XmlElement String title;
    @XmlElement Photo photo;
    @XmlElement(name="section") List<Section> sections = new ArrayList<>();


    Document(){this.title = "";}
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

       if(this.photo != null)this.photo.writeHTML(out);
       for(Section s: this.sections) s.writeHTML(out);
       out.printf("\n</body> \n</html>");
    }


    public void write(String fileName) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
