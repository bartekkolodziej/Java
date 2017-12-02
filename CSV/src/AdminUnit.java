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

    BoundingBox bbox = new BoundingBox();

    AdminUnit(String name,int adminLevel, double population, double area, double density){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
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