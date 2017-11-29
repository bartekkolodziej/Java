import sun.nio.cs.UTF_32;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.UnknownFormatConversionException;

public class CSVBuilder {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("titanic-part.csv",",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",true);
        while(reader.next()) try {
            int id = reader.getInt("PassengerId");
            String name = reader.get("Name");
            double fare = reader.getDouble("Fare");

            System.out.printf(Locale.US, "%d %s %f \n", id, name, fare);
        } catch (NumberFormatException | UnknownFormatConversionException e) {
            e.printStackTrace();
        }



    }
}
