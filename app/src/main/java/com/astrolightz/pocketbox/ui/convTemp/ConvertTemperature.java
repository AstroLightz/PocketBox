package com.astrolightz.pocketbox.ui.convTemp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.TempConversion;
import com.astrolightz.pocketbox.Utilities;
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup Spinners
        setupSpinners();
        tempFromSpinner();
        tempToSpinner();

        // Setup Convert Button
        convertButton();

    }

    private void convertButton()
    {
        bnt_j_convTemp_conv.setOnClickListener(v -> {

            // Get vals
            String tempTo = sp_j_convTemp_tempTo.getText().toString();
            String tempFrom = sp_j_convTemp_tempFrom.getText().toString();
            String sTemp = et_j_convTemp_tempInput.getText().toString();

            if (tempCheckFull(tempTo, tempFrom) && !sTemp.isEmpty())
            {
                // Perform conversion
                double temp = Double.parseDouble(sTemp);
                double result = TempConversion.performConversion(tempFrom, tempTo, temp);
                String sResult = "";

                // Display result
                switch (tempTo)
                {
                    case "Fahrenheit":
                        sResult = String.format("%s°F", result);
                        break;
                    case "Celsius":
                        sResult = String.format("%s°C", result);
                        break;
                    case "Kelvin":
                        sResult = String.format("%sK", result);
                        break;
                }

                // Adjust font size based on length
                Utilities.adjustFontSize(sResult, tv_j_convTemp_temp);

                tv_j_convTemp_temp.setText(sResult);
            }
            else if (sTemp.isEmpty())
            {
                Utilities.displayError(getView(), "Please fill in temperature.");
            }

        });
    }

    // Checks
    /**
     * Checks for a single temperature value. Ensures it's not empty and not the same as the other spinner
     * @param temp    The value to check
     * @param tempTo  The value of the To spinner
     * @param tempFrom The value of the From spinner
     * @param til     The TextInputLayout to display the error
     */
    private void tempCheckSingle(String temp, String tempTo, String tempFrom, TextInputLayout til)
    {
        // Empty
        if (temp.isEmpty())
        {
            til.setErrorEnabled(true);
            til.setError(errorMsgs.get(0));
        }

        // Same as the other spinner
        else if (tempTo.equals(tempFrom))
        {
            til.setErrorEnabled(true);
            til.setError(errorMsgs.get(1));
        }
        else
        {
            til.setErrorEnabled(false);
            til.setError(null);
        }

    }

    /**
     * Checks for both temperature values. Ensures they're not empty and not the same as each other
     * @param tempTo   The value of the To spinner
     * @param tempFrom The value of the From spinner
     * @return         True if the values are valid
     */
    private boolean tempCheckFull(String tempTo, String tempFrom)
    {
        // Empty
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

        // Both the same
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

            // Perform Checks
            tempCheckSingle(tempFrom, tempTo, tempFrom, til_j_convTemp_tempFromLayout);

            // Set the ET icon based on selected temp
            switch (tempFrom)
            {
                case "Fahrenheit":
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_temp_fahrenheit, 0);
                    break;
                case "Celsius":
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_temp_celsius, 0);
                    break;
                case "Kelvin":
                    et_j_convTemp_tempInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_temp_kelvin, 0);
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

            // Perform Checks
            tempCheckSingle(tempTo, tempTo, tempFrom, til_j_convTemp_tempToLayout);

        });
    }

    private void setupSpinners()
    {
        // From Spinner
        adapterFrom = new ArrayAdapter<>(requireContext(), R.layout.dropdown_menu_popup_item, tempTypes);
        sp_j_convTemp_tempFrom.setAdapter(adapterFrom);

        // To Spinner
        adapterTo = new ArrayAdapter<>(requireContext(), R.layout.dropdown_menu_popup_item, tempTypes);
        sp_j_convTemp_tempTo.setAdapter(adapterTo);
    }
}