package com.rongchut.shuvo.shasthokothon.Starting.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.Starting.Emergency.Emergency;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.NameData;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.PatientData;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.VaccineData;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.VaccineState;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by ASUS on 01-Nov-16.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public static final String VaccinePatientDetails="VaccinePatientDetails";
    public  static String EmergencyServices;
    private  Context context;
    private SQLiteDatabase database;
    private String vaccine_patient_name="NAME";
    private String Patient_Birth_Date="BIRTH_DATE";
    private String id="ID";
    private String BCG="BCG";
    private String DPT="DPT";
    private String POLIO="POLIO";
    private String HAM="HAM";
    private String HPV="HPV";
    private String  HEPAB="HEPAB";
    private  String patient_Gender="GENDER";
    private  String selection;


    public DataBaseOpenHelper(Context context, String DataBaseName) {
        super(context, DataBaseName, null, 1);
        this.context=context;
        selection=DataBaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table "+selection+" ("
                        +id+" integer primary key AUTOINCREMENT, "
                        +vaccine_patient_name+" text, "
                        +Patient_Birth_Date +" text, "
                        +patient_Gender+" text, "
                        +BCG+" text, "
                        +DPT+" text, "
                        +POLIO+" text, "
                        +HAM+" text, "
                        +HPV+" text, "
                        +HEPAB+" text )"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+selection);
        onCreate(sqLiteDatabase);

    }

    public synchronized void close() {
        if (this.database != null) {
            this.database.close();
        }
    }
    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }
    public ArrayList<NameData>getPatientName()
    {
        ArrayList<NameData> allName=new ArrayList<>();

        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT "+vaccine_patient_name+" , "+id+" FROM "+VaccinePatientDetails+" ORDER BY "+vaccine_patient_name+" ASC ",null);
        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String temp = cursor.getString(cursor.getColumnIndex(vaccine_patient_name));
                String temp1 = cursor.getString(cursor.getColumnIndex(id));
                allName.add(new NameData(temp,temp1));
                cursor.moveToNext();
            }
        }
        return allName;

    }
    public boolean addName(String name,String dateofbirth,String gender,VaccineData vaccineData)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(vaccine_patient_name,name);
        contentValues.put(Patient_Birth_Date,dateofbirth);
        contentValues.put(patient_Gender,gender);
        contentValues.put(BCG,vaccineData.getBCG());
        contentValues.put(DPT,vaccineData.getDPT());
        contentValues.put(POLIO,vaccineData.getPOLIO());
        contentValues.put(HAM,vaccineData.getHAM());
        contentValues.put(HPV,vaccineData.getHPV());
        contentValues.put(HEPAB,vaccineData.getHEPAB());
        db.insert(VaccinePatientDetails,null,contentValues);
        return  true;


    }
    public  int getID(String  name,String  dob,String gender)
    {
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT "+this.id+" FROM "+VaccinePatientDetails+" WHERE "+
                this.vaccine_patient_name+" = '"+name+"' and "+this.Patient_Birth_Date+" = '"+dob+"' and "+this.patient_Gender+" = '"+gender+"'",null);
        cursor.moveToFirst();
        int id=(cursor.getInt(cursor.getColumnIndex(this.id)));
        return id;

    }
    public  int delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(selection,this.id+" = ?",new String[]{id});
    }
    public  boolean update(String id,VaccineData vaccineData)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        PatientData patientData=getAll(id);
        //contentValues.put(this.id,id);
        contentValues.put(vaccine_patient_name,patientData.getName());
        contentValues.put(Patient_Birth_Date,patientData.getDateOfBirth());
        contentValues.put(patient_Gender,patientData.getGender());
        contentValues.put(BCG,vaccineData.getBCG());
        contentValues.put(DPT,vaccineData.getDPT());
        contentValues.put(POLIO,vaccineData.getPOLIO());
        contentValues.put(HAM,vaccineData.getHAM());
        contentValues.put(HPV,vaccineData.getHPV());
        contentValues.put(HEPAB,vaccineData.getHEPAB());

        Log.v("detailsvaccine","from put"+" id "+id);
        Log.v("detailsvaccine","from put"+BCG+vaccineData.getBCG());
        Log.v("detailsvaccine","from put"+DPT+vaccineData.getDPT());
        Log.v("detailsvaccine","from put"+POLIO+vaccineData.getPOLIO());
        Log.v("detailsvaccine","from put"+HAM+vaccineData.getHAM());
        Log.v("detailsvaccine","from put"+HPV+vaccineData.getHPV());
        Log.v("detailsvaccine","from put"+HEPAB+vaccineData.getHEPAB());




        int temp=db.update(VaccinePatientDetails, contentValues, this.id+" = ? ", new String[] { id } );
        //Toast.makeText(context,temp+" ",Toast.LENGTH_LONG).show();
        return true;
    }
    public PatientData getAll(String id)
    {

        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT *"+" FROM "+VaccinePatientDetails+" WHERE "+this.id+" = "+id+" ORDER BY "+vaccine_patient_name+" ASC ",null);
        cursor.moveToFirst();
        try {
            PatientData patientData = new PatientData();
            VaccineData vaccineData = new VaccineData();
            patientData.setName(cursor.getString(cursor.getColumnIndex(vaccine_patient_name)));
            patientData.setDateOfBirth(cursor.getString(cursor.getColumnIndex(Patient_Birth_Date)));
            patientData.setGender(cursor.getString(cursor.getColumnIndex(patient_Gender)));
            patientData.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.id))));
            if (patientData.getGender().compareTo("FEMALE") == 0) {
                vaccineData.setEssential(true);
            }

            vaccineData.setBCG(cursor.getString(cursor.getColumnIndex(BCG)));
            vaccineData.setDPT(cursor.getString(cursor.getColumnIndex(DPT)));
            vaccineData.setPOLIO(cursor.getString(cursor.getColumnIndex(POLIO)));
            vaccineData.setHAM(cursor.getString(cursor.getColumnIndex(HAM)));
            vaccineData.setHPV(cursor.getString(cursor.getColumnIndex(HPV)));
            vaccineData.setHEPAB(cursor.getString(cursor.getColumnIndex(HEPAB)));
            patientData.setVaccineData(vaccineData);

            ArrayList<VaccineState> sk = patientData.getVaccineData().getlist();
            Log.v("detailsvaccine", "from get" + " id " + id);
            for (int i = 0; i < sk.size(); i++) {
                Log.v("detailsvaccine", "from get" + sk.get(i).getVaccineName() + "  " + sk.get(i).getVaccineState());
            }
            return patientData;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;

    }




}


