import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.IOException;

public class Gauss {

    
    /*** Fungsi Mencari Solusi dari Matriks ***/
    /***
     * Prekondisi: Matriks sudah berbentuk Row Echelon atau Reduced Row Echelon
     ***/
    public static String[] GaussSolver(MATRIKS M) {
        // I.S Matriks M berbentuk rowEchelon/reducedRE dan mempunyai solusi
        // F.S Solusi dari matriks berbentuk string gabungan nilai dan parameter
        String[] resultString = new String[M.ColEff - 1]; // Check
        for (int i = 0; i < M.ColEff - 1; i++) {
            resultString[i] = Character.toString((char) (i + 96));
        }
        for (int i = M.RowEff - 1; i >= 0; i--) {
            int lead = leadElmtIndex(i, M);
            if (!isAllInRowZero(M, i)) {
                if (isOneLeadElmt(i, M)) {
                    // Baris indeks i hanya memiliki satu leading element dengan elemen lain nol
                    resultString[lead] = Double.toString(M.Mat[i][M.ColEff - 1]);
                } else {
                    // baris indeks i memiliki lebih dari satu leading element
                    checkResult(M, i, resultString);
                }
            }
        }
        return resultString;
    }

    public static void checkResult(MATRIKS M, int i, String[] resultString) {
        // I.S : Menerima sebuah Matriks M, indeks baris i, dan array hasil resulString
        // F.S : Mengembalikan string solusi dengan parameter jika diperlukan
        int lead = leadElmtIndex(i, M);
        double numResult = M.Mat[i][M.ColEff - 1];
        String strResult = "";
        for (int j = leadElmtIndex(i, M) + 1; j < M.ColEff - 1; j++) {
            if (!isElmtZero(M, i, j)) {
                // Memastikan elemen baris i kolom j bukan nol
                double koefisien = -(M.Mat[i][j]);
                try {
                    // Apabila hasil merupakan bilangan dijumlahkan dengan hasil
                    numResult += koefisien * Double.parseDouble(resultString[j]);
                } catch (NumberFormatException e) {
                    // Apabila hasil bukan bilangan koefisien disambungkan dengan parameter
                    strResult += addParameter(koefisien, resultString[j]);
                }
            }
        }
        // Menggabungkan string angka dan string parameter
        resultString[lead] = numResult + strResult;
    }

    public static boolean isElmtZero(MATRIKS M, int i, int j) {
        return (M.Mat[i][j] == 0);
    }

