package com.example.ghichucanhan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ghichucanhan.db.DatabaseHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {
    DatabaseHelper databaseHelper;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        PieChart pieChart = (PieChart) view.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        databaseHelper =new DatabaseHelper(getContext());

        Description desc = new Description();

        desc.setText("Dashboard");
        desc.setTextSize(20f);

        pieChart.setDescription(desc);

        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);

        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(60f, "Processing"));
        value.add(new PieEntry(20f, "Pending"));
        value.add(new PieEntry(20f, "Done"));

        PieDataSet pieDataSet = new PieDataSet(value, "TYPE");

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChart.animateXY(1400, 1400);
        return view;
    }

}



