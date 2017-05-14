package com.dev.minhmin.gymmanager.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.fragment.ExerciseFragment;
import com.dev.minhmin.gymmanager.fragment.HomeFragment;
import com.dev.minhmin.gymmanager.fragment.ListFoodFragment;
import com.dev.minhmin.gymmanager.fragment.MealDetailFragment;
import com.dev.minhmin.gymmanager.fragment.MealFragment;
import com.dev.minhmin.gymmanager.fragment.StatisticFragment;
import com.dev.minhmin.gymmanager.fragment.WorkoutFragment;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener, MealDetailFragment.OnAddFoodListener, ListFoodFragment.onAddNewFoodListener {

    private RadioGroup bottomBar;
    private RadioButton rbHome, rbWorkout, rbMeal, rbExercise, rbProfile;
    private TextView tvTitleActionbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ImageView ivBack, ivAdd;
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByID();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View customActionbar = LayoutInflater.from(this).inflate(R.layout.action_bar_layout, null);
        getSupportActionBar().setCustomView(customActionbar);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addNewFood()) {
                    addFood();
                }
            }
        });
        bottomBar.setOnCheckedChangeListener(this);
        Fragment fragment = new HomeFragment().newInstance();
        replaceFragment(fragment);
        navigationView.setCheckedItem(R.id.nav_home);
//        ArrayList<Exercise> list = new ArrayList<>();
//        ArrayList<String> imageUrl = new ArrayList<>();
//        imageUrl.add("crunch_ava.jpg");
//        imageUrl.add("crunch.jpg");
//
//        list.add(new Exercise("Crunch", imageUrl, "crunch.mp4", "content", 30, ""));
//        list.add(new Exercise("Crunch", imageUrl, "crunch.mp4", "content", 30, ""));
//        list.add(new Exercise("Crunch", imageUrl, "crunch.mp4", "content", 30, ""));
//        list.add(new Exercise("Crunch", imageUrl, "crunch.mp4", "content", 30, ""));
//        list.add(new Exercise("Crunch", imageUrl, "crunch.mp4", "content", 30, ""));
//        list.add(new Exercise("Crunch", imageUrl, "crunch.mp4", "content", 30, ""));
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        ref.child("Exercise").child("abs").setValue(list);
//        ref.child("Exercise").child("back").setValue(list);
//        ref.child("Exercise").child("biceps").setValue(list);
//        ref.child("Exercise").child("triceps").setValue(list);
//        ref.child("Exercise").child("chest").setValue(list);
//        ref.child("Exercise").child("shoulders").setValue(list);
//        ref.child("Exercise").child("legs").setValue(list);
    }

    private void findViewByID() {
        tvTitleActionbar = (TextView) findViewById(R.id.tv_title_actionbar);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        ivBack = (ImageView) findViewById(R.id.iv_actionbar_back);
        bottomBar = (RadioGroup) findViewById(R.id.bottom_bar);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbWorkout = (RadioButton) findViewById(R.id.rb_workout);
        rbMeal = (RadioButton) findViewById(R.id.rb_meal);
        rbExercise = (RadioButton) findViewById(R.id.rb_exercise);
        rbProfile = (RadioButton) findViewById(R.id.rb_statistic);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_workout) {

        } else if (id == R.id.nav_meal) {

        } else if (id == R.id.nav_exercise) {

        } else if (id == R.id.nav_statistic) {

        } else if (id == R.id.nav_group) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_home: {
                if (page == 1) break;
                page = 1;
                Fragment fragment = new HomeFragment().newInstance();
                replaceFragment(fragment);
                navigationView.setCheckedItem(R.id.nav_home);
                break;
            }
            case R.id.rb_workout: {
                if (page == 2) break;
                page = 2;
                Fragment fragment = new WorkoutFragment().newInstance();
                replaceFragment(fragment);
                navigationView.setCheckedItem(R.id.nav_workout);
                break;
            }
            case R.id.rb_meal: {
                if (page == 3) break;
                page = 3;
                Fragment fragment = new MealFragment().newInstance();
                replaceFragment(fragment);
                navigationView.setCheckedItem(R.id.nav_meal);
                break;
            }
            case R.id.rb_exercise: {
                if (page == 4) break;
                page = 4;
                Fragment fragment = new ExerciseFragment().newInstance();
                replaceFragment(fragment);
                navigationView.setCheckedItem(R.id.nav_exercise);
                break;
            }
            case R.id.rb_statistic: {
                if (page == 5) break;
                page = 5;
                Fragment fragment = new StatisticFragment().newInstance();
                replaceFragment(fragment);
                navigationView.setCheckedItem(R.id.nav_statistic);
                break;
            }
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }

    public void updateActionbar(String title, boolean isShowBack, boolean isShowAdd) {
        tvTitleActionbar.setText(title);
        if (isShowBack) {
            toggle.setDrawerIndicatorEnabled(false);
            ivBack.setVisibility(View.VISIBLE);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
            ivBack.setVisibility(View.GONE);
        }
        if (isShowAdd) {
            ivAdd.setVisibility(View.VISIBLE);
        } else {
            ivAdd.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void addFood() {
        MealDetailFragment fragment = (MealDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_MEAL_DETAIL);
        if (fragment != null) {
            fragment.transToListFoodFragment();
        }
    }

    @Override
    public boolean addNewFood() {
        ListFoodFragment fragment = (ListFoodFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_LIST_FOOD);
        if (fragment != null) {
            fragment.transToAddFoodFragment();
            return true;
        }
        return false;
    }
}
