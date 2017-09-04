package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

/**
 * Created by ASUS on 04-Nov-16.
 */

public class VaccineState {
    private String VaccineName;
    private String VaccineState;

    public VaccineState(String vaccineName, String vaccineState) {
        VaccineName = vaccineName;
        VaccineState = vaccineState;
    }

    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }

    public String getVaccineState() {
        return VaccineState;
    }

    public void setVaccineState(String vaccineState) {
        VaccineState = vaccineState;
    }
}
