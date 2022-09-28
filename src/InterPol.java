import java.util.*;
import java.math.*;

public class InterPol {
    static Scanner in = new Scanner(System.in);
    public static void InterpolPolaStart(){
        System.out.println("Masukkan titik terakhir (n) :");
        int n = in.nextInt();

        double[] X = new double[n+1];
        double[] Y = new double[n+1];
        double[] Result = SolvedFunctInterPol(X, Y, n);

        System.out.println(" ");
        System.out.println("Masukkan nilai X :");
        double X1 = in.nextDouble();
        EstimateInterPol(Result, X1);
        
        in.close();
    }

    public static double[] SolvedFunctInterPol(double[] X, double[] Y, int n){
        ReadPoint(X, Y, n);
        System.out.println(" ");
        double[][] MatXSym = CreateMatInterPol(X);
        double[][] MatInterPol = MergeMatWithArr(MatXSym, Y);
        double[] Result = SPLResult(MatInterPol);
        printFunct(Result);
        return Result;
    }

    public static void EstimateInterPol(double[] Result, double X){
        System.out.println("");
        double Estimated = 0;

        for (int i = 0;i < Result.length;i++){
            Estimated += Result[i]*(Math.pow(X,i));
        }
        System.out.printf("Hasil taksiran fungsi f(%.1f    ) = %.4f", X, Estimated);
    }

    public static void ReadPoint(double[] X, double[] Y, int n){
        for (int i = 0; i <= n;i++){
            System.out.println("Masukkan titik X"+(i));
            X[i] = in.nextDouble();
            System.out.println("Masukkan titik Y"+(i));
            Y[i] = in.nextDouble();
        }
    }

    public static double[][] CreateMatInterPol(double[] X){
        int n = X.length;
        double[][] Mat = new double[n][n];

        for (int i = 0;i < n;i++){
            for (int j = 0;j < n;j++){
                Mat[i][j] = Math.pow((X[i]),j);
            }
        }
        return Mat;
    }

    public static double[][] MergeMatWithArr(double[][] Mat, double[] Y){
        double[][] NewMat = new double[Mat.length][Mat[0].length+1];

        for (int i = 0;i < Mat.length;i++){
            for (int j = 0;j < Mat[0].length;j++){
                NewMat[i][j] = Mat[i][j];
                if (j == Mat[0].length-1){
                    NewMat[i][j+1] = Y[i];
                }
            }
        }
        return NewMat;
    }

    public static double[] SPLResult(double[][] Mat){
        double[] Result = new double[Mat.length];

        MATRIKS.GaussJordan(Mat);
        for (int i = 0;i < Mat.length;i++){
            Result[i] = Mat[i][Mat[0].length-1];
        }
        return Result;
    }

    public static void printFunct(double[] Res){
        System.out.printf("f(x) = %.4f + ",Res[0]);
        for (int i = 1;i < Res.length;i++){
            if (i > 1){
                if (i != Res.length-1){
                    System.out.printf("%.4fX^"+(i)+" + ",Res[i]);
                } else {
                    System.out.printf("%.4fX^"+(i),Res[i]);
                }
            } else {
                System.out.printf("%.4fX + ",Res[i]);
            }
        }
    }
}