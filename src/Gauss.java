
public class Gauss {

/** Missing:
 * 1. method to solve matrix
 * 1. method to print solution
 * **/

//    public static String[] Solver(MATRIKS M){
//        // I.S MATRIKS M berbentuk REF/RREF dan mempunyai solusi
//        // F.S Sebuah Array of String yang berisi solusi dari persamaan yang direpresentasikan Matriks M
//        String[] resultArray = new String[M.NKolEff-1]; //Check
//        for (int i=0; i<M.NKolEff-1; i++) {
//            resultArray[i] = Character.toString((char) (i + 96));
//        }
//        for (int i=M.NBrsEff-1; i>=0; i--) {
//            if (!isCoefZero(i,M)) {
//                if (SingleLeading(i,M)) {
//                    //Hanya ada leading one saja pada baris ke-i, sisanya 0
//                    resultArray[IndexLeading(i,M)] = Double.toString(M.Tab[i][M.NKolEff-1]);
//                }
//                else{
//                    //Ada elemen non-0 setelah leading one pada baris ke-i
//                    double resDouble = M.Tab[i][M.NKolEff-1];
//                    String resString = "";
//                    for (int j=IndexLeading(i,M)+1; j<M.NKolEff-1; j++) {
//                        if (M.Tab[i][j] != 0) {
//                            //Kondisi elemen M ke i,j bukan 0
//                            try {
//                                //Jika hasil ke-x merupakan bilangan, maka jumlahkan dengan elemen hasil
//                                resDouble += (-1)*M.Tab[i][j]*Double.valueOf(resultArray[j]);
//                            } catch(NumberFormatException e) {
//                                //Jika hasil ke-x bukan bilangan, sambungkan koefisien dengan parameter yang sesuai
//                                resString += ConCoefParam((-1)*M.Tab[i][j],resultArray[j]);
//                            }
//                        }
//                    }
//                    //Gabungkan bilangan hasil dengan parameter
//                    resultArray[IndexLeading(i,M)] = Double.toString(resDouble) + resString;
//                }
//            }
//        }
//        return resultArray;
//    }
    /** function to print solution **/
//    public void printSolution(MATRIKS Matrix, ARRAY Array) {
//
//        int N = Matrix.RowEff;
//        int M = Matrix.ColEff;
//        System.out.println("\nSolution : ");
//        if (N >= M) {
//            for (int i = 0; i < N; i++)
//                System.out.printf("x" + (i + 1) + " = %.2f\n ", Array.Arr[i]);
//
//            System.out.println();
//        } else {
//            for (int i = 0; i < M; i++)
//                System.out.printf("x" + (i + 1) + " = %.2f\n ", Array.Arr[i]);
//            System.out.println();
//        }
//    }

    public static void printSolution(MATRIKS M, ARRAY A){
        if(hasSolution(M,A)){

        }else{
            System.out.println("There is no solution");
        }
    }
    public static boolean isAllInRowZero(MATRIKS M, ARRAY A, int i){
        // I.S Matriks M berbentuk Row Echelon atau Reduced Row Echelon dan i menunjukkan
        // indeks baris
        // F.S Mengembalikan nilai true jika semua nilai dalam baris
        // bernilai nol
        boolean found = false;
        boolean found1 = false;
        boolean found2 = false;
        int j= 0;
        int k =0;
        while(j < M.ColEff && !found){
            if(M.Mat[i][j] == 0){
                ++j;
            }else{
                found1 = true;
            }
        }
        while(k < A.Len && !found){
            if(A.Arr[i] == 0){
                ++k;
            }else{
                found2 = true;
            }
        }
        if(found1 || found2){
            found = true;
        }
        return !found;
    }
    public static boolean isSolZero(ARRAY A){
        // I.S : Array A terdefinisi dan tidak kosong
        // F.S : Mengembalikan nilai true jika pada array hasil terdapat
        // nilai 0 pada sebuah baris indeks i
        int i;
        boolean found = false;
        for(i=0; i< A.Len; ++i) {
            if (A.Arr[i] == 0) {
                found = true;
            }
        }
        return found;
    }

