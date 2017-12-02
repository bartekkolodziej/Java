import java.io.IOException;

public class UnitsBuilder {
    public static void main(String[] args) throws IOException {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();
       // unitlist.list(System.out);
       unitlist.relationsPrint(System.out);
       // unitlist.selectByName(".*woj.*", true).list(System.out);

    }
}
