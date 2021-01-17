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
import com.example.ghichucanhan.db.DatabaseHelper;
import com.example.ghichucanhan.db.Note;


import java.util.ArrayList;


public class NoteAdapter extends ArrayAdapter<Note> {
    DatabaseHelper db;
    public NoteAdapter(@NonNull Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_layout,parent,false);

            TextView tvNameNote = convertView.findViewById(R.id.tvNameNote);
            TextView tvNoteCate = convertView.findViewById(R.id.tvNoteCategory);
            TextView tvNoteSta = convertView.findViewById(R.id.tvNoteStatus);
            TextView tvNoteDate = convertView.findViewById(R.id.tvDateNote);
            TextView tvNotePri = convertView.findViewById(R.id.tvNotePriority);
            TextView tvNotePlanDate = convertView.findViewById(R.id.tvNotePlanDate);




            db= new DatabaseHelper(getContext());

            String catName = db.getNameCategory(note.getIdCategory());
            String staName = db.getNameStatus(note.getIdStatus());
            String priName = db.getNamePriority(note.getIdPriority());


            tvNameNote.setText(note.getName());
            tvNoteCate.setText(catName);
            tvNoteSta.setText(staName);
            tvNotePri.setText(priName);
            tvNoteDate.setText(note.getDate());
            tvNotePlanDate.setText(note.getPlanDate());
        }
        return convertView;
    }
}
