package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ListFoodAdapter;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/6/2017.
 */

public class ListFoodFragment extends Fragment {
    ArrayAdapter<String> adapterspin; //tạo vector adapter để truyền vào spinner
    String spinmeal[] = {ConstantUtils.Breakfast, ConstantUtils.Lunch, ConstantUtils.Dinner, ConstantUtils.Snack};
    private RelativeLayout layout_add;
    private int number;
    private ArrayList<Food> listFoods = new ArrayList<Food>();
    private Button btcan, btadd;
    private ImageView iv_plus, iv_sub, iv_food;
    private TextView tv_name, tv_number_food, tv_number_calo, tv_don_vi_food, tv_detail_don_vi_food;
    private Spinner spinner;
    private int[] image = new int[]{R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4};
    private ListView lvFood;
    private ListFoodAdapter adapter;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();

    public static ListFoodFragment newInstance() {
        ListFoodFragment fragment = new ListFoodFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_listfood, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

        lvFood = (ListView) getView().findViewById(R.id.lv_list_food);
        adapter = new ListFoodAdapter(getActivity(), listFoods);
        lvFood.setAdapter(adapter);
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = listFoods.get(position);
                layout_add.setEnabled(true);
                tv_name.setText(food.getName());
                tv_number_calo.setText(food.getCalo() + " Calo");
                tv_don_vi_food.setText("x" + food.getUnit2() + " " + food.getUnit1());
                number = Integer.parseInt(tv_number_food.getText().toString());
                StorageReference mref = sref.child("food/" + listFoods.get(position).getImageUrl());
                Glide.with(getActivity())
                        .using(new FirebaseImageLoader())
                        .load(mref)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(iv_food);
                iv_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        number++;
                        tv_number_food.setText(number + "");
                    }
                });
                iv_sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (number > 1) {
                            number = number - 1;
                        } else {
                            number = 1;
                        }
                        tv_number_food.setText(number + "");

                    }
                });
                btadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                btcan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_add.setEnabled(false);
                    }
                });


            }
        });


    }

    private void init() {

        layout_add = (RelativeLayout) getView().findViewById(R.id.relayout_add_food);
        iv_food = (ImageView) getView().findViewById(R.id.iv_food_list_detail);
        iv_plus = (ImageView) getView().findViewById(R.id.iv_plus);
        iv_sub = (ImageView) getView().findViewById(R.id.iv_sub);
        tv_name = (TextView) getView().findViewById(R.id.tv_name_food_list_detail);
        tv_number_calo = (TextView) getView().findViewById(R.id.tv_number_calo_food);
        tv_number_food = (TextView) getView().findViewById(R.id.tv_number_food);
        tv_don_vi_food = (TextView) getView().findViewById(R.id.tv_don_vi_food);
        tv_detail_don_vi_food = (TextView) getView().findViewById(R.id.tv_detail_don_vi_food);
        spinner = (Spinner) getView().findViewById(R.id.spin_meal);
        adapterspin = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinmeal);
        adapterspin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapterspin);


    }


}
