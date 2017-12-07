import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UnitsBuilder {
    public static void main(String[] args) throws IOException {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.relationsPrint(System.out);
    }
}
