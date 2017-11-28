import sun.nio.cs.UTF_32;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.UnknownFormatConversionException;

public class CSVBuilder {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        while(reader.next()) {
            try {
                int id = reader.getInt("id");
                String name = reader.get("name");
                double area = reader.getDouble("area");

                System.out.printf(Locale.US,"%d %s %f \n", id, name, area);
            } catch (NumberFormatException | UnknownFormatConversionException e) {
                e.printStackTrace();
            }
        }


    }
}
