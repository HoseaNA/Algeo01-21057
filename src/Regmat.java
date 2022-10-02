import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class Regmat {

    double[][] Mat = new double[100][100];
    int RowEff;
    int ColEff;

    Regmat(int Row, int Col) {
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

    public double getElmt(Regmat Matrix, int Row, int Col) {
        return this.Mat[Row][Col];
    }

    public static void readMat(Regmat Matrix, int Row, int Col) {

        Scanner scanner = new Scanner(System.in);
        Matrix.setRow(Row);
        Matrix.setCol(Col+1);

        System.out.println("\nMasukkan nilai x11..xmn dan y1..ym:");
        for (int i = 0; i < Row; i++) {
            String stringElmt = scanner.nextLine();
            String[] splitElmt = stringElmt.split("\\s+");
            double[] doubleElmt = Arrays.stream(splitElmt).mapToDouble(Double::parseDouble).toArray();
            Matrix.Mat[i] = doubleElmt;
        }

    }

    public static void readFile(String path, Regmat Matrix) {
        try {
            File file = new File (path);
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

    public static void makeRegMat(Regmat regMat, int varCount, int sampleCount, Regmat inputMat) {

        regMat.setRow(varCount+1);
        regMat.setCol(varCount+2);

        for(int i = 0; i < regMat.RowEff; i++){
            for(int j = 0; j < regMat.ColEff; j++){
                if(i == 0 && j == 0){
                    regMat.Mat[i][j] = Double.valueOf(sampleCount);
                }
                else if(i == 0 && j > 0){
                    double Elmt = 0;
                    for(int k = 0; k < sampleCount; k++){
                        Elmt += inputMat.Mat[k][j-1];
                    }
                    regMat.Mat[i][j] = Elmt;
                }
                else if(i > 0 && j == 0){
                    double Elmt = 0;
                    for(int k = 0; k < sampleCount; k++){
                        Elmt += inputMat.Mat[k][i-1];
                    }
                    regMat.Mat[i][j] = Elmt;
                }
                else{
                    double Elmt = 0;
                    for(int k = 0; k < sampleCount; k++){
                        Elmt += inputMat.Mat[k][i-1]*inputMat.Mat[k][j-1];
                    }
                    regMat.Mat[i][j] = Elmt;
                }
            }
        }
        
    }

    public static void printMat(Regmat Matrix) {

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