package com.astrolightz.pocketbox;

import static androidx.navigation.Navigation.findNavController;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.splashscreen.SplashScreen;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    // Activity Vars
    Toolbar tb_j_toolbar;
    DrawerLayout dl_j_drawer;
    ActionBarDrawerToggle dt_j_toggle;
    NavigationView nv_j_navView;
    TextView tv_j_appVersion;
    AppBarConfiguration ab_j_config;
    NavController navController;

    private SplashScreen splashScreen;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        splashScreen = SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);

        // Setup toolbar
        tb_j_toolbar = findViewById(R.id.tb_v_toolbar);
        setSupportActionBar(tb_j_toolbar);

        // Setup Drawer
        dl_j_drawer = findViewById(R.id.dl_v_drawer);
        dt_j_toggle = new ActionBarDrawerToggle(this, dl_j_drawer, tb_j_toolbar, R.string.nav_open, R.string.nav_close);
        dl_j_drawer.addDrawerListener(dt_j_toggle);
        dt_j_toggle.syncState();

        // Setup Navigation
        nv_j_navView = findViewById(R.id.nv_v_navView);
        navController = findNavController(this, R.id.nc_v_navHostFragment);
        ab_j_config = new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(dl_j_drawer).build();
        NavigationUI.setupActionBarWithNavController(this, navController, ab_j_config);
        NavigationUI.setupWithNavController(nv_j_navView, navController);

        // Setup app version in Nav view
        tv_j_appVersion = findViewById(R.id.tv_v_appVersion);
        tv_j_appVersion.setText(this.getString(R.string.version_summary, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));


        navController.addOnDestinationChangedListener((controller, destination, arguments) ->
        {
            // Update Toolbar button based on what fragment is loaded
            if (destination.getId() == R.id.nav_home)
            {
                // Show drawer button
                dt_j_toggle.setDrawerIndicatorEnabled(true);
                dl_j_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
            else
            {
                // Show Up button
                dt_j_toggle.setDrawerIndicatorEnabled(false);
                dl_j_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(0);
                dt_j_toggle.setToolbarNavigationClickListener(v -> onSupportNavigateUp());
            }

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, ab_j_config)
                || super.onSupportNavigateUp();
    }
}