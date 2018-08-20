public class MFP {
    private final static Object monitor1 = new Object();
    private final static Object monitor2 = new Object();

    public void doPrint(String docName, int pageNum) {
        synchronized (monitor1) {
            try {
                for (int i = 1; i <= pageNum; i++) {
                    System.out.println("Документ " + docName + ". Отпечатана страница " + i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void doScan(String docName, int pageNum) {
        synchronized (monitor2) {
            try {
                for (int i = 1; i <= pageNum; i++) {
                    System.out.println("Документ " + docName + ". Отсканирована страница " + i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}