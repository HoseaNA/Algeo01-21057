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

    public static double[][] GaussJordan(double[][] mat){
        int Row = mat.length;
        int Col = mat[0].length;

        if (Col <= Row){
            int inCol = 0;
            for (int k = 0; k < Col - 1; k++){
                if (inCol == Col-1){
                    break;
                }
                while (isColZero(mat,inCol) && (inCol < Col)){
                    inCol++;
                }
                double pivot = mat[k][inCol];

                if(pivot == 0){
                    for (int i = k + 1;i < Row;i++){
                        if(mat[i][inCol] != 0){
                            for (int j = 0;j < Col;j++){
                                double temp = mat[k][j];
                                mat[k][j] = mat[i][j];
                                mat[i][j] = temp;
                            }
                            break;
                        }
                    }
                }
                pivot = mat[k][inCol];
                if (pivot == 0){
                    continue;
                } else {
                    for (int j = inCol;j < Col;j++){
                        mat[k][j] = mat[k][j]/pivot;
                    }
                    for (int i = 0;i < Row;i++){
                        if (i == k || mat[i][inCol] == 0){
                            continue;
                        }
                        double factor = mat[i][inCol];
                        for (int j = 0;j < Col;j++){
                            mat[i][j] = mat[i][j] - (factor * mat[k][j]);
                        }
                    }
                }
                inCol++;
            }
        } else {
            int inCol = 0;
            for (int k = 0;k < Row;k++){
                if (inCol == Col-1){
                    break;
                }

                while (isColZero(mat,inCol) && inCol < Col){
                    inCol++;
                }

                double pivot = mat[k][inCol];
                if (pivot == 0){
                    for (int i = k+1;i < Row;i++){
                        if (mat[i][inCol] != 0){
                            for (int j = 0;j < Col;j++){
                                double temp = mat[k][j];
                                mat[k][j] = mat[i][j];
                                mat[i][j] = temp;
                            }
                            break;
                        }
                    }
                }
                pivot = mat[k][inCol];
                if (pivot == 0){
                    continue;
                } else {
                    for (int j = inCol;j < Col;j++){
                        mat[k][j] = mat[k][j]/pivot;
                    }

                    for (int i = 0;i < Row;i++){
                        if (i == k || mat[i][inCol] == 0){
                            continue;
                        }
                        double factor = mat[i][inCol];
                        for (int j = inCol;j < Col;j++){
                            mat[i][j] = mat[i][j] - (factor * mat[k][j]);
                        }
                    }
                }
                inCol++;
            }
        }
        return mat;
    }

    public static boolean isColZero(double[][] mat, int inCol){
        boolean ColZero = true;

        for(int i = 0;i < mat.length;i++){
            if(mat[i][inCol] != 0){
                ColZero = false;
            }
        }
        return ColZero;
    }

    public static double det(double[][] mat){
        int Row = mat.length;
        double det = 0;
        double lowerDet;

        if (Row == 1){
            det = mat[0][0];
        } else {
            for (int i = 0;i < Row;i++){
                int c = 1;
                if (i%2 == 1){
                    c = -1;
                }
                lowerDet = det(oneLower(mat,1,i+1));

                det += c * mat[0][i] * lowerDet;
            }
        }
        return det;
    }

    public static double[][] oneLower(double[][] mat, int Row, int Col){
        int size = mat.length;
        double[][] lowerMat = new double[size-1][size-1];
        Row -= 1;
        Col -= 1;

        for (int i = 0;i < size;i++){
            for (int j = 0;j < size;j++){
                if (i < Row){
                    if (j < Col){
                        lowerMat[i][j] = mat[i][j];
                    } else if (j > Col){
                        lowerMat[i][j-1] = mat[i][j];
                    }
                } else if (i > Row){
                    if (j < Col){
                        lowerMat[i][j] = mat[i][j];
                    } else if (j > Col){
                        lowerMat[i-1][j-1] = mat[i][j];
                    }
                }
            }
        }
        return lowerMat;
    }

    public static double[][] Cofactor(double[][] mat){
        int size = mat.length;
        double[][] cofMat = new double[size][size];
        double lowerDet;

        if (size == 1){
            cofMat[0][0] = mat[0][0];
        } else {
            for (int i = 0;i < size;i++){
                for (int j = 0;j < size;j++){
                    int c = -1;
                    if ((i + j)%2 == 1){
                        c = -1;
                    }
                    lowerDet = det(oneLower(mat,i+1,j+1));
                    cofMat[i][j] = c * lowerDet;
                }
            }
        }
        return cofMat;
    }

    public static double[][] transpose(double[][] mat){
        int i, j;
        double[][] transposeMat = new double[mat[0].length][mat.length];

        for (int i = 0;i < transposeMat.length;i++){
            for (int j = 0;j < transposeMat[0].length;j++){
                transposeMat[i][j] = mat[i][j];
            }
        }
        return transposeMat;
    }

    public static double[][] multiplyMatbyConst(double[][] mat, double x){
        int Row = mat.length;
        int Col = mat[0].length;
        double[][] multipliedMat = new double[Row][Col];

        for (int i = 0;i < Row;i++){
            for (int j = 0;j < Col;j++){
                multipliedMat[i][j] = x * mat[i][j];
            }
        }
        return multipliedMat;
    }
}
