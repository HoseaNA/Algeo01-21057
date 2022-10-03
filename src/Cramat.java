import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class Cramat {

    double[][] Mat = new double[100][100];
    int RowEff;
    int ColEff;

    Cramat(int Row, int Col) {
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

    public double getElmt(Cramat Matrix, int Row, int Col) {
        return this.Mat[Row][Col];
    }

    public static void readMat(Cramat Matrix, int Row, int Col) {

        Scanner scanner = new Scanner(System.in);
        Matrix.setRow(Row);
        Matrix.setCol(Col+1);

        System.out.println("\nMasukkan koefisien aij dan bi:");
        for (int i = 0; i < Row; i++) {
            String stringElmt = scanner.nextLine();
            String[] splitElmt = stringElmt.split("\\s+");
            double[] doubleElmt = Arrays.stream(splitElmt).mapToDouble(Double::parseDouble).toArray();
            Matrix.Mat[i] = doubleElmt;
        }

    }

    public static void readFile(String fileName, Cramat Matrix) {
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

    public static void mainMat(Cramat inpMat, Cramat outMat) {
        
        for (int i = 0; i < inpMat.RowEff; i++) {
            for (int j = 0; j < inpMat.ColEff-1; j++) {
                    outMat.Mat[i][j] = inpMat.Mat[i][j];
            }
        }

    }
    
    public static void curMat(Cramat inpMat, Cramat outMat, int curCol) {
        
        for (int i = 0; i < inpMat.RowEff; i++) {
            for (int j = 0; j < inpMat.ColEff-1; j++) {
                if (j == curCol) {
                    outMat.Mat[i][j] = inpMat.Mat[i][inpMat.ColEff-1];
                } else {
                    outMat.Mat[i][j] = inpMat.Mat[i][j];
                }
            }
        }

    }

    public static void swapRow(Cramat Matrix, int curRow, int targetRow) {
        double[] temp = new double[Matrix.ColEff];
        for (int j = 0; j < Matrix.ColEff; j++) {
            temp[j] = Matrix.Mat[curRow][j];
            Matrix.Mat[curRow][j] = Matrix.Mat[targetRow][j];
            Matrix.Mat[targetRow][j] = temp[j];
        }
    }

    public static void makeOne(Cramat Matrix, int curRow, int curCol) {
        for (int j = Matrix.ColEff-1; j >= 0; j--) {
            if (Matrix.Mat[curRow][curCol] != 0) {
                Matrix.Mat[curRow][j] = Matrix.Mat[curRow][j] / Matrix.Mat[curRow][curCol];
            }
        }
    }

    public static void makeZero(Cramat Matrix, int curRow, int prevRow) {
        for (int j = Matrix.ColEff-1; j >= prevRow; j--) {
            Matrix.Mat[curRow][j] = Matrix.Mat[curRow][j] - (Matrix.Mat[curRow][prevRow]*Matrix.Mat[prevRow][j]);
        }
    }

    public static double leadVal(Cramat Matrix, int curRow, int curCol) {
        return Matrix.Mat[curRow][curCol];
    }

    public static double findDet(Cramat Matrix) {
        double detMat = 1;
        for (int i = 0; i < Matrix.RowEff; i++) {
            if (leadVal(Matrix, i, i) == 0) {
                int targetRow = i;
                boolean notFound = true;
                while ((targetRow < Matrix.RowEff) && (notFound)) {
                    if (leadVal(Matrix, targetRow, i) != 0) {
                        notFound = false;
                    } else {
                        targetRow++;
                    }
                }
                if (!(notFound)) {
                    swapRow(Matrix, i, targetRow);
                    detMat = (-1)*detMat;
                }
            }
            if (Matrix.Mat[i][i] != 0) {
                detMat = detMat*Matrix.Mat[i][i];
            } else {
                detMat = 0;
            }
            makeOne(Matrix, i, i);
            for (int k = i+1; k < Matrix.RowEff; k++) {
                makeZero(Matrix, k, i);
            }
        }
        return detMat;
    }

    public static void printMat(Cramat Matrix) {

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