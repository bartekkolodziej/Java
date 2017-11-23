import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CSVReader {

    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    CSVReader(String filename,String delimiter){

    }

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    /**
     * @param filename  - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) try {
            parseHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    String[]current;
    boolean next() throws IOException {
        String line = reader.readLine();
        current = line.split(delimiter);
        if(line == null) return false;
        return true;
    }

    int getInt(String s){
        return Integer.parseInt(current[columnLabelsToInt.get(s)]);
    }

    int getInt(int columnIndex){
        return Integer.parseInt(current[columnIndex]);
    }

    String get(String name){
    return "asd";
    }


    public double getDouble(String fare) {
        return 2;
    }
}