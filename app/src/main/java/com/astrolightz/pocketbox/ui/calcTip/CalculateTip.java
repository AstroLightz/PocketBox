package com.astrolightz.pocketbox.ui.calcTip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astrolightz.pocketbox.DecimalInputFilter;
import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.Utilities;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;

public class CalculateTip extends Fragment
{
    // Vars
    MaterialTextView tv_j_calcTip_tipAmount;
    MaterialTextView tv_j_calcTip_tip;
    MaterialAutoCompleteTextView et_j_calcTip_total;
    MaterialButton btn_j_calcTip_calc;
    Slider sl_j_calcTip_tipPerc;

    public static CalculateTip newInstance() {
        return new CalculateTip();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_tip, container, false);

        // Connect Vars
        tv_j_calcTip_tipAmount = view.findViewById(R.id.tv_v_calcTip_tipAmount);
        tv_j_calcTip_tip = view.findViewById(R.id.tv_v_calcTip_tip);
        et_j_calcTip_total = view.findViewById(R.id.et_v_calcTip_total);
        btn_j_calcTip_calc = view.findViewById(R.id.btn_v_calcTip_calc);
        sl_j_calcTip_tipPerc = view.findViewById(R.id.sl_v_calcTip_tipPerc);

        // Setup ET
        et_j_calcTip_total.setFilters(new InputFilter[] {new DecimalInputFilter(10, 2)});

        // Setup Tip display
        tipSlider();

        // Setup Calc Button
        calcButton();

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void tipSlider()
    {
        sl_j_calcTip_tipPerc.addOnChangeListener((slider, value, fromUser) -> {

            // Update tip display when slider changes
            float sliderValue = sl_j_calcTip_tipPerc.getValue();
            tv_j_calcTip_tip.setText(String.format("Tip: %.0f%%", sliderValue));

        });
    }

    @SuppressLint("DefaultLocale")
    private void calcButton()
    {
        btn_j_calcTip_calc.setOnClickListener(view -> {

            if (!et_j_calcTip_total.getText().toString().isEmpty())
            {
                // Get vals
                double total = Double.parseDouble(et_j_calcTip_total.getText().toString());
                double tipPerc = sl_j_calcTip_tipPerc.getValue();

                // Calculate tip amount
                double tipAmount = Utilities.roundTo(total * (tipPerc / 100), 2);
                String sTipAmount = String.format("$%.2f", tipAmount);

                // Adjust font size based on length
                Utilities.adjustFontSize(sTipAmount, tv_j_calcTip_tipAmount);

                // Display tip amount
                tv_j_calcTip_tipAmount.setText(sTipAmount);

            }
            else
            {
                Utilities.displayError(getView(), "Please fill in total.");
            }

        });
    }
}