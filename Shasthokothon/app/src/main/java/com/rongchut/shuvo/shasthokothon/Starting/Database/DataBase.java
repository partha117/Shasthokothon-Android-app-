package com.rongchut.shuvo.shasthokothon.Starting.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.NameData;

import java.util.ArrayList;

/**
 * Created by ASUS on 31-Dec-16.
 */

public class DataBase extends SQLiteOpenHelper {


    private Context context;
    private static  String  DataBaseName="MedicineDetails";
    private static  String  id="ID";
    private static  String Name="NAME";
    private static  String Qty_Morning="QUANTITY_MORNING";
    private static  String Qty_Noon="QUANTITY_NOON";
    private static  String Qty_Night="QUANTITY_NIGHT";
    private static String Sound="SOUND";
    private static String Source="SOURCE";
    private static String Time_Morning="TIME_MORNING";
    private static String Time_Noon="TIME_NOON";
    private static String Time_Night="TIME_NIGHT";
    private static String Date_Start="START_DATE";
    private static String Date_End="END_DATE";
    private SQLiteDatabase database;

    public DataBase(Context context) {
        super(context, DataBaseName, null, 1);
        this.context=context;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table "+DataBaseName+" ("
                        +id+" integer primary key AUTOINCREMENT, "
                        +Name+" text, "
                        +Qty_Morning +" integer, "
                        +Qty_Noon+" integer, "
                        +Qty_Night+" integer, "
                        +Sound+" text , "
                        +Source+" text, "
                        +Time_Morning+" text ,"
                        +Time_Noon+" text ,"
                        +Time_Night+" text ,"
                        +Date_Start+" text ,"
                        +Date_End+" text)"

        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DataBaseName);
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
    public ArrayList<NameData>getAllMedicine()
    {
        ArrayList<NameData> allName=new ArrayList<>();

        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT "+Name+" , "+id+" FROM "+DataBaseName+" ORDER BY "+Name+" ASC ",null);
        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String temp = cursor.getString(cursor.getColumnIndex(Name));
                String temp1 = cursor.getString(cursor.getColumnIndex(id));
                allName.add(new NameData(temp,temp1));
                cursor.moveToNext();
            }
        }
        return allName;
    }
    public void addMedicine(String  name,int qm,int qno,int qni,boolean s,String url,String tm,String tno,String tni,String ds,String de)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Qty_Morning,qm);
        contentValues.put(Qty_Noon,qno);
        contentValues.put(Qty_Night,qni);
        contentValues.put(Sound,s+"");
        contentValues.put(Source,url);
        contentValues.put(Time_Morning,tm);
        contentValues.put(Time_Noon,tno);
        contentValues.put(Time_Night,tni);
        contentValues.put(Date_Start,ds);
        contentValues.put(Date_End,de);
        db.insert(DataBaseName,null,contentValues);
        return;
    }
    public String [] getAllFromId(String  medId)
    {
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT *"+" FROM "+DataBaseName+" WHERE "+this.id+" = "+medId+" ORDER BY "+Name+" ASC ",null);
        cursor.moveToFirst();
        String []result=new String[10];
        result[0]=cursor.getString(cursor.getColumnIndex(Name));
        result[1]=cursor.getInt(cursor.getColumnIndex(Qty_Morning))+"";
        result[2]=cursor.getInt(cursor.getColumnIndex(Qty_Noon))+"";
        result[3]=cursor.getInt(cursor.getColumnIndex(Qty_Night))+"";
        result[4]=cursor.getString(cursor.getColumnIndex(Date_Start));
        result[5]=cursor.getString(cursor.getColumnIndex(Date_End));
        result[6]=cursor.getString(cursor.getColumnIndex(Time_Morning));
        result[7]=cursor.getString(cursor.getColumnIndex(Time_Noon));
        result[8]=cursor.getString(cursor.getColumnIndex(Time_Night));
        result[9]=cursor.getString(cursor.getColumnIndex(Sound));
        return result;

    }
    public int getId(String  name,int qm,int qno,int qni,boolean s,String url,String tm,String tno,String tni,String ds,String de)
    {

                String  sql="SELECT "+this.id+" FROM "+DataBaseName+" WHERE "
                +this.Name+" = '"+name+"' and "+
                this.Qty_Morning+" = '"+qm+"' and "+
                this.Qty_Noon+" = '"+qno+"' and "+
                this.Qty_Night+" = '"+qni+"' and "+
                this.Sound+" = '"+s+""+"' and "+
                this.Source+" = '"+url+"' and "+
                this.Time_Morning+" = '"+tm+"' and "+
                this.Time_Noon+" = '"+tno+"' and "+
                this.Time_Night+" = '"+tni+"' and "+
                this.Date_Start+" = '"+ds+"' and "+
                this.Date_End+" = '"+de+"'";
                Cursor cursor=this.getReadableDatabase().rawQuery(sql,null);
                cursor.moveToFirst();
                int id=(cursor.getInt(cursor.getColumnIndex(this.id)));
                return  id;
    }
    public int deleteId(String id)
    {

            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(DataBaseName,this.id+" = ?",new String[]{id});

    }
    public  void update(String id)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Sound,"false");
        contentValues.put(Source,"NONE");
        this.getWritableDatabase().update(DataBaseName,contentValues,this.id+" = ?",new String[]{id});


        return;
    }
    public String  getSourcse(String  id)
    {
        Cursor cursor=this.getWritableDatabase().rawQuery("SELECT "+this.Source+" FROM "+DataBaseName+" WHERE "+this.id+" = "+id,null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(this.Source));
    }
    public String  getSound(String  id)
    {
        Cursor cursor=this.getWritableDatabase().rawQuery("SELECT "+this.Sound+" FROM "+DataBaseName+" WHERE "+this.id+" = "+id,null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(this.Sound));
    }
    public String  getName(String  id)
    {
        Cursor cursor=this.getWritableDatabase().rawQuery("SELECT "+this.Name+" FROM "+DataBaseName+" WHERE "+this.id+" = "+id,null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(this.Name));
    }

    public boolean isOkay(String  id)
    {
        Cursor cursor=this.getWritableDatabase().rawQuery("SELECT *"+" FROM "+DataBaseName+" WHERE "+this.id+" = "+id,null);
        cursor.moveToFirst();
        if(cursor!=null && cursor.getCount()>0)
        {
            if(cursor.getString(cursor.getColumnIndex(this.id)).compareTo(id)==0)
            {
                return  true;
            }
        }
        return  false;

    }
}
