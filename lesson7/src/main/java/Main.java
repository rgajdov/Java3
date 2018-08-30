import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
	// write your code here
        Cat cat = new Cat("Barsik", "Black", 5);
        new MyCoolTest();
        MyCoolTest.start(cat.getClass(), cat);
    }
}
