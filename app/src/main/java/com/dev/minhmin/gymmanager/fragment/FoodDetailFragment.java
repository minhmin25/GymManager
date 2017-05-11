package com.dev.minhmin.gymmanager.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.MealDetailAdapter;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.DataCenter;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/6/2017.
 */

public class FoodDetailFragment extends Fragment {
    private Food f = new Food();
    private TextView tv_number_food_dialog;
    private ImageView iv_food, iv_back_left;
    private TextView tv_name, tv_calo, tv_pro, tv_fat, tv_carb, tv_number_unit;
    private Button btadd;
    private String date = "";
    private int number = 1;
    private String typeMeal = ConstantUtils.Breakfast;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();
    private TextView tv_title;

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
        tv_title = (TextView) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.tv_title_actionbar);
        tv_title.setText(ConstantUtils.TITLE_FoodDeail);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view_dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_food, null);
                ImageView iv_food, iv_plus, iv_sub;
                Spinner spinner;
                Button bt_add, bt_cancel;
                TextView tv_name, tv_number_calo, tv_don_vi_food;


                spinner = (Spinner) view_dialog.findViewById(R.id.spin_dialog_meal);
                ArrayAdapter<String> adapterspin;
                final String spinmeal[] = {ConstantUtils.Breakfast, ConstantUtils.Lunch, ConstantUtils.Dinner, ConstantUtils.Snack};
//
                adapterspin = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinmeal);
                adapterspin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner.setAdapter(adapterspin);
                tv_name = (TextView) view_dialog.findViewById(R.id.tv_dialog_name);
                bt_add = (Button) view_dialog.findViewById(R.id.bt_dialog_add);
                bt_cancel = (Button) view_dialog.findViewById(R.id.bt_dialog_cancel);
                tv_don_vi_food = (TextView) view_dialog.findViewById(R.id.tv_dialog_don_vi_food);
                tv_number_calo = (TextView) view_dialog.findViewById(R.id.tv_dialog_number_calo);
                tv_number_food_dialog = (TextView) view_dialog.findViewById(R.id.tv_dialog_number_food);
                tv_name.setText(f.getName());

                iv_food = (ImageView) view_dialog.findViewById(R.id.iv_dialog_food);
                iv_plus = (ImageView) view_dialog.findViewById(R.id.iv_dialog_plus);
                iv_sub = (ImageView) view_dialog.findViewById(R.id.iv_dialog_sub);

                tv_number_calo.setText(f.getCalo() + " Calo");
                tv_don_vi_food.setText("  " + "x" + f.getCount() + " " + f.getUnit());
                tv_number_food_dialog.setText(1 + "");
                StorageReference mref = sref.child("food/" + f.getImageUrl());
                Glide.with(getActivity())
                        .using(new FirebaseImageLoader())
                        .load(mref)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(iv_food);



                iv_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        number = Integer.parseInt(tv_number_food_dialog.getText().toString());
                        number++;
                        tv_number_food_dialog.setText(number + "");


                    }
                });

                iv_sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        number = Integer.parseInt(tv_number_food_dialog.getText().toString());
                        if (number > 1) {
                            number = number - 1;
                        } else {
                            number = 1;
                        }
                        tv_number_food_dialog.setText(number + "");


                    }
                });
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        typeMeal = spinmeal[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                builder.setView(view_dialog);

                final AlertDialog dialog = builder.create();
                dialog.show();
                bt_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MethodUtils methodUtils = new MethodUtils();
                        if (methodUtils.compareDate(date) == 1) {
                            Toast.makeText(getActivity(), "Ngày " + date + " đã qua, Bạn không thể thêm food", Toast.LENGTH_LONG).show();
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("typeMeal", typeMeal);
                            bundle.putString("date", methodUtils.getTimeNow());
                            bundle.putString("idFood", f.getId());
                            bundle.putString("number", number + "");
                            MealDetailFragment fragment = new MealDetailFragment();
                            fragment.setArguments(bundle);
                            replaceFragment(fragment);

                        }
                        dialog.cancel();


                    }
                });
                bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // tv_title.setText(ConstantUtils.TITLE_FoodDeail);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            final String idFood = bundle.getString("idFood", "");
            date = bundle.getString("date", "");
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("Food").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        if (i.getKey().equals(idFood)) {
                            f = i.getValue(Food.class);
                            updateUI(f);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }


    private void updateUI(Food food) {
        StorageReference mref = sref.child("food/" + food.getImageUrl());
        Glide.with(getActivity())
                .using(new FirebaseImageLoader())
                .load(mref)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(iv_food);
        //iv_food.setImageResource(food.getImageUrl());
        tv_name.setText(food.getName());
        tv_calo.setText(food.getCalo() + " " + ConstantUtils.unitCalo);
        tv_pro.setText(food.getProtein() + " " + ConstantUtils.unitPro);
        tv_fat.setText(food.getFat() + " " + ConstantUtils.unitFat);
        tv_carb.setText(food.getCarb() + " " + ConstantUtils.unitCarb);
        tv_number_unit.setText(food.getCount() + " " + food.getUnit());
    }

    private void innit() {
        //  tv_title=(TextView)((AppCompatActivity)getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.tv_title_actionbar);
        iv_food = (ImageView) getView().findViewById(R.id.iv_food_detail);
        tv_name = (TextView) getView().findViewById(R.id.tv_name_food_detail);
        tv_calo = (TextView) getView().findViewById(R.id.tv_calories_food_detail);
        tv_pro = (TextView) getView().findViewById(R.id.tv_protein_food_detail);
        tv_fat = (TextView) getView().findViewById(R.id.tv_fat_food_detail);
        tv_carb = (TextView) getView().findViewById(R.id.tv_carb_food_detail);
        tv_number_unit = (TextView) getView().findViewById(R.id.tv_number_unit);
        btadd = (Button) getView().findViewById(R.id.bt_add_to_diary);


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }
}