    public static boolean isAllInRowZero(MATRIKS M, int i) {
        // I.S Matriks M berbentuk Row Echelon atau Reduced Row Echelon dan i
        // menunjukkan
        // indeks baris
        // F.S Mengembalikan nilai true jika semua nilai dalam baris
        // bernilai nol
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

    public static boolean isSolZero(MATRIKS M, int i) {
        // I.S : Array A terdefinisi dan tidak kosong
        // F.S : Mengembalikan nilai true jika pada array hasil terdapat
        // nilai 0 pada sebuah baris indeks i
        return (M.Mat[i][M.ColEff - 1] == 0);
    }

    public static boolean hasSolution(MATRIKS M) {
        // I.S Matriks sudah berbentuk Row Echelon atau Reduced Row Echelon
        // F.S Menghasilkan True jika Matriks mempunyai solusi
        // dan False jika tidak
        int i = 0;
        boolean solExist = true;
        while (i < M.RowEff) {
            // Cek nilai 0 pada matrix dan array solusi
            // Solusi tidak ada jika koefisien matrix 0 dan
            // nilai solusi tidak 0
            if (isAllInRowZero(M, i) && !isSolZero(M, i)) {
                solExist = false;
                break;
            }
            ++i;
        }
        return solExist;
    }

    private static String addParameter(double koefisien, String parameter) {
        // I.S : Menerima sebuah string dan parameter
        // F.S : Menggabungkan hasil string dan parameter

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

    public static boolean isOneLeadElmt(int i, MATRIKS M) {
        // I.S : Matriks M berbentuk rowEchelon/ReducedRE
        // F.S : Mengembalikan nilai true jika Matriks M baris ke-i hanya memiliki satu
        // leadElement
        boolean value = true;
        boolean leadElmtFound = false;
        for (int j = 0; j < M.ColEff - 1; j++) {
            // Mencari leading one
            if (!isElmtZero(M, i, j)) {
                if (!leadElmtFound) {
                    leadElmtFound = true;
                } else {
                    // Memeriksa elemen setelah leading one hingga sebelum kolom hasil
                    value = false;
                }
            }
        }
        return value;
    }

    public static int leadElmtIndex(int i, MATRIKS M) {
        // I.S Mengecek Matriks M baris indeks ke-i, Matriks M berbentuk
        // rowEchelon/ReducedRE
        // F.S Posisi dari leadingElement baris ke-i

        int j = 0;
        while (j < M.ColEff - 1) {
            // Mencari leading Element
            if (M.Mat[i][j] != 0) {
                break;
            } else {
                j++;
            }
        }
        return j;
    }

    /*** Fungsi Pengubah Bentuk Matriks ***/
    public static void reducedRE(MATRIKS M) {
        // I.S : Menerima matriks M yang berbentuk rowEchelon
        // I.S : Membentuk matriks eselon baris tereduksi dari matriks M

        for (int i = M.RowEff - 1; i > 0; i--) {
            boolean stop = false;
            int j = 0;
            double div;
            while (!stop && j < M.ColEff) {
                if (M.Mat[i][j] == 1) {
                    // loop baris indeks k dan i untuk mencari div
                    for (int k = i - 1; k >= 0; k--) {
                        div = (M.Mat[k][j] / M.Mat[i][j]);
                        for (int l = M.ColEff - 1; l >= 0; l--) {
                            M.Mat[k][l] -= M.Mat[i][l] * div;
                        }
                    }
                    // Jika terpenuhi stop loop
                    stop = true;
                }
                j++;
            }
        }
    }

    public static void rowEchelon(MATRIKS M) {
        
         // I.S Menerima Matriks M yang telah terisi
         // F.S Mengubah matriks M menjadi bentuk Row Echelon

        // KAMUS LOKAL
        double pengali;
        int i, j, k, l;

        // ALGORITMA

        // Loop setiap baris
        for (i = 0; i < M.RowEff; i++) {
            // Membuat leading one dari leading element
            boolean leadElmt;

            for (k = 0; k < M.RowEff; k++) {
                pengali = 1;
                leadElmt = true;
                for (j = 0; j < M.ColEff; j++) {
                    if (leadElmt && !isElmtZero(M, k, j)) {
                        pengali = 1 / M.Mat[k][j];
                        M.Mat[k][j] *= pengali;
                        leadElmt = false;
                    } else if (!leadElmt && !isElmtZero(M, k, j)) {
                        M.Mat[k][j] *= pengali;
                    }
                }
            }

            // Mengubah semua elemen dibawah leading one menjadi nol
            for (int baris = i + 1; baris < M.RowEff; baris++) {
                int lead = leadElmtIndex(i, M);
                if (M.Mat[i][lead] != 0) {
                    pengali = M.Mat[baris][lead] / M.Mat[i][lead];
                    for (int col = 0; col < M.ColEff; col++) {
                        M.Mat[baris][col] -= pengali * (M.Mat[i][col]);
                    }
                }
            }
            // Ubah menjadi matriks segitiga atas
            upperTriangle(M);
        }
    }
    
    public static void upperTriangle(MATRIKS M){
        // Algoritma membentuk matriks segitiga atas
        int j, k, l;
        for (k = 0; k < M.RowEff -1; k++) {
            int switchRow = -1;
            findRowZero(M, k);
            // Algoritma untuk menukar 2 baris
            double temp;
            if (switchRow != -1) {
                for (int m = 0; m < M.ColEff; ++m) {
                    temp = M.Mat[k][m];
                    M.Mat[k][m] = M.Mat[switchRow][m];
                    M.Mat[switchRow][m] = temp;
                }
            }
        }
    }
    public static void findRowZero(MATRIKS M, int k){
        // Mencari jumlah nol di baris ke-i
        int j = 0;
        int numZero = 0;
        while (j < M.ColEff -1 && isElmtZero(M, k, j)) {
            ++numZero;
            ++j;
        }
        // Algoritma menghitung jumlah nol di baris setelah baris ke-i
        int switchRow;
        int rowZero = numZero;
        for (int m = k + 1; m < M.RowEff; ++m) {
            int numZero2 = 0;
            int l = 0;
            while (l < M.ColEff -1 && isElmtZero(M, m, l)) {
                ++numZero2;
                ++l;
            }
            if (rowZero > numZero2) {
                rowZero = numZero2;
                switchRow = m;
            }
        }
    }

    /*** Fungsi untuk memberikan solusi kepada user ***/
    public static String[] printSolution(MATRIKS M) {
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
        return solution;
    }

    public static String[] defineSolution(MATRIKS M, String[] solution, String[] Char) {
        // Menentukan jenis solusi yang ditemukan dan menentukan
        // format untuk mencetak solusi

        // Tentukan jumlah digit di belakang koma
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        // Menentukan jika ada char(parameter) pada string
        boolean found = false;

        for (int j = 0; j < Char.length; j++) {
            if (solution.toString().contains(Char[j])) {
                found = true;
            }
        }
        // Mencetak solusi berdasarkan jenis
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
        return solution;
    }
    public static void writeFileGauss(String FileName, MATRIKS Matrix, MATRIKS hasil) {
        try (FileWriter writer = new FileWriter("../Algeo01-21057/test/output/" + FileName)) {
            writer.write("\nInput Matrix : \n");
            String str ="";
            for (int i = 0; i < Matrix.RowEff; i++) {
                for (int j = 0; j < Matrix.ColEff; j++) {
                    String elem = String.format("%.3f", Matrix.Mat[i][j]);
                    str += (elem + " ");
                }
                str += "\n";
            }
            str += "\n";
            writer.write(str);
            writer.write("\n");
            writer.write("Matriks hasil operasi : \n");

            String strOps ="";
            for (int i = 0; i < hasil.RowEff; i++) {
                for (int j = 0; j < hasil.ColEff; j++) {
                    String elem = String.format("%.3f", hasil.Mat[i][j]);
                    strOps += (elem + " ");
                }
                strOps += "\n";
            }
            strOps += "\n";
            writer.write(strOps);
            writer.write("\n");
            
            if (hasSolution(hasil)) {
                writer.write("Solusi dari matrix adalah : \n");
                // Print solusi matriks tergantung jenis solusi
                String [] sol = printSolution(hasil);
                for (int i=0; i< sol.length; i++){
                    writer.write("x"+(i+1)+" = "+sol[i] +"\n");
                }
            } else {
                writer.write("There is no solution");
            }
            writer.write("\nBerhasil menuliskan pada " + FileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
