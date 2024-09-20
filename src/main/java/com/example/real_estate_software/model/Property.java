package com.example.real_estate_software.model;

/*This is the Property class that holds all the information regarding each property,
* it is used to set property info that is then saved into the DB*/
public class Property {
    private int id;
    private String address;
    private boolean tenanted;
    private int num_Tenants;
    private int num_Beds;
    private int num_Bath;
    private int num_Car;
    private int rent;
    private int utilities;

    public Property(String address,int num_Tenants, int num_Beds, int num_Bath, int num_Car, int rent, int utilities){
        this.address = address;
        this.tenanted = false;
        this.num_Tenants = num_Tenants;
        this.num_Beds = num_Beds;
        this.num_Bath = num_Bath;
        this.num_Car = num_Car;
        this.rent = rent;
        this.utilities = utilities;
    }

    //is this duplicate really needed? can i simplify down to one constructior?
    public Property(String address, boolean tenanted, int num_Tenants, int num_Beds, int num_Bath, int num_Car, int rent, int utilities){
        this.address = address;
        this.tenanted = tenanted;
        this.num_Tenants = num_Tenants;
        this.num_Beds = num_Beds;
        this.num_Bath = num_Bath;
        this.num_Car = num_Car;
        this.rent = rent;
        this.utilities = utilities;
    }

    //need to integrate owner with this in DAO

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public boolean getTenanted(){return tenanted;}
    public void setTenanted (boolean tenanted) {this.tenanted = tenanted;}

    public int getNum_Tenants() {return num_Tenants;}
    public void setNum_Tenants(int num_Tenants) {this.num_Tenants = num_Tenants;}

    public int getNum_Beds() {return num_Beds;}
    public void setNum_Beds(int num_Beds) {this.num_Beds = num_Beds;}

    public int getNum_Bath() {return num_Bath;}
    public void setNum_Bath(int num_Bath) {this.num_Bath = num_Bath;}

    public int getNum_Car() {return num_Car;}
    public void setNum_Car(int num_Car) {this.num_Car = num_Car;}

    public int getRent() {return rent;}
    public void setRent(int rent) {this.rent = rent;}

    public int getUtilities() {return utilities;}
    public void setUtilities(int utilities) {this.utilities = utilities;}

}
