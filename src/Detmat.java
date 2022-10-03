import java.util.Scanner;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Detmat {

    double[][] Mat = new double[100][100];
    int RowEff;
    int ColEff;

    Detmat(int Row, int Col) {
        this.RowEff = Row;
        this.ColEff = Col;
    }

    public int getRow() {
        return this.RowEff;
    }

    public int getCol() {
        return this.ColEff;
    }

    public void setRow(int Row) {
        this.RowEff = Row;
    }

    public void setCol(int Col) {
        this.ColEff = Col;
    }

    public double getElmt(Detmat Matrix, int Row, int Col) {
        return this.Mat[Row][Col];
    }

    public static void readMat(Detmat Matrix, int Row, int Col) {

        Scanner scanner = new Scanner(System.in);
        Matrix.setRow(Row);
        Matrix.setCol(Col);

        System.out.println("\nMasukkan koefisien aij:");
        for (int i = 0; i < Row; i++) {
            String stringElmt = scanner.nextLine();
            String[] splitElmt = stringElmt.split("\\s+");
            double[] doubleElmt = Arrays.stream(splitElmt).mapToDouble(Double::parseDouble).toArray();
            Matrix.Mat[i] = doubleElmt;
        }

    }

    public static void printMat(Detmat Matrix) {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        for (int i = 0; i < Matrix.RowEff; i++) {
            System.out.print("[");
            for (int j = 0; j < Matrix.ColEff; j++) {
                System.out.print(df.format(Matrix.Mat[i][j]));
                if (j < Matrix.ColEff-1) {
                    System.out.print(", ");
                } else {
                    System.out.print("]");
                }
            }
            System.out.println();
        }
        
        System.out.println();

    }

}