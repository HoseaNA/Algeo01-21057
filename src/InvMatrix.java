import java.util.*;

public class InvMatrix {
    public static void InvStartMethod1() {
        double[][] Mat = ReadMatriksSym();
        double[][] InvMat = new double[Mat.length][Mat[0].length];

        InvMat = InvGaussJordan(Mat);
        System.out.println("Hasil invers adalah :");
        printMatriks(InvMat);
    }

    public static void InvStartMethod2() {
        double[][] Mat = ReadMatriksSym();
        double[][] InvMat = new double[Mat.length][Mat[0].length];

        InvMat = InvCofactor(Mat);
        System.out.println("Hasil invers adalah :");
        printMatriks(InvMat);
    }

    public static void printMatriks(double[][] Mat) {
        int Row = Mat.length;
        int Col = Mat[0].length;

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                System.out.printf("%.2f ", Mat[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static double[][] ReadMatriksSym() {
        Scanner in = new Scanner(System.in);

        System.out.println("Masukkan ukuran matriks (n x n) :");
        int n = in.nextInt();
        double[][] Mat = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("\nEnter equations coefficients of row " + (i + 1) + " colom " + (j + 1));
                Mat[i][j] = in.nextDouble();
            }
        }
        in.close();
        return Mat;
    }

    public static double[][] InvGaussJordan(double[][] mat) {
        int Row = mat.length;
        int Col = mat[0].length;

        double[][] extMat = new double[Row][2 * Col];
        double[][] newMat = new double[Row][2 * Col];
        double[][] invMat = new double[Row][Col];

        for (int i = 0; i < extMat.length; i++) {
            for (int j = 0; j < extMat[0].length; j++) {
                if (j < Col) {
                    extMat[i][j] = mat[i][j];
                } else {
                    if (j - Col == i) {
                        extMat[i][j] = 1;
                    } else {
                        extMat[i][j] = 0;
                    }
                }
            }
        }
        MATRIKS.rowEchelon(extMat);
        MATRIKS.reducedRE(extMat);
        newMat = extMat;

        for (int i = 0; i < invMat.length; i++) {
            for (int j = 0; j < invMat[0].length; j++) {
                invMat[i][j] = newMat[i][j + Col];
            }
        }

        return invMat;
    }

    public static double[][] InvCofactor(double[][] mat) {
        double[][] copyMat = MATRIKS.CopyMatrix(mat);
        double det = OBE.detOBE(copyMat);
        double[][] matCofactor = MATRIKS.Cofactor(mat);
        double[][] adjMat = MATRIKS.transpose(matCofactor);
        double[][] finalMat = MATRIKS.multiplyMatbyConst(adjMat, (1 / det));

        return finalMat;
    }
}