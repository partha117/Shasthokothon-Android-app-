package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import java.util.ArrayList;

/**
 * Created by ASUS on 01-Nov-16.
 */

public class VaccineData {
    String BCG;
    String DPT;
    String POLIO;
    String HAM;
    String HPV;
    String  HEPAB;
    boolean essential=false;

    public VaccineData() {
        BCG="false";
        DPT="false";
        POLIO="false";
        HAM= "false";
        HPV="false";
        HEPAB="false";

    }

    public VaccineData(String BCG, String DPT, String POLIO, String HAM, String HPV, String HEPAB) {
        this.BCG = BCG;
        this.DPT = DPT;
        this.POLIO = POLIO;
        this.HAM = HAM;
        this.HPV = HPV;
        this.HEPAB = HEPAB;
    }
    public  VaccineData(ArrayList<VaccineState> arrayList)
    {
        this.BCG = arrayList.get(0).getVaccineState();
        this.DPT = arrayList.get(1).getVaccineState();
        this.POLIO = arrayList.get(2).getVaccineState();
        this.HAM = arrayList.get(3).getVaccineState();
        this.HEPAB = arrayList.get(4).getVaccineState();
        if(arrayList.size()==5)
        {
            this.HPV ="false";
        }
        else
        {
            this.HPV = arrayList.get(5).getVaccineState();
        }
    }

    public String getBCG() {
        return BCG;
    }

    public void setBCG(String BCG) {
        this.BCG = BCG;
    }

    public String getDPT() {
        return DPT;
    }

    public void setDPT(String DPT) {
        this.DPT = DPT;
    }

    public String getPOLIO() {
        return POLIO;
    }

    public void setPOLIO(String POLIO) {
        this.POLIO = POLIO;
    }

    public String getHAM() {
        return HAM;
    }

    public void setHAM(String HAM) {
        this.HAM = HAM;
    }

    public String getHPV() {
        return HPV;
    }

    public void setHPV(String HPV) {
        this.HPV = HPV;
    }

    public String getHEPAB() {
        return HEPAB;
    }

    public void setHEPAB(String HEPAB) {
        this.HEPAB = HEPAB;
    }

    public boolean isEssential() {
        return essential;
    }

    public void setEssential(boolean essential) {
        this.essential = essential;
    }

    public ArrayList<VaccineState>getlist()
    {
        ArrayList<VaccineState> arrayList=new ArrayList<>();
        arrayList.add(new VaccineState("বি.সি.জি.",getBCG()));
        arrayList.add(new VaccineState("ডি.পি.টি.",getDPT()));
        arrayList.add(new VaccineState("পোলিও",getPOLIO()));
        arrayList.add(new VaccineState("হাম",getHAM()));
        arrayList.add(new VaccineState("হেপাটাইটিস বি",getHEPAB()));
        if(essential)
        {
            arrayList.add(new VaccineState("এইচ.পি.ভি",getHPV()));
        }
        return arrayList;


    }
}
