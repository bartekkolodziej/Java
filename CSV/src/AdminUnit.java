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

    AdminUnit(String name,int adminLevel, double population, double area, double density, double[] points){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
        this.bbox = new BoundingBox(points[0], points[1], points[2], points[3]);
    }



    public String relations(){
        String str ="    children: ";
        if(!children.isEmpty()){
            for(AdminUnit au : children) str += au.name + ", ";
        }
        if(parent != null) str += "\n    parent: " + parent.name + "\n";
        return  str;
    }


    public String toString(){
        return "Name: " + name + "\n    AdminLevel: " + adminLevel + "\n    Population: " + population + "\n    Area: " + area +
                "\n    Density: " + density + "\n    BoundingBox: " + bbox.toString() + "\n    " + bbox.getWKT() +"\n" + this.relations() + "\n";
    }

}