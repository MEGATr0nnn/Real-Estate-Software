package com.example.real_estate_software.model;

/*This is the Property class that holds all the information regarding each property,
* it is used to set property info that is then saved into the DB*/
public class Property {
    private String address;
    private int num_Beds;
    private int num_Bath;
    private int num_Car;

    public Property(String address, int num_Beds, int num_Bath, int num_Car){
        this.address = address;
        this.num_Beds = num_Beds;
        this.num_Bath = num_Bath;
        this.num_Car = num_Car;
    }

    public String getAddress() {return address;}
    public void setAddress(String address) {
        this.address = address;
    }

    public int getNum_Beds() {return num_Beds;}
    public void setNum_Beds(int num_Beds) {
        this.num_Beds = num_Beds;
    }

    public int getNum_Bath() {return num_Bath;}
    public void setNum_Bath(int num_Bath) {
        this.num_Bath = num_Bath;
    }

    public int getNum_Car() {return num_Car;}
    public void setNum_Car(int num_Car) {
        this.num_Car = num_Car;
    }
}
