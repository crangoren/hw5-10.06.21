package Lessons;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable{
//    final int THREADS_COUNT = Main.CARS_COUNT;
//    final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);

    private static int CARS_COUNT;
    private static final int COUNTER = Main.CARS_COUNT;
    private static boolean winner;
    private static Lock win = new ReentrantLock();
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private int count;
    private CyclicBarrier cb;
    private CountDownLatch cdl;
    public String getName() {

        return name;
    }
    public int getSpeed() {

        return speed;
    }




    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.cdl = cdl;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
                cdl.countDown();

            }
            cdl.await();
            checkWinner(this);
            cb.await();


        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    private static void checkWinner(Car c) {
        if (!winner) {
            System.out.println(c.name + " - победитель!");
            winner = true;
        }
    }
}
