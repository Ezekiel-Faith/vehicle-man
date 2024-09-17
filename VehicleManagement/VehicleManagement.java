package VehicleManagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VehicleManagement<T extends Vehicle> {
    private List<T> vehicles;
    private LinkedList<T> recentlyRented;

    public VehicleManagement() {
        vehicles = new ArrayList<>();
        recentlyRented = new LinkedList<>();
    }

    public LinkedList<T> getRecentlyRented() {
        return recentlyRented;
    }

    public void addVehicle(T vehicle) {
        vehicles.add(vehicle);
    }

    public List<T> listVehicle() {
        if(vehicles.isEmpty()) {
            System.out.println("No Vehicles in the List");
            return null;
        }
        for (var vehicle : vehicles) {
            vehicle.displayVehicleInfo();
            System.out.println();
        }

        return vehicles;
    }

    public List<T> searchVehicle(String make, String model, int year) {
        List<T> matchSearch = new ArrayList<>();
        for(var vehicle : vehicles) {
            if(vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model) && year == vehicle.getYear()) {
                matchSearch.add(vehicle);
            }
        }

        if(matchSearch.isEmpty()) {
            System.out.printf("Vehicle with make (%s), model (%s) and year (%d) not found%n", make, model, year);
            return null;
        }

        return matchSearch;
    }

    public double calculateTotalRentalCost(T vehicle, int days) {
//        double totalCost = 0.0;
//
//        for(var vehicle : vehicles) {
//            totalCost += vehicle.calculateRentalCost(days);
//        }
//
//        return totalCost;

        return vehicle.calculateRentalCost(days);
    }

    public void rentedVehicle(T vehicle) {
        recentlyRented.addFirst(vehicle);
//        for(var rent : recentlyRented) {
//            rent.displayVehicleInfo();
//        }
        if(recentlyRented.size() > 1) {
            recentlyRented.removeLast();
        }
        //System.out.println(recentlyRented);
    }

    public void displayRecentlyRented() {
        for(var vehicle : recentlyRented) {
            vehicle.displayVehicleInfo();
            System.out.println();
        }
    }
}
