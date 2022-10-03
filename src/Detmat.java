import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;


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

    public static void readFile(String fileName, Detmat Matrix) {
        try {
            File file = new File ("../Algeo01-21057/test/" + fileName);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()){
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    Matrix.RowEff++;
                }
                Scanner getElmt = new Scanner(file);
                if (getElmt.hasNextLine()){
                    for (int i = 0; i < Matrix.RowEff; i++) {
                        String stringElmt = getElmt.nextLine();
                        String[] splitElmt = stringElmt.split("\\s+");
                        double[] doubleElmt = Arrays.stream(splitElmt).mapToDouble(Double::parseDouble).toArray();
                        Matrix.ColEff = doubleElmt.length;
                        Matrix.Mat[i] = doubleElmt;
                    }
                }
            } else {
                System.out.println("File is empty");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printMat(Detmat Matrix) {

        for (int i = 0; i < Matrix.RowEff; i++) {
            System.out.print("[");
            for (int j = 0; j < Matrix.ColEff; j++) {
                System.out.printf("%.4f", (Matrix.Mat[i][j]));
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