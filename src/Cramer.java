import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Cramer {

    public static void savePromt(double mainDet, Cramarr solArr) throws IOException {
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
                writeFile(filename, mainDet, solArr);
                back = true;
            } else if (prompt.equals("n")) {
                back = true;
            }
        } while (!back);
    }

    public static void writeFile(String FileName, double mainDet, Cramarr solArr) {
        try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
            if (mainDet == 0) {
                writer.write("Matriks tidak memiliki solusi atau memiliki solusi tak hingga\n");
            } else {
                writer.write("Solusi dari matrix adalah:\n");
                for (int i = 0; i < solArr.Len; i ++) {
                    writer.write("x" + (i+1) + " = ");
                    writer.write(Double.toString(solArr.Arr[i]));
                    writer.write("\n");
                }
            }
            writer.write("Berhasil menuliskan pada " + FileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void CramCalc1() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nilai n:");
        int n = Integer.parseInt(scanner.nextLine());

        Cramat inputMat = new Cramat(100, 100);
        Cramat.readMat(inputMat, n, n);

        Cramat mainMat = new Cramat(inputMat.RowEff, inputMat.ColEff-1);
        Cramat.mainMat(inputMat, mainMat);
        double mainDet = Cramat.findDet(mainMat);

        Cramat curMat = new Cramat(inputMat.RowEff, inputMat.ColEff-1);
        double curDet;
        Cramarr solArr = new Cramarr(inputMat.RowEff);

        if (mainDet != 0) {
            for (int j = 0; j < inputMat.ColEff-1; j ++) {
                Cramat.curMat(inputMat, curMat, j);
                curDet = Cramat.findDet(curMat);
                if (curDet == 0) {
                    solArr.Arr[j] = 0;
                } else {
                    solArr.Arr[j] = curDet / mainDet;
                }
            }
            System.out.println("Solusi dari matrix adalah:");
            for (int i = 0; i < solArr.Len; i ++) {
                System.out.print("x" + (i+1) + " = ");
                System.out.printf("%.4f", solArr.Arr[i]);
                System.out.println();
            }
        } else {
            System.out.println("Matriks tidak memiliki solusi atau memiliki solusi tak hingga");
        }

        savePromt(mainDet, solArr);
        
    }

    public static void CramCalc2() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nama file:");
        String fileName = scanner.nextLine();
        System.out.println();

        Cramat inputMat = new Cramat(0, 0);
        Cramat.readFile(fileName, inputMat);

        Cramat mainMat = new Cramat(inputMat.RowEff, inputMat.ColEff-1);
        Cramat.mainMat(inputMat, mainMat);
        double mainDet = Cramat.findDet(mainMat);

        Cramat curMat = new Cramat(inputMat.RowEff, inputMat.ColEff-1);
        double curDet;
        Cramarr solArr = new Cramarr(inputMat.RowEff);

        if (mainDet != 0) {
            for (int j = 0; j < inputMat.ColEff-1; j ++) {
                Cramat.curMat(inputMat, curMat, j);
                curDet = Cramat.findDet(curMat);
                if (curDet == 0) {
                    solArr.Arr[j] = 0;
                } else {
                    solArr.Arr[j] = curDet / mainDet;
                }
            }
            System.out.println("Solusi dari matrix adalah:");
            for (int i = 0; i < solArr.Len; i ++) {
                System.out.print("x" + (i+1) + " = ");
                System.out.printf("%.4f", solArr.Arr[i]);
                System.out.println();
            }
        } else {
            System.out.println("Matriks tidak memiliki solusi atau memiliki solusi tak hingga");
        }

        savePromt(mainDet, solArr);

    }

}