    public static boolean hasSolution(MATRIKS M, ARRAY A){
        // I.S Matriks sudah berbentuk Row Echelon atau Reduced Row Echelon
        // F.S Menghasilkan True jika Matriks mempunyai solusi
        // dan False jika tidak
        int i =0;
        boolean solExist = true;
        while(solExist && i<M.RowEff){
            // Cek nilai 0 pada matrix dan array solusi
            // Solusi tidak ada jika koefisien matrix 0 dan
            // nilai solusi tidak 0
            if(isAllInRowZero(M,A,i) && !isSolZero(A)){
                solExist = false;
            }else{
                ++i;
            }
        }
        return solExist;
    }
    private static String addParameter(double koefisien, String parameter){
        // I.S : Menerima sebuah string dan parameter
        // F.S : Menggabungkan hasil string dan parameter
        if (koefisien == 1){
            return "+" + parameter;
        }else if (koefisien == -1) {
            return "-" + parameter;
        }
        else if (koefisien>1) {
            return "+" + koefisien + parameter;
        }else{
            return koefisien + parameter;
        }

    }
    public static void RowEchelon(MATRIKS Matrix, ARRAY Array) {
        // I.S : menerima sebuah matrix input dan sebuah array solusi tiap persamaan
        // F.S : Membentuk sebuah matriks eselon baris dari matrix input
        int N = Matrix.RowEff;
        for (int k = 0; k < N; k++) {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(Matrix.Mat[i][k]) > Math.abs(Matrix.Mat[max][k]))
                    max = i;

            /** swap row in A matrix **/
            double[] temp = Matrix.Mat[k];
            Matrix.Mat[k] = Matrix.Mat[max];
            Matrix.Mat[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = Array.Arr[k];
            Array.Arr[k] = Array.Arr[max];
            Array.Arr[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++) {
                double factor = Matrix.Mat[i][k] / Matrix.Mat[k][k];
                Array.Arr[i] -= factor * Array.Arr[k];
                for (int j = k; j < N; j++) {
                    Matrix.Mat[i][j] -= factor * Matrix.Mat[k][j];
                }
            }
            /** Divide each row by its leading one-to-be **/
            double div;
            for(int i = k; i<N; i++){
                Array.Arr[i] = Array.Arr[i]/Matrix.Mat[i][i];
                for(int j = N-1; j>=k; j--){
                    div = Matrix.Mat[i][i];
                    Matrix.Mat[i][j] = Matrix.Mat[i][j]/div;
                }
            }
        }

        /** Print row echelon form **/
        MATRIKS.printMatrix(Matrix,Array);

//        /** back substitution **/
//        ARRAY solution = new ARRAY(N);
//        for (int i = N - 1; i >= 0; i--) {
//            double sum = 0.0;
//            for (int j = i + 1; j < N; j++)
//                sum += Matrix.Mat[i][j] * solution.Arr[j];
//            solution.Arr[i] = (Array.Arr[i] - sum) / Matrix.Mat[i][i];
//        }

    }

    public static void ReducedRowEchelon(MATRIKS Matrix, ARRAY Array) {
	    // I.S : Menerima sebuah Matriks yang sudah berbentuk eselon baris
	    // F.S : Mengubah matriks eselon baris menjadi eselon baris tereduksi
        double div;
        boolean leadingToBe;

        //Mengurangi tiap elemen diatas leading one-to-be
//        int k = 0;
        for ( int i = 0; i < Matrix.RowEff; i++) {
            for (int j = i+1; j < Matrix.ColEff; j++) {
                div = 1;
                leadingToBe = true;
//                while(k<Matrix.ColEff && Matrix.Mat[j][k] != 0){
//                    if (leadingToBe){
//                        div = Matrix.Mat[i][k]/Matrix.Mat[j][k];
//                        Matrix.Mat[i][k] -= div*Matrix.Mat[j][k];
//                        Array.Arr[i] -= div*Array.Arr[k];
//                        leadingToBe = false;
//                    }else if (!leadingToBe){
//                        Matrix.Mat[i][k] -= div*Matrix.Mat[j][k];
//                        Array.Arr[i] -= div*Array.Arr[k];
//                    }
                for ( int k = 0; k < Matrix.ColEff; k++) {
                    if (leadingToBe && Matrix.Mat[j][k] != 0){
                        div = Matrix.Mat[i][k]/Matrix.Mat[j][k];
                        Matrix.Mat[i][k] -= div*Matrix.Mat[j][k];
                        Array.Arr[i] -= div*Array.Arr[k];
                        leadingToBe = false;
                    }else if ((!leadingToBe)){
                        Matrix.Mat[i][k] -= div*Matrix.Mat[j][k];
                        Array.Arr[i] -= div*Array.Arr[k];
                    }
                }
            }
        }
    }
}
