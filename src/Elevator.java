import java.util.ArrayList;

public class Elevator {
    public int id;
    public int currentFloor;
    public boolean up;
    public ArrayList<ArrayList<Integer>> florPairList;

    public ArrayList<ArrayList<Integer>> getFlorPairList() {
        return florPairList;
    }

    public void setFlorPairList(ArrayList<ArrayList<Integer>> florPairList) {
        this.florPairList = florPairList;
    }

    public Elevator(int id, int currentFloor) {
        setFlorPairList(new ArrayList<>());
        setId(id);
        setCurrentFloor(currentFloor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
