package com.rongchut.shuvo.shasthokothon.Starting.Medicine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBase;
import com.rongchut.shuvo.shasthokothon.Starting.Medicine.FurtherInteraction.Interaction;
import com.rongchut.shuvo.shasthokothon.Starting.Others.Converter;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.VaccineActivity;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class MedicineActivity3 extends AppCompatActivity {


    final int FILE_SELECT_CODE=0;
    boolean recording=false;
    MediaRecorder mediaRecorder;
    String  PathInDevice;
    EditText file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine3);
        final EditText morning=(EditText)findViewById(R.id.editTextMorning);
        final  EditText noon=(EditText)findViewById(R.id.editTextNoon);
        final  EditText night=(EditText)findViewById(R.id.editTextNight);
        final  EditText start=(EditText)findViewById(R.id.editTextStart);
        final  EditText end=(EditText)findViewById(R.id.editTextEnd);
        final CheckBox sound=(CheckBox)findViewById(R.id.checkBox);
        Button done=(Button)findViewById(R.id.button4);
        final String  mName=getIntent().getStringExtra("NAME");
        final int qm=getIntent().getIntExtra("MORNING",-1);
        final int qno=getIntent().getIntExtra("NOON",-1);
        final int qni=getIntent().getIntExtra("NIGHT",-1);
        final String[] stMorning=new String[1];
        final String[] stNoon=new String[1];
        final String[] stNight=new String[1];
        final String[] stStartDate=new String[1];
        final String[] stEndDate=new String[1];
        stMorning[0]="";
        stNoon[0]="";
        stNight[0]="";
        stStartDate[0]="";
        stEndDate[0]="";




        final Calendar myCalender = Calendar.getInstance();



        final TimePickerDialog.OnTimeSetListener timeMorning=new TimePickerDialog.OnTimeSetListener(){


            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar calender=Calendar.getInstance();

                calender.set(Calendar.HOUR_OF_DAY,i);
                calender.set(Calendar.MINUTE,i1);


                String myFormat = "hh:mm a"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                stMorning[0]=sdf.format(calender.getTime());
                morning.setText(Converter.readyTime(sdf.format(calender.getTime())));


            }
        };
        morning.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {





                     new TimePickerDialog(MedicineActivity3.this,
                            timeMorning,myCalender.get(Calendar.HOUR),
                            myCalender.get(Calendar.MINUTE),false).show();


                }


                return true;
            }
        });



        final TimePickerDialog.OnTimeSetListener timeNoon=new TimePickerDialog.OnTimeSetListener(){


            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar calender=Calendar.getInstance();

                calender.set(Calendar.HOUR_OF_DAY,i);
                calender.set(Calendar.MINUTE,i1);


                String myFormat = "hh:mm a"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                stNoon[0]=sdf.format(calender.getTime());
                noon.setText(Converter.readyTime(sdf.format(calender.getTime())));


            }
        };
        noon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {





                    new TimePickerDialog(MedicineActivity3.this,
                            timeNoon,myCalender.get(Calendar.HOUR),
                            myCalender.get(Calendar.MINUTE),false).show();


                }


                return true;
            }
        });



        final TimePickerDialog.OnTimeSetListener timeNight=new TimePickerDialog.OnTimeSetListener(){


            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar calender=Calendar.getInstance();

                calender.set(Calendar.HOUR_OF_DAY,i);
                calender.set(Calendar.MINUTE,i1);


                String myFormat = "hh:mm a"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                stNight[0]=sdf.format(calender.getTime());
                night.setText(Converter.readyTime(sdf.format(calender.getTime())));


            }
        };
        night.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {





                    new TimePickerDialog(MedicineActivity3.this,
                            timeNight,myCalender.get(Calendar.HOUR),
                            myCalender.get(Calendar.MINUTE),false).show();


                }


                return true;
            }
        });



        final DatePickerDialog.OnDateSetListener dateStart = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if(!(calendar.getTime().before(myCalender.getTime())))
                {
                    String myFormat = "dd/MM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                    stStartDate[0]=sdf.format(calendar.getTime());
                    start.setText(Converter.convertToBengali(sdf.format(calendar.getTime())));
                }
                else
                {
                    Toast.makeText(MedicineActivity3.this, "শুরুর দিন অবশ্যই আজকে অথবা তারপরে হতে হবে।", Toast.LENGTH_SHORT).show();
                }



            }


        };
        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                    {
                        new DatePickerDialog(MedicineActivity3.this, dateStart, myCalender
                                .get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                                myCalender.get(Calendar.DAY_OF_MONTH)).show();

                }


                return true;
            }
        });




        final DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if(!(calendar.getTime().before(myCalender.getTime()))) {
                    String myFormat = "dd/MM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                    stEndDate[0]=sdf.format(calendar.getTime());
                    end.setText(Converter.convertToBengali(sdf.format(calendar.getTime())));
                }
                else
                {
                    Toast.makeText(MedicineActivity3.this, "শুরুর দিন অবশ্যই আজকে অথবা তারপরে হতে হবে।", Toast.LENGTH_SHORT).show();
                }



            }


        };
        end.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                {
                    new DatePickerDialog(MedicineActivity3.this, dateEnd, myCalender
                            .get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                            myCalender.get(Calendar.DAY_OF_MONTH)).show();

                }


                return true;
            }
        });


        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               // Toast.makeText(MedicineActivity3.this, b+"", Toast.LENGTH_SHORT).show();
                if(b)
                {
                    final Dialog dialog = new Dialog(MedicineActivity3.this);

                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                    dialog.setContentView(R.layout.medicine_dialog);
                    PathInDevice="DEFAULT";
                    dialog.show();




                    final Button record=(Button)dialog.findViewById(R.id.record);
                    final Button end=(Button)dialog.findViewById(R.id.button7);
                    final TextView state=(TextView)dialog.findViewById(R.id.state);
                    final TextView address=(TextView)dialog.findViewById(R.id.fileAddress);


                    record.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            if (!recording)
                            {

                                mediaRecorder = MediaRecorderReady();
                                try {
                                    mediaRecorder.prepare();
                                    state.setVisibility(View.VISIBLE);
                                    state.setText("রেকর্ডিং...");
                                    record.setText("রেকর্ডিং শেষ করুন");
                                    recording=true;
                                    mediaRecorder.start();
                                    end.setText("শেষ");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                            else
                            {
                                mediaRecorder.stop();
                                recording=false;
                                address.setText("রেকর্ড করা ফাইলঃ"+PathInDevice);
                                record.setText("রেকর্ডিং শুরু করুন");
                                state.setVisibility(View.INVISIBLE);


                            }

                        }
                    });
                    end.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((stMorning[0].length()!=0)&&(stNoon[0].length()!=0)&&(stNight[0].length()!=0)&&(stStartDate[0].length()!=0)&&(stEndDate[0].length()!=0)) {
                    if(!(todate(stStartDate[0]).after(todate(stEndDate[0]))))
                    {

                        if (sound.isChecked()) {
                            new Thread(new Runnable() {
                                public void run() {

                                    DataBase db = new DataBase(MedicineActivity3.this);
                                    db.addMedicine(mName, qm, qno, qni,
                                            sound.isChecked(), PathInDevice,
                                            stMorning[0], stNoon[0], stNight[0], stStartDate[0],
                                            stEndDate[0]);
                                    int id=db.getId(mName, qm, qno, qni,
                                            sound.isChecked(), PathInDevice,
                                            stMorning[0], stNoon[0], stNight[0], stStartDate[0],
                                            stEndDate[0]);
                                    SetNotification notification=new SetNotification(MedicineActivity3.this,
                                            stEndDate[0],id,new int[]{qm,qno,qni},stStartDate[0],stMorning[0]
                                            ,stNight[0],stNoon[0]);
                                    notification.setNotification();
                                    Interaction interaction=new Interaction(MedicineActivity3.this,stEndDate[0],mName,stStartDate[0]);
                                    interaction.interact();
                                }
                            }).start();

                        } else {
                            new Thread(new Runnable() {
                                public void run() {

                                    DataBase db = new DataBase(MedicineActivity3.this);
                                    db.addMedicine(mName, qm, qno, qni, sound.isChecked(), "NONE",
                                            stMorning[0], stNoon[0], stNight[0], stStartDate[0],
                                            stEndDate[0]);
                                    int id=db.getId(mName, qm, qno, qni, sound.isChecked(), "NONE",
                                            stMorning[0], stNoon[0], stNight[0], stStartDate[0],
                                            stEndDate[0]);
                                    SetNotification notification=new SetNotification(MedicineActivity3.this,
                                            stEndDate[0],id,new int[]{qm,qno,qni},stStartDate[0],stMorning[0]
                                            ,stNight[0],stNoon[0]);
                                    notification.setNotification();
                                    Interaction interaction=new Interaction(MedicineActivity3.this,stEndDate[0],mName,stStartDate[0]);
                                    interaction.interact();

                                }
                            }).start();

                        }
                        Intent intent = new Intent(MedicineActivity3.this, MedicineActivity1.class);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(MedicineActivity3.this, "শুরুর দিন অবশ্যই শেষের দিনের আগে অথবা সমান হতে হবে", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MedicineActivity3.this, "অনুগ্রহ করে সবগুলো ঘর পূরণ করুন", Toast.LENGTH_SHORT).show();
                }
            }
        });











    }









    public MediaRecorder MediaRecorderReady(){
        MediaRecorder mediaRecorder1=new MediaRecorder();
        mediaRecorder1.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder1.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder1.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);


        File myDirectory = new File(Environment.getExternalStorageDirectory(), "Shasthokothon");

        if(!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

         PathInDevice =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Shasthokothon/" +
                        "Shasthokothon_"+ UUID.randomUUID()+"_" + "AudioRecording.mp3";

        mediaRecorder1.setOutputFile(PathInDevice);
        return mediaRecorder1;
    }
    public Date todate(String date)
    {



        String startDateString = date;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate=null;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;

    }
}
