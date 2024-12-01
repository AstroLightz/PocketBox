package com.astrolightz.pocketbox;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class LicenseAdapter  extends RecyclerView.Adapter<LicenseAdapter.ViewHolder>
{
    private final Context context;
    private final List<License> licenses;
    private final OnItemClickListener listener;

    public LicenseAdapter(Context context, List<License> licenses, OnItemClickListener listener)
    {
        this.context = context;
        this.licenses = licenses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.party_license_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        // Set data
        License license = licenses.get(position);
        holder.tv_j_pLicense_party.setText(license.getName());
        holder.tv_j_pLicense_license.setText(license.getDetails());

        // Set click listener
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));

    }

    @Override
    public int getItemCount()
    {
        return licenses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        MaterialTextView tv_j_pLicense_party;
        MaterialTextView tv_j_pLicense_license;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_j_pLicense_party = itemView.findViewById(R.id.tv_v_pLicense_party);
            tv_j_pLicense_license = itemView.findViewById(R.id.tv_v_pLicense_license);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
}
