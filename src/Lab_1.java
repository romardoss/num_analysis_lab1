import java.util.Arrays;

public class Lab_1 {
    static int variant = 6;
    static double a = 0.2 * (variant - 5);
    static double b = a;
    static double[][] matrix = {
            {8.30, 2.62+a, 4.10, 1.90, -10.65+b},
            {3.92, 8.45, 8.78-a, 2.46, 12.21},
            {3.77, 7.21+a, 8.04, 2.28, 15.45-b},
            {2.21, 3.65-a, 1.69, 6.99, -8.35}
    };
    static int[] x = new int[matrix.length];

    public static void main(String[] args) {
        printMatrix(matrix);
        System.out.println(Arrays.toString(findMAX(matrix)));
        fillX(x);
        System.out.println(Arrays.toString(x));
        swapElementsInArray(x, 2, 3);
        System.out.println(Arrays.toString(x));
        swapRowInMatrix(matrix, 1, 2);
        printMatrix(matrix);
    }

    public static void printMatrix(double[][] m){
        //print matrix on the screen
        int n = m.length;
        for (double[] doubles : m) {
            System.out.print("| ");
            for (int j = 0; j < n; j++) {
                System.out.printf("%.3f ", doubles[j]);
            }
            System.out.printf("| %.3f |\n", doubles[n]);     //last element
        }
    }

    public  static int[] findMAX(double[][] m){
        //searching for maximum element in matrix (without  last right column)
        double max = Double.MIN_VALUE;
        int[] index = {-1, -1};
        int n = m.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(max < m[i][j]){
                    max = m[i][j];
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }

    public static void fillX(int[] x){
        //fill x array with sequence from 1 to n (from x1 to xn)
        for(int i = 0; i < x.length; i++){
            x[i] = i+1;
        }
    }

    public static void swapElementsInArray(int[] x, int i, int j){
        //swap two elements in array of integer type
        int buf = x[i];
        x[i] = x[j];
        x[j] = buf;
    }

    public static void swapRowInMatrix(double[][] m, int r1, int r2){
        //swap two rows in matrix
        for(int i = 0; i < m.length; i++){
            double buf = m[r1][i];
            m[r1][i] = m[r2][i];
            m[r2][i] = buf;
        }
    }
}
