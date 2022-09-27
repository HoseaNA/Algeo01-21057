import java.util.*;

public class InterPol {
    public static void InterpolPolaStart(){
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan jumlah titik (n) :");
        int n = in.nextInt();
        double[] X = new double[n];
        double[] Y = new double[n];
        ReadPoint(X, Y, n);
        double[][] MatXSym = CreateMatInterPol(X);
        double[][] MatInterPol = MergeMatWithArr(MatXSym, Y);
        double[] Coeff = SPLResult(MatInterPol);

        printFunct(Coeff);
        in.close();
    }

    public static void ReadPoint(double[] X, double[] Y, int n){
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < n;i++){
            System.out.println("Masukkan titik X"+(i));
            X[i] = in.nextDouble();
            System.out.println("Masukkan titik Y"+(i));
            Y[i] = in.nextDouble();
        }
        in.close();
    }

    public static double[][] CreateMatInterPol(double[] X){
        int n = X.length;
        double[][] Mat = new double[n][n];

        for (int i = 0;i < n;i++){
            for (int j = 0;j < n;j++){
                Mat[i][j] = pow((X[i]),j);
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

    private static double pow(double d, int j) {
        if (j == 0){
            return 1;
        } else {
            return d * pow(d,j-1);
        }
    }

    public static void printFunct(double[] Res){
        System.out.printf("p"+(Res.length-1)+"(x) = %.4f + ",Res[0]);
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