public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean empty = true;

    BoundingBox(){}

    BoundingBox(double xmin, double ymin, double xmax, double ymax){
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        if(this.xmin - this.xmax == 0 || this.ymin - this.ymax == 0)
            empty = true;
        else
            empty = false;
    }

    void addPoint(double x, double y){
        if(empty)
            return;
        this.xmin = Math.min(x, this.xmin);
        this.ymin = Math.min(y, this.ymin);
        this.xmax = Math.max(x, this.xmax);
        this.ymax = Math.max(y, this.ymax);
    }

    boolean contains(double x, double y){
        if(empty)
            return false;
        return x > this.xmin && x < this.xmax && y > this.ymin && y < this.ymin;
    }


    boolean contains(BoundingBox bb){
        if(empty)
            return true;
        else
            return bb.xmin > this.xmin && bb.xmax < this.xmax && bb.ymin > this.ymin && bb.ymax < this.ymax;
    }

    boolean intersects(BoundingBox bb){
        if(empty)
            return false;
        else
            return contains(bb.xmin, bb.ymin) || contains(bb.xmax, bb.ymax) || contains(bb.xmin, bb.ymax) || contains(bb.xmax, ymin);

    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return true;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        throw new RuntimeException("Not implemented");
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        throw new RuntimeException("Not implemented");
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx){
        throw new RuntimeException("Not implemented");
    }

}
