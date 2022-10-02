import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;

public class InterPol {
    static Scanner in = new Scanner(System.in);

    public static void InterPolaStart1() throws IOException {
        System.out.println("Masukkan titik terakhir (n) :");
        int n = in.nextInt();

        double[][] X = new double[n + 1][2];
        double[] Result = SolvedFunctInterPol1(X, n);

        System.out.println(" ");
        System.out.println("Masukkan nilai X :");
        double X1 = in.nextDouble();
        double Estimated = EstimateInterPol(Result, X1);
        displaySavePromptPolInter(Result, Estimated, X1);

    }

    public static void InterPolaStart2() throws IOException {
        double[] Result = SolvedFunctInterPol2();

        System.out.println(" ");
        System.out.println("Masukkan nilai X :");
        double X1 = in.nextDouble();
        double Estimated = EstimateInterPol(Result, X1);
        displaySavePromptPolInter(Result, Estimated, X1);

    }

    public static double[] SolvedFunctInterPol1(double[][] X, int n) {
        ReadPoint(X, n);
        System.out.println(" ");
        double[][] MatXSym = CreateMatInterPol(X);
        double[] Result = SPLResult(MatXSym);
        printFunct(Result);
        return Result;
    }

    public static double[] SolvedFunctInterPol2() {
        System.out.println("Masukkan nama file :");
        String FileName = in.next();
        double[][] X = MATRIKS.readMatFile(FileName);
        System.out.println(" ");
        double[][] MatXSym = CreateMatInterPol(X);
        double[] Result = SPLResult(MatXSym);
        printFunct(Result);
        return Result;
    }

    public static double EstimateInterPol(double[] Result, double X) {
        System.out.println("");
        double Estimated = 0;

        for (int i = 0; i < Result.length; i++) {
            Estimated += Result[i] * (Math.pow(X, i));
        }
        System.out.printf("Hasil taksiran fungsi f(%.3f) = %.4f\n", X, Estimated);
        return Estimated;
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

    public static void displaySavePromptPolInter(double[] Result, double Estimated, double X) throws IOException {

        String prompt;
        boolean back = false;
        System.out.println("\nApakah ingin menyimpan hasil? (Y/N) ");
        System.out.println("Masukkan Y untuk Save ");
        System.out.println("Masukkan N untuk Quit\n");
        Main.displayCommand();
        do {
            Scanner scanner = new Scanner(System.in);
            prompt = scanner.nextLine().toLowerCase();
            if (prompt.equals("y")) {
                System.out.println("Masukkan nama file (.txt)");
                String filename = scanner.nextLine();
                // Algoritma save to file isi disini
                writeFilePolInter(filename, Result, Estimated, X);
                back = true;
            } else if (prompt.equals("n")) {
                back = true;
            }
        } while (!back);
    }

    public static void writeFilePolInter(String FileName, double[] Result, double Estimated, double X) {
        try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
            writer.write("Persamaan polinom\n");
            writer.write("f(x) =  ");
            writer.write(Double.toString(Result[0]));
            writer.write(" + ");
            for (int i = 1; i < Result.length; i++) {
                if (i > 1) {
                    if (i != Result.length - 1) {
                        writer.write(Double.toString(Result[i]));
                        writer.write("X^");
                        writer.write(Integer.toString(i));
                        writer.write(" + ");
                    } else {
                        writer.write(Double.toString(Result[i]));
                        writer.write("X^");
                        writer.write(Integer.toString(i));
                    }
                } else {
                    writer.write(Double.toString(Result[i]));
                    writer.write("X + ");
                }
            }
            writer.write("\n");
            writer.write("\n");
            writer.write("Hasil taksiran fungsi f(");
            writer.write(Double.toString(X));
            writer.write(") = ");
            writer.write(Double.toString(Estimated));
            writer.write("\n");
            writer.write("\n");
            writer.write("Berhasil menuliskan pada " + FileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}