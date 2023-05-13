import java.util.*;

public class SystemOfElevators implements ElevatorSystem {

    private int noOfFloor;
    private int noOfElevators;
    private ArrayList<Elevator> elevatorList;
    private ArrayList<ArrayList<Integer>> temp;

    public SystemOfElevators(int noOfFloors, int noOfElevators) {
        this.noOfFloor = noOfFloors;
        this.noOfElevators = noOfElevators;
        elevatorList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i <= noOfElevators; i++) {
            elevatorList.add(new Elevator(i, rand.nextInt(this.noOfFloor) + 1));
        }
        temp = new ArrayList<>();
    }

    @Override
    public void pickup(int currentFloor, int destFloor) {
        boolean isUp = currentFloor < destFloor;
        if (validateFloors(currentFloor, destFloor)) {
            if (currentFloor != destFloor) {
                Elevator elevator = findClosestElevator(currentFloor, isUp);
                if (elevator.getFlorPairList().isEmpty()) {
                    elevator.setUp(isUp);
                }
                ArrayList<Integer> list = new ArrayList<>();
                list.add(destFloor);

                if (currentFloor != elevator.getCurrentFloor()) {
                    list.add(currentFloor);
                }
                elevator.getFlorPairList().add(list);
                System.out.printf("Pick up -- confirmed Elevator: %03d \n", elevator.getId());
            } else {
                System.out.println("Already on the same floor");
            }
        } else {
            System.out.println("One of the floor No. is not valid");
        }
    }

    @Override
    public void update() {

        for (Elevator elevator : elevatorList) {
            if (!elevator.getFlorPairList().isEmpty()) {
                moveElevator(elevator);
                stopElevator(elevator);
                changeElevatorDirection(elevator);
            }
        }
    }

    @Override
    public void step() {
        update();
        status();
    }

    @Override
    public void status() {
        ArrayList<ArrayList<ArrayList<Integer>>> statusList = new ArrayList<>();
        for (Elevator elevator : elevatorList) {
            ArrayList<ArrayList<Integer>> elevatorList = new ArrayList<>();
            elevatorList.add(new ArrayList<>(List.of(elevator.getId())));
            elevatorList.add(new ArrayList<>(List.of(elevator.getCurrentFloor())));
            elevatorList.addAll(elevator.getFlorPairList());
            statusList.add(elevatorList);
        }
        System.out.println(Arrays.toString(statusList.toArray()));
    }

    @Override
    public void start() {
        System.out.println("pickup format --> 3,7 --> it means 3 is you current floor and 7 is the destination");
        System.out.println("To stop the simulation input --> stop");
        while (!userInput()) {
            step();
        }
    }

    public Elevator findClosestElevator(int currentFloor, boolean isUp) {
        int min = 2 * this.noOfFloor + 1;
        int elevatorNo = 0;
        int distance;
        for (int i = 0; i < noOfElevators; i++) {
            distance = currentFloor - elevatorList.get(i).getCurrentFloor();
            if (!elevatorList.get(i).getFlorPairList().isEmpty()) {
                if (distance < 0) {
                    if (elevatorList.get(i).isUp()) {
                        distance = 2 * noOfFloor - currentFloor - elevatorList.get(i).getCurrentFloor();
                    }
                } else if (distance > 0) {
                    if (!elevatorList.get(i).isUp()) {
                        distance = elevatorList.get(i).getCurrentFloor() - 1 + currentFloor;
                    }
                }
            }
            distance = Math.abs(distance);
            if (distance < min) {
                min = distance;
                elevatorNo = i;
            } else if (distance == min && elevatorList.get(i).isUp() == isUp) {
                elevatorNo = i;
            }
        }
        return elevatorList.get(elevatorNo);

    }

    public boolean userInput() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if (!s.isEmpty()) {
            if (Objects.equals(s, "stop")) {
                return true;
            } else {
                String[] parts = s.split(",");
                if (parts.length == 2) {
                    pickup(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                }
            }
        }
        return false;
    }

    public boolean validateFloors(int currentFloor, int destFloor) {
        return ((currentFloor >= 1 && currentFloor <= this.noOfFloor) && (destFloor >= 1 && destFloor <= this.noOfFloor));
    }

    public void moveElevator(Elevator elevator) {
        if (elevator.isUp()) {
            if (elevator.getCurrentFloor() < noOfFloor) {
                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
            }
        } else {
            if (elevator.getCurrentFloor() > 1) {
                elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
            }
        }
    }

    public void stopElevator(Elevator elevator) {
        temp.clear();
        boolean open = false;
        for (int i = elevator.getFlorPairList().size() - 1; i >= 0; i--) {
            if (elevator.getFlorPairList().get(i).size() == 2) {
                if (elevator.getFlorPairList().get(i).get(1) == elevator.getCurrentFloor()) {
                    open = true;
                    elevator.getFlorPairList().get(i).remove(1);
                }
            } else {
                if (elevator.getFlorPairList().get(i).get(0) == elevator.getCurrentFloor()) {
                    open = true;
                    temp.add(elevator.getFlorPairList().get(i));
                }
            }
        }
        if (open) {
            System.out.printf("Elevator: %03d stopped at floor: %03d%n \n", elevator.getId(), elevator.getCurrentFloor());
        }
        elevator.getFlorPairList().removeAll(temp);
    }

    public void changeElevatorDirection(Elevator elevator) {
        if (elevator.getCurrentFloor() != noOfFloor || elevator.getCurrentFloor() != 1) {
            boolean changeDirection = true;
            for (ArrayList<Integer> pair : elevator.getFlorPairList()) {
                int counter;
                if (pair.size() == 1) {
                    counter = 1;
                } else {
                    counter = 0;
                }
                for (Integer floor : pair) {
                    if (elevator.isUp()) {
                        if (floor > elevator.getCurrentFloor() && counter == 1) {
                            changeDirection = false;
                        }
                    } else {
                        if (floor < elevator.getCurrentFloor() && counter == 1) {
                            changeDirection = false;
                        }
                    }
                    counter++;
                }
                if (!changeDirection) {
                    break;
                }
            }
            if (changeDirection) {
                elevator.setUp(!elevator.isUp());
            }
        } else {
            elevator.setUp(!elevator.isUp());
        }
    }

    public int getNoOfFloor() {
        return noOfFloor;
    }

    public void setNoOfFloor(int noOfFloor) {
        this.noOfFloor = noOfFloor;
    }

    public int getNoOfElevators() {
        return noOfElevators;
    }

    public void setNoOfElevators(int noOfElevators) {
        this.noOfElevators = noOfElevators;
    }

    public ArrayList<Elevator> getElevatorList() {
        return elevatorList;
    }

    public void setElevatorList(ArrayList<Elevator> elevatorList) {
        this.elevatorList = elevatorList;
    }

    public ArrayList<ArrayList<Integer>> getTemp() {
        return temp;
    }

    public void setTemp(ArrayList<ArrayList<Integer>> temp) {
        this.temp = temp;
    }
}
