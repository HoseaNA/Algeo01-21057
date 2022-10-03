import java.util.Scanner;
import java.util.Arrays;

public class Cramarr {
    
    double[] Arr = new double[100];
    int Len;

    Cramarr(int Len) {
        this.Len = Len;
    }

    public int getLen() {
        return this.Len;
    }

    public void setLen(int Len) {
        this.Len = Len;
    }

    public double getArrElmt(Cramarr Array, int Col) {
        return this.Arr[Col];
    }

    public static void printArr(Cramarr Array) {

        System.out.print("[");
        for (int i = 0; i < Array.Len; i++) {
            System.out.printf("%.4f", (Array.Arr[i]));
            if (i < Array.Len-1) {
                System.out.print(", ");
            } else {
                System.out.print("]");
            }
        }
        System.out.println();
    }

}