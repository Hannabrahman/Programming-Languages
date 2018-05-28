/**
 * Reference: https://math.nist.gov/javanumerics/jama/
 * Modified by Wen Cui
 * Date: May 27, 2018
 * */

package svd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MathsTest {
    private double zero = 0.0;
    private double a = 4.0;
    private double b = 3.0;
    private double rel = 5.0;
    private Maths math;

    @Before
    public void setUp() {
        math = new Maths();
    }

    @Test
    public void testHypot () {
        assertEquals(math.hypot(a, b), rel, 0.001);
        assertEquals(math.hypot(b, a), math.hypot(a, b), 0.001);
        assertEquals(math.hypot(a, zero), a, 0.001);
        assertEquals(math.hypot(b, zero), b, 0.001);
        assertEquals(math.hypot(zero, zero), zero, 0.001);
    }

}