import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Expansion {

    public static Detmat Minor(Detmat Matrix, int curCol){

        Detmat minMat =  new Detmat(Matrix.RowEff-1, Matrix.ColEff-1);

        for (int i = 0; i < minMat.RowEff; i++) {
            int lastCol = 0;
            for (int j = 0; j < minMat.ColEff; j++) {
                boolean notFound = true;
                while (notFound) {
                    if (lastCol == curCol) {
                        lastCol++;
                    } else {
                        minMat.Mat[i][j] = Matrix.Mat[i+1][lastCol];
                        lastCol++;
                        notFound = false;
                    }
                }
            }
        }

        return minMat;

    }


    public static double Recursion(Detmat Matrix, int curCol){
        
        double ResRec = 0;
        Detmat nextMinor = Minor(Matrix, curCol);

        if (nextMinor.RowEff > 2) {
            for(int j = 0; j < nextMinor.ColEff; j++){
                // System.out.println(Matrix.Mat[0][j]);
                // System.out.println();
                // System.out.println(ResRec);
                ResRec += Matrix.Mat[0][j]*Cofactor(j)*(Recursion(nextMinor, j));
            }
        } else {
            // System.out.println();
            // System.out.println(Matrix.Mat[0][curCol]);
            // System.out.println();
            // System.out.println(Cofactor(curCol));
            // System.out.println();
            // System.out.println(DetMinor(nextMinor));
            ResRec += Matrix.Mat[0][curCol]*Cofactor(curCol)*DetMinor(nextMinor);
        }

        // System.out.println();
        // Detmat.printMat(nextMinor);
        // System.out.println(ResRec);
        return ResRec;

    }

    public static int Cofactor(int curCol){
        int Cof;
        if ((curCol % 2) == 0) {
            Cof = 1;
        } else {
            Cof = -1;
        }
        return Cof;
    }

    public static double DetMinor(Detmat Matrix) {
        double detMin = (Matrix.Mat[0][0]*Matrix.Mat[1][1]) - (Matrix.Mat[1][0]*Matrix.Mat[0][1]);
        return detMin;
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

    public static void ExpCalc1() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nilai n:");
        int n = Integer.parseInt(scanner.nextLine());

        Detmat inputMat = new Detmat(100, 100);
        Detmat.readMat(inputMat, n, n);

        double ExpRes = 0;
        for (int i = 0; i < inputMat.ColEff; i++) {
            ExpRes += Recursion(inputMat, i);
            // System.out.println(ExpRes);
        }

        System.out.println("\nDeterminan matriks:");
        System.out.printf("%.4f", (ExpRes));
        System.out.println();

        savePromt(ExpRes);

    }

    public static void ExpCalc2() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nama file:");
        String fileName = scanner.nextLine();

        Detmat inputMat = new Detmat(0, 0);
        Detmat.readFile(fileName, inputMat);

        double ExpRes = 0;
        for (int i = 0; i < inputMat.ColEff; i++) {
            ExpRes += Recursion(inputMat, i);
            // System.out.println(ExpRes);
        }

        System.out.println("\nDeterminan matriks:");
        System.out.printf("%.4f", (ExpRes));
        System.out.println();

        savePromt(ExpRes);

    }

}