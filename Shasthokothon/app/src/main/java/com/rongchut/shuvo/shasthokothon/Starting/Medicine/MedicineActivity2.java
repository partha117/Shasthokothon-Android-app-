package com.rongchut.shuvo.shasthokothon.Starting.Medicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine2);
        final EditText name=(EditText)findViewById(R.id.editTextName);
        final Spinner morning=(Spinner)findViewById(R.id.spinnerM);
        final Spinner noon=(Spinner)findViewById(R.id.spinnerNOON);
        final Spinner night=(Spinner)findViewById(R.id.spinnerN);
        Button button=(Button)findViewById(R.id.button10);

        final List<String > qty=new ArrayList<>();
        qty.add("ঔষধের পরিমান");
        qty.add("০ টি");
        qty.add("১ টি");
        qty.add("২ টি");
        ArrayAdapter<String> qty_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qty);

        // Drop down layout style - list view with radio button
        qty_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        morning.setAdapter(qty_adapter);
        noon.setAdapter(qty_adapter);
        night.setAdapter(qty_adapter);
        //morning.setPrompt("ঔষধের পরিমান");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int m=morning.getSelectedItemPosition();
                int n1=noon.getSelectedItemPosition();
                int n2=night.getSelectedItemPosition();
                if((m==0)||(n1==0)||(n2==0)||(name.getText().toString().length()==0))
                {
                    Toast.makeText(MedicineActivity2.this, "অনুগ্রহ করে সবগুলো ঘর পূরণ করুন(কোন বেলায় ঔষধ প্রয়োজন না হলে \"০ টি\" দিন)", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(MedicineActivity2.this,MedicineActivity3.class);
                    intent.putExtra("MORNING",m-1);
                    intent.putExtra("NOON",n1-1);
                    intent.putExtra("NIGHT",n2-1);
                    intent.putExtra("NAME",name.getText().toString());
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

}
