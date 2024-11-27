public class Aircraft extends Vehicle {

    private int maximumSpeed;
    private String landingGear; //Tricycle, Tail-Wheel (Conventional), Pontoons, and Skis

    // default constructor

    public Aircraft() {

    }
    // call only this classes variables

    public Aircraft(int maximumSpeed, String landingGear) {
        this.landingGear = landingGear;
        this.maximumSpeed = maximumSpeed;
    }
    // call parent and child class

    public Aircraft(String vehicleType, String vehicleName,
                    String regNumber, String petrolType, int numberOfWheels,
                    double vehiclePrice, String landingGear, int maximumSpeed) {
        super(vehicleType, vehicleName, regNumber, petrolType, numberOfWheels, vehiclePrice);
        this.maximumSpeed = maximumSpeed;
        this.landingGear = landingGear;
    }

//    get and set


    public int getMaximumSpeed() {
        return maximumSpeed;
    }


    public String getLandingGear() {
        return landingGear;
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public void setLandingGear(String landingGear) {
        this.landingGear = landingGear;
    }


    @Override
    public void printMethods() {
        System.out.println(
                "\nVehicle Type: " + getVehicleType() +
                        "\nVehicle Name: " + getVehicleName() +
                        "\nRegistration Number: " + getRegistrationNumber() +
                        "\nPetrol Type: " + getPetrolType() +
                        "\nNumber of Wheels: " + getNumberOfWheels() +
                        "\nVehicle Price: " + getVehiclePrice() +
                        "\nLanding Gear: " + landingGear +
                        "\nMaximum Speed: " + maximumSpeed
        );
    }
}
