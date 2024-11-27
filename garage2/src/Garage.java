import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Garage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LocalDate date = LocalDate.now();  // declare date
        LocalTime time = LocalTime.now();  // declare time
        System.out.println("Date: " + date); //display date
        System.out.println("Time: " + time); //display time

        List<Vehicle> garage = new ArrayList<>(); // ArrayList
        int option; // declare option
        loadUserGarage(garage); // calling loadUserGarage

        try { // try catch
            do {
                //call display menu function
                displayMenu(); //call display menu function
                option = input.nextInt();

                switch (option) {
                    case 1 -> viewVehicles(garage); //call view vehicles function
                    case 2 -> addVehicle(input, garage); //call add vehicles function
                    case 3 -> modifyVehicle(garage, input); //call modify vehicle function
                    case 4 -> deleteVehicle(garage, input); //call delete vehicle function
                    case 5 -> saveProgress(garage, input); // call saveProgress function

                    default -> System.out.print("Invalid Input");
                }
            } while (option != 5);
        } catch (InputMismatchException e) { // catch invalid input.
            System.out.println("Invalid input." + e);
        } catch (Exception e) { // catch unexpected error.
            System.out.print("An unexpected error occurred: " + e.getMessage()); // print error char
            e.printStackTrace(); // line occured.
        }
    }

    // Load Method
    public static void loadUserGarage(List<Vehicle> garage) {

        File latestFile = getLatestTimestampFile(); // call getLatestTimestampFile();
        if (latestFile != null) { // when latest file is not
            try (Scanner scanner = new Scanner(latestFile)) { // scan the lastest timestamp file
                while (scanner.hasNextLine()) { // while still got line
                    String line = scanner.nextLine();
                    if (!line.trim().isEmpty()) { //  ensuring that the variable line has content after trimming whitespace
                        String[] userData = line.split("\\*\\*\\*\\*\\*"); // split the ***** in the file

                        if (userData.length >= 8) { //if userData has at least 8 elements
                            // read the vehicle type from the file
                            String vehicleType = userData[0];
                            // read vehicleName from the file
                            String vehicleName = userData[1];
                            // read regNumber from the file
                            String regNumber = userData[2];
                            // read petrolType from the file
                            String petrolType = userData[3];
                            // read Number of wheels from the file
                            int numberOfWheels = Integer.parseInt(userData[4]);
                            // read vehiclePrice from the file
                            double vehiclePrice = Double.parseDouble(userData[5]);
                            //convert string representations of numbers into their respective integer and double data types

                            if (vehicleType.equals("Aircraft")) {
                                // read additional specific attributes
                                String landingGear = userData[6];
                                int maximumSpeed = Integer.parseInt(userData[7]);
                                Aircraft aircraft = new Aircraft(vehicleType, vehicleName, regNumber, petrolType, numberOfWheels,
                                        vehiclePrice, landingGear, maximumSpeed);
                                // and create a object aircraft then store into garage
                                garage.add(aircraft);
                            } else if (vehicleType.equals("Rocket")) {
                                // read additional specific attributes
                                int totalLaunches = Integer.parseInt(userData[6]);
                                int totalLanding = Integer.parseInt(userData[7]);
                                Rocket rocket = new Rocket(vehicleType, vehicleName, regNumber,
                                        petrolType, numberOfWheels, vehiclePrice, totalLaunches, totalLanding);
                                // and create a object rocket then store into garage
                                garage.add(rocket);
                            }
                        }
                    }
                }

                System.out.print("Garage loaded successfully.");

            } catch (FileNotFoundException e) { // catch file not found
                e.printStackTrace();
                System.out.println("Error: User's garage file not found. \nStarting with an empty garage.");
            } catch (Exception e) { // catch unexpected error
                System.out.print("An unexpected error occurred while loading the garage.\n" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.print("No saved garage files found ! \nStarting with an empty garage.");
        }
    }

    // DISPLAY MAIN MENU METHODS
    public static void displayMenu() { //  display menu
        System.out.println("\n   --------------------");
        System.out.println("Premium Vehicle Repository ");
        System.out.println("   --------------------");
        System.out.println("1. View Vehicle");
        System.out.println("2. Add Vehicle");
        System.out.println("3. Edit Vehicle");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. Exit");

        System.out.print("Select Option (1-5) >>> ");
    }


    // VIEW VEHICLES METHODS - 1
    public static void viewVehicles(List<Vehicle> garage) {
        try {
            if (garage.isEmpty()) { // if garage no vehicle
                System.out.println("You have no vehicles, man.");
            } else {
                int totalRocket = 0;
                int totalAircraft = 0;
                System.out.println("\nGarage Summary >>>>\nTotal Vehicles: " + garage.size());
                for (Vehicle vehicle : garage) { //for each loop to loop the vehicle in garage.
                    if (vehicle instanceof Rocket) { //if vehicles is type of rocket
                        totalRocket++;
                    }
                }
                System.out.println("Total Rocket: " + totalRocket);
                for (Vehicle vehicle : garage) { //for each loop to loop the vehicle in garage.
                    if (vehicle instanceof Aircraft) { // if vehicles is type of aircraft
                        totalAircraft++;
                    }
                }
                System.out.println("Total Aircraft: " + totalAircraft);
                System.out.println("\nThis is Your Vehicle Details: ");
                for (int i = 0; i < garage.size(); i++) { //forLoop, loop the garage
                    System.out.print("\nVehicle " + (i + 1) + ": ");
                    garage.get(i).printMethods(); // call the printMethod
                }
            }
        } catch (Exception e) { // catch unexpected error
            System.out.print("An unexpected error occurred while viewing the garage." + e.getMessage());
            e.printStackTrace();
        }
    }


    // ADD VEHICLE METHODS - 2
    public static void addVehicle(Scanner input, List<Vehicle> garage) {
        try { // try catch
            System.out.print("Enter vehicle type (1 for Aircraft,2 for Rocket)\n");
            System.out.print("Enter your option (-1 to exit) >>> ");
            int selectVehicleType = input.nextInt();

            if (selectVehicleType == -1) { // if -1 then exit
                System.out.print("Exiting.......");
                return;
            }

            if (selectVehicleType == 1) {
                String vehicleType = "Aircraft";


                System.out.print("Enter Registration Number (-1 to exit) >>> ");
                String regNumber = input.next();
                input.nextLine();

                if (regNumber.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter Petrol Type (Jet A | Jet A-1 | Jet B | Avgas 100LL | JP-8)  (-1 to exit) >>> ");
                String petrolType = input.next();
                input.nextLine();

                if (petrolType.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter Number of Wheels (-1 to exit) >>> ");
                int numberOfWheels = input.nextInt();
                input.nextLine();

                if (numberOfWheels == -1) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter Maximum Speed of your aircraft (-1 to exit) >>> ");
                int maximumSpeed = input.nextInt();

                if (maximumSpeed == -1) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter landingGear of your aircraft (-1 to exit) >>> ");
                String landingGear = input.next();
                input.nextLine();

                if (landingGear.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter Vehicle Price(-1 to exit) >>> ");
                double vehiclePrice = input.nextDouble();
                input.nextLine();

                if (vehiclePrice == -1) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter Name Of Your Vehicle(-1 to exit) >>> ");
                String vehicleName = input.next();
                input.nextLine();

                if (vehicleName.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("--------- CONFIRM YOUR VEHICLE DETAILS ---------\n");
                System.out.println("Vehicle Type: " + vehicleType +
                        "\nVehicle Name: " + vehicleName +
                        "\nRegistration Number: " + regNumber +
                        "\nPetrol Type: " + petrolType +
                        "\nNumber of Wheels: " + numberOfWheels +
                        "\nVehicle Price: " + vehiclePrice +
                        "\nMaximum Speed: " + maximumSpeed +
                        "\nLanding Gear: " + landingGear);
                System.out.print("Please confirm vehicle details (-1 to Cancel, 2 to Confirm) >>> ");
                int confirmOpt = input.nextInt();

                if (confirmOpt == 2) {
                    System.out.println("Added Successfully.");
                    Aircraft newAircraft = new Aircraft(vehicleType, vehicleName, regNumber, petrolType, numberOfWheels,
                            vehiclePrice, landingGear, maximumSpeed);
                    // create object and store into garage.
                    garage.add(newAircraft);
                } else if (confirmOpt == -1) {
                    System.out.println("Canceling......");
                } else {
                    System.out.println("INVALID INPUT");
                }
            } else if (selectVehicleType == 2) {
                String vehicleType = "Rocket";

                System.out.print("Enter Registration Number (-1 to exit) >>> ");
                String regNumber = input.next();
                input.nextLine();

                if (regNumber.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.println("\nNOTE: JUST ASSUME THAT PETROL TYPE IS ROCKET PROPELLANTS.");
                System.out.print("Enter Petrol Type (LIQUID | SOLID | HYBRID) (-1 to exit) >>> ");
                String petrolType = input.nextLine();

                if (petrolType.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                int numberOfWheels = 9;
                boolean b = true;

                while (b) {
                    System.out.print("Enter Number of Wheels (ONLY ENTER 0) (-1 to exit) >>> ");
                    int userInp = input.nextInt();
                    input.nextLine();

                    if (userInp == -1) {
                        System.out.print("Exiting.....");
                        return;
                    } else if (userInp == 0) {
                        numberOfWheels = userInp;
                        b = false;
                    } else {
                        System.out.print("Rocket Should Not Have A Wheels\n");
                    }
                }

                System.out.print("Enter Total Landing of your rocket (-1 to exit) >>> ");
                int totalLanding = input.nextInt();

                if (totalLanding == -1) {
                    System.out.print("Exiting.....");
                    return;
                }

                boolean validInput = false;
                int totalLaunches = 9;
                while (!validInput) { // when totalLaunches < totalLanding it will continue loop
                    System.out.print("Enter Total Launches of your rocket (-1 to exit) >>> ");
                    totalLaunches = input.nextInt();
                    input.nextLine();

                    if (totalLaunches < totalLanding) {
                        System.out.print("Total Launches Cannot Less Than Total Landing\n");
                    } else {
                        validInput = true;
                    }

                    if (totalLaunches == -1) {
                        System.out.print("Exiting.....");
                        return;
                    }
                }

                System.out.print("Enter Rocket Price(-1 to exit) >>> ");
                double vehiclePrice = input.nextDouble();
                input.nextLine();

                if (vehiclePrice == -1) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("Enter Name Of Your Rocket(-1 to exit) >>> ");
                String vehicleName = input.nextLine();

                if (vehicleName.equals("-1")) {
                    System.out.print("Exiting.....");
                    return;
                }

                System.out.print("--------- CONFIRM YOUR VEHICLE DETAILS ---------\n");
                System.out.println(
                        "Vehicle Type: " + vehicleType +
                                "\nVehicle Name: " + vehicleName +
                                "\nRegistration Number: " + regNumber +
                                "\nPetrol Type: " + petrolType +
                                "\nNumber of Wheels: " + numberOfWheels +
                                "\nVehicle Price: " + vehiclePrice +
                                "\nTotal Landing: " + totalLanding +
                                "\nTotal Launches: " + totalLaunches);
                System.out.print("Please confirm vehicles details (-1 to Cancel, 2 to Confirm) >>> ");
                int confirmOpt = input.nextInt();
                if (confirmOpt == 2) {
                    System.out.println("Added Successfully.");
                    Rocket newRocket = new Rocket(vehicleType, vehicleName, regNumber, petrolType, numberOfWheels,
                            vehiclePrice, totalLaunches, totalLanding);
                    garage.add(newRocket);
                } else if (confirmOpt == -1) {
                    System.out.println("Canceling......");
                } else {
                    System.out.println("INVALID INPUT");
                }
            } else {
                System.out.println("Invalid vehicle type.");
            }
        } catch (InputMismatchException e) { // catch invalid input
            System.out.print("Invalid Input.");
            e.printStackTrace();
        } catch (Exception e) { // catch unexpected error
            System.out.print("An unexpected error occurred while adding vehicles." + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void modifyVehicle(List<Vehicle> garage, Scanner input) {
        try {
            if (garage.isEmpty()) { // if garage empty ( no vehicle )
                System.out.print("You Have No Vehicles To Edit.\n");
            } else {
                boolean editing = true;
                do {
                    System.out.println("\nList of Vehicles:");

                    for (int i = 0; i < garage.size(); i++) {
                        System.out.println("Vehicle " + (i + 1) + ": " + garage.get(i).getVehicleName());
                    }

                    System.out.println(" (EXAMPLE: Vehicle 1: CyberTruck8) Enter >>> 1 ");
                    System.out.print("Enter the index of the vehicle to modify (0 to cancel): ");
                    int vehicleIndex = input.nextInt();

                    if (vehicleIndex == 0) {
                        editing = false;
                        System.out.print("Modify Canceling.....");
                        break;
                    }

                    if (vehicleIndex <= 0 || vehicleIndex > garage.size()) { // if vehicle index out of range
                        System.out.println("Vehicle Index Out Of Range");
                    } else {
                        Vehicle selectedVehicle = garage.get(vehicleIndex - 1);
                        if (selectedVehicle instanceof Aircraft aircraft) {
                            System.out.print("\nThis is Your Selected Vehicle Details: ");
                            aircraft.printMethods(); // printDetails
                            System.out.print("Enter 1 to confirm edit, 2 to reselect: ");
                            int confirmOption = input.nextInt();
                            if (confirmOption == 1) {
                                System.out.println("Edit Confirmed.");
                                modifyAircraftAttributes(aircraft, input);
                            } else if (confirmOption == 2) {
                                continue;
                            } else {
                                System.out.print("Invalid Input");
                            }
                        } else if (selectedVehicle instanceof Rocket rocket) {
                            System.out.println("This is Your Selected Vehicle Details: ");
                            rocket.printMethods();
                            System.out.print("Enter 1 to confirm edit, 2 to reselect: ");
                            int confirmOption = input.nextInt();
                            if (confirmOption == 1) {
                                System.out.println("Edit Confirmed.");
                                modifyRocketAttributes(rocket, input);
                            } else if (confirmOption == 2) {
                                continue;
                            } else {
                                System.out.print("Invalid Input");
                            }
                        }
                    }
                } while (editing);
            }
        } catch (InputMismatchException e) {
            System.out.print("Invalid Input." + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.print("An unexpected error occurred while modifying the vehicle." + e.getMessage());
            e.printStackTrace();
        }

    }


    public static void modifyAircraftAttributes(Aircraft aircraft, Scanner input) {

        /**
         * How to get into this section ? only when user enter option 3 in the main menu
         * and the selected vehicle is aircraft then only get into this section,
         * in this section basically covered the modify Aircraft Attributes & Modify menu.
         * the overall logic will be when select 8 or 0 to exit, if select 8 it will prompt summary modifications
         * */


        String modifications = "";
        boolean isEditing = true;

        while (isEditing) {
            System.out.println("\nWelcome To Modify Vehicle Lab.");
            System.out.print("Noted: Vehicle Type is Fixed. If want to edit vehicle type. Just delete it.");

            System.out.print("""
                    \nSelect the option to modify:\s 
                    ---- Modify Menu ----
                    1. Vehicle Name
                    2. Registration Number
                    3. Petrol Type
                    4. Maximum Speed
                    5. Landing Gear
                    6. Number of Wheels
                    7. Vehicle Price
                    8. Exit to Modify Successfully
                    Enter Your Option To Modify ( 1-8 ) (0 to exit) >>>\s""");

            // modifyOpt
            int modifyOpt = input.nextInt();

            //SWITCH CASE FOR MODIFY OPTION
            switch (modifyOpt) {
                case 0 -> {
                    isEditing = false;
                    System.out.print("Thank You For Using Modify Vehicle Lab.");
                }
                case 1 -> {
                    try {
                        System.out.print("Enter the new name for the vehicle: ");
                        String newVehicleName = input.next();
                        input.nextLine();
                        aircraft.setVehicleName(newVehicleName);
                        modifications += "- Vehicle Name modified to: " + newVehicleName + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                }

                case 2 -> {
                    try {
                        System.out.print("Enter the new registration number: ");
                        String newRegistrationNumber = input.next();
                        input.nextLine();
                        aircraft.setRegistrationNumber(newRegistrationNumber);
                        modifications += "- Registration Number modified to: " + newRegistrationNumber + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 3 -> {
                    try {
                        System.out.print("Enter the new petrol type: ");
                        String newPetrolType = input.next();
                        input.nextLine();
                        aircraft.setPetrolType(newPetrolType);
                        modifications += "- Petrol Type modified to: " + newPetrolType + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 4 -> {
                    try {
                        System.out.print("Enter the new maximum speed: ");
                        int newMaximumSpeed = input.nextInt();
                        if (newMaximumSpeed >= 0) {
                            aircraft.setMaximumSpeed(newMaximumSpeed);
                            modifications += "- Maximum Speed modified to: " + newMaximumSpeed + "\n";
                            System.out.print("Modification successful.\n");
                        } else {
                            System.out.println("Invalid input. Maximum speed cannot be negative.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 5 -> {
                    try {
                        System.out.print("Enter the new landing gear: ");
                        String newLandingGear = input.next();
                        input.nextLine();
                        aircraft.setLandingGear(newLandingGear);
                        modifications += "- Landing Gear modified to: " + newLandingGear + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 6 -> {
                    try {
                        System.out.print("Enter the new number of wheels: ");
                        int newNumberOfWheels = input.nextInt();
                        if (newNumberOfWheels >= 0) {
                            aircraft.setNumberOfWheels(newNumberOfWheels);
                            modifications += "- Number of Wheels modified to: " + newNumberOfWheels + "\n";
                            System.out.print("Modification successful.\n");
                        } else {
                            System.out.println("Invalid input. Number of wheels cannot be negative.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 7 -> {
                    try {
                        System.out.print("Enter the new price: ");
                        double newPrice = input.nextDouble();
                        if (newPrice >= 0) {
                            aircraft.setVehiclePrice(newPrice);
                            modifications += "- Vehicle Price modified to: " + newPrice + "\n";
                            System.out.print("Modification successful.\n");
                        } else {
                            System.out.println("Invalid input. Price cannot be negative.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 8 -> {
                    System.out.println("Vehicle " + aircraft.getVehicleName() + " has been modified successfully.");
                    System.out.println("Modify Summary >>> ");
                    System.out.print(modifications);
                    isEditing = false;
                    break;
                }

                default -> System.out.println("Invalid option. No changes made.");
            }
        }
    }

    public static void modifyRocketAttributes(Rocket rocket, Scanner input) {

        String modifications = "";
        boolean isEditing = true;
        while (isEditing) {
            System.out.println("\nWelcome To Modify Vehicle Lab.");
            System.out.print("Noted: Vehicle Type is Fixed. If want to edit vehicle type. Just delete it.");
            System.out.print("""
                    \nSelect the option to modify \n---- Modify Menu ----
                    1. Registration Number
                    2. Petrol Type
                    3. Total Landing
                    4. Total Launches
                    5. Number of Wheels
                    6. Vehicle Price
                    7. Vehicle Name
                    8. Exit to Modify Successfully
                    Enter Your Option To Modify ( 1-8  (0 To Exit) ) >>>\s""");


            int modifyOpt = input.nextInt();

            //SWITCH CASE FOR MODIFY OPTION
            switch (modifyOpt) {
                case 0 -> {
                    isEditing = false;
                    System.out.print("Thank You For Using Modify Vehicles Lab.");
                }
                case 1 -> {
                    try {
                        System.out.print("Enter the new registration number: ");
                        String newRegistrationNumber = input.next();
                        input.nextLine();
                        rocket.setRegistrationNumber(newRegistrationNumber);
                        modifications += "- Registration Number modified to: " + newRegistrationNumber + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 2 -> {
                    try {
                        System.out.print("Enter the new petrol type: ");
                        String newPetrolType = input.next();
                        input.nextLine();
                        rocket.setPetrolType(newPetrolType);
                        modifications += "- Petrol Type modified to: " + newPetrolType + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 3 -> {
                    try {
                        System.out.print("Enter the new total landing: ");
                        int newTotalLanding = input.nextInt();
                        if (newTotalLanding <= rocket.getTotalLaunches()) {
                            rocket.setTotalLanding(newTotalLanding);
                            modifications += "- Total Landings modified to: " + newTotalLanding + "\n";
                            System.out.print("Modification successful.\n");
                        } else if (newTotalLanding <= 0) {
                            System.out.print("Total landing cannot be negative.");
                        } else {
                            System.out.println("Invalid input. Total landings cannot exceed total launches.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 4 -> {
                    try {
                        System.out.print("Enter the new total launches: ");
                        int newTotalLaunches = input.nextInt();
                        if (newTotalLaunches >= rocket.getTotalLanding()) {
                            rocket.setTotalLaunches(newTotalLaunches);
                            modifications += "- Total Launches modified to: " + newTotalLaunches + "\n";
                            System.out.print("Modification successful.\n");
                        } else if (newTotalLaunches <= 0) {
                            System.out.print("Total Launches cannot be negative.");
                        } else {
                            System.out.println("Invalid input. Total launches cannot be less than total landings.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 5 -> {
                    try {
                        System.out.print("Enter the new number of wheels: ");
                        int newNumberOfWheels = input.nextInt();
                        if (newNumberOfWheels == 0) {
                            rocket.setNumberOfWheels(newNumberOfWheels);
                            modifications += "- Number of Wheels modified to: " + newNumberOfWheels + "\n";
                            System.out.print("Modification successful.\n");
                        } else if (newNumberOfWheels < 0) {
                            System.out.println("Invalid input. Number of wheels cannot be negative.");
                        } else {
                            System.out.println("Invalid input. Rocket should not have wheels.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }
                case 6 -> {
                    try {
                        System.out.print("Enter the new price: ");
                        double newPrice = input.nextDouble();
                        if (newPrice >= 0) {
                            rocket.setVehiclePrice(newPrice);
                            modifications += "- Vehicle Price modified to: " + newPrice + "\n";
                            System.out.print("Modification successful.\n");
                        } else {
                            System.out.println("Invalid input. Price cannot be negative.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }
                case 7 -> {
                    try {
                        System.out.print("Enter the new name for your rocket: ");
                        String newVehicleName = input.next();
                        input.nextLine();
                        rocket.setVehicleName(newVehicleName);
                        modifications += "- Vehicle Name modified to: " + newVehicleName + "\n";
                        System.out.print("Modification successful.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }

                case 8 -> {
                    System.out.println("Vehicle " + rocket.getVehicleName() + " has been modified successfully.");
                    System.out.println("Modify Summary >>> ");
                    System.out.print(modifications);
                    isEditing = false;
                }
                default -> System.out.println("Invalid option. No changes made.");
            }
        }
    }
// DELETE VEHICLES METHODS - 4

    public static void deleteVehicle(List<Vehicle> garage, Scanner input) {
        try {
            File latestFile = getLatestTimestampFile();
            if (garage.isEmpty()) {
                System.out.println("You have no vehicle to delete.");
            } else {
                //(Enter 3 to Deleted File)
                System.out.println("You Want To Delete Selected Vehicle or Clear File & Garage.");
                System.out.println("(Enter 1 to Delete Selected Vehicle)(Enter 2 to Clear File or Garage.)");
                System.out.print("Enter your option (1-2) (-1 to exit) >>>> ");
                int delOpt = input.nextInt();

                if (delOpt == -1) {
                    return;
                }

                if (delOpt == 2) {
                    // let user confirm whether it is his/her own file,
                    System.out.println("----------Confirm To Delete File or Garage----------");
                    System.out.println("Warning,once deleted your file. Couldn't recover.");
                    System.out.println("Noted:File Deletion only delete the latest file.");
                    System.out.print("Enter 1 to confirm your deletion (enter 2 to exit) >>> ");

                    int confirmFile = input.nextInt();
                    if (confirmFile == 1) {
                        System.out.println("Garage has been clear successfully......");

                        if (latestFile.delete()) { // delete latest file
                            System.out.println("File deleted successfully.........");
                        } else {
                            System.out.println("File not found or couldn't be deleted.......");
                        }
                        garage.clear();
                    } else if (confirmFile == 2) {
                        System.out.println("Garage & File deletion cancelled.....");
                        return;
                    } else {
                        System.out.println("Invalid Input.");
                    }
                } else if (delOpt == 1) {
                    System.out.println("List of Vehicles:");

                    for (int i = 0; i < garage.size(); i++) {
                        System.out.println("Vehicle " + (i + 1) + ": " + garage.get(i).getVehicleName());
                    }

                    System.out.print("Enter the index of the vehicle to delete: ");
                    int del = input.nextInt();

                    if (del >= 1 && del <= garage.size()) {
                        System.out.println("Vehicle " + garage.get(del - 1).getVehicleName() + " is deleting......");
                        garage.remove(del - 1);
                    } else {
                        System.out.println("Invalid index. Vehicle not found.");
                    }
                } else {
                    System.out.print("Invalid Input. (Please enter only 1-2)");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input. Please enter a valid option.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // SAVE PROGRESS
    public static void saveProgress(List<Vehicle> garage, Scanner input) {
        try {
            if (garage.isEmpty()) {
                System.out.print("Exiting......");
            } else {
                System.out.print("Do you want to save progress? (Enter 1 to save, other to exit) >>> ");
                int saveOption = input.nextInt();

                if (saveOption == 1) { // create a date format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,HH:mm a");
                    String timestamp = sdf.format(new Date());
                    String filename = "vehicle_inventory_" + timestamp + ".txt";
                    int i = 0;
                    int totalAircraft = 0;
                    int totalRocket = 0;

                    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                        for (Vehicle vehicle : garage) {
                            i++;
                            writer.println("Vehicle " + i + " Details:");
                            writer.print(vehicle.getVehicleType() + "*****"
                                    + vehicle.getVehicleName() + "*****" + vehicle.getRegistrationNumber()
                                    + "*****" + vehicle.getPetrolType() + "*****"
                                    + vehicle.getNumberOfWheels() + "*****" + vehicle.getVehiclePrice());

                            // Add specific details for each vehicle type
                            if (vehicle instanceof Aircraft aircraft) {
                                writer.print("*****" + aircraft.getLandingGear()
                                        + "*****" + aircraft.getMaximumSpeed());
                                totalAircraft++;
                            } else if (vehicle instanceof Rocket rocket) {
                                writer.print("*****" + rocket.getTotalLaunches()
                                        + "*****" + rocket.getTotalLanding());
                                totalRocket++;
                            }
                            writer.println(); // Separate each vehicle's details with an empty line
                            writer.println("------------------------------");
                        }
                        int totalVehicles = garage.size();
                        writer.println("\n-------------Garage Summary-------------");
                        writer.println("Total Vehicles Added: " + totalVehicles);
                        writer.println("Total Aircraft Added: " + totalAircraft);
                        writer.println("Total Rocket Added: " + totalRocket);
                        System.out.println("\n------------ Garage Summary -------------");
                        System.out.println("Total Vehicles Added: " + totalVehicles);
                        System.out.println("Total Aircraft Added: " + totalAircraft);
                        System.out.println("Total Rocket Added: " + totalRocket);
                        System.out.println("\nVehicle information saved to " + filename + " Successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving to the text file." + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Exiting.......");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static File getLatestTimestampFile() {
        // get the file path
        File folder = new File("/Users/js./Desktop/Js/Learnt - Courses/TAYLOR - Diploma/SEMESTER 2/Object-Oriented Programming/Assignment/Assignment 2/garage2");
        // Replace with your directory path
        // array of files object ( current directory )
        // to get all the files in this folder and filters them using below start with and end with criteria.
        File[] files = folder.listFiles((dir, name) -> name.startsWith("vehicle_inventory_")
                && name.endsWith(".txt"));

        if (files != null && files.length > 0) {
            // Find the latest timestamped file using lastModified
            try {
                File latestFile = files[0];
                for (int i = 1; i < files.length; i++) {
                    if (files[i].lastModified() > latestFile.lastModified()) {
                        latestFile = files[i];
                    }
                }
                return latestFile; // Return the latest timestamped file
            } catch (Exception e) {
                System.out.println("An error occurred while fetching the latest file: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}


