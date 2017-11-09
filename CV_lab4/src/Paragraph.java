import java.io.PrintStream;

public class Paragraph {
    String content;

    Paragraph(String content){
        this.content = content;
    }


    void setContent(String content){
        this.content = content;
    }
    void writeHTML(PrintStream out){
        out.printf("\n<p>" + this.content + "</p>");
    }
}
