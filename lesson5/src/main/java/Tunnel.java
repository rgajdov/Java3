import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore semaphore;

    Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров.";
        this.semaphore = new Semaphore(MainClass.TUNNEL_CARS_COUNT);
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car.getName() + " готовится к этапу (ждет): " + description);
                semaphore.acquire();
                System.out.println(car.getName() + " начал этап: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);
                car.setWinStageCount(car.getWinStageCount() + 1);
                System.out.println(car.getName() + " закончил этап: " + description);
                semaphore.release();
                car.checkWin(car);
                MainClass.cdlFinish.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
