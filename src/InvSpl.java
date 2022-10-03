import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InvSpl {

    public static double[][] readMatFile(String FileName) {
        int Row = 0;
        int Col = 0;
        // membaca size matrix dari file
        try {
            File file = new File("../Algeo01-21057/test/" + FileName);
            Scanner reader = new Scanner(file);
            int i = 0;
            while (reader.hasNextLine()) {
                Row += 1;
                Scanner colReader = new Scanner(reader.nextLine());
                while (colReader.hasNextDouble()) {
                    if (i == 0) {
                        Col += 1;
                    }
                    colReader.nextDouble();
                }
                i += 1;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        

        // isi matrix
        double[][] Mat = new double[Row][Col];
        File file = new File("../Algeo01-21057/test/" + FileName);
        try {
            Scanner rowReader = new Scanner(file);
            for (int i = 0; i < Row; i++) {
                Scanner colReader = new Scanner(rowReader.nextLine());
                for (int j = 0; j < Col; j++) {
                    double data = colReader.nextDouble();
                    Mat[i][j] = data;
                }
                colReader.close();
            }
            rowReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return Mat;
    }
    
    public static void findSol(boolean keyboardInput) throws IOException {
        if (keyboardInput) {
            double[][] inputMat = ReadMatriks();
            double[][] mainMat1 = new double[inputMat.length][inputMat[0].length-1];
            double[][] mainMat2 = new double[inputMat.length][inputMat[0].length-1];
            for (int i = 0; i < mainMat1.length; i++) {
                for (int j = 0; j < mainMat1[0].length; j++) {
                    mainMat1[i][j] = inputMat[i][j];
                    mainMat2[i][j] = inputMat[i][j];
                }
            }
            double det = OBE.detOBE(mainMat1);
            double[][] invMat = new double[inputMat.length][inputMat[0].length-1];
            invMat = InvGaussJordan(mainMat2);
            double[] solArr = new double[invMat.length];
            if (det != 0) {
                for (int i = 0; i < invMat.length; i++) {
                    double curVal = 0;
                    for (int j = 0; j < invMat[0].length; j++) {
                        curVal += invMat[i][j]*inputMat[j][inputMat.length];
                    }
                    if (curVal == 0) {
                        solArr[i] = 0;
                    } else {
                        solArr[i] = curVal;
                    }
                }
                System.out.println("\nSolusi dari matrix adalah:");
                for (int i = 0; i < solArr.length; i ++) {
                    System.out.print("x" + (i+1) + " = ");
                    System.out.printf("%.4f", solArr[i]);
                    System.out.println();
                }
            } else {
                System.out.println("\nMatriks tidak memiliki solusi atau memiliki solusi tak hingga");
            }
            displaySavePrompt(det, solArr);
        } else {
            System.out.println("Masukkan nama file (.txt)");
            Scanner in = new Scanner(System.in);
            String filename = in.nextLine();
            double[][] inputMat = readMatFile(filename);
            double[][] mainMat1 = new double[inputMat.length][inputMat[0].length-1];
            double[][] mainMat2 = new double[inputMat.length][inputMat[0].length-1];
            for (int i = 0; i < mainMat1.length; i++) {
                for (int j = 0; j < mainMat1[0].length; j++) {
                    mainMat1[i][j] = inputMat[i][j];
                    mainMat2[i][j] = inputMat[i][j];
                }
            }
            double det = OBE.detOBE(mainMat1);
            double[][] invMat = new double[inputMat.length][inputMat[0].length-1];
            invMat = InvGaussJordan(mainMat2);
            double[] solArr = new double[invMat.length];
            if (det != 0) {
                for (int i = 0; i < invMat.length; i++) {
                    double curVal = 0;
                    for (int j = 0; j < invMat[0].length; j++) {
                        curVal += invMat[i][j]*inputMat[j][inputMat.length];
                    }
                    if (curVal == 0) {
                        solArr[i] = 0;
                    } else {
                        solArr[i] = curVal;
                    }
                }
                System.out.println("\nSolusi dari matrix adalah:");
                for (int i = 0; i < solArr.length; i ++) {
                    System.out.print("x" + (i+1) + " = ");
                    System.out.printf("%.4f", solArr[i]);
                    System.out.println();
                }
            } else {
                System.out.println("\nMatriks tidak memiliki solusi atau memiliki solusi tak hingga");
            }
            displaySavePrompt(det, solArr);
        }
    }
    
        public static double[][] ReadMatriks() {
            Scanner in = new Scanner(System.in);
    
            System.out.println("Masukkan n:");
            int n = in.nextInt();
            double[][] Mat = new double[n][n+1];
    
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n+1; j++) {
                    System.out.println("\nMasukkan koefisien dari baris " + (i + 1) + " kolom " + (j + 1));
                    Mat[i][j] = in.nextDouble();
                }
            }
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
    
        public static void displaySavePrompt(double det, double[] solArr) throws IOException {
    
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
                    writeFileInverse(filename, det, solArr);
                    back = true;
                } else if (prompt.equals("n")) {
                    back = true;
                }
            } while (!back);
        }
    
        public static void writeFileInverse(String FileName, double det, double[] solArr) {
            try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
                if (det == 0) {
                    writer.write("Matriks tidak memiliki solusi atau memiliki solusi tak hingga\n");
                } else {
                    writer.write("Solusi dari matrix adalah:\n");
                    for (int i = 0; i < solArr.length; i ++) {
                        writer.write("x" + (i+1) + " = ");
                        writer.write(Double.toString(solArr[i]));
                        writer.write("\n");
                    }
                }
                writer.write("Berhasil menuliskan pada " + FileName);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
