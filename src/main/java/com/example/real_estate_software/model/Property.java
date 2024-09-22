package com.example.real_estate_software.model;

/*This is the Property class that holds all the information regarding each property,
* it is used to set property info that is then saved into the DB*/
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

    public Property(String address, int num_Tenants, int num_Beds, int num_Baths, int num_Cars, int rent, int utilities){
        this.address = address;
        this.num_Tenants = num_Tenants;
        this.num_Beds = num_Beds;
        this.num_Baths = num_Baths;
        this.num_Cars = num_Cars;
        this.rent = rent;
        this.utilities = utilities;
        has_Tenants = checkHas_Tenants();
    }

    //is this duplicate really needed? can I simplify down to one constructor?
    public Property(String address, int num_Tenants, int num_Beds, int num_Baths, int num_Cars, int rent, int utilities, boolean has_Tenants){
        this.address = address;
        this.num_Tenants = num_Tenants;
        this.num_Beds = num_Beds;
        this.num_Baths = num_Baths;
        this.num_Cars = num_Cars;
        this.rent = rent;
        this.utilities = utilities;
        this.has_Tenants = has_Tenants;
    }

    //need to integrate owner with this in DAO
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

    public boolean checkHas_Tenants() {
        return num_Tenants > 0;
    }
}
