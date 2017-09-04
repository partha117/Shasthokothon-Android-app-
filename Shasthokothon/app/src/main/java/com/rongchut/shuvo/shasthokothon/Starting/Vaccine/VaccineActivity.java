package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBaseOpenHelper;

import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidShow;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Others.Converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class VaccineActivity extends AppCompatActivity {
    Boolean maleCheck=false;
    Boolean femaleCheck=false;
    Boolean nameField=false;
    Boolean dateField=false;
    String []pdate=new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("টিকা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fabVaccine);
        final ListView nameList=(ListView)findViewById(R.id.name);



        DataBaseOpenHelper myDB=new DataBaseOpenHelper(VaccineActivity.this,DataBaseOpenHelper.VaccinePatientDetails);
        ArrayList<NameData> array=myDB.getPatientName();
        final ListViewAdapter listAdapter=new ListViewAdapter(VaccineActivity.this,R.layout.listview,R.id.textViewInList,array);
        nameList.setAdapter(listAdapter);


        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Log.v("on click","This is i "+i);

                Intent myIntent=new Intent(VaccineActivity.this,DetailsActivity.class);
                myIntent.putExtra("ID",listAdapter.getArrayList().get(i).getId());
                startActivity(myIntent);

            }
        });




        nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                new AlertDialog.Builder(VaccineActivity.this)
                        .setTitle("নিশ্চিত করুন...")
                        .setMessage("আপনি কি এই নাম মুছে ফেলতে চাচ্ছেন?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //Toast.makeText(VaccineActivity.this, "Yaay", Toast.LENGTH_SHORT).show();
                                DataBaseOpenHelper myDB=new DataBaseOpenHelper(VaccineActivity.this,DataBaseOpenHelper.VaccinePatientDetails);
                                myDB.delete(myDB.getPatientName().get(i).getId());
                                listAdapter.getArrayList().remove(i);
                                listAdapter.notifyDataSetChanged();
                            }})
                        .setNegativeButton("না", null).show();

                return true;
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(VaccineActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.vaccine_dialog);
                dialog.show();


                //dialog set

                final EditText name=(EditText)dialog.findViewById(R.id.name_vaccine);
                final EditText dateText=(EditText)dialog.findViewById(R.id.editText2);
                Button endDialog=(Button)dialog.findViewById(R.id.button_add);
                final CheckBox male=(CheckBox) dialog.findViewById(R.id.checkBox_Male);
                final CheckBox female=(CheckBox) dialog.findViewById(R.id.checkBox_Female);
                name.requestFocus();


                //layout load


                male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(maleCheck)
                        {

                            maleCheck=false;
                            male.setChecked(maleCheck);
                            Log.d("bool","maleCheck "+maleCheck+" femaleCheck "+femaleCheck+" from male t");

                        }
                        else
                        {
                            maleCheck=true;
                            femaleCheck=false;
                            female.setChecked(femaleCheck);

                            Log.d("bool","maleCheck "+maleCheck+" femaleCheck "+femaleCheck+"  from male f");

                        }
                    }
                });
                female.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(femaleCheck)
                        {
                            femaleCheck=false;
                            female.setChecked(femaleCheck);
                            Log.d("bool","maleCheck "+maleCheck+" femaleCheck "+femaleCheck+" from female t");

                        }

                        else
                        {
                            femaleCheck=true;
                            maleCheck=false;
                            male.setChecked(maleCheck);

                            Log.d("bool","maleCheck "+maleCheck+" femaleCheck "+femaleCheck+" from female f");
                        }
                    }
                });

                //Checkbox logic
                final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                        pdate[0]=sdf.format(myCalendar.getTime());
                        dateText.setText(Converter.convertToBengali(sdf.format(myCalendar.getTime())));



                    }


                };



                //calendar load



               /*dateText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new DatePickerDialog(VaccineActivity.this, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                }
                });*/

                dateText.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                        {
                            new DatePickerDialog(VaccineActivity.this, date, myCalendar
                                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                        }
                        return true;
                    }
                });


                //calendar show


                endDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(name.getText().toString().length()!=0)
                        {
                            nameField=true;
                        }
                        if(dateText.getText().toString().length()!=0)
                        {
                            dateField=true;
                        }
                        if(nameField&&dateField&&(maleCheck|femaleCheck))
                        {
                            String pName=name.getText().toString();

                            String pgender;
                            VaccineData vd=new VaccineData();
                            DataBaseOpenHelper myDB=new DataBaseOpenHelper(VaccineActivity.this,DataBaseOpenHelper.VaccinePatientDetails);
                            if(maleCheck)
                            {
                                pgender="MALE";
                            }
                            else
                            {
                                pgender="FEMALE";
                                vd.setEssential(true);
                            }
                            myDB.addName(pName,pdate[0],pgender,vd);
                            int id=myDB.getID(pName,pdate[0],pgender);
                            NotificationSetter notificationSetter=new NotificationSetter(new PatientData(vd,pName,pdate[0],pgender,id),VaccineActivity.this);
                            notificationSetter.setNotification();
                            listAdapter.clear();
                            listAdapter.addAll(myDB.getPatientName());
                            listAdapter.notifyDataSetChanged();
                            dialog.dismiss();

                        }
                        else
                        {
                            Toast.makeText(VaccineActivity.this, "অনুগ্রহ করে সবগুলো ঘর পূরণ করুন", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                //dialog end


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_others, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if ((id ==R.id.homeButton)||(id==android.R.id.home))
        {

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(VaccineActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(VaccineActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.about_dialoge);
            TextView  link=(TextView)dialog.findViewById(R.id.textView51);
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/%E0%A6%B8%E0%A7%8D%E0%A6%AC%E0%A6%BE%E0%A6%B8%E0%A7%8D%E0%A6%A5%E0%A7%8D%E0%A6%AF%E0%A6%95%E0%A6%A5%E0%A6%A8-697701580408010/"));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {

                    }

                }
            });
            ImageView iv=(ImageView)dialog.findViewById(R.id.imageView4);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/%E0%A6%B8%E0%A7%8D%E0%A6%AC%E0%A6%BE%E0%A6%B8%E0%A7%8D%E0%A6%A5%E0%A7%8D%E0%A6%AF%E0%A6%95%E0%A6%A5%E0%A6%A8-697701580408010/"));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {

                    }
                }
            });
            dialog.show();

        }




        return super.onOptionsItemSelected(item);
    }

}
