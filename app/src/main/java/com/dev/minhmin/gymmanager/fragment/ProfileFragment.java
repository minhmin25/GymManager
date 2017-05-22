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

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Minh min on 4/19/2017.
 */

public class ProfileFragment extends Fragment {

    private TextView tvName, tvNameContent, tvAge, tvEmail, tvGold, tvHeight, tvWeight;
    private EditText edtNameContent, edtAge, edtEmail, edtHeight, edtWeight;
    private CircleImageView ivImage;
    private ImageView ivEdit, ivSave;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;

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
        findViewById();
        return viewGroup;
    }

    public void findViewById() {
        tvName = (TextView) getView().findViewById(R.id.tv_profile_name);
        tvNameContent = (TextView) getView().findViewById(R.id.tv_profile_name_content);
        tvAge = (TextView) getView().findViewById(R.id.tv_profile_age);
        tvEmail = (TextView) getView().findViewById(R.id.tv_profile_email);
        tvGold = (TextView) getView().findViewById(R.id.tv_profile_gold);
        tvHeight = (TextView) getView().findViewById(R.id.tv_profile_height);
        tvWeight = (TextView) getView().findViewById(R.id.tv_profile_weight);
        edtNameContent = (EditText) getView().findViewById(R.id.edt_profile_name_content);
        edtAge = (EditText) getView().findViewById(R.id.edt_profile_age);
        edtEmail = (EditText) getView().findViewById(R.id.edt_profile_email);
        edtHeight = (EditText) getView().findViewById(R.id.edt_profile_height);
        edtWeight = (EditText) getView().findViewById(R.id.edt_profile_weight);
        ivImage = (CircleImageView) getView().findViewById(R.id.iv_profile_image);
        ivEdit = (ImageView) getView().findViewById(R.id.iv_profile_edit);
        ivSave = (ImageView) getView().findViewById(R.id.iv_profile_save);
        rgGender = (RadioGroup) getView().findViewById(R.id.rg_profile_gender);
        rbMale = (RadioButton) getView().findViewById(R.id.rb_profile_male);
        rbFemale = (RadioButton) getView().findViewById(R.id.rb_profile_female);
    }

    public void updateUI(boolean isEdit) {
        if (isEdit) {
            edtNameContent.setText(tvNameContent.getText());
            edtEmail.setText(tvEmail.getText());
            edtAge.setText(tvAge.getText());
            edtHeight.setText(tvHeight.getText());
            edtWeight.setText(tvWeight.getText());
            rgGender.setClickable(true);
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
        } else {

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
}
