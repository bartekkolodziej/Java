import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();



    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader("admin-units.csv", ",", true);
        while (reader.next()) {
            String name = reader.get("name");
            int admin_level = reader.getInt("admin_level");
            double population = reader.getDouble("population");
            double area = reader.getDouble("area");
            double density = reader.getDouble("density");
            this.units.add(new AdminUnit(name, admin_level, population, area, density));

        }
    }


    void list(PrintStream out){
        for(AdminUnit au : units) out.printf(au.toString());
    }

    void list(PrintStream out,int offset, int limit ){
        for(int i=0; i<limit; i++){
            out.print(units.get(offset + i - 1));
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit au : this.units){
           if(regex){
               if(au.name.matches(pattern)) ret.units.add(au);
           }else if(au.name.contains(pattern)) ret.units.add(au);
        }
        return ret;
    }




}

