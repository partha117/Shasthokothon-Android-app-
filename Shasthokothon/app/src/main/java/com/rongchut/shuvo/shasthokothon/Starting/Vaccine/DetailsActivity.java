package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBaseOpenHelper;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Others.Converter;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    DataBaseOpenHelper db;
    String id;
    ListViewAdapter2 listAdapter;
    ArrayList<VaccineState> prev;
    ArrayList<VaccineState>before=null;




    @Override
    public void onBackPressed() {
        PatientData pd=db.getAll(id);
        prev=db.getAll(id).getVaccineData().getlist();
        db.update(id,new VaccineData(listAdapter.getArrayList()));
        if(before!=null)
        {
            for (int i = 0; i < prev.size(); i++) {
                if (prev.get(i).getVaccineState().compareTo(before.get(i).getVaccineState()) == 0) {
                    prev.get(i).setVaccineState("NO CHANGE");


                }
            }

            VaccineData vd = new VaccineData(prev);
            pd.setVaccineData(vd);
            NotificationSetter notificationSetter = new NotificationSetter(pd, this);
            notificationSetter.setNotification();
        }


        db.close();
        //Toast.makeText(this, "Called on back", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("টিকা :");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id=getIntent().getStringExtra("ID");
        db=new DataBaseOpenHelper(DetailsActivity.this,DataBaseOpenHelper.VaccinePatientDetails);
        PatientData data=db.getAll(id);
        if(data!=null)
        {
            //print(data);
            //Toast.makeText(DetailsActivity.this, "Called "+id, Toast.LENGTH_SHORT).show();
            TextView name = (TextView) findViewById(R.id.textViewName);
            TextView birth_date = (TextView) findViewById(R.id.textViewBD);

            final ListView listView = (ListView) findViewById(R.id.listviewDetails);
            getSupportActionBar().setTitle(data.getName()+"এর টিকার তালিকা");
            name.setText("নাম " + " : " + data.getName());
            birth_date.setText("জন্ম তারিখ " + " : " + Converter.convertToBengali(data.getDateOfBirth()));
            ArrayList<VaccineState> sk = data.getVaccineData().getlist();
            Log.v("detailsvaccine", "from get" + " id " + id);
            for (int i = 0; i < sk.size(); i++) {
                Log.v("detailsvaccine", "from get before set" + sk.get(i).getVaccineName() + "  " + sk.get(i).getVaccineState());
            }


            listAdapter = new ListViewAdapter2(DetailsActivity.this, R.layout.listview2, R.id.checkedTextView, data.getVaccineData().getlist());
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    CheckedTextView tv = (CheckedTextView) view.findViewById(R.id.checkedTextView);
                    if (tv.isChecked()) {

                        listAdapter.getArrayList().get(i).setVaccineState("false");
                        listAdapter.notifyDataSetChanged();
                    } else {

                        listAdapter.getArrayList().get(i).setVaccineState("true");
                        listAdapter.notifyDataSetChanged();
                    }
                    before = listAdapter.getArrayList();

                }
            });


        }














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

        if (id ==R.id.homeButton)
        {
            Intent intent=new Intent(DetailsActivity.this,MainActivity.class);
            startActivity(intent);

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(DetailsActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(DetailsActivity.this);
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
        else if(id==android.R.id.home)
        {
            finish();
        }




        return super.onOptionsItemSelected(item);
    }
    private  void print(PatientData pd)
    {
        String st="";
        if(pd.getVaccineData().getBCG().compareTo("true")==0)
        {
            st+=" BCG";
        }
        if(pd.getVaccineData().getDPT().compareTo("true")==0)
        {
            st+=" DPT";
        }
        if(pd.getVaccineData().getPOLIO().compareTo("true")==0)
        {
            st+=" POLIO";
        }
        if(pd.getVaccineData().getHAM().compareTo("true")==0)
        {
            st+=" HAM";
        }
        if(pd.getVaccineData().getHPV().compareTo("true")==0)
        {
            st+=" HPV";
        }
        if(pd.getVaccineData().getHEPAB().compareTo("true")==0)
        {
            st+=" HEPAB";
        }
        st+=" true ";
        Toast.makeText(this, st, Toast.LENGTH_SHORT).show();

    }
}
