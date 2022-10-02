import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.IOException;

public class Regression {

    public static String[] GaussSolver(Regmat M) {
        String[] resultString = new String[M.ColEff - 1];
        for (int i = 0; i < M.ColEff - 1; i++) {
            resultString[i] = Character.toString((char) (i + 96));
        }
        for (int i = M.RowEff - 1; i >= 0; i--) {
            int lead = leadElmtIndex(i, M);
            if (!isAllInRowZero(M, i)) {
                if (isOneLeadElmt(i, M)) {
                    resultString[lead] = Double.toString(M.Mat[i][M.ColEff - 1]);
                } else {
                    checkResult(M, i, resultString);
                }
            }
        }
        return resultString;
    }

    public static void checkResult(Regmat M, int i, String[] resultString) {
        int lead = leadElmtIndex(i, M);
        double numResult = M.Mat[i][M.ColEff - 1];
        String strResult = "";
        for (int j = leadElmtIndex(i, M) + 1; j < M.ColEff - 1; j++) {
            if (!isElmtZero(M, i, j)) {
                double koefisien = -(M.Mat[i][j]);
                try {
                    numResult += koefisien * Double.parseDouble(resultString[j]);
                } catch (NumberFormatException e) {
                    strResult += addParameter(koefisien, resultString[j]);
                }
            }
        }
        resultString[lead] = numResult + strResult;
    }

    public static boolean isElmtZero(Regmat M, int i, int j) {
        return (M.Mat[i][j] == 0);
    }

    public static boolean isAllInRowZero(Regmat M, int i) {
        boolean found = false;
        int j = 0;
        while (j < M.ColEff - 1 && !found) {
            if (!isElmtZero(M, i, j)) {
                found = true;
            } else {
                j++;
            }
        }
        return !found;
    }

    public static boolean isSolZero(Regmat M, int i) {
        return (M.Mat[i][M.ColEff - 1] == 0);
    }

    public static boolean hasSolution(Regmat M) {
        int i = 0;
        boolean solExist = true;
        while (i < M.RowEff) {
            if (isAllInRowZero(M, i) && !isSolZero(M, i)) {
                solExist = false;
                break;
            }
            ++i;
        }
        return solExist;
    }

    private static String addParameter(double koefisien, String parameter) {
        if (koefisien == 1) {
            return "+" + parameter;
        } else if (koefisien == -1) {
            return "-" + parameter;
        } else if (koefisien > 1) {
            return "+" + koefisien + parameter;
        } else {
            return koefisien + parameter;
        }
    }

    public static boolean isOneLeadElmt(int i, Regmat M) {
        boolean value = true;
        boolean leadElmtFound = false;
        for (int j = 0; j < M.ColEff - 1; j++) {
            if (!isElmtZero(M, i, j)) {
                if (!leadElmtFound) {
                    leadElmtFound = true;
                } else {
                    value = false;
                }
            }
        }
        return value;
    }

    public static int leadElmtIndex(int i, Regmat M) {
        int j = 0;
        while (j < M.ColEff - 1) {
            if (M.Mat[i][j] != 0) {
                break;
            } else {
                j++;
            }
        }
        return j;
    }

    public static void rowEchelon(Regmat M) {
        double ratio;
        double temp;
        int i, j, k, l;
        for (i = 0; i < M.RowEff; i++) {
            boolean leadElmt;
            for (k = 0; k < M.RowEff; k++) {
                ratio = 1;
                leadElmt = true;
                for (j = 0; j < M.ColEff; j++) {
                    if (leadElmt && M.Mat[k][j] != 0) {
                        ratio = 1 / M.Mat[k][j];
                        M.Mat[k][j] *= ratio;
                        leadElmt = false;
                    } else if (!leadElmt && M.Mat[k][j] != 0) {
                        M.Mat[k][j] *= ratio;
                    }
                }
            }
            for (int row = i + 1; row < M.RowEff; row++) {
                if (M.Mat[i][leadElmtIndex(i, M)] != 0) {
                    ratio = M.Mat[row][leadElmtIndex(i, M)] / M.Mat[i][leadElmtIndex(i, M)]; // ratio untuk pengali
                    for (int col = 0; col < M.ColEff; col++) {
                        M.Mat[row][col] -= ratio * (M.Mat[i][col]);
                    }
                }
            }
            for (k = 0; k < M.RowEff - 1; k++) {
                int Nzero;
                Nzero = 0;
                j = 0;
                while (j < M.ColEff - 1 && M.Mat[k][j] == 0) {
                    Nzero++;
                    j++;
                }
                int RowToSwitch, zeroRowSw;
                RowToSwitch = -1;
                zeroRowSw = Nzero;
                for (int k1 = k + 1; k1 < M.RowEff; k1++) {
                    int NzeroX;
                    NzeroX = 0;
                    l = 0;
                    while (l < M.ColEff - 1 && M.Mat[k1][l] == 0) {
                        NzeroX++;
                        l++;
                    }
                    if (NzeroX < zeroRowSw) {
                        RowToSwitch = k1;
                        zeroRowSw = NzeroX;
                    }
                }
                if (RowToSwitch != -1) {
                    for (int m = 0; m < M.ColEff; m++) {
                        temp = M.Mat[k][m];
                        M.Mat[k][m] = M.Mat[RowToSwitch][m];
                        M.Mat[RowToSwitch][m] = temp;
                    }
                }
            }
        }
    }

