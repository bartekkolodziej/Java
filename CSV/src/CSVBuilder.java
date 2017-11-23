import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class CSVBuilder {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("titanic-part.csv",",",true);
        while(reader.next()){
            int id = reader.getInt("PassengerId");
            String name = reader.get("Name");
            double fare = reader.getDouble("Fare");

            System.out.printf("%d %s %d",id, name, fare);
        }

    }
}
