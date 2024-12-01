package com.astrolightz.pocketbox.ui.calcTotal;

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
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;

public class CalculateTotal extends Fragment
{
    // Vars
    MaterialAutoCompleteTextView et_j_calcTotal_subtotal;
    MaterialAutoCompleteTextView et_j_calcTotal_tax;
    MaterialButton btn_j_calcTotal_calc;
    MaterialTextView tv_j_calcTotal_total;

    public static CalculateTotal newInstance() {
        return new CalculateTotal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_total, container, false);

        // Connect Vars
        et_j_calcTotal_subtotal = view.findViewById(R.id.et_v_calcTotal_subtotal);
        et_j_calcTotal_tax = view.findViewById(R.id.et_v_calcTotal_tax);
        btn_j_calcTotal_calc = view.findViewById(R.id.btn_v_calcTotal_calc);
        tv_j_calcTotal_total = view.findViewById(R.id.tv_v_calcTotal_total);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ETs
        et_j_calcTotal_subtotal.setFilters(new InputFilter[] {new DecimalInputFilter(10, 2)});
        et_j_calcTotal_tax.setFilters(new InputFilter[] {new DecimalInputFilter(3, 2)});

        // Setup Calc Button
        calcButton();

    }

    @SuppressLint("DefaultLocale")
    private void calcButton()
    {
        btn_j_calcTotal_calc.setOnClickListener(v -> {

            String sSubtotal = et_j_calcTotal_subtotal.getText().toString();
            String sTax = et_j_calcTotal_tax.getText().toString();

            // Checks
            if (!sSubtotal.isEmpty() && !sTax.isEmpty())
            {
                // Get vals
                double subtotal = Double.parseDouble(sSubtotal);
                double tax = Double.parseDouble(sTax);

                // Calculate total
                double total = Utilities.roundTo(subtotal + (subtotal * (tax / 100)), 2);
                String sTotal = String.format("$%.2f", total);

                // Adjust font size based on length
                Utilities.adjustFontSize(sTotal, tv_j_calcTotal_total);

                // Display answer
                tv_j_calcTotal_total.setText(sTotal);
            }
            else
            {
                Utilities.displayError(getView(), "Please fill in all fields.");
            }

        });
    }
}