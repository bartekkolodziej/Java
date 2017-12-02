import java.io.*;
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

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        if (hasHeader) try {
            parseHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CSVReader(String filename) throws FileNotFoundException {
        this(filename,",", true);
    }

    public CSVReader(String filename, String delimiter) throws FileNotFoundException {
        this(filename,delimiter,true);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws FileNotFoundException {
        this(new FileReader(filename),delimiter,hasHeader);
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
        if (line == null)
            return false;
        current = line.split(delimiter);

        return true;
    }

    int getInt(String columnLabel) {
        if (!isMissing(columnLabel))
            return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
        return 0;
    }

    int getInt(int columnIndex) {
        if(!isMissing(columnIndex))
            return Integer.parseInt(current[columnIndex]);
        return 0;
    }

    long getLong(int columnIndex) {
        if(!isMissing(columnIndex))
            return Long.parseLong(current[columnIndex]);
        return 0;
    }

    long getLong(String columnLabel) {
        if(!isMissing(columnLabel))
            return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
        return 0;
    }

    double getDouble(int columnIndex) {
        if(!isMissing(columnIndex))
            return Double.parseDouble(current[columnIndex]);
        return 0;
    }

    double getDouble(String columnLabel) {
        if(!isMissing(columnLabel))
            return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
        return 0;
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

    boolean isMissing(int columnIndex) {
        return columnIndex >= current.length || columnIndex < 0 || current[columnIndex].isEmpty() ;
    }

    boolean isMissing(String columnLabel) {
        Integer x = columnLabelsToInt.get(columnLabel);
        if(x == null)
            return true;
        return isMissing(x);
    }

    public LocalTime getTime(int columnIndex, String pattern){
        if(!isMissing(columnIndex)){
            String time_string = current[columnIndex];
            LocalTime time = LocalTime.parse(time_string,DateTimeFormatter.ofPattern(pattern));
            System.out.println(time);
            return time;
        }
        return LocalTime.MIDNIGHT;
    }

    public LocalDate getDate(int columnIndex, String pattern) {
        if(!isMissing(columnIndex)) {
            String date_string = current[columnIndex];
            LocalDate date = LocalDate.parse(date_string, DateTimeFormatter.ofPattern(pattern));
            System.out.println(date);
            return date;
        }
        return LocalDate.of(1111,11,11);
    }

    public LocalDateTime getDateTime(int columnIndex, String pattern){
        String dateTime_string = current[columnIndex];
        LocalDateTime dateTime = LocalDateTime.parse(dateTime_string, DateTimeFormatter.ofPattern(pattern));
        System.out.println(dateTime);
        return dateTime;
    }


}