public class Main {

    public static void main(String[] args) {
	// write your code here

        int rows = 5;
        int columns = 6;
        fillSpiralArray(rows, columns);
    }

    private static void fillSpiralArray(int rows, int columns) {

        int[][] array = new int[rows][columns];
        int totalCycles = (int) Math.ceil((double)columns / 2);
        int currentCycle = 0;
        int currentNumber = 0;

        int totalNumbers = rows * columns;

        if (rows == 1) {
            for (int i = 0; i < columns; i++) {
                array[0][i] = i + 1;
            }
        } else if (columns == 1) {
            for (int i = 0; i < rows; i++) {
                array[i][0] = i + 1;
            }
        } else {

            while (currentCycle < totalCycles) {

                // слева направо
                for (int i = currentCycle; i <= currentCycle; i++) {
                    for (int j = currentCycle; j < columns - currentCycle; j++) {
                        array[i][j] = ++currentNumber;
                    }
                }

                // сверху вниз
                for (int i = currentCycle + 1; i < rows - currentCycle; i++) {
                    for (int j = columns - currentCycle - 1; j < columns - currentCycle; j++) {
                        array[i][j] = ++currentNumber;
                    }
                }

                if (currentNumber == totalNumbers)
                    break;

                // справа налево
                for (int i = rows - currentCycle - 1; i < rows - currentCycle; i++) {
                    for (int j = columns - currentCycle - 2; j >= currentCycle; j--) {
                        array[i][j] = ++currentNumber;
                    }
                }

                if (currentNumber == totalNumbers)
                    break;

                // снизу вверх
                for (int i = rows - currentCycle - 2; i > currentCycle; i--) {
                    for (int j = currentCycle; j < currentCycle + 1; j++) {
                        array[i][j] = ++currentNumber;
                    }
                }

                currentCycle++;
            }
        }

        // Вывод массива
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //System.out.print(array[i][j] + "\t");
                System.out.printf("%3d", array[i][j]);
            }
            System.out.println();
        }
    }
}
