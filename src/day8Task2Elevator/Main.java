package day8Task2Elevator;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        // TODO How to restrict multiple thread for elevator
        Thread elevatorTread = new Thread(elevator);
        elevatorTread.start();
        while (true) {
            new Thread(new User(elevator)).start();
            try {
                Thread.sleep(new Random().nextInt(5000) + 5000);
            } catch (InterruptedException ignore) {
                // NOP
            }
        }
    }
}
