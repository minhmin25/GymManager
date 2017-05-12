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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.fragment.HomeFragment;
import com.dev.minhmin.gymmanager.fragment.MealFragment;
import com.dev.minhmin.gymmanager.fragment.ProfileFragment;
import com.dev.minhmin.gymmanager.fragment.WorkoutFragment;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup bottomBar;
    private RadioButton rbHome, rbWorkout, rbMeal, rbExercise, rbProfile;
    private TextView tvTitleActionbar;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View customActionbar = LayoutInflater.from(this).inflate(R.layout.action_bar_layout, null);
        getSupportActionBar().setCustomView(customActionbar);
        tvTitleActionbar = (TextView) customActionbar.findViewById(R.id.tv_title_actionbar);
        findViewByID();
        bottomBar.setOnCheckedChangeListener(this);
        Fragment fragment = new HomeFragment().newInstance();
        replaceFragment(fragment);

    }

    private void findViewByID() {
        bottomBar = (RadioGroup) findViewById(R.id.bottom_bar);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbWorkout = (RadioButton) findViewById(R.id.rb_workout);
        rbMeal = (RadioButton) findViewById(R.id.rb_meal);
        rbExercise = (RadioButton) findViewById(R.id.rb_exercise);
        rbProfile = (RadioButton) findViewById(R.id.rb_profile);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
                tvTitleActionbar.setText(ConstantUtils.TITLE_HOME);
                Fragment fragment = new HomeFragment().newInstance();
                replaceFragment(fragment);
                break;
            }
            case R.id.rb_workout: {
                if (page == 2) break;
                page = 2;
                tvTitleActionbar.setText(ConstantUtils.TTLE_WORKOUT);
                Fragment fragment = new WorkoutFragment().newInstance();
                replaceFragment(fragment);
                break;
            }
            case R.id.rb_meal: {
                if (page == 3) break;
                page = 3;
                tvTitleActionbar.setText(ConstantUtils.TITLE_MEAL);
                Fragment fragment = new MealFragment().newInstance();
                replaceFragment(fragment);
                break;
            }
            case R.id.rb_exercise: {
                if (page == 4) break;
                page = 4;
                tvTitleActionbar.setText(ConstantUtils.TITLE_EXERCISE);
                break;
            }
            case R.id.rb_profile: {
                if (page == 5) break;
                page = 5;
                tvTitleActionbar.setText(ConstantUtils.TITLE_PROFILE);
                Fragment fragment = new ProfileFragment().newInstance();
                replaceFragment(fragment);
                break;
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }
}
