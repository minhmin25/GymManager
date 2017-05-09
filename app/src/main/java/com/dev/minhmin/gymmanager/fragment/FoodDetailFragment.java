package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.DataCenter;

/**
 * Created by Administrator on 5/6/2017.
 */

public class FoodDetailFragment extends Fragment {

    private ImageView iv_food, iv_back_left;
    private TextView tv_name, tv_calo, tv_pro, tv_fat, tv_carb, tv_number_unit;
    private Button btadd;

    public static FoodDetailFragment newInstance() {
        FoodDetailFragment fragment = new FoodDetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_food_detail, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        innit();
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        DataCenter dataCenter = new DataCenter();

        if (bundle != null) {
            String idFood = bundle.getString("idFood", "");
            if (!idFood.equals("")) {

                Food food = dataCenter.getFood(idFood);
                updateUI(food);


            }


        }
    }

    private void updateUI(Food food) {
//        iv_food.setImageResource(food.getImageUrl());
        tv_name.setText(food.getName());
        tv_calo.setText(food.getCalo() + " " + ConstantUtils.unitCalo);
        tv_pro.setText(food.getProtein() + " " + ConstantUtils.unitPro);
        tv_fat.setText(food.getFat() + " " + ConstantUtils.unitFat);
        tv_carb.setText(food.getCarb() + " " + ConstantUtils.unitCarb);
        tv_number_unit.setText(food.getUnit2() + " " + food.getUnit1());
    }

    private void innit() {
        iv_food = (ImageView) getView().findViewById(R.id.iv_food_detail);
        tv_name = (TextView) getView().findViewById(R.id.tv_name_food_detail);
        tv_calo = (TextView) getView().findViewById(R.id.tv_calories_food_detail);
        tv_pro = (TextView) getView().findViewById(R.id.tv_protein_food_detail);
        tv_fat = (TextView) getView().findViewById(R.id.tv_fat_food_detail);
        tv_carb = (TextView) getView().findViewById(R.id.tv_carb_food_detail);
        tv_number_unit = (TextView) getView().findViewById(R.id.tv_number_unit);
        btadd = (Button) getView().findViewById(R.id.bt_add_to_diary);


    }
}
