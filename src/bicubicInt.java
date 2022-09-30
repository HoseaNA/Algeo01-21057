package SPL;

import java.util.Scanner;

public class bicubicInt {
    // Kamus lokal
    static int i;
    static int j;
    static int k;
    static int l;
    static double x;
    static double y;
    static double hasil;
    static Scanner scan = new Scanner(System.in);

    // Buat matriks 4 x 4 dan matriks 16 x 16
    static MATRIKS M = new MATRIKS(4,4);
    static MATRIKS result = new MATRIKS(4,4);
    static MATRIKS cubic = new MATRIKS(16,16);
    static MATRIKS finalCubic = new MATRIKS(16,17);


    public static void bicubicInterpolation(){
        // Masukkan nilai titik-titik yang akan diinterpolasi
        inputPoints();
        // Prompt ke user untuk menginput titik yang dicari
        inputXY();
        // Mencari nilai dari f(x,y) dengan interpolasi bikubik
        findCubicInter();

    }

    public static void inputPoints(){

        for(i=0;i<4;++i){
            for(j=0;j<4;++j){
                System.out.printf("Masukkan nilai dari f(%d,%d) : \n", (i-1),(j-1));
                M.Mat[i][j] = scan.nextDouble();

            }
        }
    }

    public static void inputXY(){
        System.out.println("\nMencari nilai f(x,y) dengan interpolasi bikubik\n");
        System.out.println("Input nilai x : ");
        x = scan.nextDouble();
        System.out.println("Input nilai y : ");
        y = scan.nextDouble();
    }

    public static void findCubicInter(){
        // Assign values
        for(i=0;i<4;++i){
            for(j=0;j<4;++j){
                for(k=0;k<4;++k){
                    for(l=0;l<4;++l){
                        double val = Math.pow((j-1),l) * Math.pow((i-1),k);
                        cubic.Mat[(4*i) +j][(4*k) +l] = val;
                        finalCubic.Mat[(4*i) +j][(4*k) +l] = val;
                    }
                }
            }
        }

        for(i=0;i<4;++i){
            for(j=0;j<4;++j){
                finalCubic.Mat[(4*i)+j][16] = M.Mat[i][j];
            }
        }

        // Ubah bentuk matriks ke eselon baris reduksi
        Gauss.rowEchelon(finalCubic);
        Gauss.reducedRE(finalCubic);


        for(j=0;j<4;++j){
            for(i=0;i<4;++i){
                result.Mat[i][j] = finalCubic.Mat[(4*j)+i][16];
            }
        }

        for(j=0;j<4;++j){
            for(i=0;i<4;++i){
                hasil += result.Mat[i][j] * Math.pow(x,i) * Math.pow(y,j);
            }
        }
        System.out.printf("\nNilai dari f(%.3f,%.3f) adalah %.3f\n",x,y,hasil);
    }
}

