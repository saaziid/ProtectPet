package com.example.protectpetapp;

public class Pets {

    private String image, location, petName, species, birthday, gender, breed, foodHabit, Status, MedicalHistory;

    public Pets()
    {

    }

    public Pets(String image, String location, String petName, String species, String birthday, String gender, String breed, String foodHabit, String status, String medicalHistory) {
        this.image = image;
        this.location = location;
        this.petName = petName;
        this.species = species;
        this.birthday = birthday;
        this.gender = gender;
        this.breed = breed;
        this.foodHabit = foodHabit;
        Status = status;
        MedicalHistory = medicalHistory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFoodHabit() {
        return foodHabit;
    }

    public void setFoodHabit(String foodHabit) {
        this.foodHabit = foodHabit;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMedicalHistory() {
        return MedicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        MedicalHistory = medicalHistory;
    }
}
