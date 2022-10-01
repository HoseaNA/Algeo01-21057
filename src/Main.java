import java.io.IOException;
import java.util.Scanner;

import static SPL.bicubicInt.M;

public class Main {
    // KAMUS GLOBAL
    static int option;
    static int c = 0;
    static int choice;
    static String inputfile;
    boolean exit = false;
    static boolean backToMain = false;
    static Scanner scanner = new Scanner(System.in);
    static final int rowCap = 100;
    static final int colcap = 100;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();
            displayCommand();
            option = scanner.nextInt();
            optionBranch(option);
        } while (option != 7);
        scanner.close();

    }

    /*** PROSEDUR DISPLAY MENU DAN SUBMENU ***/
    public static void displayCommand() {
        System.out.print("Enter input :\n");
    }

    public static void displayMenu() {
        System.out.println("\n---------------MAIN MENU---------------");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
        System.out.println();
    }

    public static void displaySubSPL() {
        System.out.println("\n********Sistem Persamaan Linier********");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("9. Back to Main Menu");
        System.out.println();
    }

    public static void displaySubDet() {
        System.out.println("\n***************Determinan**************");
        System.out.println("1. Metode Ekspansi Kofaktor");
        System.out.println("2. Metode Reduksi Baris");
        System.out.println("9. Back to Main Menu");
        System.out.println();
    }

    public static void displaySubInverse() {
        System.out.println("\n************Matriks Balikan************");
        System.out.println("1. Metode eliminasi Gauss-Jordan");
        System.out.println("2. Metode Adjoin");
        System.out.println("9. Back to Main Menu");
        System.out.println();
    }

    public static void displaySubInterpol() {
        System.out.println("\n************Interpolasi Polinom************");
        System.out.println("1. Mencari nilai interpolasi polinom");
        System.out.println("9. Back to Main Menu");
        System.out.println();
    }

    public static void displaySubBicubic() {
        System.out.println("\n************Interpolasi Bikubik************");
        System.out.println("1. Mencari nilai interpolasi Bikubik");
        System.out.println("9. Back to Main Menu");
        System.out.println();
    }

    public static void displaySubRegression() {

    }

    public static void displayInputType() {
        System.out.println("\nSilahkan pilih metode input : ");
        System.out.println("1. Input from Keyboard");
        System.out.println("2. Input from File");
        System.out.println("9. Kembali");
        choice = scanner.nextInt();

    }

    public static void displaySavePrompt() {

        String prompt;
        boolean back = false;
        System.out.println("\nWould you like to save the result? (Y/N) ");
        System.out.println("Enter Y to Save ");
        System.out.println("Enter N to Quit\n");
        displayCommand();
        do {
            prompt = scanner.nextLine().toLowerCase();
            if (prompt.equals("y")) {
                System.out.println("please enter file name (.txt)");
                String filename = scanner.nextLine();
                // Algoritma save to file isi disini

                back = true;
            } else if (prompt.equals("n")) {
                back = true;
            }
        } while (!back);
    }

    // Prosedur untuk memilih menu selanjutnya
    public static void optionBranch(int option) throws IOException {
        switch (option) {
            case 1:
                // Menu SPL
                SPL();
                break;

            case 2:
                // Menu determinan
                determinant();
                break;

            case 3:
                // Menu inverse
                inverse();
                break;

            case 4:
                // Menu interpolasi polinom
                polInter();
                break;

            case 5:
                // Menu interpolasi bicubic
                cubInter();
                break;

            case 6:
                // Menu regresi linear berganda
                regression();
                break;

            case 7:
                // Menu pilihan exit
                break;

            default:
                System.out.println("Option is invalid!! Please enter the right option");
                break;
        }

    }

    /*** PROSEDUR EKSEKUSI PILIHAN ***/
    /** Setiap prosedur dari pilihan main menu beserta submenu dan prosesnya **/

    /** PROSEDUR SPL **/
    public static void SPL() {

        do {
            displaySubSPL();
            displayCommand();
            c = scanner.nextInt();

            switch (c) {
                case 1:
                    gaussianElim();
                    break;

                case 2:
                    gaussjordanElim();
                    break;

                case 3:
                    inverseEq();
                    break;

                case 4:
                    cramer();
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;

            }
        } while (c != 9);

    }

    // PROSEDUR SUBMENU SPL
    public static void gaussianElim() {
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);

        displayInputType();
        if (choice == 1) {
            MATRIKS.readMatrix(Matrix);
            System.out.println("\nInput Matrix : ");
            MATRIKS.printMatrix(Matrix);
            System.out.println("Matriks hasil operasi : ");
            Gauss.rowEchelon(Matrix);
            MATRIKS.printMatrix(Matrix);
            Gauss.printSolution(Matrix);

            displaySavePrompt();

        } else if (choice == 2) {
            // Input matriks dengan file

            Scanner in = new Scanner(System.in);
            System.out.println("Please enter input file (.txt)");

            String filename = in.nextLine();
            // Bagian ini isi path di github
            String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
            MATRIKS M = new MATRIKS(origin + filename);

            System.out.println("\nInput Matrix : ");
            MATRIKS.printMatrix(M);
            System.out.println("Matriks hasil operasi : ");
            Gauss.rowEchelon(M);
            MATRIKS.printMatrix(M);
            Gauss.printSolution(M);

            displaySavePrompt();
        } else {
            System.out.println("Invalid input");
        }
    }

    public static void gaussjordanElim() {
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);

        displayInputType();
        if (choice == 1) {
            MATRIKS.readMatrix(Matrix);
            System.out.println("Input Matrix : ");
            MATRIKS.printMatrix(Matrix);
            System.out.println("Matriks hasil operasi : ");
            Gauss.rowEchelon(Matrix);
            Gauss.reducedRE(Matrix);
            MATRIKS.printMatrix(Matrix);
            Gauss.printSolution(Matrix);

            displaySavePrompt();
        } else if (c == 2) {
            // Input matriks dengan file
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter input file (.txt)");

            String filename = in.nextLine();
            // Bagian ini isi path di github
            String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
            MATRIKS M = new MATRIKS(origin + filename);

            System.out.println("\nInput Matrix : ");
            MATRIKS.printMatrix(M);
            System.out.println("Matriks hasil operasi : ");
            Gauss.rowEchelon(M);
            MATRIKS.printMatrix(M);
            Gauss.printSolution(M);

            displaySavePrompt();
        } else {
            System.out.println("Invalid input");
        }
    }

    public static void inverseEq() {

    }

    public static void cramer() {

    }

    /** PROSEDUR DETERMINAN **/
    public static void determinant() {

        do {
            displaySubDet();
            displayCommand();
            c = scanner.nextInt();
            if (c == 1) { // Metode determinan cara 1
                detMetode1();
            } else if (c == 2) { // Metode determinan cara 2
                detMetode2();
            } else { // Metode tidak dikenali
                System.out.println("Invalid input");
            }
        } while (c != 9);
    }

    public static void detMetode1() {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                MATRIKS.readMatrix(Matrix);
                // Algoritma cari determinan isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");

                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);

                // Algoritma cari determinan isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    public static void detMetode2() {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                MATRIKS.readMatrix(Matrix);
                // Algoritma cari determinan isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");

                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);

                // Algoritma cari determinan isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    /** PROSEDUR INVERSE MATRIKS **/
    public static void inverse() {

        do {
            displaySubInverse();
            c = scanner.nextInt();
            if (c == 1) { // Metode determinan cara 1
                invMetode1();
            } else if (c == 2) { // Metode determinan cara 2
                invMetode2();
            } else { // Metode tidak dikenali
                System.out.println("Invalid input");
            }
        } while (c != 9);
    }

    public static void invMetode1() {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                InvMatrix.InvStartMethod1();
                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");

                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);

                // Algoritma cari inverse isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    public static void invMetode2() {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                InvMatrix.InvStartMethod2();

                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");

                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);

                // Algoritma cari inverse isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    /**
     * PROSEDUR INTERPOLASI POLINOM
     * 
     * @throws IOException
     **/
    public static void polInter() throws IOException {

        do {
            displaySubInterpol();
            c = scanner.nextInt();
            if (c == 1) { // Metode determinan cara 1
                polMetode1();
            } else { // Metode tidak dikenali
                System.out.println("Invalid input");
            }
        } while (c != 9);
    }

    public static void polMetode1() throws IOException {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                InterPol.InterPolaStart1();
                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                InterPol.InterPolaStart2();

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    /** PROSEDUR INTERPOLASI BICUBIC **/
    public static void cubInter() {

        do {
            displaySubBicubic();
            c = scanner.nextInt();
            if (c == 1) { // Metode determinan cara 1
                bicInterpolation();
            } else { // Metode tidak dikenali
                System.out.println("Invalid input");
            }
        } while (c != 9);
    }

    public static void bicInterpolation() {
        int input = 0;
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                bicubicInt.inputPoints(M);
                // Algoritma cari interpolasi bikubik isi disini
                bicubicInt.bicubicInterpolation(M);
                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");
                System.out.println("Matrix must be 4x4 in size!");

                displayCommand();
                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);
                // Algoritma cari interpolasi bikubik isi disini
                bicubicInt.bicubicInterpolation(M);
                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    /** PROSEDUR REGRESI LINEAR BERGANDA **/
    public static void regression() {

        do {
            displaySubRegression();
            c = scanner.nextInt();
            if (c == 1) { // Metode determinan cara 1
                regMetode1();
            } else if (c == 2) { // Metode determinan cara 2
                regMetode2();
            } else { // Metode tidak dikenali
                System.out.println("Invalid input");
            }
        } while (c != 9);
    }

    public static void regMetode1() {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                MATRIKS.readMatrix(Matrix);
                // Algoritma cari regresi isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");

                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);

                // Algoritma cari regresi isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    public static void regMetode2() {
        int input = 0;
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                MATRIKS.readMatrix(Matrix);
                // Algoritma cari regresi isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                Scanner in = new Scanner(System.in);
                System.out.println("Please enter input file (.txt)");

                String filename = in.nextLine();
                // Bagian ini isi path di github
                String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
                MATRIKS M = new MATRIKS(origin + filename);

                // Algoritma cari regresi isi disini

                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

}
