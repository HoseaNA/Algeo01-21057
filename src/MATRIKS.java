import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MATRIKS {
    // Atribut
    double[][] Mat = new double[100][100];
    int RowEff;
    int ColEff;

    // Method

    // Konstruktor dengan input
    MATRIKS(int baris, int kolom) {
        this.RowEff = baris;
        this.ColEff = kolom;
    }

    // Konstruktor metode file
    MATRIKS(String path) {
        readFileMatrix(path);

    }

    public int getRow() {
        return this.RowEff;
    }

    public int getCol() {
        return this.ColEff;
    }

    public void setRow(int N) {
        this.RowEff = N;
    }

    public void setCol(int N) {
        this.ColEff = N;
    }

    public static double getElmt(MATRIKS M, int baris, int kolom) {
        return M.Mat[baris][kolom];
    }

    public static void readMatrix(MATRIKS Matrix) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter number of rows");
        int N = scanner.nextInt();

        System.out.println("\nEnter number of columns");
        int M = scanner.nextInt();
        Matrix.setRow(N);
        Matrix.setCol(M);

        /** Read inputs to set number of variables and equations **/
        for (int i = 0; i < N; i++) {
            System.out.println("\nEnter equations coefficients of row " + (i + 1));
            for (int j = 0; j < M; j++)
                Matrix.Mat[i][j] = scanner.nextDouble();
        }

    }

    public void readFileMatrix(String path) {
        // I.S : menerima file path dengan format file .txt
        // F.S : membaca file .txt dan menyalin isi ke matriks
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                String str = scanner.nextLine().trim();
                int i = 0;
                int j = 0;

                // Menghitung size kolom matriks file
                while (i < str.length()) {
                    if (str.charAt(i) == ' ') {
                        this.ColEff++;
                    }
                    ++i;
                }
                this.ColEff++;
                i = 0;

                Scanner rowreader = new Scanner(file);

                while (rowreader.hasNextDouble()) {
                    double matVal = rowreader.nextDouble();
                    this.Mat[i][j] = matVal;
                    j++;
                    // ganti baris jika sudah kolom terakhir
                    if (j == this.ColEff) {
                        j = 0;
                        i++;
                        this.RowEff++;
                    }
                }
            } else {
                System.out.println("File is empty");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printMatrix(MATRIKS Matrix) {

        for (int i = 0; i < Matrix.RowEff; i++) {
            for (int j = 0; j < Matrix.ColEff; j++)
                System.out.printf("%.2f ", Matrix.Mat[i][j]);
            System.out.println();
        }
        System.out.println();
    }

    public static void printMatriks(double[][] Mat) {
        int Row = Mat.length;
        int Col = Mat[0].length;

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                System.out.printf("%.2f ", Mat[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static boolean isColZero(double[][] mat, int inCol) {
        boolean ColZero = true;

        for (int i = 0; i < mat.length; i++) {
            if (mat[i][inCol] != 0) {
                ColZero = false;
            }
        }
        return ColZero;
    }

    public static double[][] minor(double[][] mat, int Row, int Col) {
        int size = mat.length;
        double[][] lowerMat = new double[size - 1][size - 1];

        int k = 0, l = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != Row && j != Col) {
                    lowerMat[k][l] = mat[i][j];
                    l++;
                }
            }
            if (l == size - 1) {
                l = 0;
                k++;
            }
        }
        return lowerMat;
    }

    public static double[][] Cofactor(double[][] Mat) {
        int size = Mat.length;
        double[][] cofMat = new double[size][size];
        double lowerDet;
        if (size == 1) {
            cofMat[0][0] = Mat[0][0];
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int c = 1;
                    if (((i + 1) + (j + 1)) % 2 == 1) {
                        c = -1;
                    }
                    lowerDet = OBE.detOBE(minor(Mat, i, j));

                    cofMat[i][j] = c * lowerDet;
                }
            }
        }
        return cofMat;
    }

    public static double[][] transpose(double[][] mat) {
        double[][] transposeMat = new double[mat[0].length][mat.length];

        for (int i = 0; i < transposeMat.length; i++) {
            for (int j = 0; j < transposeMat[0].length; j++) {
                transposeMat[i][j] = mat[j][i];
            }
        }
        return transposeMat;
    }

    public static double[][] multiplyMatbyConst(double[][] mat, double x) {
        int Row = mat.length;
        int Col = mat[0].length;
        double[][] multipliedMat = new double[Row][Col];

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                multipliedMat[i][j] = x * mat[i][j];
            }
        }
        return multipliedMat;
    }

    public static double[][] CopyMatrix(double[][] mat) {
        double[][] mCopy = new double[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mCopy[i][j] = mat[i][j];
            }
        }
        return mCopy;
    }

    public static MATRIKS copyMatriks(MATRIKS M) {
        MATRIKS mCopy = new MATRIKS(M.RowEff, M.ColEff);
        MATRIKS temp = new MATRIKS(M.RowEff, M.ColEff);
        for (int i = 0; i < M.RowEff; i++) {
            for (int j = 0; j < M.ColEff; j++) {
                temp.Mat[i][j] = M.Mat[i][j];
                mCopy.Mat[i][j] = temp.Mat[i][j];
            }
        }
        return mCopy;
    }

    /*** Fungsi Pengubah Bentuk Matriks ***/
    public static void reducedRE(double[][] M) {
        // I.S : Menerima matriks M yang berbentuk rowEchelon
        // I.S : Membentuk matriks eselon baris tereduksi dari matriks M

        for (int i = M.length - 1; i > 0; i--) {
            boolean stop = false;
            int j = 0;
            double div;
            while (!stop && j < M[0].length) {
                if (M[i][j] == 1) {
                    // loop baris indeks k dan i untuk mencari div
                    for (int k = i - 1; k >= 0; k--) {
                        div = (M[k][j] / M[i][j]);
                        for (int l = M[0].length - 1; l >= 0; l--) {
                            M[k][l] -= M[i][l] * div;
                        }
                    }
                    // Jika terpenuhi stop loop
                    stop = true;
                }
                j++;
            }
        }
    }

    public static void rowEchelon(double[][] M) {
        /*
         * I.S Menerima Matriks M yang telah terisi
         * /* F.S Mengubah matriks M menjadi bentuk Row Echelon
         */

        // KAMUS LOKAL
        double ratio;
        double temp;

        int i, j, k, l;

        // ALGORITMA

        // Loop setiap baris
        for (i = 0; i < M.length; i++) {
            // Membuat leading one dari leading element
            boolean leadElmt;

            for (k = 0; k < M.length; k++) {
                ratio = 1;
                leadElmt = true;
                for (j = 0; j < M[0].length; j++) {
                    if (leadElmt && M[k][j] != 0) {
                        ratio = 1 / M[k][j];
                        M[k][j] *= ratio;
                        leadElmt = false;
                    } else if (!leadElmt && M[k][j] != 0) {
                        M[k][j] *= ratio;
                    }
                }
            }

            // Mengubah semua elemen dibawah leading one menjadi nol
            for (int row = i + 1; row < M.length; row++) {
                if (M[i][leadElmtIndex(i, M)] != 0) {
                    ratio = M[row][leadElmtIndex(i, M)] / M[i][leadElmtIndex(i, M)]; // ratio untuk pengali
                    for (int col = 0; col < M[0].length; col++) {
                        M[row][col] -= ratio * (M[i][col]);
                    }
                }
            }

            // Membentuk matriks segitiga atas dengan menukar baris
            for (k = 0; k < M.length - 1; k++) {
                // Mencari jumlah nol di baris ke-i
                int Nzero;
                Nzero = 0;
                j = 0;
                while (j < M[0].length - 1 && M[k][j] == 0) {
                    Nzero++;
                    j++;
                }
                // Mencari jumlah nol di baris setelah baris ke-i
                int RowToSwitch, zeroRowSw;
                RowToSwitch = -1;
                zeroRowSw = Nzero;
                for (int k1 = k + 1; k1 < M.length; k1++) {
                    int NzeroX;
                    NzeroX = 0;
                    l = 0;
                    while (l < M[0].length - 1 && M[k1][l] == 0) {
                        NzeroX++;
                        l++;
                    }
                    if (NzeroX < zeroRowSw) {
                        RowToSwitch = k1;
                        zeroRowSw = NzeroX;
                    }
                }
                // Menukar 2 baris
                if (RowToSwitch != -1) {

                    for (int m = 0; m < M[0].length; m++) {
                        temp = M[k][m];
                        M[k][m] = M[RowToSwitch][m];
                        M[RowToSwitch][m] = temp;
                    }
                }
            }
        }
    }

    public static int leadElmtIndex(int i, double[][] M) {
        // I.S Mengecek Matriks M baris indeks ke-i, Matriks M berbentuk
        // rowEchelon/ReducedRE
        // F.S Posisi dari leadingElement baris ke-i

        int j = 0;
        while (j < M[0].length - 1) {
            // Mencari leading Element
            if (M[i][j] != 0) {
                break;
            } else {
                j++;
            }
        }
        return j;
    }

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
    //
}
