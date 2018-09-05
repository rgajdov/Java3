import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

    private final static Object monitor = new Object();
    private final static File outputFile = new File("output.txt");
    private static FileWriter fw;

    static {
        try {
            fw = new FileWriter(outputFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(String str) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 10; i++) {
                    fw.write(str + "\n");
                    fw.flush();
                    Thread.sleep(20);
                    monitor.notifyAll();
                }
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void writeToFile() throws InterruptedException {
        // небольшой метод :)
        System.out.println("Идет запись в файл...");

        Thread myThread1 = new Thread(() -> write("Thread1"));
        myThread1.start();
        myThread1.join();

        Thread myThread2 = new Thread(() -> write("Thread2"));
        myThread2.start();
        myThread2.join();

        Thread myThread3 = new Thread(() -> write("Thread3"));
        myThread3.start();
        myThread3.join();

        System.out.println("Запись в файл окончена!");
    }

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        // write your code here
        System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));

        System.out.println("Задание 1.");
        PrintLetters w = new PrintLetters();
        Thread t1 = new Thread(() -> w.printA());
        Thread t2 = new Thread(() -> w.printB());
        Thread t3 = new Thread(() -> w.printC());
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);
        System.out.println("\n\nЗадание 2.");
        writeToFile();

        Thread.sleep(1000);
        System.out.println("\nЗадание 3.");

        MFP mfp = new MFP();
        Thread thread1 = new Thread(() -> mfp.doPrint("doc1", 3));
        Thread thread3 = new Thread(() -> mfp.doScan("doc1", 3));
        Thread thread2 = new Thread(() -> mfp.doPrint("doc2", 3));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}