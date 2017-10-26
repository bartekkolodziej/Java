package Lab2;

import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void asArray() throws Exception {
        double[][] array = {{2},{12}, {13}};
        Matrix tmp = new Matrix(array);
        double [][] result = tmp.asArray();

        assertArrayEquals(array[0],result[0],.1);
        assertArrayEquals(array[1],result[1],.1);
        assertArrayEquals(array[2],result[2],.1);

    }

    @org.junit.Test
    public void get() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        double exp = tmp.get(2,2);
       assertEquals(exp,7,0.1);

    }

    @org.junit.Test
    public void set() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        tmp.set(2,2, 111);
        double exp = tmp.get(2,2);
        assertEquals(exp,111, 0.1);
    }

    @org.junit.Test
    public void testtoString() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        int brackets =0, commas = 0;
        String array_string = tmp.toString();
        String exp_array = "[[2,56,12],\n[12,44,19],\n[13,66,7],\n[1,1,3]]";
        assertEquals(exp_array, tmp.toString());
    }

    @org.junit.Test
    public void reshape() throws Exception {
    }

    @org.junit.Test
    public void shape() throws Exception {
    }

    @org.junit.Test
    public void add() throws Exception {
    }

    @org.junit.Test
    public void sub() throws Exception {
    }

    @org.junit.Test
    public void mul() throws Exception {
    }

    @org.junit.Test
    public void div() throws Exception {
    }

    @org.junit.Test
    public void add1() throws Exception {
    }

    @org.junit.Test
    public void sub1() throws Exception {
    }

    @org.junit.Test
    public void mul1() throws Exception {
    }

    @org.junit.Test
    public void div1() throws Exception {
    }

    @org.junit.Test
    public void dot() throws Exception {
    }

    @org.junit.Test
    public void main() throws Exception {
    }

}