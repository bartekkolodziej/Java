import java.io.IOException;
import java.util.Locale;


public class UnitsBuilder {
    public static void main(String[] args) throws IOException, EmptyBoundingBox {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();

        unitlist.sortInplaceByName().filter(a->a.name.startsWith("K"),3, 5).list(System.out);


    }

}

