
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> id2admin = new HashMap<>();
    Map<AdminUnit, Long> admin2parentId = new HashMap<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);
        while (reader.next()) {
            long id = reader.getLong("id");
            String name = reader.get("name");
            int admin_level = reader.getInt("admin_level");
            double population = reader.getDouble("population");
            double area = reader.getDouble("area");
            double density = reader.getDouble("density");
            long parent = reader.getLong("parent");
            AdminUnit tmp = new AdminUnit(name, admin_level, population, area, density);
            this.units.add(tmp);
            id2admin.put(id, tmp);
            admin2parentId.put(tmp, parent);
        }
        setParents();
        setChildren();
    }


    void setParents(){
        for (AdminUnit au : units) {
            long id_parent = admin2parentId.get(au);
            if (id_parent != 0) // if parent ID == 0 it means the AdminUnit has no parent
                au.parent = id2admin.get(admin2parentId.get(au));
            else au.parent = null;
        }
    }

    void setChildren(){
        for(AdminUnit au : units) {
            if(au.parent != null)
                au.parent.children.add(au);
        }
    }

    void list(PrintStream out){
        for(AdminUnit au : units) out.printf(au.toString());
    }

    void relationsPrint(PrintStream out) {for(AdminUnit au : units) out.printf(au.relations());}

    void list(PrintStream out,int offset, int limit ){
        for(int i=0; i<limit; i++)
            out.print(this.units.get(offset + i - 1));
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
    private void fixMissingValues(AdminUnit au){
        if(au.parent == null) {
            au.density = 0;
            au.population = 10;
        }else if(au.parent.density == 0)
            fixMissingValues(au.parent);
        au.density = au.parent.density;
        au.population = au.area * au.density;

    }
    public void fixAllMissingValues(){
        for(AdminUnit au : units){
            if(au.density == 0)
                fixMissingValues(au);
        }
    }




}

