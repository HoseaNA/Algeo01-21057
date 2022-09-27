import java.util.*;

public class InterPol {
    public static void InterpolPolaStart(){
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan jumlah titik (n) :");
        int n = in.nextInt();
        double[] X = new double[n];
        double[] Y = new double[n];
        ReadPoint(X, Y, n);
        

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

    public static void FinalFunctInterPol(double[][] Mat){

        

    }

    private static double pow(double d, int j) {
        if (j == 0){
            return 1;
        } else {
            return d * pow(d,j-1);
        }
    }
}