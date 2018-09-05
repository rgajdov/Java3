public class PrintLetters {
    private final Object monitor = new Object();
    private char currentLetter = 'A';

    void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A')
                        monitor.wait();
                    System.out.print("A");
                    //Thread.sleep(300);
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B')
                        monitor.wait();
                    System.out.print("B");
                    //Thread.sleep(300);
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C')
                        monitor.wait();
                    System.out.print("C");
                    //Thread.sleep(300);
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}