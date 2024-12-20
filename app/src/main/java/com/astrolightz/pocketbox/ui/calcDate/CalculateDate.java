package com.astrolightz.pocketbox.ui.calcDate;

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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textview.MaterialTextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculateDate extends Fragment
{
    // Vars
    MaterialTextView tv_j_calcDate_date1;
    MaterialTextView tv_j_calcDate_date2;
    MaterialTextView tv_j_calcDate_daysApart;
    MaterialButton btn_j_calcDate_calc;

    public static CalculateDate newInstance() {
        return new CalculateDate();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_date, container, false);

        // Connect vars
        tv_j_calcDate_date1 = view.findViewById(R.id.tv_v_calcDate_date1);
        tv_j_calcDate_date2 = view.findViewById(R.id.tv_v_calcDate_date2);
        tv_j_calcDate_daysApart = view.findViewById(R.id.tv_v_calcDate_daysApart);
        btn_j_calcDate_calc = view.findViewById(R.id.btn_v_calcDate_calc);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup Date TVs
        tv_j_calcDate_date1.setOnClickListener(datePickerListener());
        tv_j_calcDate_date2.setOnClickListener(datePickerListener());

        // Setup Calculate Button
        calcButton();

    }

    // Button
    @SuppressLint("DefaultLocale")
    private void calcButton()
    {
        btn_j_calcDate_calc.setOnClickListener(v -> {

            // Get dates
            String date1 = tv_j_calcDate_date1.getText().toString();
            String date2 = tv_j_calcDate_date2.getText().toString();

            // Check if dates are set
            if (date1.isEmpty() || date2.isEmpty())
            {
                Utilities.displayError(getView(), "Please select both dates");
            }

            else
            {
                // Calculate days apart
                long daysApart = Math.abs(Utilities.calculateDaysApart(date1, date2, ""));

                // Display
                String day = daysApart == 1 ? "Day" : "Days";
                String result = String.format("%d %s Apart", daysApart, day);

                // Adjust font size based on length
                Utilities.adjustFontSize(result, tv_j_calcDate_daysApart);

                tv_j_calcDate_daysApart.setText(result);
            }

        });
    }

    // Date Picker
    private View.OnClickListener datePickerListener()
    {
        return v -> {

            // Setup Date Picker Dialog
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();

            datePicker.show(getParentFragmentManager(), datePicker.toString());

            // Set Date TV
            datePicker.addOnPositiveButtonClickListener(s -> {

                // Get date name
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.DEFAULT_DATE_FORMAT);
                String date = LocalDate.ofEpochDay(s / (24 * 60 * 60 * 1000)).format(formatter);

                // Set date to TV
                if (v.getId() == R.id.tv_v_calcDate_date1)
                {
                    tv_j_calcDate_date1.setText(date);
                }
                else if (v.getId() == R.id.tv_v_calcDate_date2)
                {
                    tv_j_calcDate_date2.setText(date);
                }

            });

        };
    }

}