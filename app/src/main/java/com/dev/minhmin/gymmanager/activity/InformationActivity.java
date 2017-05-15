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
import com.dev.minhmin.gymmanager.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        findViewById();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight, height, age, gender;
                weight = edtWeight.getText().toString();
                height = edtHeight.getText().toString();
                age = edtAge.getText().toString();
                if (weight.equals("") || height.equals("") || age.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay dien day du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> value = new HashMap<>();
                    value.put("height", height);
                    value.put("weight", weight);
                    value.put("age", age);
                    float goal, w, h, a;
                    w = Float.parseFloat(weight);
                    h = Float.parseFloat(height);
                    a = Float.parseFloat(age);
                    if (rbFemale.isChecked()) {
                        gender = rbFemale.getText().toString();
                        value.put("gender", gender);
                        goal = (float) (((9.246 * w) + (3.098 * h) - (4.330 * a) + 88.362) * 1.55);
                        value.put("goal", goal);
                    } else {
                        gender = rbMale.getText().toString();
                        value.put("gender", gender);
                        goal = (float) (((13.397 * w) + (4.799 * h) - (5.677 * a) + 447.593) * 1.55);
                        value.put("goal", goal);
                    }
                    value.put("name", user.getDisplayName());
                    value.put("email", user.getEmail());
                    value.put("imageUrl", user.getPhotoUrl());
                    User u = new User(user.getUid(), user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString(), gender, Integer.parseInt(height), Float.parseFloat(weight), goal);
                    ref.child(user.getUid()).setValue(u);
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
