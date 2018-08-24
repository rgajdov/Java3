import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

public class MainClass {

    static final int CARS_COUNT = 4;
    static final int TUNNEL_CARS_COUNT = CARS_COUNT / 2;
    static CountDownLatch cdlStart = null;
    static CountDownLatch cdlFinish = null;

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        cdlStart = new CountDownLatch(MainClass.CARS_COUNT);
        cdlFinish = new CountDownLatch(CARS_COUNT * race.getStageCount());
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        try {
            cdlStart.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            cdlFinish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
