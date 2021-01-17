package com.example.ghichucanhan.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ghichucanhan.R;
import com.example.ghichucanhan.adapter.CategoryAdapter;
import com.example.ghichucanhan.db.Category;
import com.example.ghichucanhan.db.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AddCategoryFragment extends Fragment {
    Button btnAdd, btnClose;
    Dialog dialog;
    FloatingActionButton button;
    EditText edtName;
    ListView lvCategory;
    DatabaseHelper databaseHelper;
    ArrayList<Category> categoryArrayList;
    CategoryAdapter adapter;


    public AddCategoryFragment() {
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
        return inflater.inflate(R.layout.fragment_add_category, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryArrayList = databaseHelper.getCategory();
        adapter = new CategoryAdapter(getContext(), categoryArrayList);
        lvCategory = view.findViewById(R.id.lvCategory);
        lvCategory.setAdapter(adapter);
        registerForContextMenu(lvCategory);

        //show dialog
        button = view.findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                //Toast.makeText(getContext(), "thien", Toast.LENGTH_SHORT).show();
            }
        });


        //Hien dialog
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.category_dialog_layout);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnClose = dialog.findViewById(R.id.btnClose);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        //Them gia tri vao mang
        lvCategory = view.findViewById(R.id.lvCategory);
        edtName = dialog.findViewById(R.id.edtName);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                String strdate = formatter.format(date);


                Category category = new Category(name, strdate);

                if (databaseHelper.insertCategory(category) > 0) {
                    adapter.clear();
                    categoryArrayList.addAll(databaseHelper.getCategory());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });


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
        Category category=categoryArrayList.get(pos);
        int idCategory=category.getIdCategory();
        switch (item.getItemId()) {
            case R.id.edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Cập nhật nội dung");
                builder.setCancelable(false);
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.fragment_update_category,null);

                EditText edtName =(EditText)view.findViewById(R.id.edtName);

                Date date = Calendar.getInstance().getTime();

                builder.setView(view);
                builder.setPositiveButton("Cập nhập", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=edtName.getText().toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                        String strdate = formatter.format(date);
                        Category newCa=new Category(name,strdate);
                        if( databaseHelper.updateCategory(idCategory,newCa)>0)
                        {
                            adapter.clear();
                            categoryArrayList.addAll(databaseHelper.getCategory());
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
                        if(databaseHelper.deleteCategory(idCategory)>0)
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