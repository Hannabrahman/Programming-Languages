/**
 * Reference: https://math.nist.gov/javanumerics/jama/
 * Modified by Wen Cui
 * Date: May 27, 2018
 * */

package svd;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Matrix {

    // Memebers
    private double[][] A;
    private int m, n; //m=row & n=col dimensions


    /** Constructor: initialize m*n zero matrix */
    public Matrix (int m, int n) {
        this.m = m;
        this.n = n;
        A = new double[m][n];
    }

    /** Construct a matrix from a 2-D array. */

    public Matrix (double[][] A) {
        m = A.length;
        n = A[0].length;
        for (int i = 0; i < m; i++) {
            if (A[i].length != n) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }
        this.A = A;
    }

    /** Construct a matrix quickly without checking arguments. */
    public Matrix (double[][] A, int m, int n) {
        this.A = A;
        this.m = m;
        this.n = n;
    }

    // Public methods

    /** Access the internal two-dimensional array.*/
    public double[][] getArray () {
        return A;
    }

    /** Get row dimension.*/
    public int getRowDimension () {
        return m;
    }

    /** Get column dimension. */
    public int getColumnDimension () {
        return n;
    }

    /** Get a single element. */
    public double get (int i, int j) {
        double element = 0.0;
        try {
            element = A[i][j];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Get element out of bounds");
        }
        return element;
    }

    /** Set a single element */
    public void set (int i, int j, double s) {
        try {
            A[i][j] = s;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Set element out of bounds");
        }
    }

    /** Copy the internal two-dimensional array */
    public double[][] getArrayCopy () {
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }
        return C;
    }

    /** C = A-B */
    public Matrix minus (Matrix B) {
        checkMatrixDimensions(B);
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B.A[i][j];
            }
        }
        return X;
    }

    /** C = A+B */
    public Matrix plus (Matrix B) {
        checkMatrixDimensions(B);
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B.A[i][j];
            }
        }
        return X;
    }


    /** Matrix transpose A' */
    public Matrix transpose () {
        Matrix X = new Matrix(n,m);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[j][i] = A[i][j];
            }
        }
        return X;
    }

    /** Calculate one norm of matrix*/
    public double norm1 () {
        double f = 0;
        for (int j = 0; j < n; j++) {
            double s = 0;
            for (int i = 0; i < m; i++) {
                s += Math.abs(A[i][j]);
            }
            f = Math.max(f,s);
        }
        return f;
    }

    /** svd of matrix */
    public SingularValueDecomposition svd () {
        return new SingularValueDecomposition(this);
    }
    

    /** Print the matrix to stdout.
     * w: Column width.
     * d: Number of digits after the decimal.
     */
    public void print (int w, int d) {
        print(new PrintWriter(System.out,true),w,d); }

    /** Print the matrix to the output stream.
     * output: Output stream.
     * w: Column width.
     * d: Number of digits after the decimal.
     */
    public void print (PrintWriter output, int w, int d) {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(d);
        format.setMinimumFractionDigits(d);
        format.setGroupingUsed(false);
        print(output,format,w+2);
    }



    /** Print the matrix to the output stream.
     * output: the output stream.
     * format: A formatting object to format the matrix elements
     * width: Column width.
     */
    public void print (PrintWriter output, NumberFormat format, int width) {
//        output.println();  // start on new line.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String s = format.format(A[i][j]); // format the number
                int padding = Math.max(1,width-s.length()); // At _least_ 1 space
                for (int k = 0; k < padding; k++)
                    output.print(' ');
                output.print(s);
            }
            output.println();
        }
//        output.println();   // end with blank line.
    }

    /** Read a matrix from a stream. Elements are separated by
     * whitespace, all the elements for each row appear on a single line,
     * the last row is followed by a blank line.
     * input the input stream.
     */
    public static Matrix read (BufferedReader input) throws java.io.IOException {
        StreamTokenizer tokenizer= new StreamTokenizer(input);

        // Although StreamTokenizer will parse numbers, it doesn't recognize
        // scientific notation (E or D); however, Double.valueOf does.
        // The strategy here is to disable StreamTokenizer's number parsing.
        // We'll only get whitespace delimited words, EOL's and EOF's.
        // These words should all be numbers, for Double.valueOf to parse.

        tokenizer.resetSyntax();
        tokenizer.wordChars(0,255);
        tokenizer.whitespaceChars(0, ' ');
        tokenizer.eolIsSignificant(true);
        java.util.Vector<Double> vD = new java.util.Vector<Double>();

        // Ignore initial empty lines
        while (tokenizer.nextToken() == StreamTokenizer.TT_EOL);
        if (tokenizer.ttype == StreamTokenizer.TT_EOF)
            throw new java.io.IOException("Unexpected EOF on matrix read.");
        do {
            vD.addElement(Double.valueOf(tokenizer.sval)); // Read & store 1st row.
        } while (tokenizer.nextToken() == StreamTokenizer.TT_WORD);

//        int n = vD.size();  // Now we've got the number of columns!
        int n = vD.elementAt(1).intValue();
        int m = vD.elementAt(0).intValue();

        double row[] = new double[n];

        tokenizer.nextToken();
        do {
            vD.addElement(Double.valueOf(tokenizer.sval)); // Read & store 1st row.
        } while (tokenizer.nextToken() == StreamTokenizer.TT_WORD);

        for (int j=0; j<n; j++)  // extract the elements of the 1st row.
            row[j]=vD.elementAt(j).doubleValue();
        java.util.Vector<double[]> v = new java.util.Vector<double[]>();
        v.addElement(row);  // Start storing rows instead of columns.
        while (tokenizer.nextToken() == StreamTokenizer.TT_WORD) {
            // While non-empty lines
            v.addElement(row = new double[n]);
            int j = 0;
            do {
                if (j >= n) throw new java.io.IOException
                        ("Row " + v.size() + " is too long.");
                row[j++] = Double.valueOf(tokenizer.sval).doubleValue();
            } while (tokenizer.nextToken() == StreamTokenizer.TT_WORD);
            if (j < n) throw new java.io.IOException
                    ("Row " + v.size() + " is too short.");
        }
//        int m = v.size();  // Now we've got the number of rows.
        double[][] A = new double[m][];
        v.copyInto(A);  // copy the rows out of the vector
        return new Matrix(A);
    }

    /** Check if size(A) == size(B) */
    private void checkMatrixDimensions (Matrix B) {
        if (B.m != m || B.n != n) {
            throw new IllegalArgumentException("Matrix dimensions must agree.");
        }
    }
}
