package com.astrolightz.pocketbox.ui.calcPercent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.Utilities;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;

public class CalculatePercent extends Fragment
{
    // Vars
    MaterialAutoCompleteTextView et_j_calcPerc_num1;
    MaterialAutoCompleteTextView et_j_calcPerc_num2;
    MaterialButton btn_j_calcPerc_calc;
    MaterialTextView tv_j_calcPerc_percent;

    public static CalculatePercent newInstance() {
        return new CalculatePercent();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_percent, container, false);

        // Connect vars
        et_j_calcPerc_num1 = view.findViewById(R.id.et_v_calcPerc_num1);
        et_j_calcPerc_num2 = view.findViewById(R.id.et_v_calcPerc_num2);
        btn_j_calcPerc_calc = view.findViewById(R.id.btn_v_calcPerc_calc);
        tv_j_calcPerc_percent = view.findViewById(R.id.tv_v_calcPerc_percent);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup Calculate Button
        calcButton();

    }

    @SuppressLint("DefaultLocale")
    private void calcButton()
    {
        btn_j_calcPerc_calc.setOnClickListener(v -> {

            // Get vals
            String sNum1 = et_j_calcPerc_num1.getText().toString();
            String sNum2 = et_j_calcPerc_num2.getText().toString();

            // Checks
            if (!sNum1.isEmpty() && !sNum2.isEmpty())
            {
                // Calculate
                double num1 = Double.parseDouble(sNum1);
                double num2 = Double.parseDouble(sNum2);

                // Relative Percentage Change Formula: ( ( num2 - num1 ) / num1 ) * 100
                double percent = ((num2 - num1) / num1) * 100;

                // Display Result
                if (num1 < num2)
                {
                    tv_j_calcPerc_percent.setText(String.format("+%.02f%%", Math.abs(percent)));
                }
                else
                {
                    tv_j_calcPerc_percent.setText(String.format("-%.02f%%", Math.abs(percent)));
                }
            }
            else
            {
                Utilities.displayError(getView(), "Please fill in all fields.");
            }

        });
    }
}