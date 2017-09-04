package com.rongchut.shuvo.shasthokothon.Starting.Medicine;

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
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBase;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBaseOpenHelper;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.ListViewAdapter;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.NameData;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.VaccineActivity;

import java.util.ArrayList;

public class MedicineActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ঔষধের তালিকা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fabVaccine);
        final ListView nameList=(ListView)findViewById(R.id.name);
        DataBase myDB=new DataBase(MedicineActivity1.this);
        final ArrayList<NameData> array=myDB.getAllMedicine();
        final ListViewAdapter listAdapter=new ListViewAdapter(MedicineActivity1.this,R.layout.listview,R.id.textViewInList,array);
        nameList.setAdapter(listAdapter);
        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(MedicineActivity1.this,Medicine_ItemActivity.class);
                intent.putExtra("ID",Integer.parseInt(array.get(i).getId()));
                startActivity(intent);

            }
        });
        nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                new AlertDialog.Builder(MedicineActivity1.this)
                        .setTitle("নিশ্চিত করুন...")
                        .setMessage("আপনি কি এই ঔষধটি মুছে ফেলতে চাচ্ছেন?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //Toast.makeText(VaccineActivity.this, "Yaay", Toast.LENGTH_SHORT).show();
                                DataBase dataBase=new DataBase(MedicineActivity1.this);
                                dataBase.deleteId(listAdapter.getArrayList().get(i).getId());
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
                Intent intent=new Intent(MedicineActivity1.this,MedicineActivity2.class);
                startActivity(intent);
                finish();
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
            final Dialog dialog = new Dialog(MedicineActivity1.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(MedicineActivity1.this);
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
