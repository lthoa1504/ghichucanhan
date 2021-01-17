package com.example.ghichucanhan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ghichucanhan.R;
import com.example.ghichucanhan.db.Priority;

import java.util.ArrayList;

public class PriorirySpinnerAdapter extends ArrayAdapter<Priority> {
    public PriorirySpinnerAdapter(@NonNull Context context, ArrayList<Priority> priorities) {
        super(context, 0, priorities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Priority priority = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_spinner, parent, false);
            TextView tvName = convertView.findViewById(R.id.tvNameSpinner);

            tvName.setText(priority.getNamePriority());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Priority priority = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_spinner, parent, false);
            TextView tvName = convertView.findViewById(R.id.tvNameSpinner);

            tvName.setText(priority.getNamePriority());
        }
        return convertView;
    }
}
