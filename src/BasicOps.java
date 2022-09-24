import java.util.Scanner;

public class BasicOps {
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

    public void ReadMatrixSym(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter number of rows");
        int N = scanner.nextInt();
        
        double[][] A = new double[N][N];

        for (int i = 0; i < N; i++) {
            System.out.println("\nEnter equations coefficients of row " + (i+1));
            for (int j = 0; j < N; j++)
                A[i][j] = scanner.nextDouble();
        }
    }
}
