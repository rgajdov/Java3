import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class ArrayTest1 {

    private int[] getElementsAfterLast4(int[] arr) {
        int pos = 0;
        try {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 4) {
                    pos = i + 1;
                }
            }
            if (pos == 0) throw new RuntimeException();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return Arrays.copyOfRange(arr, pos, arr.length);
    }

    private void testArray(int[] arrayGiven, int[] arrayExpected) {
        System.out.println("\nИсходный массив: " + Arrays.toString(arrayGiven));
        Assert.assertArrayEquals(getElementsAfterLast4(arrayGiven), arrayExpected);
        System.out.println("Полученный массив: " + Arrays.toString(arrayExpected));
    }

    @Test
    public void test1() {
        int[] arrayGiven = {1, 3, 5, 2, 4, 0, 3, 6, 2, 2};
        int[] arrayExpected = {0, 3, 6, 2, 2};
        testArray(arrayGiven, arrayExpected);
    }

    @Test
    public void test2() {
        int[] arrayGiven = {1, 4, 7, 1, 4, 0, 4, 3, 1, 5};
        int[] arrayExpected = {3, 1, 5};
        testArray(arrayGiven, arrayExpected);
    }

    @Test
    public void test3() {
        int[] arrayGiven = {1, 1, 5, 3, 8, 0, 3, 3, 9, 4};
        int[] arrayExpected = {};
        testArray(arrayGiven, arrayExpected);
    }

    @Test
    public void test4() throws RuntimeException {
        int[] arrayGiven = {1, 2, 3, 5, 6, 7, 8, 9, 0, 1};
        System.out.println("\nИсходный массив: " + Arrays.toString(arrayGiven));
        try {
            getElementsAfterLast4(arrayGiven);
        } catch (RuntimeException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}
