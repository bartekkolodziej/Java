
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();



    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);
        Map<Long, AdminUnit> id2admin = new HashMap<>();
        Map<AdminUnit, Long> admin2parentId = new HashMap<>();
        while (reader.next()) {
            double x1 = reader.getDouble("x1");
            double y1 = reader.getDouble("y1");
            double x2 = reader.getDouble("x2");
            double y2 = reader.getDouble("y2");
            double x3 = reader.getDouble("x3");
            double y3 = reader.getDouble("y3");
            double x4 = reader.getDouble("x4");
            double y4 = reader.getDouble("y4");
            double[] points = new double[4];
            points = FindXY(x1,y1,x2,y2,x3,y3,x4,y4);
            long id = reader.getLong("id");
            String name = reader.get("name");
            int admin_level = reader.getInt("admin_level");
            double population = reader.getDouble("population");
            double area = reader.getDouble("area");
            double density = reader.getDouble("density");
            long parent = reader.getLong("parent");
            AdminUnit tmp = new AdminUnit(name, admin_level, population, area, density, points[0], points[1], points[2], points[3]);
            this.units.add(tmp);
            id2admin.put(id, tmp);
            admin2parentId.put(tmp, parent);
        }
        setParents(admin2parentId, id2admin);
        setChildren();
    }


    double[] FindXY(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        double[] points = new double[4];
        double xmin = x1;
        if(x2 < xmin) xmin = x2;
        if(x3 < xmin) xmin = x3;
        if(x4 < xmin) xmin = x4;
        points[0] = xmin;

        double ymin = y1;
        if(y2 < ymin) ymin = x2;
        if(y3 < ymin) ymin = x3;
        if(y4 < ymin) ymin = x4;
        points[1] = ymin;

        double xmax = x1;
        if(x2 > xmax) xmax = x2;
        if(x3 > xmax) xmax = x3;
        if(x4 > xmax) xmax = x4;
        points[2] = xmax;

        double ymax = y1;
        if(y2 > ymax) ymax = x2;
        if(y3 > ymax) ymax = x3;
        if(y4 > ymax) ymax = x4;
        points[3] = ymax;

        return points;
    }

    void setParents(Map<AdminUnit, Long> admin2parentId, Map<Long, AdminUnit> id2admin){
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

