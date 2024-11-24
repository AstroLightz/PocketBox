package com.astrolightz.pocketbox.ui.home;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.astrolightz.pocketbox.MainActivity;
import com.astrolightz.pocketbox.R;
import com.astrolightz.pocketbox.ToolButton;
import com.astrolightz.pocketbox.ToolListAdapter;
import com.astrolightz.pocketbox.ui.calcDate.CalculateDate;
import com.astrolightz.pocketbox.ui.calcPercent.CalculatePercent;
import com.astrolightz.pocketbox.ui.calcTip.CalculateTip;
import com.astrolightz.pocketbox.ui.calcTotal.CalculateTotal;
import com.astrolightz.pocketbox.ui.convTemp.ConvertTemperature;
import com.astrolightz.pocketbox.ui.numName.NumberName;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private HomeViewModel mViewModel;

    public static Home newInstance() {
        return new Home();
    }

    // Tools List
    RecyclerView rv_j_hf_toolList;
    private List<ToolButton> toolList = new ArrayList<ToolButton>();
    private ToolListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup RecyclerView
        rv_j_hf_toolList = view.findViewById(R.id.rv_v_hf_toolList);
        rv_j_hf_toolList.setLayoutManager(new LinearLayoutManager(getContext()));

        // Add Tools if list is empty
        if (toolList.isEmpty())
        {
            toolList.add(new ToolButton("Calculate Total", ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_credit_card_24, null)));
            toolList.add(new ToolButton("Calculate Tip", ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_attach_money_24, null)));
            toolList.add(new ToolButton("Temperature Converter", ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_device_thermostat_24, null)));
            toolList.add(new ToolButton("Number Formatter", ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_numbers_24, null)));
            toolList.add(new ToolButton("Days Apart", ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_calendar_month_24, null)));
            toolList.add(new ToolButton("Percentage Calculator", ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_percent_24, null)));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ViewModel
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Setup Adapter
        adapter = new ToolListAdapter(toolList, position -> {
            // Handle Click
            switch (position)
            {
                case 0:
                    // Calculate Total
                    findNavController(this).navigate(R.id.action_nav_home_to_nav_calcTotal);
                    break;
                case 1:
                    // Calculate Tip
                    findNavController(this).navigate(R.id.action_nav_home_to_nav_calcTip);
                    break;
                case 2:
                    // Temperature Converter
                    findNavController(this).navigate(R.id.action_nav_home_to_nav_convTemp);
                    break;
                case 3:
                    // Number Formatter
                    findNavController(this).navigate(R.id.action_nav_home_to_nav_numFormat);
                    break;
                case 4:
                    // Days Apart
                    findNavController(this).navigate(R.id.action_nav_home_to_nav_daysApart);
                    break;
                case 5:
                    // Percentage Calculator
                    findNavController(this).navigate(R.id.action_nav_home_to_nav_percChange);
                    break;
            }
        });

        // Set Adapter
        rv_j_hf_toolList.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}