package com.astrolightz.pocketbox;

import static androidx.navigation.Navigation.findNavController;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.astrolightz.pocketbox.ui.calcDate.CalculateDate;
import com.astrolightz.pocketbox.ui.calcPercent.CalculatePercent;
import com.astrolightz.pocketbox.ui.calcTip.CalculateTip;
import com.astrolightz.pocketbox.ui.calcTotal.CalculateTotal;
import com.astrolightz.pocketbox.ui.convTemp.ConvertTemperature;
import com.astrolightz.pocketbox.ui.home.Home;
import com.astrolightz.pocketbox.ui.numName.NumberName;
import com.astrolightz.pocketbox.ui.settings.Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    // Vars


    // Activity Vars
    Toolbar tb_j_toolbar;
    DrawerLayout dl_j_drawer;
    ActionBarDrawerToggle dt_j_toggle;
    NavigationView nv_j_navView;
    TextView tv_j_appVersion;
    AppBarConfiguration ab_j_config;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect vars


        // Setup toolbar
        tb_j_toolbar = findViewById(R.id.tb_v_toolbar);
        setSupportActionBar(tb_j_toolbar);

        // Setup Drawer
        dl_j_drawer = findViewById(R.id.dl_v_drawer);
        dt_j_toggle = new ActionBarDrawerToggle(this, dl_j_drawer, tb_j_toolbar, R.string.nav_open, R.string.nav_close);
        dl_j_drawer.addDrawerListener(dt_j_toggle);
        dt_j_toggle.syncState();

        // TODO: Fix the following bug:
        // Pressing tool in NavigationView, then pressing home goes to home fragment.
        // Pressing tool button in home fragment, then pressing home in the NavigationView
        // Does not go home.
        // Pressing a different tool in the NavigationView, then pressing home goes back
        // to the tool fragment button pressed instead of home.

        // Setup Navigation
        nv_j_navView = findViewById(R.id.nv_v_navView);
        ab_j_config = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_calcTotal, R.id.nav_calcTip, R.id.nav_convTemp,
                R.id.nav_numFormat, R.id.nav_daysApart, R.id.nav_percChange, R.id.nav_settings)
                .setOpenableLayout(dl_j_drawer)
                .build();
        navController = findNavController(this, R.id.nc_v_navHostFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, ab_j_config);
        NavigationUI.setupWithNavController(nv_j_navView, navController);

        // Setup app version in Nav view
        tv_j_appVersion = findViewById(R.id.tv_v_appVersion);
        tv_j_appVersion.setText("v" + BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")");

    }

    // Set toolbar title
    public static void setToolBarTitle(Context context, String title)
    {
        Objects.requireNonNull(((AppCompatActivity) context).getSupportActionBar()).setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nc_v_navHostFragment);
        return NavigationUI.navigateUp(navController, ab_j_config)
                || super.onSupportNavigateUp();
    }
}