    public static void printSolution(Regmat M) {
        String[] solution = new String[0];
        String[] Char = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p" };
        if (hasSolution(M)) {
            solution = GaussSolver(M);
            System.out.println("Solusi dari matrix adalah : ");
            // Print solusi matriks tergantung jenis solusi
            defineSolution(M, solution, Char);
        } else {
            System.out.println("There is no solution");
        }
    }

    public static void defineSolution(Regmat M, String[] solution, String[] Char) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        boolean found = false;
        for (int j = 0; j < Char.length; j++) {
            if (solution.toString().contains(Char[j])) {
                found = true;
            }
        }
        if (!found) {
            for (int i = 0; i < solution.length; i++) {
                double sol = Double.parseDouble(solution[i]);
                System.out.printf("x" + (i + 1) + " = " + df.format(sol) + "\n");
            }
        } else {
            for (int i = 0; i < solution.length; i++) {
                System.out.printf("x" + (i + 1) + " = " + solution[i] + "\n");
            }
        }
    }

    public static void savePromt(Regarr ResArr, double RegRes, Regarr inputArr) throws IOException {
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
                writeFile(filename, ResArr, RegRes, inputArr);
                back = true;
            } else if (prompt.equals("n")) {
                back = true;
            }
        } while (!back);
    }

    public static void writeFile(String FileName, Regarr ResArr, double RegRes, Regarr inputArr) {
        try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
            writer.write("Persamaan regresi:\n");
            writer.write("f(x) =  ");
            writer.write(Double.toString(ResArr.Arr[0]));
            writer.write(" + ");
            for (int i = 1; i < ResArr.Len; i++) {
                if (i > 1) {
                    if (i != ResArr.Len-1) {
                        writer.write(Double.toString(ResArr.Arr[i]));
                        writer.write("X");
                        writer.write(Integer.toString(i));
                        writer.write(" + ");
                    } else {
                        writer.write(Double.toString(ResArr.Arr[i]));
                        writer.write("X^");
                        writer.write(Integer.toString(i));
                    }
                } else {
                    writer.write(Double.toString(ResArr.Arr[i]));
                    writer.write("X1 + ");
                }
            }
            writer.write("\n");
            writer.write("Hasil taksiran fungsi f(");
            for (int i = 0; i < inputArr.Len; i++) {
                writer.write(Double.toString(inputArr.Arr[i]));
                if (i < inputArr.Len-1) {
                    writer.write(", ");
                }
            }
            writer.write(") = ");
            writer.write(Double.toString(RegRes));
            writer.write("\n");
            writer.write("Berhasil menuliskan pada " + FileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RegCalc1() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nilai n (jumlah peubah x):");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("\nMasukkan nilai m (jumlah sampel):");
        int m = Integer.parseInt(scanner.nextLine());

        Regmat inputMat = new Regmat(100, 100);
        Regmat.readMat(inputMat, m, n);

        Regarr inputArr = new Regarr(100);
        Regarr.readArr(inputArr, n);

        Regmat regMat = new Regmat(100, 100);
        Regmat.makeRegMat(regMat, n, m, inputMat);

        rowEchelon(regMat);
        String[] strArr = GaussSolver(regMat);

        Regarr numArr = new Regarr(100);
        Regarr.Convert(numArr, strArr);

        double RegRes = 0;
        for (int i = 0; i < numArr.Len; i++) {
            if (i == 0) {
                RegRes += numArr.Arr[i];
            } else {
                RegRes += numArr.Arr[i]*inputArr.Arr[i-1];
            }
        }

        Regarr.printFunct(numArr);
        System.out.println("\nTaksiran nilai fungsi:");
        System.out.printf("f(xk) = " + "%.4f", (RegRes));
        System.out.println();

        savePromt(numArr, RegRes, inputArr);
        
    }

    public static void RegCalc2() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMasukkan nama file:");
        String fileName = scanner.nextLine();

        Regmat fileMat = new Regmat(0, 0);
        Regmat.readFile(fileName, fileMat);

        Regarr inputArr = new Regarr(100);
        Regarr.readArr(inputArr, fileMat.ColEff-1);

        rowEchelon(fileMat);
        String[] strArr = GaussSolver(fileMat);

        Regarr numArr = new Regarr(100);
        Regarr.Convert(numArr, strArr);

        double RegRes = 0;
        for (int i = 0; i < numArr.Len; i++) {
            if (i == 0) {
                RegRes += numArr.Arr[i];
            } else {
                RegRes += numArr.Arr[i]*inputArr.Arr[i-1];
            }
        }

        Regarr.printFunct(numArr);
        System.out.println("\nTaksiran nilai fungsi:");
        System.out.printf("f(xk) = " + "%.4f", (RegRes));
        System.out.println();

        savePromt(numArr, RegRes, inputArr);
        
    }

}