package com.astrolightz.pocketbox;

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

import com.astrolightz.pocketbox.ui.calcDate.CalculateDate;
import com.astrolightz.pocketbox.ui.calcPercent.CalculatePercent;
import com.astrolightz.pocketbox.ui.calcTip.CalculateTip;
import com.astrolightz.pocketbox.ui.calcTotal.CalculateTotal;
import com.astrolightz.pocketbox.ui.convTemp.ConvertTemperature;
import com.astrolightz.pocketbox.ui.home.Home;
import com.astrolightz.pocketbox.ui.numName.NumberName;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    // Vars


    // Activity Vars
    Toolbar tb_j_toolbar;
    DrawerLayout dl_j_drawer;
    ActionBarDrawerToggle dt_j_toggle;
    NavigationView nv_j_navView;
    TextView tv_j_appVersion;


    // Fragment Vars

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

        // Setup Navigation View
        nv_j_navView = findViewById(R.id.nv_v_navView);
        nv_j_navView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            // Get which fragment to load
            if (id == R.id.nav_home)
            {
                // Load home
                tb_j_toolbar.setTitle("PocketBox");
                loadFragment(new Home());
            }
            else if (id == R.id.nav_calcTotal)
            {
                // Load Calc Total
                tb_j_toolbar.setTitle("Calculate Total");
                loadFragment(new CalculateTotal());
            }
            else if (id == R.id.nav_calcTip)
            {
                // Load Calc Tip
                tb_j_toolbar.setTitle("Calculate Tip");
                loadFragment(new CalculateTip());
            }
            else if (id == R.id.nav_convTemp)
            {
                // Load Temp Converter
                tb_j_toolbar.setTitle("Convert Temperature");
                loadFragment(new ConvertTemperature());
            }
            else if (id == R.id.nav_numFormat)
            {
                // Load Number Format
                tb_j_toolbar.setTitle("Number Formatter");
                loadFragment(new NumberName());
            }
            else if (id == R.id.nav_daysApart)
            {
                // Load Days Apart
                tb_j_toolbar.setTitle("Days Apart");
                loadFragment(new CalculateDate());
            }
            else if (id == R.id.nav_percChange)
            {
                // Load Percentage Change
                tb_j_toolbar.setTitle("Percentage Change");
                loadFragment(new CalculatePercent());
            }
            else if (id == R.id.nav_settings)
            {
                // Load settings
            }

            // TODO: Load fragments

            // Close drawer
            dl_j_drawer.closeDrawer(GravityCompat.START);
            return true;

        });

        // Setup app version in Nav view
        tv_j_appVersion = findViewById(R.id.tv_v_appVersion);
        tv_j_appVersion.setText("v" + BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")");

        // Load home fragment by default
        loadFragment(new Home());

    }

    // Load a fragment
    private void loadFragment(Fragment f)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_v_fragentDisplay, f);
        ft.commit();
    }

}