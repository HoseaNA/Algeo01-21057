import java.util.*;
import java.math.*;

public class InterPol {
    static Scanner in = new Scanner(System.in);

    public static void InterpolPolaStart() {
        System.out.println("Masukkan titik terakhir (n) :");
        int n = in.nextInt();

        double[][] X = new double[n + 1][2];
        double[] Result = SolvedFunctInterPol(X, n);

        System.out.println(" ");
        System.out.println("Masukkan nilai X :");
        double X1 = in.nextDouble();
        EstimateInterPol(Result, X1);

        in.close();
    }

    public static double[] SolvedFunctInterPol(double[][] X, int n) {
        ReadPoint(X, n);
        System.out.println(" ");
        double[][] MatXSym = CreateMatInterPol(X);
        double[] Result = SPLResult(MatXSym);
        printFunct(Result);
        return Result;
    }

    public static void EstimateInterPol(double[] Result, double X) {
        System.out.println("");
        double Estimated = 0;

        for (int i = 0; i < Result.length; i++) {
            Estimated += Result[i] * (Math.pow(X, i));
        }
        System.out.printf("Hasil taksiran fungsi f(%.3f) = %.4f\n", X, Estimated);
    }

    public static void ReadPoint(double[][] X, int n) {
        for (int i = 0; i <= n; i++) {
            System.out.println("Masukkan titik X" + (i));
            X[i][0] = in.nextDouble();
            System.out.println("Masukkan titik Y" + (i));
            X[i][1] = in.nextDouble();
        }
    }

    public static double[][] CreateMatInterPol(double[][] X) {
        int n = X.length;
        double[][] Mat = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Mat[i][j] = Math.pow((X[i][0]), j);
            }
            Mat[i][Mat[0].length - 1] = X[i][1];
        }
        return Mat;
    }

    public static double[] SPLResult(double[][] Mat) {
        double[] Result = new double[Mat.length];

        MATRIKS.rowEchelon(Mat);
        MATRIKS.reducedRE(Mat);
        for (int i = 0; i < Mat.length; i++) {
            Result[i] = Mat[i][Mat[0].length - 1];
        }
        return Result;
    }

    public static void printFunct(double[] Res) {
        System.out.printf("f(x) = %.4f + ", Res[0]);
        for (int i = 1; i < Res.length; i++) {
            if (i > 1) {
                if (i != Res.length - 1) {
                    System.out.printf("%.4fX^" + (i) + " + ", Res[i]);
                } else {
                    System.out.printf("%.4fX^" + (i), Res[i]);
                }
            } else {
                System.out.printf("%.4fX + ", Res[i]);
            }
        }
    }
}