package com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback;

/**
 * Created by ASUS on 07-Jan-17.
 */

public class Feedback {


    private String  State;
    private String Itching;
    private String Rash;
    private String DifficultyInBreathing;
    private String Nausea;
    private String Headache;
    private String Diarrhoea;
    private  String Inflammation;
    private  String Others;

    public Feedback() {
    }

    public Feedback(String diarrhoea, String difficultyInBreathing, String headache, String inflammation, String itching, String nausea, String others, String rash, String state) {
        Diarrhoea = diarrhoea;
        DifficultyInBreathing = difficultyInBreathing;
        Headache = headache;
        Inflammation = inflammation;
        Itching = itching;
        Nausea = nausea;
        Others = others;
        Rash = rash;
        State = state;
    }

    public String getOthers() {
        return Others;
    }

    public void setOthers(String others) {
        Others = others;
    }

    public String getInflammation() {
        return Inflammation;
    }

    public void setInflammation(String inflammation) {
        Inflammation = inflammation;
    }

    public String getDiarrhoea() {
        return Diarrhoea;
    }

    public void setDiarrhoea(String diarrhoea) {
        Diarrhoea = diarrhoea;
    }

    public String getDifficultyInBreathing() {
        return DifficultyInBreathing;
    }

    public void setDifficultyInBreathing(String difficultyInBreathing) {
        DifficultyInBreathing = difficultyInBreathing;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getHeadache() {
        return Headache;
    }

    public void setHeadache(String headache) {
        Headache = headache;
    }

    public String getItching() {
        return Itching;
    }

    public void setItching(String itching) {
        Itching = itching;
    }

    public String getNausea() {
        return Nausea;
    }

    public void setNausea(String nausea) {
        Nausea = nausea;
    }

    public String getRash() {
        return Rash;
    }

    public void setRash(String rash) {
        Rash = rash;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "Diarrhoea='" + Diarrhoea + '\'' +
                ", State='" + State + '\'' +
                ", Itching='" + Itching + '\'' +
                ", Rash='" + Rash + '\'' +
                ", DifficultyInBreathing='" + DifficultyInBreathing + '\'' +
                ", Nausea='" + Nausea + '\'' +
                ", Headache='" + Headache + '\'' +
                ", Inflammation='" + Inflammation + '\'' +
                ", Others='" + Others + '\'' +
                '}';
    }

}
