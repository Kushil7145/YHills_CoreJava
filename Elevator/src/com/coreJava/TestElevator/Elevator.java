package com.coreJava.TestElevator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.Queue;
import java.util.*;

public class Elevator {

    private int currentFloor;
    private Direction direction;
    private Queue<Request> requests;

    public Elevator() {
        this.currentFloor = 1;
        this.direction = Direction.UP;
        this.requests = new LinkedList<>();
    }

    public void moveTo(int floor) {
        if (this.direction == Direction.UP && floor > this.currentFloor) {
            this.currentFloor++;
        } else if (this.direction == Direction.DOWN && floor < this.currentFloor) {
            this.currentFloor--;
        }
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void processRequests() {
        while (!this.requests.isEmpty()) {
            Request request = this.requests.poll();
            if (request.getDirection() == this.direction) {
                moveTo(request.getFloor());
            } else {
                this.direction = request.getDirection();
                moveTo(request.getFloor());
            }
        }
    }
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Queue<Request> getRequests() {
        return this.requests;
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Elevator elevator = new Elevator();

        System.out.println("Elevator Simulation");
        System.out.println("===================");

        while (true) {
            System.out.print("Enter floor (1-10) or -1 to exit: ");
            int floor = scanner.nextInt();
            if (floor == -1) {
                break;
            }

            if (floor < 1 || floor > 10) {
                System.out.println("Invalid floor number. Please enter a floor between 1 and 10.");
                continue;
            }

            System.out.print("Enter direction (UP or DOWN): ");
            String directionStr = scanner.next();
            Direction direction = Direction.valueOf(directionStr);

            elevator.addRequest(new Request(floor, direction));
        }

        elevator.processRequests();
        int n=20;
        System.out.print("Processing requests...");
        TimeUnit.SECONDS.sleep(1);
        
        System.out.println();
        System.out.println("\nElevator has finished processing requests.");
        System.out.println("Current floor: " + elevator.getCurrentFloor());
        System.out.println("Direction: " + elevator.getDirection());

        scanner.close();
    }
}

enum Direction {
    UP,
    DOWN,
    IDLE
}

class Request {

    private int floor;
    private Direction direction;

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return this.floor;
    }

    public Direction getDirection() {
        return this.direction;
    }
}