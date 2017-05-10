package com.dev.minhmin.gymmanager.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.adapter.ListFoodAdapter;
import com.dev.minhmin.gymmanager.adapter.MealDetailAdapter;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.model.LineItem;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.DataCenter;
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

public class ListFoodFragment extends Fragment {
    private DataCenter dataCenter;
    private Food food = new Food();
    private TextView tv_title;
    private Meal meal = new Meal();
    ArrayAdapter<String> adapterspin; //tạo vector adapter để truyền vào spinner
    String spinmeal[] = {ConstantUtils.Breakfast, ConstantUtils.Lunch, ConstantUtils.Dinner, ConstantUtils.Snack};
    private RelativeLayout layout_add;
    private ImageView iv_add_food;
    private String typeMeal = ConstantUtils.Breakfast;
    private String date = "";
    private ArrayList<Food> listFoods = new ArrayList<>();
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
        tv_title = (TextView) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.tv_title_actionbar);
        tv_title.setText(ConstantUtils.TITLE_ListFood);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Food").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listFoods.clear();
                ArrayList<Food> listdata = new ArrayList<>();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Food m = i.getValue(Food.class);
                    listdata.add(m);
                }
                listFoods.addAll(listdata);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new ListFoodAdapter(getActivity(), listFoods);
        lvFood.setAdapter(adapter);

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                food = listFoods.get(position);
                layout_add.setVisibility(View.VISIBLE);
                tv_name.setText(listFoods.get(position).getName() + "");
                tv_number_calo.setText(listFoods.get(position).getCalo() + " " + ConstantUtils.unitCalo);
                tv_number_food.setText("1");
                tv_don_vi_food.setText(" x" + listFoods.get(position).getCount() + " " + listFoods.get(position).getUnit());
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
                        int number = Integer.parseInt(tv_number_food.getText().toString());
                        number++;
                        tv_number_food.setText(number + "");
                    }
                });
                iv_sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int number = Integer.parseInt(tv_number_food.getText().toString());
                        if (number > 1) {
                            number = number - 1;
                        } else {
                            number = 1;
                        }
                        tv_number_food.setText(number + "");
                    }
                });
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        typeMeal = spinmeal[position].toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                btadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("ahihi", "btađ");
                        Bundle bundle = new Bundle();
                        bundle.putString("typeMeal", typeMeal);
                        bundle.putString("date", date);
                        bundle.putString("idFood", listFoods.get(position).getId());
                        bundle.putString("number", tv_number_food.getText().toString());
                        MealDetailFragment fragment = new MealDetailFragment();
                        fragment.setArguments(bundle);
                        replaceFragment(fragment);
                    }
                });
                btcan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_add.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //   ActionBar actionBar =((MainActivity)context).getSupportActionBar();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        date = bundle.getString("date", "");
        typeMeal = bundle.getString("typeMeal", "");
        if (typeMeal.equals("")) {
            typeMeal = ConstantUtils.Breakfast;
        }

        Toast.makeText(getActivity(), "ListFood", Toast.LENGTH_LONG).show();

    }

    private void init() {

        listFoods = new ArrayList<>();
        tv_title = (TextView) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.tv_title_actionbar);
        layout_add = (RelativeLayout) getView().findViewById(R.id.relayout_add_food);
        iv_food = (ImageView) getView().findViewById(R.id.iv_food_list_detail);
        iv_plus = (ImageView) getView().findViewById(R.id.iv_plus);
        iv_sub = (ImageView) getView().findViewById(R.id.iv_sub);
        tv_name = (TextView) getView().findViewById(R.id.tv_name_food_list_detail);
        tv_number_calo = (TextView) getView().findViewById(R.id.tv_number_calo_food);
        tv_number_food = (TextView) getView().findViewById(R.id.tv_number_food_fragment);
        tv_don_vi_food = (TextView) getView().findViewById(R.id.tv_don_vi_food);
        tv_detail_don_vi_food = (TextView) getView().findViewById(R.id.tv_detail_don_vi_food);
        btcan = (Button) getView().findViewById(R.id.bt_cancel);
        btadd = (Button) getView().findViewById(R.id.bt_add);


        lvFood = (ListView) getView().findViewById(R.id.lv_list_food);


        spinner = (Spinner) getView().findViewById(R.id.spin_meal);
        adapterspin = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinmeal);
        adapterspin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapterspin);


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }


}
