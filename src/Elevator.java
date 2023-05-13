import java.util.ArrayList;

public class Elevator {
    private int id;
    private int currentFloor;
    private boolean up;
    private ArrayList<ArrayList<Integer>> florPairList;

    public Elevator(int id, int currentFloor) {
        setFlorPairList(new ArrayList<>());
        setId(id);
        setCurrentFloor(currentFloor);
    }

    public ArrayList<ArrayList<Integer>> getFlorPairList() {
        return florPairList;
    }

    public void setFlorPairList(ArrayList<ArrayList<Integer>> florPairList) {
        this.florPairList = florPairList;
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
