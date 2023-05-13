public interface ElevatorSystem {

    public void pickup(int currentFloor, int destFloor);
    public void update();
    public void step();
    public void status();
}
