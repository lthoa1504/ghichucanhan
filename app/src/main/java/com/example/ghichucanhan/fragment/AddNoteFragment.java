package com.example.ghichucanhan.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.ghichucanhan.R;
import com.example.ghichucanhan.adapter.CatagorySpinnerAdapter;
import com.example.ghichucanhan.adapter.NoteAdapter;
import com.example.ghichucanhan.adapter.PriorirySpinnerAdapter;
import com.example.ghichucanhan.adapter.StatusSpinnerAdapter;
import com.example.ghichucanhan.db.Category;
import com.example.ghichucanhan.db.DatabaseHelper;
import com.example.ghichucanhan.db.Note;
import com.example.ghichucanhan.db.Priority;
import com.example.ghichucanhan.db.Status;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNoteFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    Button btnAdd, btnClose;
    Dialog dialog;
    FloatingActionButton button;
    EditText edtName;
    Spinner catSpinner, priSpinner, staSpinner;
    TextView tvDatePick, tvTimePick, posCat, posSta, posPri;
    int hour;
    int minute;
    int sec;


    ArrayList<Note> noteArrayList;
    NoteAdapter adapter;



    ListView listView;



    private DatePickerDialog.OnDateSetListener dataSetListenner;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    DatabaseHelper databaseHelper;

    ArrayList<Category> categoryArrayList;
    CatagorySpinnerAdapter adapterCat;

    ArrayList<Priority> priorityArrayList;
    PriorirySpinnerAdapter adapterPri;

    ArrayList<Status> statusArrayList;
    StatusSpinnerAdapter adapterSta;


    public AddNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }
        databaseHelper = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteArrayList = databaseHelper.getNote();
        adapter = new NoteAdapter(getContext(), noteArrayList);

        //listview
        listView = view.findViewById(R.id.rvNote);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);




        //show dialog
        button = view.findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        //them note

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.note_dialog_layout);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;

        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnClose = dialog.findViewById(R.id.btnClose);
        edtName = dialog.findViewById(R.id.edtNoteName);


        posCat = dialog.findViewById(R.id.positionCat);
        posSta = dialog.findViewById(R.id.positionSta);
        posPri = dialog.findViewById(R.id.positionPri);


        //spinner category
        categoryArrayList = databaseHelper.getCategory();
        adapterCat = new CatagorySpinnerAdapter(getContext(), categoryArrayList);
        catSpinner = dialog.findViewById(R.id.spnCategory);
        catSpinner.setAdapter(adapterCat);







        //spinner priority
        priorityArrayList = databaseHelper.getPriority();
        adapterPri = new PriorirySpinnerAdapter(getContext(), priorityArrayList);
        priSpinner = dialog.findViewById(R.id.spnPriority);
        priSpinner.setAdapter(adapterPri);


        //spinner status
        statusArrayList = databaseHelper.getStatus();
        adapterSta = new StatusSpinnerAdapter(getContext(), statusArrayList);
        staSpinner = dialog.findViewById(R.id.spnStatus);
        staSpinner.setAdapter(adapterSta);




        tvDatePick = dialog.findViewById(R.id.tvDatePick);
        tvTimePick = dialog.findViewById(R.id.tvTimePick);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        //click choose date
        tvDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //time picker
                timePickDialog();

                //date picker
                datePickerDialog(year, month, day);


            }
        });



        //get value spinner

        //category spinner
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Code
                Category category = categoryArrayList.get(position);

                String msg = "" +category.getIdCategory();
                posCat.setText(msg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "not thing", Toast.LENGTH_SHORT).show();

            }
        });

        //priority spinner
        priSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Code
                Priority priority = priorityArrayList.get(position);

                String msg = "" +priority.getIdPriority();
                posPri.setText(msg);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "not thing", Toast.LENGTH_SHORT).show();

            }
        });

        //priority spinner
        staSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Code
                Status status = statusArrayList.get(position);

                String msg = "" +status.getIdStatus();
                posSta.setText(msg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "not thing", Toast.LENGTH_SHORT).show();

            }
        });




        //close

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = tvDatePick.getText().toString() + " " + tvTimePick.getText().toString();
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
//                String planDate = tvDatePick.getText().toString() + " " + tvTimePick.getText().toString();

                String name = edtName.getText().toString();
                int id_user = 1;
                String planDate = tvDatePick.getText().toString() + " " + tvTimePick.getText().toString();
                int id_category = Integer.parseInt(posSta.getText().toString());
                int id_status = Integer.parseInt(posCat.getText().toString());
                int id_priority = Integer.parseInt(posPri.getText().toString());
                Date date = Calendar.getInstance().getTime();
                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                String strdate = formatter.format(date);


                Note note = new Note(name, strdate, planDate,id_user, id_category, id_status, id_priority);

                if(databaseHelper.insertNote(note)>0){
                    adapter.clear();
                    noteArrayList.addAll(databaseHelper.getNote());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), planDate, Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });


    }

    private void datePickerDialog(int year, int month, int day) {
        DatePickerDialog pickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        tvDatePick.setText(date);

                    }
                },year,month,day);

        pickerDialog.show();
    }

    private void timePickDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
                        hour = hourOfDay;
                        minute = minute1;
                        String time = hour + ":" + minute;
                        SimpleDateFormat f24hour = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24hour.parse(time);
                            SimpleDateFormat f12hour = new SimpleDateFormat("hh:mm aa");
                            tvTimePick.setText(" "+f12hour.format(date));

                            String tpd = f12hour.format(date);
                            Toast.makeText(getContext(), tpd, Toast.LENGTH_SHORT).show();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false
        );
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(hour, minute);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + ":" + minute;
    }
    //context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.contexts_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos=info.position;
        Note note=noteArrayList.get(pos);
        int idNote=note.getId();
        switch (item.getItemId()) {
            case R.id.edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Cập nhật nội dung");
                builder.setCancelable(false);
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.fragment_update_note,null);

                EditText editText = (EditText) view.findViewById(R.id.edtNoteName) ;
                Date date = Calendar.getInstance().getTime();

                builder.setView(view);
                builder.setPositiveButton("Cập nhập", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editText.getText().toString();
                        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                        String strdate = formatter.format(date);
                        String planDate = tvDatePick.getText().toString() + " " + tvTimePick.getText().toString();


                        Note note = new Note(name, strdate,planDate,1,2,2,2);

                        if( databaseHelper.updateNote(idNote,note)>0)
                        {
                            adapter.clear();
                            noteArrayList.addAll(databaseHelper.getNote());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            //delete category
            case R.id.delete:
                AlertDialog.Builder builder1= new AlertDialog.Builder(getContext());
                builder1.setTitle("Delete");
                builder1.setCancelable(false);
                builder1.setMessage("Bạn muốn xóa?");
                builder1.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(databaseHelper.deleteNote(idNote)>0)
                        {
                            adapter.clear();
                            categoryArrayList.addAll(databaseHelper.getCategory());
                            adapter.notifyDataSetChanged();
                        }
                        Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });

                builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog1= builder1.create();
                alertDialog1.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
    // end context menu
}