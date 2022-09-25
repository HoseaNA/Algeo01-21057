public class OBE {

    static void printMatrix(double[][] dataArray){
        int lastRowIdx = dataArray.length-1;
        int lastColIdx = dataArray[0].length-1;

        for(int i = 0; i <= lastRowIdx; i++){
            System.out.print("[");
            for(int j = 0; j <= lastColIdx; j++){
                System.out.print(dataArray[i][j]);
                if (j < lastColIdx){
                    System.out.print(", ");
                } else {
                    System.out.print("]");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    static void swapRow(double[][] dataArray, int curRow, int targetRow){
        int lastColIdx = dataArray[0].length-1;
        double[] temp = new double[lastColIdx+1];

        for(int j = 0; j <= lastColIdx; j++){
            temp[j] = dataArray[curRow][j];
            dataArray[curRow][j] = dataArray[targetRow][j];
            dataArray[targetRow][j] = temp[j];
        }
    }

    static void makeOne(double[][] dataArray, int curRow, int curCol){
        int lastColIdx = dataArray[0].length-1;

        for(int j = lastColIdx; j >= 0; j--){
            if((dataArray[curRow][curCol]) != 0){
                dataArray[curRow][j] = dataArray[curRow][j] / dataArray[curRow][curCol];
            }
        }
    }

    static void makeZero(double[][] dataArray, int curRow, int prevRow){
        int lastColIdx = dataArray[0].length-1;

        for(int j = lastColIdx; j >= prevRow; j--){
            dataArray[curRow][j] = dataArray[curRow][j] - (dataArray[curRow][prevRow]*dataArray[prevRow][j]);
        }
    }

    static double leadValue(double[][] dataArray, int curRow, int curCol){
        return dataArray[curRow][curCol];
    }

    public static double detOBE(double[][] dataArray){
        int lastRowIdx = dataArray.length-1;
        double detMatrix = 1;
        for(int i = 0; i <= lastRowIdx; i++){
            if(OBE.leadValue(dataArray, i, i) == 0){
                int targetRow = i;
                boolean notFound = true;
                while((targetRow <= lastRowIdx) && (notFound)){
                    if(OBE.leadValue(dataArray, targetRow, i) != 0){
                        notFound = false;
                    }
                    else{
                        targetRow++;
                    }
                }
                if(!(notFound)){
                    OBE.swapRow(dataArray, i, targetRow);
                    detMatrix = (-1)*detMatrix;
                }
            }
            if(dataArray[i][i] != 0){
                detMatrix = detMatrix * dataArray[i][i];
            }
            else{
                detMatrix = 0;
            }
            OBE.makeOne(dataArray, i, i);
            for(int k = i+1; k <= lastRowIdx; k++){
                OBE.makeZero(dataArray, k, i);
            }
        }
        return detMatrix;
    }

}