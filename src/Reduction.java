import java.util.Scanner;
import java.text.DecimalFormat;

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

    public static void RedCalc() {

        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        System.out.println("\nMasukkan nilai n:");
        int n = Integer.parseInt(scanner.nextLine());

        Detmat inputMat = new Detmat(100, 100);
        Detmat.readMat(inputMat, n, n);

        double RedRes = RedSolve(inputMat);
        System.out.println("\nDeterminan matriks:");
        System.out.println(df.format(RedRes));

    }

}