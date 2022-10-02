import java.util.Scanner;
import java.util.Arrays;

public class Regarr {
    
    double[] Arr = new double[100];
    int Len;

    Regarr(int Len) {
        this.Len = Len;
    }

    public int getLen() {
        return this.Len;
    }

    public void setLen(int Len) {
        this.Len = Len;
    }

    public double getArrElmt(Regarr Array, int Col) {
        return this.Arr[Col];
    }

    public static void readArr(Regarr Array, int Len) {

        Scanner scanner = new Scanner(System.in);
        Array.setLen(Len);

        System.out.println("\nMasukkan nilai xk:");
        String stringElmt = scanner.nextLine();
        
        String[] splitElmt = stringElmt.split("\\s+");
        double[] doubleElmt = Arrays.stream(splitElmt).mapToDouble(Double::parseDouble).toArray();
    
        for (int i = 0; i < Len; i++) {
            Array.Arr[i] = doubleElmt[i];
        }
        
    }

    public static void Convert(Regarr numArr, String[] strArr) {
        numArr.setLen(strArr.length);
        double[] doubleElmt = Arrays.stream(strArr).mapToDouble(Double::parseDouble).toArray();
        for (int i = 0; i < strArr.length; i++) {
            numArr.Arr[i] = doubleElmt[i];
        }
    }

    public static void printArr(Regarr Array) {

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

    public static void printFunct(Regarr Array) {
        System.out.println("\nPersamaan regresi:");
        System.out.printf("f(x) = %.4f + ", Array.Arr[0]);
        for (int i = 1; i < Array.Len; i++) {
            if (i > 1) {
                if (i != Array.Len-1) {
                    System.out.printf("%.4fx" + (i) + " + ", Array.Arr[i]);
                } else {
                    System.out.printf("%.4fx" + (i), Array.Arr[i]);
                }
            } else {
                System.out.printf("%.4fx1 + ", Array.Arr[i]);
            }
        }
    }

}