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
    static double[] foundX = new double[matrix.length];

    public static void main(String[] args) {
        System.out.println("Початкова матриця:");
        printMatrix(matrix);
        fillX(x);
        System.out.println();
        controller(matrix);
        System.out.println("Трикутна матриця");
        printMatrix(matrix);
        getResults(matrix, foundX);
        // Отримані значення іксів та їх порядок
        System.out.println();
//        System.out.print("Порядок іксів: ");
//        System.out.println(Arrays.toString(x));
//        System.out.print("Повне значення іксів: ");
//        System.out.println(Arrays.toString(foundX));
//        System.out.println();
        System.out.println("Знайдені відповіді:");
        printAnswers(x, foundX);
    }

    public static void printMatrix(double[][] m){
        //print matrix on the screen
        int n = m.length;
        for (double[] doubles : m) {
            System.out.print("| ");
            for (int j = 0; j < n; j++) {
//                if(doubles[j] >= 0){
//                    System.out.printf("%.4f ", doubles[j]);
//                }
//                else{
//                    System.out.printf("%.3f ", doubles[j]);
//                }
                System.out.print(val(doubles[j]) + " ");
            }
//            if(doubles[n] >= 0){
//                System.out.printf("| %.4f |\n", doubles[n]);     //last element
//            }
//            else{
//                System.out.printf("| %.3f |\n", doubles[n]);
//            }
            System.out.println("| " + val(doubles[n]) + " |");
        }
    }

    public  static int[] findMAX(double[][] m, int row){
        //searching for maximum element in matrix (without  last right column)
        double max = Double.MIN_VALUE;
        int[] index = {-1, -1};
        int n = m.length;
        int column = n - row;
        for(int i = row; i < n; i++){
            for(int j = 0; j < column; j++){
                if(max < Math.abs(m[i][j])){
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

    public static void swapElementsInArray(double[] x, int i, int j){
        //swap two elements in array of double type
        double buf = x[i];
        x[i] = x[j];
        x[j] = buf;
    }

    public static void swapRowInMatrix(double[][] m, int r1, int r2){
        //swap two rows in matrix
        double[] temp = m[r1];
        m[r1] = m[r2];
        m[r2] = temp;
    }

    public static void swapColumnsInMatrix(double[][] m, int c1, int c2){
        //swap two columns in matrix
        swapElementsInArray(x, c1, c2);
        for (double[] doubles : m) {
            swapElementsInArray(doubles, c1, c2);
        }
    }

    public static void replaceMax(double[][] m, int row){
        //replace max element to first position of matrix (submatrix if first != 0)
        int column = m[0].length - 2 - row;
        int[] index = findMAX(m, row);
        swapRowInMatrix(m, row, index[0]);
        swapColumnsInMatrix(m, column, index[1]);
    }

    public static void makeElementEqualOne(double[][] m, int row){
        //method makes the element x (on m[row][column] position) equal 1 and other
        //elements in a row divide by x element
        int column = m[0].length - 2 - row;
        m[row][m[0].length-1] /= m[row][column];       //last element
        for(int i = 0; i <= column; i++){
            m[row][i] /= m[row][column];
        }
    }

    public static void makeZeros(double[][] m, int row, int index){
        //this method makes zeros under row
        int column = m[0].length - 2 - row;
        double a = m[row+index][column];
        for(int i = 0; i < m[0].length; i++){
            m[row+index][i] -= m[row][i] * a;
        }
    }

    public static void controller(double[][] m){
        //controls methods replaceMax(), makeElementEqualOne() and makeZeros() to make triangle matrix
        int row = m.length - 1;

        for(int i = 0; i <= row; i++){
            replaceMax(m, i);
            //each row need to have at lest one element which equals 1
            makeElementEqualOne(m, i);
            if(i<row){
                System.out.println("Проміжний результат:");
                printMatrix(m);
                System.out.println();
            }
            //after that other rows must make zeroes
            for(int k = 1; k <= row-i; k++){
                makeZeros(m, i, k);
            }
            if(i<row){
                System.out.println("Проміжний результат:");
                printMatrix(m);
                System.out.println();
            }
        }
    }

    public static void getResults(double[][] m, double[] x){
        int n = m.length;
        for(int i = n-1; i >= 0 ; i--){
            int index = n - 1 - i;
            double last = m[i][m[0].length-1];     // = last element in row
            for(int j = 0; j < n; j++){
                last -= m[i][j] * x[j];
            }
            x[index] = last;
        }
    }

    public static String val(double n){
        String answer;
        n *= Math.pow(10, 6);
        n = Math.round(n);
        n /= Math.pow(10, 6);       //відкидання зайвих чисел
        if (n == 0){
            return "     0";
        }
        else if ((int) n == n){
            answer = Integer.toString((int)n);
        }
        else{
            answer = Double.toString(n);
        }
        if (answer.length() < 6){
            int x = 6 - answer.length();
            for (int i = 0; i < x; i++) {
                answer = "0" + answer;
            }
        }
        else if (answer.length() > 6){
            answer = answer.substring(0, 6);
        }
        return answer;
    }

    public static void printAnswers(int[] x, double[] answers){
        for (int i = 0; i < x.length; i++) {
            System.out.println("X" + x[i] + " = " + val(answers[i]));
        }
    }
}
