import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Reduction {

    public static void swapRow(Detmat Matrix, int curRow, int targetRow) {
        double[] temp = new double[Matrix.ColEff];
        for (int j = 0; j < Matrix.ColEff; j++) {
            temp[j] = Matrix.Mat[curRow][j];
            Matrix.Mat[curRow][j] = Matrix.Mat[targetRow][j];
            Matrix.Mat[targetRow][j] = temp[j];
        }
    }

    public static void makeOne(Detmat Matrix, int curRow, int curCol) {
        for (int j = Matrix.ColEff-1; j >= 0; j--) {
            if (Matrix.Mat[curRow][curCol] != 0) {
                Matrix.Mat[curRow][j] = Matrix.Mat[curRow][j] / Matrix.Mat[curRow][curCol];
            }
        }
    }

    public static void makeZero(Detmat Matrix, int curRow, int prevRow) {
        for (int j = Matrix.ColEff-1; j >= prevRow; j--) {
            Matrix.Mat[curRow][j] = Matrix.Mat[curRow][j] - (Matrix.Mat[curRow][prevRow]*Matrix.Mat[prevRow][j]);
        }
    }

    public static double leadVal(Detmat Matrix, int curRow, int curCol) {
        return Matrix.Mat[curRow][curCol];
    }

    public static double RedSolve(Detmat Matrix) {
        double detMat = 1;
        for (int i = 0; i < Matrix.RowEff; i++) {
            if (Reduction.leadVal(Matrix, i, i) == 0) {
                int targetRow = i;
                boolean notFound = true;
                while ((targetRow < Matrix.RowEff) && (notFound)) {
                    if (Reduction.leadVal(Matrix, targetRow, i) != 0) {
                        notFound = false;
                    } else {
                        targetRow++;
                    }
                }
                if (!(notFound)) {
                    Reduction.swapRow(Matrix, i, targetRow);
                    detMat = (-1)*detMat;
                }
            }
            if (Matrix.Mat[i][i] != 0) {
                detMat = detMat*Matrix.Mat[i][i];
            } else {
                detMat = 0;
            }
            Reduction.makeOne(Matrix, i, i);
            for (int k = i+1; k < Matrix.RowEff; k++) {
                Reduction.makeZero(Matrix, k, i);
            }
        }
        return detMat;
    }

    public static void savePromt(double redRes) throws IOException {
        String prompt;
        boolean back = false;
        System.out.println("\nApakah ingin menyimpan hasil? (Y/N) ");
        do {
            Scanner scanner = new Scanner(System.in);
            prompt = scanner.nextLine().toLowerCase();
            if (prompt.equals("y")) {
                System.out.println("Masukkan nama file (.txt)");
                String filename = scanner.nextLine();
                // Algoritma save to file isi disini
                writeFile(filename, redRes);
                back = true;
            } else if (prompt.equals("n")) {
                back = true;
            }
        } while (!back);
    }

    public static void writeFile(String FileName, double redRes) {
        try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
            writer.write("Determinan matriks:\n");
            writer.write(Double.toString(redRes));
            writer.write("\n");
            writer.write("Berhasil menuliskan pada " + FileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RedCalc1() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nilai n:");
        int n = Integer.parseInt(scanner.nextLine());

        Detmat inputMat = new Detmat(100, 100);
        Detmat.readMat(inputMat, n, n);

        double RedRes = RedSolve(inputMat);
        System.out.println("\nDeterminan matriks:");
        System.out.printf("%.4f", (RedRes));
        System.out.println();

        savePromt(RedRes);

    }

    public static void RedCalc2() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nama file:");
        String fileName = scanner.nextLine();

        Detmat inputMat = new Detmat(0, 0);
        Detmat.readFile(fileName, inputMat);

        double RedRes = RedSolve(inputMat);
        System.out.println("\nDeterminan matriks:");
        System.out.printf("%.4f", (RedRes));
        System.out.println();

        savePromt(RedRes);

    }

}