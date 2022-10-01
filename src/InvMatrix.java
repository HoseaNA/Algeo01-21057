import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InvMatrix {
    public static void InvStartMethod1(boolean keyboardInput) throws IOException {
        if (keyboardInput) {
            double[][] Mat = ReadMatriksSym();
            double[][] InvMat = new double[Mat.length][Mat[0].length];

            InvMat = InvGaussJordan(Mat);
            System.out.println("Hasil invers adalah :");
            printMatriks(InvMat);
            displaySavePromptInverse(InvMat);
        } else {
            System.out.println("Please enter input file (.txt)");
            Scanner in = new Scanner(System.in);
            String filename = in.nextLine();
            double[][] Mat = MATRIKS.readMatFile(filename);
            double[][] InvMat = new double[Mat.length][Mat[0].length];

            InvMat = InvGaussJordan(Mat);
            System.out.println("Hasil invers adalah :");
            printMatriks(InvMat);
            displaySavePromptInverse(InvMat);
            in.close();
        }
    }

    public static void InvStartMethod2(boolean keyboardInput) throws IOException {
        if (keyboardInput) {
            double[][] Mat = ReadMatriksSym();
            double[][] InvMat = new double[Mat.length][Mat[0].length];

            InvMat = InvCofactor(Mat);
            System.out.println("Hasil invers adalah :");
            printMatriks(InvMat);
            displaySavePromptInverse(InvMat);
        } else {
            System.out.println("Please enter input file (.txt)");
            Scanner in = new Scanner(System.in);
            String filename = in.nextLine();
            double[][] Mat = MATRIKS.readMatFile(filename);
            double[][] InvMat = new double[Mat.length][Mat[0].length];

            InvMat = InvCofactor(Mat);
            System.out.println("Hasil invers adalah :");
            printMatriks(InvMat);
            displaySavePromptInverse(InvMat);
            in.close();
        }
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

    public static void displaySavePromptInverse(double[][] InvMat) throws IOException {

        String prompt;
        boolean back = false;
        System.out.println("\nWould you like to save the result? (Y/N) ");
        System.out.println("Enter Y to Save ");
        System.out.println("Enter N to Quit\n");
        Main.displayCommand();
        do {
            Scanner scanner = new Scanner(System.in);
            prompt = scanner.nextLine().toLowerCase();
            if (prompt.equals("y")) {
                System.out.println("please enter file name (.txt)");
                String filename = scanner.nextLine();
                // Algoritma save to file isi disini
                writeFileInverse(filename, InvMat);
                back = true;
            } else if (prompt.equals("n")) {
                back = true;
            }
        } while (!back);
    }

    public static void writeFileInverse(String FileName, double[][] InvMat) {
        try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
            writer.write("Hasil inverse matriks adalah\n\n");
            for (int i = 0; i < InvMat.length; i++) {
                for (int j = 0; j < InvMat[0].length; j++) {
                    if (j == InvMat[0].length - 1) {
                        writer.write(Double.toString(InvMat[i][j]));
                    } else {
                        writer.write(Double.toString(InvMat[i][j]));
                        writer.write(" ");
                    }
                }
                writer.write("\n");
            }
            writer.write("\n");
            writer.write("\n");
            writer.write("Berhasil menuliskan pada " + FileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}