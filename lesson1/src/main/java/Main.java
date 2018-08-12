import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // write your code here
        System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));

        System.out.println("Задача 1.");
        Integer[] arr1 = { 1, 3, 5, 2, 4 };
        VirtualArrayImpl<Integer> va1 = new VirtualArrayImpl<>();
        System.out.println("Исходный массив: " + va1.display(arr1));
        va1.swapElements(arr1, 2, 3);
        System.out.println("Измененный массив: " + va1.display(arr1));

        System.out.println("\nЗадача 2.");
        System.out.println("Массив Integer, преобразованный в ArrayList: " + va1.arrayToArrayList(arr1));

        System.out.println("\nЗадача 3.");
        Box<Apple> appleBox = new Box<>(new Apple(), 100);
        Box<Orange> orangeBox = new Box<>(new Orange(), 150);
        System.out.println("Апельсинов в коробке: " + orangeBox.getFruitsCount() + ", вес коробки: " + orangeBox.getWeight());
        System.out.println("Яблок в коробке: " + appleBox.getFruitsCount() + ", вес коробки: " + appleBox.getWeight());
        appleBox.addFruits(10);
        System.out.println("Добавлено 10 яблок.");
        appleBox.addFruits(7);
        System.out.println("Добавлено 7 яблок.");
        orangeBox.addFruits(5);
        System.out.println("Добавлено 5 апельсинов.");
        System.out.println("Апельсинов в коробке: " + orangeBox.getFruitsCount() + ", вес коробки: " + orangeBox.getWeight());
        System.out.println("Яблок в коробке: " + appleBox.getFruitsCount() + ", вес коробки: " + appleBox.getWeight());
        System.out.println("Вес коробок одинаковый? " + (appleBox.compare(orangeBox) ? "Да." : "Нет."));
        Box<Apple> appleBox1 = new Box<>(new Apple(), 0);
        appleBox.move(appleBox1);
        System.out.println("Пересыпали яблоки в пустую коробку.");
        System.out.println("Количество яблок в старой коробке: " + appleBox.getFruitsCount());
        System.out.println("Количество яблок в новой коробке: " + appleBox1.getFruitsCount());
        System.out.print("Попытались пересыпать яблоки в коробку с апельсинами... ");
        appleBox1.move(orangeBox);
    }
}
