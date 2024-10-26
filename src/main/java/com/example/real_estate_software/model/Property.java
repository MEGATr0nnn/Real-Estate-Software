package com.example.real_estate_software.model;

/**
 * The Property class is used to create and manage objects representing a property.
 * It includes attributes such as address, number of tenants, number of rooms,
 * rent, and utility costs. It also tracks whether the property has tenants
 * and if it is currently selected for some operation.
 *
 * @author Steven Hujbert, Harrison Mega
 * @version 1.3
 *
 */
public class Property {
    private int id;
    private String address;
    private int num_Tenants;
    private int num_Beds;
    private int num_Baths;
    private int num_Cars;
    private int rent;
    private int utilities;
    private boolean has_Tenants;
    private boolean is_Selected;

    /**
     * Constructor to create a new Property object.
     *
     * @param address Address of the property
     * @param num_Beds Number of bedrooms in the property
     * @param num_Baths Number of bathrooms in the property
     * @param num_Cars Number of car spaces available
     * @param rent Monthly rent amount
     * @param utilities Utility costs
     */
    public Property(String address, int num_Beds, int num_Baths, int num_Cars, int rent, int utilities){
        this.address = address;
        num_Tenants = 0;
        this.num_Beds = num_Beds;
        this.num_Baths = num_Baths;
        this.num_Cars = num_Cars;
        this.rent = rent;
        this.utilities = utilities;
        has_Tenants = checkHas_Tenants();
        is_Selected = false;
    }

    /**
     * Overloaded constructor to create a new Property object with specified tenant and selection status.
     *
     * @param address Address of the property
     * @param num_Tenants Number of tenants in the property
     * @param num_Beds Number of bedrooms in the property
     * @param num_Baths Number of bathrooms in the property
     * @param num_Cars Number of car spaces available
     * @param rent Monthly rent amount
     * @param utilities Utility costs
     * @param is_Selected Indicates if the property is selected
     */
    public Property(String address, int num_Tenants, int num_Beds, int num_Baths, int num_Cars, int rent, int utilities, boolean is_Selected){
        this.address = address;
        this.num_Tenants = num_Tenants;
        this.num_Beds = num_Beds;
        this.num_Baths = num_Baths;
        this.num_Cars = num_Cars;
        this.rent = rent;
        this.utilities = utilities;
        has_Tenants = checkHas_Tenants();
        this.is_Selected = is_Selected;
    }

    //Getters and Setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public int getNum_Tenants() {return num_Tenants;}
    public void setNum_Tenants(int num_Tenants) {this.num_Tenants = num_Tenants;}

    public int getNum_Beds() {return num_Beds;}
    public void setNum_Beds(int num_Beds) {this.num_Beds = num_Beds;}

    public int getNum_Baths() {return num_Baths;}
    public void setNum_Baths(int num_Baths) {this.num_Baths = num_Baths;}

    public int getNum_Cars() {return num_Cars;}
    public void setNum_Cars(int num_Cars) {this.num_Cars = num_Cars;}

    public int getRent() {return rent;}
    public void setRent(int rent) {this.rent = rent;}

    public int getUtilities() {return utilities;}
    public void setUtilities(int utilities) {this.utilities = utilities;}

    public boolean getHas_Tenants() {return has_Tenants;}
    public void setHas_Tenants(boolean has_Tenants) {this.has_Tenants = has_Tenants;}

    public boolean getIs_Selected() {return is_Selected;}
    public void setIs_Selected(boolean is_Selected) {this.is_Selected = is_Selected;}

    public boolean checkHas_Tenants() {
        return num_Tenants > 0;
    }
}
