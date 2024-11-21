package com.astrolightz.pocketbox.ui.convTemp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrolightz.pocketbox.R;

public class ConvertTemperature extends Fragment {

    private ConvertTemperatureViewModel mViewModel;

    public static ConvertTemperature newInstance() {
        return new ConvertTemperature();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_convert_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ViewModel
        mViewModel = new ViewModelProvider(this).get(ConvertTemperatureViewModel.class);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}