import java.awt.*;
import java.io.*;

public class CVBuilder {
    public static void main(String[] args) throws IOException {
        Document cv = new Document("Jana Kowalski - CV");
        cv.addPhoto("http://www.publicdomainpictures.net/pictures/130000/velka/clip-art-smiley-face.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Języki programowania")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        cv.writeHTML(new PrintStream("cv.html","utf-8"));
        File file = new File("cv.html");
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);

    }
}
