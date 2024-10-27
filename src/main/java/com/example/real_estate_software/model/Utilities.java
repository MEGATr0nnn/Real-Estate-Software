package com.example.real_estate_software.model;

/**
 * The Utilities class is used to create and manage objects representing utilities
 */
public class Utilities {
    private int id;
    private int waterUtilities;
    private int electricityUtilities;
    private int gasUtilities;
    private int totalUtilites;

    /**
     * Constructor to create a new Utilities object
     * @param waterUtilities Water
     * @param electricityUtilities Electricity
     * @param gasUtilities Gas
     */
    public Utilities(int waterUtilities, int electricityUtilities, int gasUtilities) {
        this.waterUtilities = waterUtilities;
        this.electricityUtilities = electricityUtilities;
        this.gasUtilities = gasUtilities;
        totalUtilites = waterUtilities + electricityUtilities + gasUtilities;
    }

    //Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaterUtilities() {
        return waterUtilities;
    }

    public void setWaterUtilities(int waterUtilities) {
        this.waterUtilities = waterUtilities;
    }

    public int getElectricityUtilities() {
        return electricityUtilities;
    }

    public void setElectricityUtilities(int electricityUtilities) {
        this.electricityUtilities = electricityUtilities;
    }

    public int getGasUtilities() {
        return gasUtilities;
    }

    public void setGasUtilities(int gasUtilities) {
        this.gasUtilities = gasUtilities;
    }

    public int getTotalUtilites() {
        return totalUtilites;
    }
}
