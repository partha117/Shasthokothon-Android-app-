package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

/**
 * Created by ASUS on 01-Nov-16.
 */

public class PatientData {
    private  VaccineData vaccineData;
    private  String name;
    private  String dateOfBirth;
    private  String gender;
    private  int id;

    public PatientData(VaccineData vaccineData, String name, String dateOfBirth, String gender, int id) {
        this.vaccineData = vaccineData;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    public PatientData() {
    }

    public VaccineData getVaccineData() {
        return vaccineData;
    }

    public void setVaccineData(VaccineData vaccineData) {
        this.vaccineData = vaccineData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
