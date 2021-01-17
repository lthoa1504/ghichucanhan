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
import com.example.ghichucanhan.db.Status;

import java.util.ArrayList;

public class StatusSpinnerAdapter extends ArrayAdapter<Status> {
    public StatusSpinnerAdapter(@NonNull Context context, ArrayList<Status> statuses) {
        super(context, 0, statuses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Status category = getItem(position);
        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_spinner,parent,false);
            TextView tvName = convertView.findViewById(R.id.tvNameSpinner);
            tvName.setText(category.getNameStatus());
        }
        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Status status = getItem(position);
        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_spinner,parent,false);
            TextView tvName = convertView.findViewById(R.id.tvNameSpinner);

            tvName.setText(status.getNameStatus());
        }
        return convertView;
    }
}
