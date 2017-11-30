import java.io.IOException;

public class UnitsBuilder {
    public static void main(String[] args) throws IOException {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");

        AdminUnitList tmp = unitlist.selectByName("^woj.*", true);
        tmp.list(System.out);
    }
}
