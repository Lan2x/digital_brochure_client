package com.example.digitalbrochureapp.api;

public class User {

    private int id;
    private String fullName, birthDate, gender, birthPlace, email, password;

    public User(int id, String fullName, String birthDate, String gender, String birthPlace, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
