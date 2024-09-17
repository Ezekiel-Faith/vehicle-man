package VehicleManagement;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        var management = new VehicleManagement<Vehicle>();
        VehicleManagement<Vehicle> management = new VehicleManagement<Vehicle>();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while(!exit) {
            System.out.printf("""
                    1. Add Vehicle
                    2. List Vehicles
                    3. Search Vehicle
                    4. Calculate Total Rental Cost
                    5. Rent a Vehicle
                    6. Display Recently Rented Vehicle
                    7. Exit
                    Enter Choice:%1s""", "");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1 -> {
                    System.out.println();
                    System.out.printf("""
                            What do you want to add:
                            1. Car
                            2. Truck
                            3. Motorcycle
                            Enter type of Vehicle to add:%1s""", "");
                    int vehicleToAdd = sc.nextInt();
                    sc.nextLine();

                    System.out.println();
                    if(vehicleToAdd < 1 || vehicleToAdd > 3) {
                        System.out.println("Invalid Selection");
                        System.out.println();
                    } else {
                        System.out.print("Vehicle Make: ");
                        String make = sc.nextLine().toUpperCase();

                        System.out.print("Vehicle Model: ");
                        String model = sc.nextLine().toUpperCase();

                        System.out.print("Vehicle Year: ");
                        int year = sc.nextInt();

                        System.out.print("Base Rent: ");
                        double baseRent = sc.nextDouble();

                        switch (vehicleToAdd) {
                            case 1 -> {
                                System.out.printf("Is (%s %s %d) an Electric Car? (true/false): ", make, model, year);
                                boolean isElectric = sc.nextBoolean();

                                var car = new Car(make, model, year, baseRent, isElectric);
                                management.addVehicle(car);
                                System.out.printf("[%s %s %d %.2f] added successfully!!%n", make, model, year, baseRent);

                                car.displayVehicleInfo();
                                System.out.println();
                            }
                            case 2 -> {
                                System.out.printf("Enter (%s %s %d) Payload Capacity (tons): ", make, model, year);
                                double payLoadCapacity = sc.nextDouble();

                                var truck = new Truck(make, model, year, baseRent, payLoadCapacity);
                                management.addVehicle(truck);
                                System.out.printf("[%s %s %d %.2f] added successfully!!%n", make, model, year, baseRent);

                                truck.displayVehicleInfo();
                                System.out.println();
                            }
                            case 3 -> {
                                System.out.printf("(%s %s %d) has a Sidecar? (true/false): ", make, model, year);
                                boolean hasSideCar = sc.nextBoolean();

                                var motor = new Motorcycle(make, model, year, baseRent, hasSideCar);
                                management.addVehicle(motor);
                                System.out.printf("[%s %s %d %.2f] added successfully!!%n", make, model, year, baseRent);

                                motor.displayVehicleInfo();
                                System.out.println();
                            }
                        }
                    }
                }
                case 2 -> {
                    management.listVehicle();
                }
                case 3 -> {
                    System.out.print("Vehicle make to search: ");
                    String make = sc.nextLine();

                    System.out.print("Vehicle model to search: ");
                    String model = sc.nextLine();

                    System.out.print("Vehicle year to search: ");
                    int year = sc.nextInt();

                    var search = management.searchVehicle(make, model, year);
                    if(search != null) {
                        for(var vehicle : search) {
                            vehicle.displayVehicleInfo();
                        }
                    }
                    System.out.println();
                }
                case 4 -> {
                    System.out.print("Vehicle make to Calculate Rental Cost: ");
                    String make = sc.nextLine();

                    System.out.print("Vehicle model to Calculate Rental Cost: ");
                    String model = sc.nextLine();

                    System.out.print("Vehicle year to Calculate Rental Cost: ");
                    int year = sc.nextInt();
                    var search = management.searchVehicle(make, model, year);
                    if(search != null) {
                        System.out.print("Number of days: ");
                        int days = sc.nextInt();

                        if(search.size() > 1) {
                            int index = 1;
                            for(var searchVehicle : search) {
                                System.out.printf("%d. ",index);
                                searchVehicle.displayVehicleInfo();
                                index++;
                            }
                            System.out.printf("Kindly enter choice of %s %s: ", make, model);
                            int chce = sc.nextInt();

                            if(chce > 0 && chce <= search.size()) {
                                var selectedVehicle = search.get(chce - 1);
                                var selectedVehicleBaseRate = selectedVehicle.getBaseRate();
                                var calRent = management.calculateTotalRentalCost(selectedVehicle, days);
//                                calRent *= selectedVehicleBaseRate;
                                System.out.printf("Base Rent for (%s %s) is: %.2f/day%n" +
                                        "Total Rent Cost for %d days = %.2f%n", make, model, selectedVehicleBaseRate, days, calRent);
                            } else {
                                System.out.println("Invalid choice");
                            }
                        } else {
                            var selectedVehicle = search.getFirst();
                            var selectedVehicleBaseRate = selectedVehicle.getBaseRate();
                            var calRent = management.calculateTotalRentalCost(selectedVehicle, days);
                            System.out.printf("Base Rent for (%s %s) is: %.2f/day%n" +
                                    "Total Rent Cost for %d days = %.2f%n", make, model, selectedVehicleBaseRate, days, calRent);
                        }
                    }
                    System.out.println();
                }
                case 5 -> {
                    System.out.print("Vehicle make to rent: ");
                    String make = sc.nextLine().toUpperCase();

                    System.out.print("Vehicle model to rent: ");
                    String model = sc.nextLine().toUpperCase();

                    System.out.print("Vehicle year to rent: ");
                    int year = sc.nextInt();
                    //consume nextLine
                    var searchRent = management.searchVehicle(make, model, year);
                    if(searchRent != null) {
                        if(searchRent.size() > 1) {
                            for(int i = 0; i < searchRent.size(); i++) {
                                System.out.printf("%d ", i + 1);
                                searchRent.get(i).displayVehicleInfo();
                                System.out.println();
                            }
                            System.out.printf("Kindly enter choice of %s %s: ", make, model);
                            int chce = sc.nextInt();
                            //consume nextLine

                            if(chce > 0 && chce <= searchRent.size()) {
                                var selectedVehicle = searchRent.get(chce - 1);
                                management.rentedVehicle(selectedVehicle);
                                System.out.printf("%d. %s %s %d rented successfully!!!", chce, make, model, year);
                            } else {
                                System.out.println("Invalid choice");
                            }
                        } else {
                            var selectedVehicle = searchRent.getFirst();
                            management.rentedVehicle(selectedVehicle);
                            System.out.printf("1. %s %s %d rented successfully!!!%n", make, model, year);
                        }
                    } else {
                        System.out.println("No matching vehicle found");
                    }
                    System.out.println();
                }
                case 6 -> {
                    System.out.print("Vehicle make to Display Recently Rented: ");
                    String make = sc.nextLine();

                    System.out.print("Vehicle model to Display Recently Rented: ");
                    String model = sc.nextLine();

                    System.out.print("Vehicle year to Display Recently Rented: ");
                    int year = sc.nextInt();

                    var searchRent = management.searchVehicle(make, model, year);
                    if(searchRent != null) {
                        boolean foundVehicle = false;

                        for(var search : searchRent) {
                            for(var rentedVehicle : management.getRecentlyRented()) {
                                if(search.equals(rentedVehicle)) {
                                    foundVehicle = true;
                                    rentedVehicle.displayRentalDetails();
                                    System.out.println();
                                }
                            }
                        }

                        if(!foundVehicle) {
                            System.out.printf("Vehicle (%s %s %d) hasn't been rented yet.%n", make, model, year);
                        }
                    } else {
                        System.out.println("No vehicle match");
                    }
                    System.out.println();
                }
                case 7 -> {
                    exit = true;
                    System.out.println("Exiting....");
                }
                default -> {
                    System.out.println("Invalid Selection (1 - 7)");
                    System.out.println();
                }
            }
        }
    }
}
