/**
 * Author: Wen Cui
 * Date: May 27, 2018
 * */

package svd;

import java.io.*;

public class MatrixSVD {

    public static void main(String[] args) {
        if (null == args || 2 != args.length) {
            System.err.println("Usage: MatrixSVD <inputfile> <outputdir>");
            System.exit(1);
        }
        File outSFile = new File(args[1] + "matrix_javasvd_s.output");
        File outUFile = new File(args[1] + "matrix_javasvd_u.output");
        File outVFile = new File(args[1] + "matrix_javasvd_v.output");


        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(args[0]));
            PrintWriter outputS = new PrintWriter(outSFile);
            PrintWriter outputU = new PrintWriter(outUFile);
            PrintWriter outputV = new PrintWriter(outVFile);


            Matrix m = Matrix.read(bufferReader);
            SingularValueDecomposition SVD = m.svd();
            Matrix S = SVD.getS();
            Matrix U = SVD.getU();
            Matrix V = SVD.getV();
            S.print(outputS, 0, 1);
            U.print(outputU, 0, 1);
            V.print(outputV, 0, 1);


            outputS.close();
            outputU.close();
            outputV.close();
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
