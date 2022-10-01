import java.util.Scanner;
import java.util.Arrays;
import java.text.DecimalFormat;

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

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        System.out.print("[");
        for (int i = 0; i < Array.Len; i++) {
            System.out.print(df.format(Array.Arr[i]));
            if (i < Array.Len - 1) {
                System.out.print(", ");
            } else {
                System.out.print("]");
            }
        }
        System.out.println();
    }

}