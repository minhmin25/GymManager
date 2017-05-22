package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.model.User;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Minh min on 4/19/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView tvName, tvNameContent, tvAge, tvEmail, tvGold, tvHeight, tvWeight;
    private EditText edtNameContent, edtAge, edtEmail, edtHeight, edtWeight;
    private CircleImageView ivImage;
    private ImageView ivEdit, ivSave;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private User user;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MainActivity.stateStatistic = ConstantUtils.FRAGMENT_PROFILE;
        ((MainActivity) getActivity()).updateTitle(MainActivity.page, MainActivity.stateStatistic);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        tvName = (TextView) viewGroup.findViewById(R.id.tv_profile_name);
        tvNameContent = (TextView) viewGroup.findViewById(R.id.tv_profile_name_content);
        tvAge = (TextView) viewGroup.findViewById(R.id.tv_profile_age);
        tvEmail = (TextView) viewGroup.findViewById(R.id.tv_profile_email);
        tvGold = (TextView) viewGroup.findViewById(R.id.tv_profile_gold);
        tvHeight = (TextView) viewGroup.findViewById(R.id.tv_profile_height);
        tvWeight = (TextView) viewGroup.findViewById(R.id.tv_profile_weight);
        edtNameContent = (EditText) viewGroup.findViewById(R.id.edt_profile_name_content);
        edtAge = (EditText) viewGroup.findViewById(R.id.edt_profile_age);
        edtEmail = (EditText) viewGroup.findViewById(R.id.edt_profile_email);
        edtHeight = (EditText) viewGroup.findViewById(R.id.edt_profile_height);
        edtWeight = (EditText) viewGroup.findViewById(R.id.edt_profile_weight);
        ivImage = (CircleImageView) viewGroup.findViewById(R.id.iv_profile_image);
        ivEdit = (ImageView) viewGroup.findViewById(R.id.iv_profile_edit);
        ivSave = (ImageView) viewGroup.findViewById(R.id.iv_profile_save);
        rgGender = (RadioGroup) viewGroup.findViewById(R.id.rg_profile_gender);
        rbMale = (RadioButton) viewGroup.findViewById(R.id.rb_profile_male);
        rbFemale = (RadioButton) viewGroup.findViewById(R.id.rb_profile_female);
        ivEdit.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                tvName.setText(user.getName());
                tvNameContent.setText(user.getName());
                tvEmail.setText(user.getEmail());
                tvAge.setText(user.getAge() + "");
                tvGold.setText(user.getGoal() + "");
                tvHeight.setText(user.getHeight() + "");
                tvWeight.setText(user.getWeight() + "");
                if (user.getGender().equals("Male")) {
                    rbMale.setChecked(true);
                } else {
                    rbFemale.setChecked(true);
                }
                rbMale.setClickable(false);
                rbFemale.setClickable(false);
                Glide.with(getActivity())
                        .load(user.getImageUrl())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(ivImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return viewGroup;
    }

    public void updateUI(boolean isEdit) {
        if (isEdit) {
            edtNameContent.setText(tvNameContent.getText());
            edtEmail.setText(tvEmail.getText());
            edtAge.setText(tvAge.getText());
            edtHeight.setText(tvHeight.getText());
            edtWeight.setText(tvWeight.getText());
            edtNameContent.setVisibility(View.VISIBLE);
            edtAge.setVisibility(View.VISIBLE);
            edtEmail.setVisibility(View.VISIBLE);
            edtHeight.setVisibility(View.VISIBLE);
            edtWeight.setVisibility(View.VISIBLE);
            tvNameContent.setVisibility(View.GONE);
            tvAge.setVisibility(View.GONE);
            tvEmail.setVisibility(View.GONE);
            tvHeight.setVisibility(View.GONE);
            tvWeight.setVisibility(View.GONE);
            rbMale.setClickable(true);
            rbFemale.setClickable(true);
        } else {
            rbMale.setClickable(false);
            rbFemale.setClickable(false);
            edtNameContent.setVisibility(View.GONE);
            edtAge.setVisibility(View.GONE);
            edtEmail.setVisibility(View.GONE);
            edtHeight.setVisibility(View.GONE);
            edtWeight.setVisibility(View.GONE);
            tvNameContent.setVisibility(View.VISIBLE);
            tvAge.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
            tvHeight.setVisibility(View.VISIBLE);
            tvWeight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_profile_edit: {
                ivEdit.setVisibility(View.GONE);
                ivSave.setVisibility(View.VISIBLE);
                updateUI(true);
                break;
            }
            case R.id.iv_profile_save: {
                String name, email, age, gender, height, weight;
                name = edtNameContent.getText().toString();
                email = edtEmail.getText().toString();
                age = edtAge.getText().toString();
                height = edtHeight.getText().toString();
                weight = edtWeight.getText().toString();
                tvNameContent.setText(name);
                tvName.setText(name);
                tvAge.setText(age);
                tvEmail.setText(email);
                tvWeight.setText(weight);
                tvHeight.setText(height);
                float g, h, w;
                int a;
                a = Integer.parseInt(age);
                h = Float.parseFloat(height);
                w = Float.parseFloat(weight);
                if (rbMale.isChecked()) {
                    gender = "Male";
                    g = (float) (((13.397 * w) + (4.799 * h) - (5.677 * a) + 447.593) * 1.55);
                } else {
                    gender = "Female";
                    g = (float) (((9.246 * w) + (3.098 * h) - (4.330 * a) + 88.362) * 1.55);
                }
                tvGold.setText(g + "");
                User u = new User(user.getId(), name, a, email, user.getImageUrl(), gender, h, w, g);
                ref.setValue(u);
                ivEdit.setVisibility(View.VISIBLE);
                ivSave.setVisibility(View.GONE);
                updateUI(false);
                break;
            }
        }
    }
}
