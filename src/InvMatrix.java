import java.util.*;

public class InvMatrix {
    public static void InvStart() {
        System.out.println("Menu Invers Matriks :");
        System.out.println("1. Metode Gauss Jordan");
        System.out.println("2. Metode Kofaktor");
        System.out.println("\n");

        System.out.println("Tentukan pilihan anda :");
        Scanner in = new Scanner(System.in);
        int pilihan = in.nextInt();
        if (pilihan == 1) {
            double[][] Mat = ReadMatriksSym();
            double[][] InvMat = new double[Mat.length][Mat[0].length];

            InvMat = InvGaussJordan(Mat);
            System.out.println("Hasil invers adalah :");
            printMatriks(InvMat);
        } else if (pilihan == 2) {
            double[][] Mat = ReadMatriksSym();
            double[][] InvMat = new double[Mat.length][Mat[0].length];
            InvMat = InvCofactor(Mat);
            System.out.println("Hasil invers adalah :");
            printMatriks(InvMat);
        }
        in.close();
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

    // public static double[][] RowEchelonSym(double[][] Matrix) {
    // // I.S : menerima sebuah matrix input dan sebuah array solusi tiap persamaan
    // // F.S : Membentuk sebuah matriks eselon baris dari matrix input
    // int N = Matrix.length;
    // for (int k = 0; k < N; k++) {
    // /** find pivot row **/
    // int max = k;
    // for (int i = k + 1; i < N; i++)
    // if (Math.abs(Matrix[i][k]) > Math.abs(Matrix[max][k]))
    // max = i;

    // /** swap row in A matrix **/
    // double[] temp = Matrix[k];
    // Matrix[k] = Matrix[max];
    // Matrix[max] = temp;

    // /** pivot within A and B **/
    // for (int i = k + 1; i < N; i++) {
    // double factor = Matrix[i][k] / Matrix[k][k];
    // for (int j = k; j < N; j++) {
    // Matrix[i][j] -= factor * Matrix[k][j];
    // }
    // }
    // /** Divide each row by its leading one-to-be **/
    // double div;
    // for (int i = k; i < N; i++) {
    // for (int j = N - 1; j >= k; j--) {
    // div = Matrix[i][i];
    // Matrix[i][j] = Matrix[i][j] / div;
    // }
    // }
    // }
    // return Matrix;
    // }

    // public static double[][] ReducedRowEchelonSym(double[][] Matrix) {
    // // I.S : Menerima sebuah Matriks yang sudah berbentuk eselon baris
    // // F.S : Mengubah matriks eselon baris menjadi eselon baris tereduksi
    // double div;
    // boolean leadingToBe;

    // for (int i = 0; i < Matrix.length; i++) {
    // for (int j = i + 1; j < Matrix[0].length; j++) {
    // div = 1;
    // leadingToBe = true;
    // for (int k = 0; k < Matrix[0].length; k++) {
    // if (leadingToBe && Matrix[j][k] != 0) {
    // div = Matrix[i][k] / Matrix[j][k];
    // Matrix[i][k] -= div * Matrix[j][k];
    // leadingToBe = false;
    // } else if ((!leadingToBe)) {
    // Matrix[i][k] -= div * Matrix[j][k];
    // }
    // }
    // }
    // }
    // return Matrix;
    // }

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
        newMat = MATRIKS.GaussJordan(extMat);

        for (int i = 0; i < invMat.length; i++) {
            for (int j = 0; j < invMat[0].length; j++) {
                invMat[i][j] = newMat[i][j + Col];
            }
        }

        return invMat;
    }

    public static double[][] InvCofactor(double[][] mat) {
        double[][] copyMat = MATRIKS.CopyMatrix(mat) ;
        double det = OBE.detOBE(copyMat);
        double[][] matCofactor = MATRIKS.Cofactor(mat);
        double[][] adjMat = MATRIKS.transpose(matCofactor);
        double[][] finalMat = MATRIKS.multiplyMatbyConst(adjMat, (1 / det));

        return finalMat;
    }
}
