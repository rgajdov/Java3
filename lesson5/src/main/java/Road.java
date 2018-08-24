public class Road extends Stage {

    Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров.";
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " начал этап: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            car.setWinStageCount(car.getWinStageCount() + 1);
            System.out.println(car.getName() + " закончил этап: " + description);
            car.checkWin(car);
            MainClass.cdlFinish.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}