import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoundingBoxTest {


    @Test
    public void addPoint() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        bb.addPoint(2,2);
        assertEquals(-1, bb.xmin, 1e-6);
        assertEquals(-1, bb.ymin, 1e-6);
        assertEquals(2, bb.xmax, 1e-6);
        assertEquals(2, bb.ymax, 1e-6);

        empty_bb.addPoint(2,2);
        assertEquals(Double.NaN, empty_bb.xmin, 1e-6);
        assertEquals(Double.NaN, empty_bb.ymin, 1e-6);
        assertEquals(Double.NaN, empty_bb.xmax, 1e-6);
        assertEquals(Double.NaN, empty_bb.ymax, 1e-6);

    }


    @Test
    public void containsPoint() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        assertEquals(false, bb.contains(5,5));
        assertEquals(true, bb.contains(0,0));
        assertEquals(false, empty_bb.contains(0,0));
    }

    @Test
    public void containsBB() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        BoundingBox bb1 = new BoundingBox(-0.5,-0.5,0.5,0.5);
        assertEquals(true, bb.contains(bb1));
        assertEquals(false, bb1.contains(bb));
        assertEquals(false, bb.contains(empty_bb));
    }

    @Test
    public void intersects() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        BoundingBox bb1 = new BoundingBox(-0.5,-0.5,0.5,0.5);
        BoundingBox bb2 = new BoundingBox(0,0,2,2);
        assertEquals(true, bb.intersects(bb2));
        assertEquals(false, bb.intersects(bb1));
        assertEquals(false, bb.intersects(empty_bb));
    }

    @Test
    public void add() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        BoundingBox bb1 = new BoundingBox(0,0,2,2);
        BoundingBox expanded_BB = bb.add(bb1);
        assertEquals(true, expanded_BB.contains(bb1) && expanded_BB.contains(bb));
    }

    @Test
    public void isEmpty() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
        assertEquals(true, empty_bb.isEmpty());
    }

    @Test
    public void getCenterX() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        assertEquals(0, bb.getCenterX(),1e-5);
    }

    @Test
    public void getCenterY() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        assertEquals(0, bb.getCenterY(),1e-5);
    }

    @Test(expected = EmptyBoundingBox.class)
    public void getCenterXEmptyBB() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
        empty_bb.getCenterY();
    }

    @Test(expected = EmptyBoundingBox.class)
    public void getCenterYEmptyBB() throws Exception {
        BoundingBox empty_bb = new BoundingBox();
       empty_bb.getCenterY();
    }

    @Test
    public void distanceTo() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        BoundingBox bb1 = new BoundingBox(0,0,2,2);
        assertEquals(true,bb.distanceTo(bb1) == bb1.distanceTo(bb));
    }

    @Test(expected = EmptyBoundingBox.class)
    public void distanceToEmptyBB() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        BoundingBox empty_bb = new BoundingBox();
        bb.distanceTo(empty_bb);
    }

    @Test
    public void getWKT() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        assertEquals("POLYGON(-1.0 -1.0, 1.0 -1.0, 1.0 1.0, -1.0 1.0, -1.0 -1.0)", bb.getWKT());
    }

    @Test
    public void testToString() throws Exception {
        BoundingBox bb = new BoundingBox(-1,-1,1,1);
        assertEquals("(-1.0 -1.0) (1.0 1.0)",bb.toString());
    }

}