package com.astrolightz.pocketbox.ui.home;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrolightz.pocketbox.R;
import com.google.android.material.button.MaterialButton;

public class Home extends Fragment
{
    // Vars
    // Tool Buttons
    MaterialButton btn_j_hf_calcTotal;
    MaterialButton btn_j_hf_calcTip;
    MaterialButton btn_j_hf_convTemp;
    MaterialButton btn_j_hf_numFormat;
    MaterialButton btn_j_hf_daysApart;
    MaterialButton btn_j_hf_percChange;

    private NavController nvController;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get NavController
        nvController = findNavController(this);

        // Connect Vars
        btn_j_hf_calcTotal = view.findViewById(R.id.btn_v_hf_calcTotal);
        btn_j_hf_calcTip = view.findViewById(R.id.btn_v_hf_calcTip);
        btn_j_hf_convTemp = view.findViewById(R.id.btn_v_hf_convTemp);
        btn_j_hf_numFormat = view.findViewById(R.id.btn_v_hf_numFormat);
        btn_j_hf_daysApart = view.findViewById(R.id.btn_v_hf_daysApart);
        btn_j_hf_percChange = view.findViewById(R.id.btn_v_hf_percChange);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup Tool Buttons
        calcTotalButton();
        calcTipButton();
        convTempButton();
        numFormatButton();
        daysApartButton();
        percChangeButton();

    }

    // =============================================================================================
    //                                  TOOL BUTTON LISTENERS
    // =============================================================================================

    // Calc Total
    private void calcTotalButton()
    {
        btn_j_hf_calcTotal.setOnClickListener(v -> {

            // Navigate to Calc Total
            nvController.navigate(R.id.action_nav_home_to_nav_calcTotal);
        });
    }

    // Calc Tip
    private void calcTipButton()
    {
        btn_j_hf_calcTip.setOnClickListener(v -> {

            // Navigate to Calc Tip
            nvController.navigate(R.id.action_nav_home_to_nav_calcTip);
        });
    }

    // Conv Temp
    private void convTempButton()
    {
        btn_j_hf_convTemp.setOnClickListener(v -> {

            // Navigate to Conv Temp
            nvController.navigate(R.id.action_nav_home_to_nav_convTemp);
        });
    }

    // Num Format
    private void numFormatButton()
    {
        btn_j_hf_numFormat.setOnClickListener(v -> {

            // Navigate to Num Format
            nvController.navigate(R.id.action_nav_home_to_nav_numFormat);
        });
    }

    // Days Apart
    private void daysApartButton()
    {
        btn_j_hf_daysApart.setOnClickListener(v -> {

            // Navigate to Days Apart
            nvController.navigate(R.id.action_nav_home_to_nav_daysApart);
        });
    }

    // Perc Change
    private void percChangeButton()
    {
        btn_j_hf_percChange.setOnClickListener(v -> {

            // Navigate to Perc Change
            nvController.navigate(R.id.action_nav_home_to_nav_percChange);
        });
    }

}