package com.astrolightz.pocketbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ToolListAdapter extends  RecyclerView.Adapter<ToolListAdapter.ToolViewHolder>
{
    private List<ToolButton> toolList;
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    // Constructor
    public ToolListAdapter(List<ToolButton> toolList, OnItemClickListener listener)
    {
        this.toolList = toolList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_cell, parent, false);
        return new ToolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolViewHolder holder, int position) {
        ToolButton tool = toolList.get(position);
        holder.btn_j_rvc_tool.setText(tool.getTitle());
        holder.btn_j_rvc_tool.setIcon(tool.getIcon());
        holder.btn_j_rvc_tool.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return toolList.size();
    }

    public static class ToolViewHolder extends RecyclerView.ViewHolder
    {
        MaterialButton btn_j_rvc_tool;

        public ToolViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_j_rvc_tool = itemView.findViewById(R.id.btn_v_rvc_tool);
        }
    }
}
