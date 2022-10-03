import java.io.IOException;
import java.util.Scanner;

// import static SPL.bicubicInt.M;

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
        System.out.println("\n************Regresi Linier Berganda************");
        System.out.println("1. Mencari nilai regresi linier berganda");
        System.out.println("9. Back to Main Menu");
        System.out.println();
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
    public static void displaySavePromptGauss(MATRIKS Matrix, MATRIKS hasil) {

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
                Gauss.writeFileGauss(filename,Matrix,hasil);
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
            MATRIKS hasil = MATRIKS.copyMatriks(Matrix);
            System.out.println("Input Matrix : \n");
            MATRIKS.printMatrix(Matrix);
            System.out.println("Matriks hasil operasi : \n");
            Gauss.rowEchelon(hasil);
            MATRIKS.printMatrix(hasil);
            Gauss.printSolution(hasil);

            displaySavePromptGauss(Matrix,hasil);
            
        } else if (choice == 2) {
            // Input matriks dengan file

            Scanner in = new Scanner(System.in);
            System.out.println("Please enter input file (.txt)");

            String filename = in.nextLine();
            // Bagian ini isi path di github
            String origin = "C:\\Users\\LENOVO\\IdeaProjects\\Tubes Algeo\\src\\SPL\\test\\";
            MATRIKS M = new MATRIKS(origin + filename);
            MATRIKS hasil = MATRIKS.copyMatriks(M);
            System.out.println("\nInput Matrix : \n");
            MATRIKS.printMatrix(hasil);
            System.out.println("Matriks hasil operasi : \n");
            Gauss.rowEchelon(hasil);
            MATRIKS.printMatrix(hasil);
            Gauss.printSolution(hasil);

            displaySavePromptGauss(M,hasil);
        } else {
            System.out.println("Invalid input");
        }
    }

    public static void gaussjordanElim() {
        MATRIKS Matrix = new MATRIKS(rowCap, colcap);
        
        displayInputType();
        if (choice == 1) {
            MATRIKS.readMatrix(Matrix);
            MATRIKS hasil = MATRIKS.copyMatriks(Matrix);
            System.out.println("Input Matrix : \n");
            MATRIKS.printMatrix(Matrix);
            MATRIKS.printMatrix(hasil);
            System.out.println("Matriks hasil operasi : \n");
            Gauss.rowEchelon(hasil);
            Gauss.reducedRE(hasil);
            MATRIKS.printMatrix(hasil);
            MATRIKS.printMatrix(Matrix);
            Gauss.printSolution(hasil);

            displaySavePromptGauss(Matrix,hasil);
        } else if (c == 2) {
            // Input matriks dengan file
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter input file (.txt)");

            String filename = in.nextLine();
            // Bagian ini isi path di github
            String origin = "C:\\Users\\LENOVO\\OneDrive - Institut Teknologi Bandung\\Documents\\GitHub\\Algeo01-21057\\test\\";
            MATRIKS M = new MATRIKS(origin + filename);

            MATRIKS hasil = MATRIKS.copyMatriks(M);
            System.out.println("\nInput Matrix : \n");
            MATRIKS.printMatrix(hasil);
            System.out.println("Matriks hasil operasi : \n");
            Gauss.rowEchelon(hasil);
            Gauss.reducedRE(hasil);
            MATRIKS.printMatrix(hasil);
            Gauss.printSolution(hasil);

            displaySavePromptGauss(M,hasil);
        } else {
            System.out.println("Invalid input");
        }
    }

    public static void inverseEq() {

    }

    public static void cramer() {

    }

    /** PROSEDUR DETERMINAN **/
    public static void determinant() throws IOException {

        do {
            displaySubDet();
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

    public static void detMetode1() throws IOException {
        do {
            displayInputType();
            if (choice == 1) {
                Expansion.ExpCalc1();
                break;
            } else if (choice == 2) {
                Expansion.ExpCalc2();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (choice != 1 && choice != 2);
    }

    public static void detMetode2() throws IOException {
        do {
            displayInputType();
            if (choice == 1) {
                Reduction.RedCalc1();
                break;
            } else if (choice == 2) {
                Reduction.RedCalc2();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (choice != 1 && choice != 2);
    }

    public static void inverse() throws IOException {

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

    public static void invMetode1() throws IOException {
        int input = 0;
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                InvMatrix.InvStartMethod1(true);
                // Save prompt
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                InvMatrix.InvStartMethod1(false);
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

    public static void invMetode2() throws IOException {
        int input = 0;
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                InvMatrix.InvStartMethod2(true);
                // Save prompt
                displaySavePrompt();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                InvMatrix.InvStartMethod2(false);
                // Save prompt
                displaySavePrompt();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (input != 1 && input != 2);
    }

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
        do {
            displayInputType();
            input = scanner.nextInt();
            if (input == 1) {
                // Input matriks dengan keyboard
                InterPol.InterPolaStart1();
                break;
            } else if (input == 2) {
                // Input matriks dengan file
                InterPol.InterPolaStart2();
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
                // bicubicInt.inputPoints(M);
                // // Algoritma cari interpolasi bikubik isi disini
                // bicubicInt.bicubicInterpolation(M);
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
    public static void regression() throws IOException {

        do {
            displaySubRegression();
            c = scanner.nextInt();
            if (c == 1) { // Metode dikenali, memilih metode input regresi
                regMetode1();
            } else { // Metode tidak dikenali
                System.out.println("Invalid input");
            }
        } while (c != 9);
    }

    public static void regMetode1() throws IOException {
        do {
            displayInputType();
            if (choice == 1) {
                Regression.RegCalc1();
                break;
            } else if (choice == 2) {
                Regression.RegCalc2();
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        } while (choice != 1 && choice != 2);
    }

}
