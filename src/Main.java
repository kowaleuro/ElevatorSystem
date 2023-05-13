import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please input number of floors in simulation:");
        Scanner in = new Scanner(System.in);
        int noOfFloors = in.nextInt();
        System.out.println("Please input number of elevators in simulation:");
        int noOfElevators = in.nextInt();
        SystemOfElevators system = new SystemOfElevators(noOfFloors, noOfElevators);
        system.start();
    }
}