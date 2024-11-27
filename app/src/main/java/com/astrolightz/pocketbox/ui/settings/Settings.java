package com.astrolightz.pocketbox.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.ui.calcTotal.CalculateTotalViewModel;

public class Settings extends Fragment
{
    // Vars

    private SettingsViewModel mViewModel;

    public static Settings newInstance() {
        return new Settings();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Connect Vars
        // TODO: Make Settings Page

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ViewModel
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    }