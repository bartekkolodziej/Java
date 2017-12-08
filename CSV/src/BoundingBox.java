public class BoundingBox {
    double xmin = Double.NaN;
    double ymin = Double.NaN;
    double xmax = Double.NaN;
    double ymax = Double.NaN;

    BoundingBox(){}

    BoundingBox(double xmin, double ymin, double xmax, double ymax){
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    void addPoint(double x, double y){ //expand this that it contains point(x,y)
        if(this.isEmpty())
            return;
        this.xmin = Math.min(x, this.xmin);
        this.ymin = Math.min(y, this.ymin);
        this.xmax = Math.max(x, this.xmax);
        this.ymax = Math.max(y, this.ymax);
    }

    boolean contains(double x, double y){ //check if this contains points(x,y)
        if(this.isEmpty())
            return false;
        return x >= this.xmin && x <= this.xmax && y >= this.ymin && y <= this.ymax;
    }


    boolean contains(BoundingBox bb){ //check if this contains bb
        if(this.isEmpty())
            return false;
        else
            return this.contains(bb.ymin, bb.xmin) && this.contains(bb.xmax, bb.ymin) && this.contains(bb.xmax, bb.ymax) && this.contains(bb.xmin, bb.ymax);
    }

    boolean intersects(BoundingBox bb){ //check if this intersects with bb
        if(this.isEmpty())
            return false;
        else
            return ((this.contains(bb.xmin, bb.ymin) || this.contains(bb.xmax, bb.ymax) || this.contains(bb.xmin, bb.ymax) || this.contains(bb.xmax, bb.ymin)) &&
                    (!this.contains(bb.xmin, bb.ymin) || !this.contains(bb.xmax, bb.ymax) || !this.contains(bb.xmin, bb.ymax) || !this.contains(bb.xmax, bb.ymin))) ||
                    ((bb.contains(this.xmin, this.ymin) || bb.contains(this.xmax, this.ymax) || bb.contains(this.xmin, this.ymax) || bb.contains(this.xmax, this.ymin)) &&
                            (!bb.contains(this.xmin, this.ymin) || !bb.contains(this.xmax, this.ymax) || !bb.contains(this.xmin, this.ymax) || !bb.contains(this.xmax, this.ymin)));

    }

    BoundingBox add(BoundingBox bb){ //expand this that it contains bb and this
        if(this.isEmpty() || bb.isEmpty() || this.contains(bb))
            return this;
        else{
            this.xmin = Math.min(bb.xmin, this.xmin);
            this.ymin = Math.min(bb.ymin, this.ymin);
            this.xmax = Math.max(bb.xmax, this.xmax);
            this.ymax = Math.max(bb.ymax, this.ymax);
            return this;
        }
    }

    boolean isEmpty(){ //check if this is empty (it's empty when vertices are NaN)
        return Double.isNaN(this.ymin) || Double.isNaN(this.xmin) || Double.isNaN(this.ymax) || Double.isNaN(this.xmax);
    }


    double getCenterX() throws EmptyBoundingBox {
        if(!this.isEmpty())
            return (this.xmin + this.xmax)/2;
        else
            throw new EmptyBoundingBox("Bounding box is empty");
    }

    double getCenterY() throws EmptyBoundingBox {
        if(!this.isEmpty())
            return (this.ymin + this.ymax)/2;
        else
            throw new EmptyBoundingBox("Bounding box is empty");
    }


    double distanceTo(BoundingBox bbx) throws EmptyBoundingBox { //return distance between center of bbx and this
        if(!this.isEmpty() && !bbx.isEmpty()) {
            int EARTH_RADIUS = 6371; // Approx Earth radius in KM
            double startLat = this.getCenterX();
            double endLat = bbx.getCenterX();
            double startLong = this.getCenterY();
            double endLong = bbx.getCenterY();
            double dLat  = Math.toRadians((endLat - startLat));
            double dLong = Math.toRadians((endLong - startLong));

            startLat = Math.toRadians(startLat);
            endLat   = Math.toRadians(endLat);

            double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(startLat) * Math.cos(endLat) * Math.pow(Math.sin(dLong / 2), 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return EARTH_RADIUS * c; // <-- d
        }else
            throw new EmptyBoundingBox("Bounding box is empty");
    }

    public String getWKT(){
        return  "POLYGON("+this.xmin+" "+this.ymin+", "+this.xmax+" "+this.ymin+", "+this.xmax+" "+this.ymax+", "+this.xmin+" "+this.ymax+", "+this.xmin+" "+this.ymin+")";
    }

    public String toString(){
        return "(" + this.xmin + " " + this.ymin + ") (" + this.xmax + " " + this.ymax + ")";
    }

}
