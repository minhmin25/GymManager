package com.dev.minhmin.gymmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dev.minhmin.gymmanager.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minh min on 5/13/2017.
 */

public class InformationActivity extends AppCompatActivity {

    private EditText edtHeight, edtWeight, edtAge;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Button btnSave;
    private String key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        findViewById();
        Intent intent = getIntent();
        key = intent.getStringExtra("id");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight, height, age;
                weight = edtWeight.getText().toString();
                height = edtHeight.getText().toString();
                age = edtAge.getText().toString();
                if (weight.equals("") || height.equals("") || age.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay dien day du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(key);
                    Map<String, Object> value = new HashMap<>();
                    value.put("height", height);
                    value.put("weight", weight);
                    value.put("age", age);
                    if (rbFemale.isChecked()) {
                        value.put("gender", rbFemale.getText().toString());
                    } else value.put("gender", rbMale.getText().toString());
                    ref.setValue(value);
                    startActivity(new Intent(InformationActivity.this, MainActivity.class));
                }

            }
        });
    }

    private void findViewById() {
        edtHeight = (EditText) findViewById(R.id.edt_init_height);
        edtAge = (EditText) findViewById(R.id.edt_init_age);
        edtWeight = (EditText) findViewById(R.id.edt_init_weight);
        btnSave = (Button) findViewById(R.id.btn_init_save);
        rgGender = (RadioGroup) findViewById(R.id.rg_init_gender);
        rbFemale = (RadioButton) findViewById(R.id.rb_init_female);
        rbMale = (RadioButton) findViewById(R.id.rb_init_male);
    }
}
