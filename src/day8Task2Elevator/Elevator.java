package day8Task2Elevator;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Elevator implements Runnable {
    public static final int floorsCount = 3;
    public static final int ELEVATOR_CAPACITY = 5;
    private BlockingQueue<User> usersOutElevator;
    private Queue<User> usersInElevator;
    private int currentFloor;

    public Elevator() {
        currentFloor = 1;
        usersOutElevator = new ArrayBlockingQueue<>(5);
        usersInElevator = new ArrayDeque<>();
    }

    public BlockingQueue<User> getUsersOutElevator() {
        return usersOutElevator;
    }

    @Override
    public void run() {
        System.out.println("Elevator starts at " + currentFloor);
        while (true) {
            User currentUser = getCurrentUser();
            // TODO is valid?
            assert currentUser != null;

            goToTargetFloorTakingByWayOtherUsers(currentUser.getFromFloor());
            goToTargetFloorTakingByWayOtherUsers(currentUser.getToFloor());
        }
    }

    private User getCurrentUser() {
        if (!usersInElevator.isEmpty()) {
            return usersInElevator.poll();
        }
        try {
            return usersOutElevator.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void goToTargetFloorTakingByWayOtherUsers(int targetFloor) {
        int direction = getDirection(targetFloor);
        assert Math.abs(direction) == 1;
        getOutAllTargetUsersInCurrentFloor();
        getInAllPossibleUsersInCurrentFloor();
        while (currentFloor != targetFloor) {
            currentFloor = currentFloor + direction;
            System.out.println("Elevator at " + currentFloor);
            getOutAllTargetUsersInCurrentFloor();
            getInAllPossibleUsersInCurrentFloor();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getDirection(int targetFloor) {
        return targetFloor - currentFloor > 0 ? +1 : -1;
    }

    private void getOutAllTargetUsersInCurrentFloor() {
        for (User user : usersInElevator) {
            if (user.getToFloor() == currentFloor) {
                getOutUser(user);
            }
        }
    }

    private void getOutUser(User user) {
        usersInElevator.remove(user);
        System.out.println("User " + user.getId() + " exits at " + currentFloor);
    }

    private void getInAllPossibleUsersInCurrentFloor() {
        for (User user : usersOutElevator) {
            if (usersInElevator.size() >= ELEVATOR_CAPACITY) return;
            if (user.getFromFloor() == currentFloor) {
                getInUser(user);
            }
        }
    }

    private void getInUser(User user) {
        usersInElevator.add(user);
        usersOutElevator.remove(user);
        System.out.println("User " + user.getId() + " enters at " + currentFloor);
    }
}
