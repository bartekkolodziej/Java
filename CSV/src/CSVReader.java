import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CSVReader {

    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;
    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(filename));
    }

    public CSVReader(String filename, String delimiter) throws FileNotFoundException {
        this(filename);
        this.delimiter = delimiter;
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws FileNotFoundException {
        this(filename, delimiter);
        this.hasHeader = hasHeader;
        if (hasHeader) try {
            parseHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        if (hasHeader) try {
            parseHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }


    boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) return false;
        current = line.split(delimiter);

        return true;
    }

    int getInt(String columnLabel) {
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }

    int getInt(int columnIndex) {
        return Integer.parseInt(current[columnIndex]);
    }

    long getLong(int columnIndex) {
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel) {
        return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
    }

    double getDouble(int columnIndex) {
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel) {
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }

    String get(String columnLabel) {
        if (!isMissing(columnLabel))
            return current[columnLabelsToInt.get(columnLabel)];
        return "";
    }

    String get(int columnIndex) {
        if (!isMissing(columnIndex))
            return current[columnIndex];
        return "";
    }

    List<String> getColumnLabels() {
        return this.columnLabels;
    }

    int getRecordLength() {
        int length = 0;
        for (String s : current)
            length += s.length();
        return length;
    }

    boolean isMissing(int columnIndex) {     // returns true if doesn't exist
        return current[columnIndex].isEmpty();
    }

    boolean isMissing(String columnLabel) {  // returns true if doesn't exist
        return current[columnLabelsToInt.get(columnLabel)].isEmpty();
    }

    public LocalTime getTime(int columnIndex, String pattern){
        String time_string = current[columnIndex];
        LocalTime time = LocalTime.parse(time_string,DateTimeFormatter.ofPattern(pattern));
        System.out.println(time);
        return time;
    }

    public LocalDate getDate(int columnIndex, String pattern) {
        String date_string = current[columnIndex];
        LocalDate date = LocalDate.parse(date_string, DateTimeFormatter.ofPattern(pattern));
        System.out.println(date);
        return date;
    }

    public LocalDateTime getDateTime(int columnIndex, String pattern){
        String dateTime_string = current[columnIndex];
        LocalDateTime dateTime = LocalDateTime.parse(dateTime_string, DateTimeFormatter.ofPattern(pattern));
        System.out.println(dateTime);
        return dateTime;
    }


}