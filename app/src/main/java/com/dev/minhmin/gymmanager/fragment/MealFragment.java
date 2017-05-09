package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.DataCenter;
import com.dev.minhmin.gymmanager.utils.MethodUtils;

import java.util.Date;

/**
 * Created by Minh min on 4/19/2017.
 */

public class MealFragment extends Fragment {
    private static String time = "";

    private ImageView iv_breakfast, iv_lun, iv_din, iv_snack, iv_listfood, iv_back_left, iv_back_right;
    private TextView tv_break, tv_lun, tv_din, tv_snack, tv_listfood, tv_calo, tv_pro, tv_carb, tv_fat, tv_total_break, tv_total_lun, tv_total_din, tv_total_snack, tv_date;
    private String date;
    private LinearLayout layout_break, layout_lunch, layout_din, layout_snack, layout_listfood;


    public static MealFragment newInstance() {
        MealFragment fragment = new MealFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_meal, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        final MethodUtils methodUtils = new MethodUtils();
        layout_break.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typeMeal", ConstantUtils.Breakfast);
                bundle.putString("date", time);
                MealDetailFragment fragment = new MealDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);


            }
        });
        layout_din.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typeMeal", ConstantUtils.Dinner);
                bundle.putString("date", time);
                MealDetailFragment fragment = new MealDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });
        layout_listfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("date", time);
                ListFoodFragment fragment = new ListFoodFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });
        layout_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typeMeal", ConstantUtils.Lunch);
                bundle.putString("date", time);
                MealDetailFragment fragment = new MealDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });
        layout_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typeMeal", ConstantUtils.Snack);
                bundle.putString("date", time);
                MealDetailFragment fragment = new MealDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });

        iv_back_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCenter dataCenter = new DataCenter();
                time = methodUtils.UpDownDay(time, -1);
                tv_date.setText(time);

                Meal mealBreak = dataCenter.getMeal(time, ConstantUtils.Breakfast);
                Meal mealLunch = dataCenter.getMeal(time, ConstantUtils.Lunch);
                Meal mealDin = dataCenter.getMeal(time, ConstantUtils.Dinner);
                Meal mealSnack = dataCenter.getMeal(time, ConstantUtils.Snack);
                tv_total_break.setText(mealBreak.getTotalCalo() + "calories");
                tv_total_lun.setText(mealLunch.getTotalCalo() + "calories");
                tv_total_din.setText(mealDin.getTotalCalo() + "calories");
                tv_total_snack.setText(mealSnack.getTotalCalo() + "calories");
                float totalCalo = mealBreak.getTotalCalo() + mealLunch.getTotalCalo() + mealDin.getTotalCalo() + mealSnack.getTotalCalo();
                float totalPro = mealBreak.getTotalPro() + mealLunch.getTotalPro() + mealDin.getTotalPro() + mealSnack.getTotalPro();
                float totalFat = mealBreak.getTotalFat() + mealLunch.getTotalFat() + mealDin.getTotalFat() + mealSnack.getTotalFat();
                float totalCab = mealBreak.getTotalCarb() + mealLunch.getTotalCarb() + mealDin.getTotalCarb() + mealSnack.getTotalCarb();
                tv_calo.setText(totalCalo + " " + ConstantUtils.unitCalo);
                tv_fat.setText(totalFat + " " + ConstantUtils.unitFat);
                tv_pro.setText(totalPro + " " + ConstantUtils.unitPro);
                tv_carb.setText(totalPro + " " + ConstantUtils.unitCarb);

            }
        });
        iv_back_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCenter dataCenter = new DataCenter();
                time = methodUtils.UpDownDay(time, 1);
                tv_date.setText(time);
                Meal mealBreak = dataCenter.getMeal(time, ConstantUtils.Breakfast);
                Meal mealLunch = dataCenter.getMeal(time, ConstantUtils.Lunch);
                Meal mealDin = dataCenter.getMeal(time, ConstantUtils.Dinner);
                Meal mealSnack = dataCenter.getMeal(time, ConstantUtils.Snack);
                tv_total_break.setText(mealBreak.getTotalCalo() + "calories");
                tv_total_lun.setText(mealLunch.getTotalCalo() + "calories");
                tv_total_din.setText(mealDin.getTotalCalo() + "calories");
                tv_total_snack.setText(mealSnack.getTotalCalo() + "calories");
                float totalCalo = mealBreak.getTotalCalo() + mealLunch.getTotalCalo() + mealDin.getTotalCalo() + mealSnack.getTotalCalo();
                float totalPro = mealBreak.getTotalPro() + mealLunch.getTotalPro() + mealDin.getTotalPro() + mealSnack.getTotalPro();
                float totalFat = mealBreak.getTotalFat() + mealLunch.getTotalFat() + mealDin.getTotalFat() + mealSnack.getTotalFat();
                float totalCab = mealBreak.getTotalCarb() + mealLunch.getTotalCarb() + mealDin.getTotalCarb() + mealSnack.getTotalCarb();
                tv_calo.setText(totalCalo + " " + ConstantUtils.unitCalo);
                tv_fat.setText(totalFat + " " + ConstantUtils.unitFat);
                tv_pro.setText(totalPro + " " + ConstantUtils.unitPro);
                tv_carb.setText(totalPro + " " + ConstantUtils.unitCarb);
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
        DataCenter dataCenter = new DataCenter();
        Bundle bundle = this.getArguments();
        MethodUtils methodUtils = new MethodUtils();

        if (bundle != null) {
            String date = bundle.getString("date", "");

            if (date.equals("")) {
                tv_date.setText("Today");
                time = methodUtils.getTimeNow();


            } else {
                tv_date.setText(date);
                time = date;
            }
            Meal mealBreak = dataCenter.getMeal(time, ConstantUtils.Breakfast);
            Meal mealLunch = dataCenter.getMeal(time, ConstantUtils.Lunch);
            Meal mealDin = dataCenter.getMeal(time, ConstantUtils.Dinner);
            Meal mealSnack = dataCenter.getMeal(time, ConstantUtils.Snack);
            tv_total_break.setText(mealBreak.getTotalCalo() + "calories");
            tv_total_lun.setText(mealLunch.getTotalCalo() + "calories");
            tv_total_din.setText(mealDin.getTotalCalo() + "calories");
            tv_total_snack.setText(mealSnack.getTotalCalo() + "calories");
            float totalCalo = mealBreak.getTotalCalo() + mealLunch.getTotalCalo() + mealDin.getTotalCalo() + mealSnack.getTotalCalo();
            float totalPro = mealBreak.getTotalPro() + mealLunch.getTotalPro() + mealDin.getTotalPro() + mealSnack.getTotalPro();
            float totalFat = mealBreak.getTotalFat() + mealLunch.getTotalFat() + mealDin.getTotalFat() + mealSnack.getTotalFat();
            float totalCab = mealBreak.getTotalCarb() + mealLunch.getTotalCarb() + mealDin.getTotalCarb() + mealSnack.getTotalCarb();
            tv_calo.setText(totalCalo + " " + ConstantUtils.unitCalo);
            tv_fat.setText(totalFat + " " + ConstantUtils.unitFat);
            tv_pro.setText(totalPro + " " + ConstantUtils.unitPro);
            tv_carb.setText(totalPro + " " + ConstantUtils.unitCarb);

        }
    }

    private void init() {
        iv_back_left = (ImageView) getView().findViewById(R.id.iv_back_left);
        iv_back_right = (ImageView) getView().findViewById(R.id.iv_back_right);
        iv_breakfast = (ImageView) getView().findViewById(R.id.iv_breakfast);
        iv_lun = (ImageView) getView().findViewById(R.id.iv_lunch);
        iv_din = (ImageView) getView().findViewById(R.id.iv_dinner);
        iv_snack = (ImageView) getView().findViewById(R.id.iv_snack);
        iv_listfood = (ImageView) getView().findViewById(R.id.iv_list_food);
        tv_break = (TextView) getView().findViewById(R.id.tv_breakfast);
        tv_lun = (TextView) getView().findViewById(R.id.tv_lunch);
        tv_din = (TextView) getView().findViewById(R.id.tv_dinner);
        tv_snack = (TextView) getView().findViewById(R.id.tv_snack);
        tv_listfood = (TextView) getView().findViewById(R.id.tv_list_food);
        tv_total_break = (TextView) getView().findViewById(R.id.tv_total_breakfast);
        tv_total_lun = (TextView) getView().findViewById(R.id.tv_total_lunch);
        tv_total_din = (TextView) getView().findViewById(R.id.tv_total_dinner);
        tv_total_snack = (TextView) getView().findViewById(R.id.tv_total_snack);
        tv_calo = (TextView) getView().findViewById(R.id.tv_calories);
        tv_pro = (TextView) getView().findViewById(R.id.tv_protein);
        tv_carb = (TextView) getView().findViewById(R.id.tv_cab);
        tv_fat = (TextView) getView().findViewById(R.id.tv_fat);
        tv_date = (TextView) getView().findViewById(R.id.tv_date);
        layout_break = (LinearLayout) getView().findViewById(R.id.layout_breakfast);
        layout_lunch = (LinearLayout) getView().findViewById(R.id.layout_lunch);
        layout_din = (LinearLayout) getView().findViewById(R.id.layout_dinner);
        layout_snack = (LinearLayout) getView().findViewById(R.id.layout_snack);


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }

}
