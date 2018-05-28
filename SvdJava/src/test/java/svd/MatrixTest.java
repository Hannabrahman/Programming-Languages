/**
 * Reference: https://math.nist.gov/javanumerics/jama/
 * Modified by Wen Cui
 * Date: May 27, 2018
 * */

package svd;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.deepEquals;
import static org.junit.Assert.*;


public class MatrixTest {
    private Matrix A;
    private double[][] avals = {{1., 4., 7., 10.}, {2., 5., 8., 11.}, {3., 6., 9., 12.}};
    private double[][] atest;

    @Before
    public void setUp() {
        A = new Matrix(avals);
    }

    @Test
    public void testGetArray() {
        atest = A.getArray();
        assertTrue(deepEquals(atest, avals));
    }

    @Test
    public void testGetArrayCopy() {
        atest = A.getArrayCopy();
        assertNotSame(atest, avals);
    }

    @Test
    public void testGetRowDimension() {
        assertEquals(A.getRowDimension(), 3);
    }

    @Test
    public void testGetColumnDimension() {
        assertEquals(A.getColumnDimension(), 4);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetException() {
        A.get(A.getRowDimension(), A.getColumnDimension() - 1);
        A.get(A.getRowDimension() - 1, A.getColumnDimension());
        A.get(A.getRowDimension(), A.getColumnDimension());
    }

    @Test
    public void testGet() {
        assertEquals(A.get(0, 0), 1.0, 0.001);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetException() {
        A.set(A.getRowDimension(), A.getColumnDimension() - 1, 1.1);
        A.set(A.getRowDimension() - 1, A.getColumnDimension(), 1.1);
        A.set(A.getRowDimension(), A.getColumnDimension(), 1.1);
    }

    @Test
    public void testSet() {
        A.set(0, 0, 1.1);
        assertEquals(A.get(0, 0), 1.1, 0.001);
    }

    @Test
    public void testMinus() {
        Matrix zero = new Matrix(A.getRowDimension(), A.getColumnDimension());
        assertTrue(deepEquals(A.minus(zero).getArray(), A.getArray()));
    }

    @Test
    public void testPlus() {
        Matrix zero = new Matrix(A.getRowDimension(), A.getColumnDimension());
        assertTrue(deepEquals(A.plus(zero).getArray(), A.getArray()));
    }


    @Test
    public void testNorm1() {
        assertEquals(A.minus(A).norm1(), 0.0, 0.001);

    }

    @Test
    public void testTranspose() {
        Matrix T = A.transpose();
        assertTrue(check(A, T.transpose()));
    }

    // Private methods
    private static boolean check(double[][] x, double[][] y) {
        Matrix A = new Matrix(x);
        Matrix B = new Matrix(y);
        return check(A,B);
    }

    /** Check matrix equality by checking norm 1 with eps difference */
    private static boolean check(Matrix X, Matrix Y) {
        double eps = Math.pow(2.0,-10.0);
        if (X.norm1() == 0. & Y.norm1() < 10 * eps) return false;
        if (Y.norm1() == 0. & X.norm1() < 10 * eps) return false;
        if (X.minus(Y).norm1() > 1000 * eps * Math.max(X.norm1(),Y.norm1())) {
            throw new RuntimeException("The norm of (X-Y) is too large: " +  Double.toString(X.minus(Y).norm1()));
        }
        return true;
    }
}
