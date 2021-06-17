package Lessons;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 4;
    public static final int HALF_CARS_COUNT = CARS_COUNT/2;
    //final int THREADS_COUNT = CARS_COUNT;
    //final CountDownLatch cdCars = new CountDownLatch(THREADS_COUNT);

    public static void main(String[] args) throws InterruptedException {


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        CyclicBarrier load = new CyclicBarrier(5);
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);


                for (int i = 0; i < cars.length; i++)

                    cars[i] = new Car(race, 20 + (int) (Math.random() * 10));


        for (Car car : cars) {
            try {
                new Thread(car).start();
                load.await();
            }catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }




            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");


//        ready.start();
//        startRace.start();
//        startRace.join();
//        thread2.start();
//        thread3.start();
//        thread2.join();
//        thread3.join();

    }
}
