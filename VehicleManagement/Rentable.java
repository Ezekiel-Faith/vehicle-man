package VehicleManagement;

public interface Rentable {
    double calculateRentalCost(int days);
    void displayRentalDetails();
}

abstract class Vehicle implements Rentable {
    private String make, model;
    private int year;
    private double baseRate;

    public Vehicle(String make, String model, int year, double baseRate) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.baseRate = baseRate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getBaseRate() {
        return baseRate;
    }

    abstract void displayVehicleInfo();

    @Override
    public double calculateRentalCost(int days) {
        return baseRate * days;
    }

    @Override
    public void displayRentalDetails() {
        System.out.printf("%s: %s %s (%d)%nBase Rate $%.2f%n", getClass().getSimpleName().toUpperCase()
                , make.toUpperCase(), model.toUpperCase(), year, baseRate);

    }
}

class Car extends Vehicle {
    private boolean isElectric;

    public Car(String make, String model, int year, double baseRate, boolean isElectric) {
        super(make, model, year, baseRate);
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    void displayVehicleInfo() {
        System.out.printf("%s Details: %s %s (%d) Electric: %s%n", getClass().getSimpleName().toUpperCase(),
                getMake().toUpperCase(), getModel().toUpperCase(), getYear(), isElectric ? "Yes" : "No");
    }
}

class Truck extends Vehicle {
    private double payLoadCapacity;

    public Truck(String make, String model, int year, double baseRate, double payLoadCapacity) {
        super(make, model, year, baseRate);
        this.payLoadCapacity = payLoadCapacity;
    }

    @Override
    void displayVehicleInfo() {
        System.out.printf("%s Details: %s %s (%d) Payload Capacity: %.2f tons%n",
                getClass().getSimpleName().toUpperCase(), getMake().toUpperCase(),
                getModel().toUpperCase(), getYear(), payLoadCapacity);
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String make, String model, int year, double baseRate, boolean hasSidecar) {
        super(make, model, year, baseRate);
        this.hasSidecar = hasSidecar;
    }

    @Override
    void displayVehicleInfo() {
        System.out.printf("%s: %s %s (%d) Has Sidecar: %s%n", getClass().getSimpleName().toUpperCase(),
                getMake().toUpperCase(), getModel().toUpperCase(), getYear(), hasSidecar ? "Yes" : "No");
    }
}

