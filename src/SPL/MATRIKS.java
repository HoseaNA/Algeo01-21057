package SPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MATRIKS {
    //Atribut
    double[][] Mat = new double[100][100];
    int RowEff;
    int ColEff;

    //Method

    //Konstruktor dengan input
    MATRIKS(int baris, int kolom) {
        this.RowEff = baris;
        this.ColEff = kolom;

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
    public double getElmt(int baris, int kolom){
        return this.Mat[baris][kolom];
    }

    public static void readMatrix(MATRIKS Matrix,ARRAY Array) {

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
        /** Solutions for each row of equation **/
        for (int i = 0; i < N; i++) {
            System.out.println("\nEnter solutions for equation " + (i + 1));
            Array.Arr[i] = scanner.nextDouble();
        }
    }
    public void readFileMatrix(String Path) {
        this.RowEff = 0;
        this.ColEff = 0;
        // Dengan metode try
        try {
            File file = new File(Path);
            Scanner scanner = new Scanner(file);
            int i =0;
            //
            while(scanner.hasNextLine()){
                this.RowEff += 1;
                Scanner colReader = new Scanner(scanner.nextLine());
                while(colReader.hasNextDouble()){
                    if(i==0){
                        this.ColEff += 1;
                    }
                    colReader.nextDouble();
                }
                i += 1;
            }
            scanner.close();
        }
        catch(FileNotFoundException exit){
            System.out.println("File not Found");
            exit.printStackTrace();
        }
        // Algoritma isi Matrix (WIP)
    }
    public static void printMatrix(MATRIKS Matrix, ARRAY Array)
    {

        for (int i = 0; i < Matrix.RowEff; i++) {
            for (int j = 0; j < Matrix.ColEff; j++)
                System.out.printf("%.2f ", Matrix.Mat[i][j]);
            System.out.printf("| %.2f\n", Array.Arr[i]);
        }
        System.out.println();
    }

}
