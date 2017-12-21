package day8Task2Elevator;

import java.util.Random;

public class User implements Runnable {
    private final Elevator elevator;
    private final int toFloor;
    private final int fromFloor;
    private long id;

    public long getId() {
        return id;
    }

    public int getToFloor() {
        return toFloor;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    public User(Elevator elevator) {
        this.elevator = elevator;
        Random random = new Random();
        this.fromFloor = random.nextInt(Elevator.floorsCount) + 1;
        if (random.nextBoolean())
            this.toFloor = random.nextInt(Elevator.floorsCount - 1) + 2;
        else
            this.toFloor = 1; // 50% chance that user starts at 1 floor
    }

    @Override
    public void run() {
        if (fromFloor == toFloor) return;
        try {
            elevator.getUsersOutElevator().put(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        id = Thread.currentThread().getId();
        System.out.println("User " + id
                + " at " + fromFloor + " floor wants get to "
                + toFloor + " floor");
    }
}
