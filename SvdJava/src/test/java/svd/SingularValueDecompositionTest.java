package svd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SingularValueDecompositionTest {
    private Matrix A;
    private SingularValueDecomposition SVD;
    private double[][] avals = {{1., 4., 7., 10.}, {2., 5., 8., 11.}, {3., 6., 9., 12.}};
    private double[][] svals = {{25.5, 0.0, 0.0, 0.0}, {0.0, 1.3, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0}};
    private double[][] uvals = {{-0.5, -0.8, 0.4}, {-0.6, -0.1, -0.8}, {-0.7, 0.7, 0.4}};
    private double[][] vvals = {{-0.1, 0.0, 0.8, 0.5}, {-0.3, 0.4, -0.1, -0.7}, {-0.5, 0.0, 0.0, -0.2}, {-0.8, -0.4, 1.0, 0.4}};
    private double[] sv = {25.4624, 1.2907, 0.0, 0.0};

    @Before
    public void setUp()
    {
        A = new Matrix(avals);
        SVD = A.svd();
    }

    @Test
    public void testGetU() {
        Matrix U = SVD.getU();
        assertTrue(check(U.getArray(), uvals));
    }

    @Test
    public void testGetV() {
        Matrix V = SVD.getV();
        assertTrue(check(V.getArray(), vvals));
    }

    @Test
    public void testGetS() {
        Matrix S = SVD.getS();
        //System.out.print(Arrays.deepToString(S.getArray()));
        assertTrue(check(S.getArray(), svals));

    }

    @Test
    public void testGetSingularValues() {
        double[] testS = SVD.getSingularValues();
        System.out.println();
        assertArrayEquals(testS, sv, 0.1);
    }

    //Private methods
    private static boolean check(double[][] x, double[][] y) {
        Matrix A = new Matrix(x);
        Matrix B = new Matrix(y);
        return check(A,B);
    }

    /** Check matrix equality by checking norm 1 with eps difference */
    private static boolean check(Matrix X, Matrix Y) {
        double eps = Math.pow(2.0,-5.0);
        if (X.norm1() == 0. & Y.norm1() < 10 * eps) return false;
        if (Y.norm1() == 0. & X.norm1() < 10 * eps) return false;
        if (X.minus(Y).norm1() > 1000 * eps * Math.max(X.norm1(),Y.norm1())) {
            throw new RuntimeException("The norm of (X-Y) is too large: " +  Double.toString(X.minus(Y).norm1()));
        }
        return true;
    }

}