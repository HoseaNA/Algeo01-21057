public class InvMatrix{
    public static double[][] InvGaussJordan(double[][] mat){
        int Row = mat.length;
        int Col = mat[0].length;

        double[][] extMat = new double[Row][2*Col];
        double[][] newMat = new double[Row][2*Col];
        double[][] invMat = new double[Row][Col];

        for (int i = 0;i < extMat.length; i++){
            for (int j = 0;j < extMat[0].length; j++){
                if (j < Col){
                    extMat[i][j] = mat[i][j];
                } else {
                    if (j - Col == i){
                        extMat[i][j] = 1;
                    } else {
                        extMat[i][j] = 0;
                    }
                }
            }
        }
        newMat = MATRIKS.GaussJordan(extMat);

        for (int i = 0;i < invMat.length;i++){
            for (int j = 0;j < invMat[0].length;j++){
                invMat[i][j] = newMat[i][j+Col];
            }
        }

        return invMat;
    }

    public static double[][] InvCofactor(double[][] mat){
        double det = MATRIKS.det(mat);
        double[][] matCofactor = MATRIKS.Cofactor(mat);
        double[][] transposeCof = MATRIKS.transpose(matCofactor);
        double[][] finalMat = MATRIKS.multiplyMatbyConst(transposeCof, 1/det);

        return finalMat;
    }
}
