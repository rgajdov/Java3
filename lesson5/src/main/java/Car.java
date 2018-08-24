import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static int carId;
    private static boolean winner;
    private final static CyclicBarrier cb = new CyclicBarrier(MainClass.CARS_COUNT);

    static {
        carId = 0;
        winner = false;
    }

    private Race race;
    private int speed;
    private String name;
    private int winStageCount;

    String getName() {
        return name;
    }

    int getSpeed() {
        return speed;
    }

    Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        carId++;
        this.name = "Участник #" + carId;
        this.winStageCount = 0;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится.");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов.");
            MainClass.cdlStart.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }

    int getWinStageCount() {
        return winStageCount;
    }

    void setWinStageCount(int winStageCount) {
        this.winStageCount = winStageCount;
    }

    private boolean isWinner() {
        return winner;
    }

    private void setWinner() {
        winner = true;
    }

    void checkWin(Car car) {
        if (car.getWinStageCount() == race.getStageCount() && !car.isWinner()) {
            car.setWinner();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> " + car.getName() + " победил в гонке!");
        }
    }
}
