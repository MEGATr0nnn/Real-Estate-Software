package com.example.real_estate_software.model;

public class Owner extends User {
    private String password;
    private boolean signedIn;


    public Owner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);
        this.password = password;
        signedIn = true;
    }

    public Owner(String firstName, String lastName, String email, String password, boolean signedIn) {
        super(firstName, lastName, email);
        this.password = password;
        this.signedIn = signedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public boolean checkValidEmail() {
        return this.getEmail().contains("@") && this.getEmail().contains(".com");
    }

    public boolean checkCapitalPassword() {
        for (int i = 0; i < this.getPassword().length(); i++) {
            if (Character.isUpperCase(this.getPassword().charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNumberPassowrd() {
        for (int i = 0; i < this.getPassword().length(); i++) {
            if (Character.isDigit(this.getPassword().charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLengthPassword(){
        return this.getPassword().length() >= 8;
    }

    public boolean checkUniqueCharacterPassword(){
        String uniqueChar = ".*[!@#$%^&*()_+{}|:;<>?,./`~]*.";

        for (int i = 0; i < this.getPassword().length(); i++){
            for (int j = 0; j < uniqueChar.length(); j++){
                char c = this.getPassword().charAt(i);
                if (c == uniqueChar.charAt(j)){
                return true;
                }
            }
        }
        return false;
    }
}

