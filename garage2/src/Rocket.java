public class Rocket extends Vehicle {

    private int totalLanding;
    private int totalLaunches;


    // default constructor
    public Rocket() {
    }

    // call only this classes variables
    public Rocket(int totalLanding, int totalLaunches) {
        this.totalLanding = totalLanding;
        this.totalLaunches = totalLaunches;
    }

    // call parent and child class
    public Rocket(String vehicleType, String vehicleName, String regNumber,
                  String petrolType, int numberOfWheels, double vehiclePrice, int totalLaunches, int totalLanding) {
        super(vehicleType, vehicleName, regNumber, petrolType, numberOfWheels, vehiclePrice);
        this.totalLanding = totalLanding;
        this.totalLaunches = totalLaunches;
    }

    // get and set
    public int getTotalLanding() {
        return totalLanding;
    }

    public int getTotalLaunches() {
        return totalLaunches;
    }

    public void setTotalLanding (int totalLanding) {
        this.totalLanding = totalLanding;
    }

    public void setTotalLaunches (int totalLaunches) {
        this.totalLaunches = totalLaunches;
    }

    public void printMethods() {
        System.out.println(
                "\nVehicle Type: " + getVehicleType() +
                        "\nVehicle Name: " + getVehicleName() +
                        "\nRegistration Number: " + getRegistrationNumber() +
                        "\nPetrol Type: " + getPetrolType() +
                        "\nNumber of Wheels: " + getNumberOfWheels() +
                        "\nVehicle Price: " + getVehiclePrice() +
                        "\nTotal Launches: " + totalLaunches +
                        "\nTotal Landing: " + totalLanding);
    }
}