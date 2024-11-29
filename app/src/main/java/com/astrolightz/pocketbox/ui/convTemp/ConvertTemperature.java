package com.astrolightz.pocketbox.ui.convTemp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.TempConversion;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ConvertTemperature extends Fragment
{
    // Vars
    TextInputLayout til_j_convTemp_tempFromLayout;
    TextInputLayout til_j_convTemp_tempToLayout;
    MaterialAutoCompleteTextView et_j_convTemp_tempInput;
    MaterialAutoCompleteTextView sp_j_convTemp_tempFrom;
    MaterialAutoCompleteTextView sp_j_convTemp_tempTo;
    MaterialButton bnt_j_convTemp_conv;
    MaterialTextView tv_j_convTemp_temp;

    // Temps
    private final String[] tempTypes = {"Fahrenheit", "Celsius", "Kelvin"};
    private ArrayAdapter<String> adapterTo;
    private ArrayAdapter<String> adapterFrom;

    private static final List<String> errorMsgs = List.of(
            "Please choose a type",
            "Type must be different"
    );

    private ConvertTemperatureViewModel mViewModel;

    public static ConvertTemperature newInstance() {
        return new ConvertTemperature();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_temperature, container, false);

        // Connect Vars
        til_j_convTemp_tempFromLayout = view.findViewById(R.id.til_v_convTemp_tempFromLayout);
        til_j_convTemp_tempToLayout = view.findViewById(R.id.til_v_convTemp_tempToLayout);
        et_j_convTemp_tempInput = view.findViewById(R.id.et_v_convTemp_tempInput);
        sp_j_convTemp_tempFrom = view.findViewById(R.id.sp_v_convTemp_tempFrom);
        sp_j_convTemp_tempTo = view.findViewById(R.id.sp_v_convTemp_tempTo);
        bnt_j_convTemp_conv = view.findViewById(R.id.btn_v_convTemp_conv);
        tv_j_convTemp_temp = view.findViewById(R.id.tv_v_convTemp_temp);

        // Setup Spinners
        setupSpinners();
        tempFromSpinner();
        tempToSpinner();

        // Setup Convert Button
        convertButton();

        return view;
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

    private void convertButton()
    {
        bnt_j_convTemp_conv.setOnClickListener(v -> {

            // Get vals
            String tempTo = sp_j_convTemp_tempTo.getText().toString();
            String tempFrom = sp_j_convTemp_tempFrom.getText().toString();
            String sTemp = et_j_convTemp_tempInput.getText().toString();

            if (tempCheck(tempTo, tempFrom) && !sTemp.isEmpty())
            {
                // Perform conversion
                double temp = Double.parseDouble(sTemp);
                double result = TempConversion.performConversion(tempFrom, tempTo, temp);

                // Display result
                switch (tempTo)
                {
                    case "Fahrenheit":
                        tv_j_convTemp_temp.setText(String.format("%s°F", result));
                        break;
                    case "Celsius":
                        tv_j_convTemp_temp.setText(String.format("%s°C", result));
                        break;
                    case "Kelvin":
                        tv_j_convTemp_temp.setText(String.format("%sK", result));
                        break;
                }
            }

        });
    }

    private boolean tempCheck(String tempTo, String tempFrom)
    {
        // Check if the values are empty
        if (tempTo.isEmpty() || tempFrom.isEmpty())
        {
            if (tempTo.isEmpty())
            {
                til_j_convTemp_tempToLayout.setErrorEnabled(true);
                til_j_convTemp_tempToLayout.setError(errorMsgs.get(0));
            }
            else
            {
                til_j_convTemp_tempToLayout.setErrorEnabled(false);
                til_j_convTemp_tempToLayout.setError(null);
            }

            if (tempFrom.isEmpty())
            {
                til_j_convTemp_tempFromLayout.setErrorEnabled(true);
                til_j_convTemp_tempFromLayout.setError(errorMsgs.get(0));
            }
            else
            {
                til_j_convTemp_tempFromLayout.setErrorEnabled(false);
                til_j_convTemp_tempFromLayout.setError(null);
            }

            return false;
        }

        // Check if the selected item is the same as the other spinner
        else if (tempTo.equals(tempFrom))
        {
            til_j_convTemp_tempToLayout.setErrorEnabled(true);
            til_j_convTemp_tempToLayout.setError(errorMsgs.get(1));
            til_j_convTemp_tempFromLayout.setErrorEnabled(true);
            til_j_convTemp_tempFromLayout.setError(errorMsgs.get(1));
            return false;
        }

        else
        {
            til_j_convTemp_tempToLayout.setErrorEnabled(false);
            til_j_convTemp_tempToLayout.setError(null);
            til_j_convTemp_tempFromLayout.setErrorEnabled(false);
            til_j_convTemp_tempFromLayout.setError(null);
            return true;
        }

    }

    private void tempFromSpinner()
    {
        sp_j_convTemp_tempFrom.setOnItemClickListener((parent, view, position, id) -> {

            String tempTo = sp_j_convTemp_tempTo.getText().toString();
            String tempFrom = sp_j_convTemp_tempFrom.getText().toString();

            // Check if the selected item is the same as the other spinner
            tempCheck(tempTo, tempFrom);

            // Set the ET icon based on selected temp
            switch (tempFrom)
            {
                case "Fahrenheit":
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.temperature_fahrenheit, 0);
                    break;
                case "Celsius":
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.temperature_celsius, 0);
                    break;
                case "Kelvin":
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.temperature_kelvin, 0);
                    break;
                default:
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    break;
            }

        });
    }

    private void tempToSpinner()
    {
        sp_j_convTemp_tempTo.setOnItemClickListener((parent, view, position, id) -> {

            String tempTo = sp_j_convTemp_tempTo.getText().toString();
            String tempFrom = sp_j_convTemp_tempFrom.getText().toString();

            // Check if the selected item is the same as the other spinner
            tempCheck(tempTo, tempFrom);

        });
    }

    private void setupSpinners()
    {
        // Setup From Spinner
        adapterFrom = new ArrayAdapter<>(requireContext(), R.layout.dropdown_menu_popup_item, tempTypes);
        sp_j_convTemp_tempFrom.setAdapter(adapterFrom);

        // Setup To Spinner
        adapterTo = new ArrayAdapter<>(requireContext(), R.layout.dropdown_menu_popup_item, tempTypes);
        sp_j_convTemp_tempTo.setAdapter(adapterTo);
    }
}