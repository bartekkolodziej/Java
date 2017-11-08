import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void asArray() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        double [][] result = tmp.asArray();

        assertArrayEquals(array[0],result[0],1e-5);
        assertArrayEquals(array[1],result[1],.1e-5);
        assertArrayEquals(array[2],result[2],.1e-5);
        assertArrayEquals(array[3],result[3],.1e-5);
    }

    @org.junit.Test
    public void get() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        double exp = tmp.get(2,2);
        assertEquals(exp,7,1e-5);

    }

    @org.junit.Test
    public void set() throws Exception {
        Matrix tmp = new Matrix(5,5);
        tmp.set(2,2, 111);
        double exp = tmp.get(2,2);
        assertEquals(exp,111, 0.1);
    }

    @org.junit.Test
    public void testtoString() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        int brackets = 0, commas = 0;
        String array_string = tmp.toString();
        String exp_array = "[[2.0,56.0,12.0]\n[12.0,44.0,19.0]\n[13.0,66.0,7.0]\n[1.0,1.0,3.0]]\n";
        assertEquals(exp_array, tmp.toString());
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void reshape() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        tmp.reshape(10,10);
    }

    @org.junit.Test
    public void shape() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        int[] exp = {4,3};
        assertArrayEquals(exp,tmp.shape());
    }

    @org.junit.Test
    public void addNumber() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);

        double[][] array2 = {{6,60,16},{16,48,23}, {17,70,11}, {5,5,7}};
        Matrix exp = new Matrix(array2);

        assertArrayEquals(exp.data, tmp.add(4).data, 1e-5);
    }

    @org.junit.Test
    public void subNumber() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);

        double[][] array2 = {{-2,52,8},{8,40,15}, {9,62,3}, {-3,-3,-1}};
        Matrix exp = new Matrix(array2);

        assertArrayEquals(exp.data, tmp.sub(4).data, 1e-5);
    }

    @org.junit.Test
    public void mulNumber() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        assertEquals(0, tmp.add(tmp.mul(-1)).frobenius(), 1e-5);
    }

    @org.junit.Test
    public void divNumber() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        assertEquals(tmp.mul(0.5).frobenius(), tmp.div(2).frobenius(), 1e-5);
    }

    @org.junit.Test
    public void addMatrix() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        assertEquals(0, tmp.add(tmp.mul(-1)).frobenius(), 1e-5);
    }

    @org.junit.Test
    public void subMatrix() throws Exception {
        double[][] array = {{2,56,12},{12,44,19}, {13,66,7}, {1,1,3}};
        Matrix tmp = new Matrix(array);
        assertEquals(0, tmp.sub(tmp).frobenius(),1e-5);
    }

    @org.junit.Test
    public void mul1Matrix() throws Exception {
        double[][] array = {{2,4,5},{3,4,9}, {11,8,7}, {1,10,3}};
        Matrix tmp = new Matrix(array);
        double[][] exp = {{4,16,25},{9,16,81}, {121,64,49}, {1,100,9}};
        assertArrayEquals(new Matrix(exp).data, tmp.mul(tmp).data, 1e-5);
    }

    @org.junit.Test
    public void divMatrix() throws Exception {
        double[][] array = {{2,4,5},{3,4,9}, {11,8,7}, {1,10,3}};
        Matrix tmp = new Matrix(array);
        double[][] exp = {{1,1,1},{1,1,1}, {1,1,1}, {1,1,1}};
        assertArrayEquals(new Matrix(exp).data, tmp.div(tmp).data, 1e-5);
    }

    @org.junit.Test
    public void dotMatrix() throws Exception {
        double[][] array = {{2,4},{3,0}};
        Matrix tmp = new Matrix(array);
        double[][] exp = {{16,8},{6,12}};
        assertArrayEquals(new Matrix(exp).data, tmp.dot(tmp).data, 1e-5);
    }

    @org.junit.Test
    public void frobenius() throws Exception {
        double[][] array = {{2,4},{3,0}};
        Matrix tmp = new Matrix(array);
        assertEquals(Math.sqrt(29),tmp.frobenius(), 1e-5);
    }

}