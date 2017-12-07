import java.util.ArrayList;
import java.util.List;


public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    List<AdminUnit> children = new ArrayList<>();

    BoundingBox bbox;

    AdminUnit(String name,int adminLevel, double population, double area, double density, double x1, double y1, double x2, double y2){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
        this.bbox = new BoundingBox(x1, y1, x2, y2);
    }


    public String relations(){
        String str = name + "\n     children: ";
        if(!children.isEmpty()){
            for(AdminUnit au : children) str += au.name + "  ";
        }
        if(parent != null) str += "\n     parent: " + parent.name + "\n";
        return  str;
    }
    public String toString(){
        return name + " " + adminLevel + " " + population + " " + area + " " + density + "\n";
    }

}