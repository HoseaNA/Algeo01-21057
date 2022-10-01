import java.io.IOException;

public class main1 {
    public static void main(String[] args) throws IOException {
        InterPol.InterPolaStart2();
    }

    public static void printMatriks(double[][] Mat) {
        int Row = Mat.length;
        int Col = Mat[0].length;

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                System.out.printf("%f ", Mat[i][j]);
            }
            System.out.println("\n");
        }
    }
}
