import java.io.IOException;
import java.util.Locale;


public class UnitsBuilder {
    public static void main(String[] args) throws IOException, EmptyBoundingBox {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();
        AdminUnitList sorted = unitlist.sortInplaceByPopulation();
        sorted.list(System.out, 1, 100);


    }

}

