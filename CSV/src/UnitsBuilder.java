import java.io.IOException;
import java.util.Locale;


public class UnitsBuilder {
    public static void main(String[] args) throws IOException, EmptyBoundingBox {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();

      //  unitlist.sortInplaceByName().filter(a->a.name.startsWith("K")).list(System.out);
       // unitlist.sortInplaceByName().filter(a-> a.parent != null && a.parent.name.equals("województwo małopolskie") && a.adminLevel == 6).list(System.out);

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(unitlist)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);



    }

}

