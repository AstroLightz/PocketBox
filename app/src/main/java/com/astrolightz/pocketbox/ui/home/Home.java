package com.astrolightz.pocketbox.ui.home;

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
    private List<ToolButton> toolList = new ArrayList<ToolButton>();
    private ToolListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup RecyclerView
        RecyclerView rv_j_hf_toolList = view.findViewById(R.id.rv_v_hf_toolList);
        rv_j_hf_toolList.setLayoutManager(new LinearLayoutManager(getContext()));



        // Add Tools
        toolList.add(new ToolButton("Calculate Total"));
        toolList.add(new ToolButton("Calculate Tip"));
        toolList.add(new ToolButton("Temperature Converter"));
        toolList.add(new ToolButton("Number Formatter"));
        toolList.add(new ToolButton("Days Apart"));
        toolList.add(new ToolButton("Percentage Calculator"));

        // Setup Adapter
        adapter = new ToolListAdapter(toolList, position -> {
            // Handle Click
            switch (position)
            {
                case 0:
                    // Calculate Total
                    MainActivity.setToolBarTitle(getContext(), "Calculate Total");
                    MainActivity.loadFragment(getContext(), new CalculateTotal());
                    break;
                case 1:
                    // Calculate Tip
                    MainActivity.setToolBarTitle(getContext(), "Calculate Tip");
                    MainActivity.loadFragment(getContext(), new CalculateTip());
                    break;
                case 2:
                    // Temperature Converter
                    MainActivity.setToolBarTitle(getContext(), "Temperature Converter");
                    MainActivity.loadFragment(getContext(), new ConvertTemperature());
                    break;
                case 3:
                    // Number Formatter
                    MainActivity.setToolBarTitle(getContext(), "Number Formatter");
                    MainActivity.loadFragment(getContext(), new NumberName());
                    break;
                case 4:
                    // Days Apart
                    MainActivity.setToolBarTitle(getContext(), "Days Apart");
                    MainActivity.loadFragment(getContext(), new CalculateDate());
                    break;
                case 5:
                    // Percentage Calculator
                    MainActivity.setToolBarTitle(getContext(), "Percentage Calculator");
                    MainActivity.loadFragment(getContext(), new CalculatePercent());
                    break;
            }
        });

        rv_j_hf_toolList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ViewModel
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}