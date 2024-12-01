package com.astrolightz.pocketbox.ui.about;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrolightz.pocketbox.BuildConfig;
import com.astrolightz.pocketbox.LicenseAdapter;
import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.Utilities;
import com.google.android.material.textview.MaterialTextView;

public class About extends Fragment
{
    // Vars
    MaterialTextView tv_j_about_version;
    MaterialTextView tv_j_about_appLicense;
    RecyclerView rv_j_about_partyLicenses;

    private LinearLayoutManager layoutManager;
    private LicenseAdapter adapter;

    private AboutViewModel mViewModel;

    public static About newInstance() {
        return new About();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Connect Vars
        tv_j_about_version = view.findViewById(R.id.tv_v_about_version);
        tv_j_about_appLicense = view.findViewById(R.id.tv_v_about_appLicense);
        rv_j_about_partyLicenses = view.findViewById(R.id.rv_v_about_partyLicenses);

        // Setup version
        tv_j_about_version.setText(this.getString(R.string.version_summary, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

        // Setup app license touch listener
        tv_j_about_appLicense.setOnClickListener(v -> {

            // Show app license
            Utilities.showFullLicense(getContext(), -1);
        });

        // Setup layout to not scroll
        layoutManager = new LinearLayoutManager(getContext())
        {
            @Override
            public boolean canScrollVertically()
            {
                return false;
            }
        };

        // Setup RecyclerView
        adapter = new LicenseAdapter(getContext(), Utilities.licenses, position -> {

            // Show full license when item is clicked
            Utilities.showFullLicense(getContext(), position);

        });
        rv_j_about_partyLicenses.setLayoutManager(layoutManager);
        rv_j_about_partyLicenses.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ViewModel
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}