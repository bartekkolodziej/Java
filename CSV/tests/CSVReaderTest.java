import org.junit.Before;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CSVReaderTest {

    @org.junit.Test
    public void parseHeader() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        String[] header = {"id", "parent", "name","admin_level", "population", "area", "density" };
        assertArrayEquals(header, reader.columnLabels.toArray());
    }

    @org.junit.Test
    public void next() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        while(reader.next()){
            System.out.printf(Arrays.toString(reader.current) + "\n");
        }
    }

    @org.junit.Test
    public void getInt() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(11670, reader.getInt("id"));

    }

    @org.junit.Test
    public void getInt1() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(11670, reader.getInt(0));
    }

    @org.junit.Test
    public void getLong() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(6165, reader.getLong("population"),1e-5);
    }

    @org.junit.Test
    public void getLong1() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(6165, reader.getLong(4),1e-5);
    }

    @org.junit.Test
    public void getDouble() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(40.4298, reader.getDouble(5),1e-5);
    }

    @org.junit.Test
    public void getDouble1() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(40.4298, reader.getDouble(5),1e-5);
    }

    @org.junit.Test
    public void get() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals("gmina Lanckorona", reader.get("name"));
    }

    @org.junit.Test
    public void get1() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals("gmina Lanckorona", reader.get(2));
    }

    @org.junit.Test
    public void getColumnLabels() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        String[] header = {"id", "parent", "name","admin_level", "population", "area", "density" };
        assertArrayEquals(header, reader.getColumnLabels().toArray());
    }

    @org.junit.Test
    public void getRecordLength() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        assertEquals(45, reader.getRecordLength());
    }

    @org.junit.Test
    public void isMissing() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        int missing_records = 0;
        while(reader.next())
            if(reader.isMissing(4)) missing_records++;
        assertEquals(10, missing_records);
    }

    @org.junit.Test
    public void isMissing1() throws Exception {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        int missing_records = 0;
        while(reader.next())
            if(reader.isMissing("population")) missing_records++;
        assertEquals(10, missing_records);
    }

    @org.junit.Test(expected = NumberFormatException.class)
    public void ReadFileWithMissingRecords() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        while(reader.next())
            reader.getInt("parent");
    }

    @org.junit.Test
    public void ReferenceToMissingStringRecords() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 6; i++) reader.next();      // acces 5th row in file
        assertEquals("", reader.get("parent"));
        assertEquals("",reader.get(1));
    }


    @org.junit.Test(expected = NumberFormatException.class)
    public void ReferenceToMissingnNumericalRecordsInteger1() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 2; i++) reader.next();      // acces second row in file
        reader.getInt(4);
    }
    @org.junit.Test(expected = NumberFormatException.class)
    public void ReferenceToMissingnNumericalRecordsInteger2() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 2; i++) reader.next();      // acces second row in file
        reader.getInt("population");
    }
    @org.junit.Test(expected = NumberFormatException.class)
    public void ReferenceToMissingnNumericalRecordsDouble1() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 2; i++) reader.next();      // acces second row in file
        reader.getDouble(4);
    }
    @org.junit.Test(expected = NumberFormatException.class)
    public void ReferenceToMissingnNumericalRecordsDouble2() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 2; i++) reader.next();      // acces second row in file
        reader.getDouble("population");
    }
    @org.junit.Test(expected = NumberFormatException.class)
    public void ReferenceToMissingnNumericalRecordsLong1() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 2; i++) reader.next();      // acces second row in file
        reader.getLong(4);
    }
    @org.junit.Test(expected = NumberFormatException.class)
    public void ReferenceToMissingnNumericalRecordsLong2() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        for(int i=0; i < 2; i++) reader.next();      // acces second row in file
        reader.getLong("population");
    }

    @org.junit.Test
    public void ReadFromString() throws IOException {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);
        reader.parseHeader();
        reader.next();
        String[] header = {"a","b","c" };
        String[] first_line = {"123.4", "567.8", "91011.12"};
        assertArrayEquals(header, reader.columnLabels.toArray());
        assertArrayEquals(first_line, reader.current);

    }

}