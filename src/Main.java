import java.util.Scanner;

public class MainProgram {
    // KAMUS GLOBAL
    boolean exit = false;
    static boolean backToMain = false;
    static Scanner scanner = new Scanner(System.in);
    static final int rowCap = 100;
    static final int colcap = 100;
    public static void main(String[] args) {

        int option;
        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();
            displayCommand();
            option = scanner.nextInt();
            optionBranch(option);
        }
        while(option != 7);
        scanner.close();

    }



        /*** PROSEDUR DISPLAY MENU DAN SUBMENU ***/
        public static void displayCommand() {
            System.out.print("Enter input : ");
        }
        public static void displayMenu() {
            System.out.println("---------------MAIN MENU---------------");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic");
            System.out.println("6. Regresi linier berganda");
            System.out.println("7. Keluar");
            System.out.println();
        }
        public static void displaySubSPL(){
            System.out.println("********Sistem Persamaan Linier********");
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.println("9. Back to Main Menu");
            System.out.println();
        }
        public static void displaySubDet(){
            System.out.println("***************Determinan**************");
            System.out.println("1. Metode Ekspansi Kofaktor");
            System.out.println("2. Metode Reduksi Baris");
            System.out.println("9. Back to Main Menu");
            System.out.println();
        }
        public static void displaySubInverse(){
            System.out.println("************Matriks Balikan************");
            System.out.println("1. Metode eliminasi Gauss-Jordan");
            System.out.println("2. Metode Adjoin");
            System.out.println("9. Back to Main Menu");
            System.out.println();
        }
        public static void displaySubInterpol(){

        }
        public static void displaySubBicubic(){

        }
        public static void displaySubRegression(){

        }
        public static void displayInputType() {
            System.out.println("Silahkan pilih metode input : ");
            System.out.println("1. Input from Keyboard");
            System.out.println("2. Input from File");
            System.out.println("9. Kembali");
        }

        // Prosedur untuk memilih menu selanjutnya
        public static void optionBranch(int option) {
            switch (option)
            {
                case 1 :
                    // Menu SPL
                    SPL();
                    break;

                case 2 :
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
        public static void SPL(){
            {

                int choice = 0;

                do {
                    displaySubSPL();
                    displayCommand();
                    choice = scanner.nextInt();

                    switch (choice) {
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
                }while(choice!=9);

            }

        }

        // PROSEDUR SUBMENU SPL
        public static void gaussianElim(){
            MATRIKS Matrix = new MATRIKS(rowCap,colcap);
            int c = 0;

            displayInputType();
            if(c==1){
                MATRIKS.readMatrix(Matrix);
                System.out.println("Input Matrix : ");
                MATRIKS.printMatrix(Matrix);
                System.out.println("Matriks hasil operasi : ");
                Gauss.rowEchelon(Matrix);
                MATRIKS.printMatrix(Matrix);
                Gauss.printSolution(Matrix);
            }else if (c==2) {
                // // Input matriks dengan file
            }else{
                System.out.println("Invalid input");
            }
        }
        public static void gaussjordanElim(){
            MATRIKS Matrix = new MATRIKS(rowCap,colcap);
            int c = 0;

            displayInputType();
            if(c==1){
                MATRIKS.readMatrix(Matrix);
                System.out.println("Input Matrix : ");
                MATRIKS.printMatrix(Matrix);
                System.out.println("Matriks hasil operasi : ");
                Gauss.rowEchelon(Matrix);
                Gauss.reducedRE(Matrix);
                MATRIKS.printMatrix(Matrix);
                Gauss.printSolution(Matrix);
            }else if (c==2) {
                // Input matriks dengan file
            }else{
                System.out.println("Invalid input");
            }
        }
        public static void inverseEq(){

        }
        public static void cramer(){

        }

        /** PROSEDUR DETERMINAN **/
        public static void determinant(){
            int c;

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
            }while(c!=9);
        }
        public static void detMetode1(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        public static void detMetode2(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        /** PROSEDUR INVERSE MATRIKS **/
        public static void inverse(){
            int c;
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
            }while(c!=9);
        }
        public static void invMetode1(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        public static void invMetode2(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }

        /** PROSEDUR INTERPOLASI POLINOM **/
        public static void polInter(){
            int c;
            do {
                displaySubInterpol();
                c = scanner.nextInt();
                if (c == 1) { // Metode determinan cara 1
                    polMetode1();
                } else if (c == 2) { // Metode determinan cara 2
                    polMetode2();
                } else { // Metode tidak dikenali
                    System.out.println("Invalid input");
                }
            }while(c!=9);
        }
        public static void polMetode1(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        public static void polMetode2(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        /** PROSEDUR INTERPOLASI BICUBIC **/
        public static void cubInter(){
            int c;
            do {
                displaySubBicubic();
                c = scanner.nextInt();
                if (c == 1) { // Metode determinan cara 1
                    bicMetode1();
                } else if (c == 2) { // Metode determinan cara 2
                    bicMetode2();
                } else { // Metode tidak dikenali
                    System.out.println("Invalid input");
                }
            }while(c!=9);
        }
        public static void bicMetode1(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        public static void bicMetode2(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        /** PROSEDUR REGRESI LINEAR BERGANDA **/
        public static void regression(){
            int c;
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
            }while(c!=9);
        }
        public static void regMetode1(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }
        public static void regMetode2(){
            int input = 0;
            do {
                displayInputType();
                input = scanner.nextInt();
                if (input == 1) {
                    // Input matriks dengan keyboard
                    break;
                } else if (input == 2) {
                    // Input matriks dengan file
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }
            }while(input != 1 && input != 2);
        }

}
