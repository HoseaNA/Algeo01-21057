package SPL;

import java.util.Scanner;

public class GaussElimination {

    /** function to print in row echelon form **/
    public void printEselonBaris(double[][] var, double[] sumEq,int N,int M)
    {

        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.printf("%.2f ", var[i][j]);
            System.out.printf("| %.2f\n", sumEq[i]);
        }
        System.out.println();
    }
    /** function to print solution **/
    public void printSolution(double[] sol,int N, int M) {

        System.out.println("\nSolution : ");
        if (N > M) {
            for (int i = 0; i < N; i++)
                System.out.printf("x" + (i + 1) + " = %.2f, ", sol[i]);
            System.out.println();
        } else {
            for (int i = 0; i < M; i++)
                System.out.printf("x" + (i + 1) + " = %.2f, ", sol[i]);
            System.out.println();
        }
    }
    public void solve(double[][] A, double[] B,int N,int M) {

        for (int k = 0; k < N; k++) {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            /** swap row in A matrix **/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++) {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++) {
                    A[i][j] -= factor * A[k][j];
                }
            }
            /** Divide each row by its leading one-to-be **/
            double div;
            for(int i = k; i<N; i++){
                B[i] = B[i]/A[i][i];
                for(int j = N-1; j>=k; j--){
                    div = A[i][i];
                    A[i][j] = A[i][j]/div;
                }
            }
        }

        /** Print row echelon form **/
        printEselonBaris(A, B,N,M);

        /** back substitution **/
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        /** Print solution **/
        printSolution(solution,N,M);
    }

    /** Main function **/
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gaussian Elimination Method\n");
        /** Turn Gauss Elimination class into an object **/
        GaussElimination GE = new GaussElimination();

        System.out.println("\nEnter number of rows");
        int N = scanner.nextInt();
        System.out.println("\nEnter number of columns");
        int M = scanner.nextInt();

        /** Initialize arrays and matrix according to input **/
        double[] B = new double[N];
        double[][] A = new double[N][M];

        /** Read inputs to set number of variables and equations **/
        for (int i = 0; i < N; i++) {
            System.out.println("\nEnter equations coefficients of row " + (i+1));
            for (int j = 0; j < M; j++)
                A[i][j] = scanner.nextDouble();
        }
        /** Solutions for each row of equation **/
        for (int i = 0; i < N; i++) {
            System.out.println("\nEnter solutions for equation " + (i+1));
            B[i] = scanner.nextDouble();
        }

        /** Solve the given matrix with gaussian elimination **/
        GE.solve(A,B,N,M);
    }
}
