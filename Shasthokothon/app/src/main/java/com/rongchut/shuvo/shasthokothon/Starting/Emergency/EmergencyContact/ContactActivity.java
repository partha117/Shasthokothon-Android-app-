package com.rongchut.shuvo.shasthokothon.Starting.Emergency.EmergencyContact;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DBOpenHelper;
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.AddFeedbackActivity;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    String area_selection="ঢাকা";
    String organization_selection="";
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        final Spinner area_spinner=(Spinner)findViewById(R.id.spinner2);
        organization_selection=getResources().getString(R.string.Hospital);
        getSupportActionBar().setTitle(organization_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Spinner organization_spinner=(Spinner)findViewById(R.id.spinner);
        final TextView orname=(TextView)findViewById(R.id.textView13);
        orname.setText(organization_selection+" এর নাম :");
        final ListView listView=(ListView)findViewById(R.id.listView);

        final DBOpenHelper database=new DBOpenHelper(this);
        context=this;







        List<String> area_category = new ArrayList<String>();
        area_category.add(getResources().getString(R.string.Dhaka));
        area_category.add(getResources().getString(R.string.Sylhet));
        area_category.add(getResources().getString(R.string.Chittagong));
        area_category.add(getResources().getString(R.string.Rajshahi));
        area_category.add(getResources().getString(R.string.Barisal));
        area_category.add(getResources().getString(R.string.Rangpur));
        area_category.add(getResources().getString(R.string.Mymensingh));
        area_category.add(getResources().getString(R.string.Khulna));


        // Creating adapter for spinner
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area_category);

        // Drop down layout style - list view with radio button
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_spinner.setAdapter(area_adapter);
        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area_selection = parent.getItemAtPosition(position).toString();
                if (organization_selection.matches(getResources().getString(R.string.Hospital))) {
                    myAdapter adapter = new myAdapter(context, R.layout.hospital, R.id.textView3, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle(organization_selection);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText("");
                            Name.setText("নাম : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("ঠিকানা : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText(" যোগাযোগ : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();





                        }
                    });
                }
                else if (organization_selection.matches(getResources().getString(R.string.Blood_bank))) {
                    myAdapter adapter = new myAdapter(context, R.layout.blood_bank, R.id.textView4, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle(organization_selection);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText("");
                            Name.setText("নাম : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("ঠিকানা : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("যোগাযোগ : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();

                        }
                    });
                }
                else  {
                    myAdapter adapter = new myAdapter(context, R.layout.ambulance, R.id.textView5, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle(organization_selection);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);
                            ;
                            Title.setText("");
                            Name.setText("নাম : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("ঠিকানা : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("যোগাযোগ : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();

                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                area_spinner.setSelection(0);

            }
        });


        List<String> organization_category = new ArrayList<String>();
        organization_category.add(getResources().getString(R.string.Hospital));
        organization_category.add(getResources().getString(R.string.Blood_bank));
        organization_category.add(getResources().getString(R.string.Ambulance));
        final ArrayAdapter<String> organization_adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, organization_category);


        organization_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organization_spinner.setAdapter(organization_adapter);
        organization_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                organization_selection=parent.getItemAtPosition(position).toString();
                getSupportActionBar().setTitle(organization_selection);
                orname.setText(organization_selection+" এর নাম :");
                if (organization_selection.matches(getResources().getString(R.string.Hospital))) {
                    myAdapter adapter = new myAdapter(context, R.layout.hospital, R.id.textView3, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle(organization_selection);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText("");
                            Name.setText("নাম : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("ঠিকানা : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("যোগাযোগ : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();
                        }
                    });
                }
                else if (organization_selection.matches(getResources().getString(R.string.Blood_bank))) {
                    myAdapter adapter = new myAdapter(context, R.layout.blood_bank, R.id.textView4, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle(organization_selection);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText("");
                            Name.setText("নাম : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("ঠিকানা : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("যোগাযোগ : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();
                        }
                    });
                }
                else  {
                    myAdapter adapter = new myAdapter(context, R.layout.ambulance, R.id.textView5, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);

                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle(organization_selection);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText("");
                            Name.setText("নাম : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("ঠিকানা : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("যোগাযোগ : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                organization_spinner.setSelection(0);

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

        if (id ==R.id.homeButton)
        {
            Intent intent=new Intent(ContactActivity.this,MainActivity.class);
            startActivity(intent);

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(ContactActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(ContactActivity.this);
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

}


