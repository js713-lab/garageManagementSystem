//abstract vehicle class
public abstract class Vehicle {

    // Private Attributes
    private String vehicleType;
    private String vehicleName;
    private String regNumber;
    private String petrolType;
    private int numberOfWheels;
    private double vehiclePrice;

    //default constructors
    public Vehicle() {

    }

    // Constructors
    public Vehicle(String vehicleType, String vehicleName, String regNumber,
                   String petrolType, int numberOfWheels, double vehiclePrice) {
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.regNumber = regNumber;
        this.petrolType = petrolType;
        this.numberOfWheels = numberOfWheels;
        this.vehiclePrice = vehiclePrice;
    }

    // GETTER


    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getRegistrationNumber() {
        return regNumber;
    }

    public String getPetrolType() {
        return petrolType;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }


    //SETTER


    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setRegistrationNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setPetrolType(String petrolType) {
        this.petrolType = petrolType;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    // PRINT METHODS / LIST VEHICLE METHODS
    public abstract void printMethods();
}