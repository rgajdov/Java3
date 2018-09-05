import org.junit.Assert;
import org.junit.Test;

public class ArrayTest2 {

    private boolean checkArray(int[] arr) {
        int counter1 = 0;
        int counter2 = 0;
        for (int anArr : arr) {
            if (anArr == 1) {
                counter1++;
            }
            if (anArr == 4) {
                counter2++;
            }
        }
        return counter1 > 0 && counter2 > 0;
    }

    @Test
    public void test1() {
        int[] arrayGiven = {1, 1, 4, 1, 4, 1, 1, 1, 1, 4};
        Assert.assertTrue(checkArray(arrayGiven));
    }

    @Test
    public void test2() {
        int[] arrayGiven = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        Assert.assertFalse(checkArray(arrayGiven));
    }

    @Test
    public void test3() {
        int[] arrayGiven = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        Assert.assertFalse(checkArray(arrayGiven));
    }

    @Test
    public void test4() {
        int[] arrayGiven = {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        Assert.assertFalse(checkArray(arrayGiven));
    }
}
