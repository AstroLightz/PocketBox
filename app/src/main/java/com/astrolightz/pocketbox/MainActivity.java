package com.astrolightz.pocketbox;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
            }
            else if (id == R.id.nav_calcTotal)
            {
                // Load Calc Total
            }
            else if (id == R.id.nav_calcTip)
            {
                // Load Calc Tip
            }

            // TODO: Make fragments, and load them here

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
        //tv_j_appVersion.setText("v" + BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")");
        tv_j_appVersion.setText("Test");

    }
}