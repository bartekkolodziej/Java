import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    AdminUnitList(List<AdminUnit> units){this.units = units;}

    public AdminUnitList() { }

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
            AdminUnit tmp = new AdminUnit(name, admin_level, population, area, density, points);
            this.units.add(tmp);
            id2admin.put(id, tmp);
            admin2parentId.put(tmp, parent);
        }
        setParents(admin2parentId, id2admin);
        setChildren();
    }


    double[] FindXY(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){ //Find xmin, ymin, xmax, ymax
        double[] points = new double[4];
        points[0] = Math.min(Math.min(x1,x2), Math.min(x3,x4));
        points[1] = Math.min(Math.min(y1,y2), Math.min(y3,y4));
        points[2] = Math.max(Math.max(x1,x2), Math.max(x3,x4));
        points[3] = Math.max(Math.max(y1,y2), Math.max(y3,y4));

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

     void getWKTofUnit(String unit_name){
        for(AdminUnit au : units)
            if(Objects.equals(au.name, unit_name)){
                System.out.printf(Locale.US,"LINESTRING(%f %f, %f %f, %f %f, %f %f, %f %f)",
                        au.bbox.xmin, au.bbox.ymin, au.bbox.xmax, au.bbox.ymin, au.bbox.xmax, au.bbox.ymax, au.bbox.xmin, au.bbox.ymax, au.bbox.xmin, au.bbox.ymin);
                break;
            }
    }

    void list(PrintStream out,int offset, int limit ){
        for(int i=0; i<limit; i++)
            out.print(this.units.get(offset + i - 1).toString());
    }

    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit au : this.units) {
            if (regex) {
                if (au.name.matches(pattern)) ret.units.add(au);
            } else if (au.name.contains(pattern)) ret.units.add(au);
        }
        return ret;
    }

    AdminUnit selectByName(String pattern){
        for(AdminUnit au : this.units) {
            if (au.name.equals(pattern))
                return au;
        }
        return null;
    }

    private void fixMissingValues(AdminUnit au){
        if(au.parent == null) {
            au.density = 0;
            au.population = 10;
        }else if(au.parent.density == 0 || Double.isNaN(au.parent.density))
            fixMissingValues(au.parent);
        au.density = au.parent.density;
        au.population = au.area * au.density;

    }
    public void fixAllMissingValues(){
        for(AdminUnit au : units){
            if(au.density == 0 || Double.isNaN(au.density))
                fixMissingValues(au);
        }
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) throws EmptyBoundingBox {
        List<AdminUnit> neighbors = new ArrayList<>();
        if(unit.parent == null || unit.parent.parent == null){ // Województa, powiaty i jednostki dla których parent == null albo parent.parent == null
            for(AdminUnit au : this.units)
                if(au.adminLevel == unit.adminLevel && !au.name.equals(unit.name) && unit.bbox.intersects(au.bbox)) neighbors.add(au);
        }
        else if(unit.adminLevel == 7){ //Gminy
            for(AdminUnit au : unit.parent.parent.children)
                for(AdminUnit au1 : au.children)
                    if(au1.adminLevel == unit.adminLevel && !au1.name.equals(unit.name) && unit.bbox.intersects(au1.bbox)) neighbors.add(au1);
        }
        else{ //Miejscowości
            for (AdminUnit au : unit.parent.parent.children)
                for (AdminUnit au1 : au.children)
                    if (au1.adminLevel == 8 && !au1.name.equals(unit.name) && (unit.bbox.intersects(au1.bbox) || unit.bbox.distanceTo(au1.bbox) < maxdistance))
                        neighbors.add(au1);
        }
        return new AdminUnitList(neighbors);
    }
}

