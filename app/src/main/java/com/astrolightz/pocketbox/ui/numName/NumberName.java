package com.astrolightz.pocketbox.ui.numName;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astrolightz.pocketbox.MainActivity;
import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.Utilities;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;

import java.math.BigDecimal;

public class NumberName extends Fragment
{
    // Vars
    MaterialAutoCompleteTextView et_j_numName_num;
    MaterialTextView tv_j_numName_name;
    MaterialButton btn_j_numName_format;

    public static NumberName newInstance() {
        return new NumberName();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_name, container, false);

        // Connect vars
        et_j_numName_num = view.findViewById(R.id.et_v_numName_num);
        tv_j_numName_name = view.findViewById(R.id.tv_v_numName_name);
        btn_j_numName_format = view.findViewById(R.id.btn_v_numName_format);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup Button
        formatButton();

    }

    private void formatButton()
    {
        btn_j_numName_format.setOnClickListener(v -> {

            // Get num
            String sNum = et_j_numName_num.getText().toString();

            if (!sNum.isEmpty() && !sNum.equals("."))
            {
                BigDecimal num = new BigDecimal(sNum);

                // Get name
                String name = Utilities.getNumberName(num);

                if (!name.equals(Utilities.LARGE_NUMBER_ERROR))
                {
                    // Adjust font size based on length
                    Utilities.adjustFontSize(name, tv_j_numName_name);

                    // Display
                    tv_j_numName_name.setText(name);
                }
                else
                {
                    // Too big
                    Utilities.displayError(getView(), "Number is too large.");
                }

            }
            else if (sNum.equals("."))
            {
                // Prevent empty
                Utilities.displayError(getView(), "Invalid number.");
            }
            else
            {
                // Empty
                Utilities.displayError(getView(), "Please enter a number.");
            }

        });
    }
}