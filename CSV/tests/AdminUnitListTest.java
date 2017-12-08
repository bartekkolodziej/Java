import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class AdminUnitListTest {



    @Test
    public void selectByName() throws Exception {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();

        assertEquals("Kraków", unitlist.selectByName("Kraków").name);
        assertEquals("województwo małopolskie", unitlist.selectByName(".*małop.*", true ).units.get(0).name);

    }


    @Test
    public void getNeighbors() throws Exception {
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();

        AdminUnitList neighbors = unitlist.getNeighbors(unitlist.selectByName("województwo małopolskie"),0);

        assertEquals(3, neighbors.units.size());
        assertEquals(true, neighbors.units.contains(unitlist.selectByName("województwo podkarpackie")));
        assertEquals(true, neighbors.units.contains(unitlist.selectByName("województwo śląskie")));
        assertEquals(true, neighbors.units.contains(unitlist.selectByName("województwo świętokrzyskie")));
    }

    @Test
    public void getNeighborsForAllUnits() throws Exception{ //algorytm wyszukujacy troche lepszy niz ten najprostszy
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();
        double t1 = System.nanoTime()/1e9;
        for(AdminUnit au : unitlist.units)
               unitlist.getNeighbors(au,0);
        double t2 = System.nanoTime()/1e9;
        System.out.printf(Locale.US,"Wyszukiwanie sąsiadów dla wszystkich jednostek: %f s\n",t2-t1);
    }

    @Test
    public void finUnitAndNeighbors() throws Exception{
        AdminUnitList unitlist = new AdminUnitList();
        unitlist.read("admin-units.csv");
        unitlist.fixAllMissingValues();
        AdminUnit Brzyna = unitlist.selectByName("Brzyna");
        System.out.print(Brzyna.toString() + "Neighbors:\n\n");
        AdminUnitList neighbors = unitlist.getNeighbors(Brzyna, 4);
        neighbors.list(System.out);
    }

}