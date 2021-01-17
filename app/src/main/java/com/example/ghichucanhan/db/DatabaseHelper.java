package com.example.ghichucanhan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class DatabaseHelper {
    Context context;

    private String dbName = "note_personal.db";


    public DatabaseHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDB() {
        return context.openOrCreateDatabase("note_personal.db", Context.MODE_PRIVATE, null);
    }

    public void closeDB(SQLiteDatabase db) {
        db.close();
    }


    public void createTable() {
        SQLiteDatabase db = openDB();
        //tao bang
        String category = "create table if not exists tbl_category(id integer PRIMARY KEY autoincrement,name text,date text);";
        String status = "create table if not exists tbl_status(id integer PRIMARY KEY autoincrement,name text,date text);";
        String priority = "create table if not exists tbl_priority(id integer PRIMARY KEY autoincrement,name text,date text);";
        String note = "create table if not exists tbl_note(id integer PRIMARY KEY autoincrement,name text,date text,plan_date text,id_User integer,id_Catagory integer,id_Status integer, id_Priority integer ,FOREIGN KEY (id_Catagory) REFERENCES tbl_category (id) ,FOREIGN KEY (id_Status) REFERENCES tbl_status (id),FOREIGN KEY (id_Priority) REFERENCES tbl_priority (id),FOREIGN KEY (id_User) REFERENCES tbl_user (id));";
        String user = "create table if not exists tbl_user(id integer PRIMARY KEY autoincrement, firstname text, lastname text,email text, password text);";
        db.execSQL(category);
        db.execSQL(priority);
        db.execSQL(status);
        db.execSQL(user);
        db.execSQL(note);
        db.close();
    }

    //Lay category
    public ArrayList<Category> getCategory() {
        SQLiteDatabase db = openDB();
        ArrayList<Category> arrayList = new ArrayList<>();
        String sql = "select * from tbl_category";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            arrayList.add(new Category(id,name, date));
        }

        db.close();
        return arrayList;
    }


    //Lay priority
    public ArrayList<Priority> getPriority() {
        SQLiteDatabase db = openDB();
        ArrayList<Priority> arrayList = new ArrayList<>();

        String sql = "select * from tbl_priority";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            arrayList.add(new Priority(id,name, date));

        }

        db.close();
        return arrayList;
    }

    //Lay status
    public ArrayList<Status> getStatus() {
        SQLiteDatabase db = openDB();
        ArrayList<Status> arrayList = new ArrayList<>();

        String sql = "select * from tbl_status";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            arrayList.add(new Status(id, name, date));

        }

        db.close();
        return arrayList;
    }

    //getnameCategory
    public String getNameCategory(int id){
        SQLiteDatabase db = openDB();
        String sql = "select * from tbl_category where id =  "+ id;
        Cursor cursor = db.rawQuery(sql, null);
        String name1;
        while (cursor.moveToNext()) {
            name1 = cursor.getString(1);
            return name1;
        }

        return null;

    }
    //get namePrioriry
    public String getNamePriority(int id){
        SQLiteDatabase db = openDB();
        String sql = "select * from tbl_priority where id =  "+ id;
        Cursor cursor = db.rawQuery(sql, null);
        String name;
        while (cursor.moveToNext()) {
            name = cursor.getString(1);
            return name;
        }

        return null;

    }

    //get nameStatus
    public String getNameStatus(int id){
        SQLiteDatabase db = openDB();
        String sql = "select * from tbl_status where id =  "+ id;
        Cursor cursor = db.rawQuery(sql, null);
        String name1;
        while (cursor.moveToNext()) {
            name1 = cursor.getString(1);
            return name1;
        }

        return null;

    }

    //get Note
    public ArrayList<Note> getNote() {
        SQLiteDatabase db = openDB();
        ArrayList<Note> arrayList = new ArrayList<>();

        String sql = "select * from tbl_note";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            String planeDate = cursor.getString(3);
            int id_User = cursor.getInt(4);
            int id_Category = cursor.getInt(5);
            int id_Status = cursor.getInt(6);
            int id_Priority = cursor.getInt(7);
            arrayList.add(new Note(id, name, date, planeDate ,id_User, id_Category, id_Status, id_Priority));
        }
        db.close();
        return arrayList;
    }



    //get user
    public ArrayList<User> getUser() {
        SQLiteDatabase db = openDB();
        ArrayList<User> arrayList = new ArrayList<>();

        String sql = "select * from tbl_user";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String email = cursor.getString(1);
                String password = cursor.getString(2);
                String firstname = cursor.getString(3);
                String lastname = cursor.getString(4);
                arrayList.add(new User(email, password,firstname, lastname));
            }
        }
        db.close();
        return arrayList;
    }


    //Them category
    public long insertCategory(Category cate) {
        SQLiteDatabase db = openDB();
        ContentValues category = new ContentValues();
        category.put("name", cate.getNameCategory());
        category.put("date", cate.getDate());
        long status = db.insert("tbl_category", null, category);
        db.close();
        return status;
    }

    //Them Note
    public long insertNote(Note not) {
        SQLiteDatabase db = openDB();
        ContentValues note = new ContentValues();
        note.put("name", not.getName());
        note.put("date", not.getDate());
        note.put("plan_date", not.getPlanDate());
        note.put("id_User", not.getIdUser());
        note.put("id_Catagory", not.getIdCategory());
        note.put("id_Status", not.getIdStatus());
        note.put("id_Priority", not.getIdPriority());
        long status = db.insert("tbl_note", null, note);
        db.close();
        return status;

    }


    //Them Priority
    public long insertPriority(Priority pri) {
        SQLiteDatabase db = openDB();
        ContentValues priority = new ContentValues();
        priority.put("name", pri.getNamePriority());
        priority.put("date", pri.getDate());
        long status = db.insert("tbl_priority", null, priority);
        db.close();
        return status;
    }

    //Them status

    public long insertStatus(Status sta) {
        SQLiteDatabase db = openDB();
        ContentValues status = new ContentValues();
        status.put("name", sta.getNameStatus());
        status.put("date", sta.getDate());
        long status1 = db.insert("tbl_status", null, status);
        db.close();
        return status1;
    }

    //Xoa Category

    public long deleteCategory(int id) {
        SQLiteDatabase db = openDB();
        long status = db.delete("tbl_category", "id=" + id, null);
        db.close();
        return status;
    }

    //Xoa Status

    public long deleteStatus(int id) {
        SQLiteDatabase db = openDB();
        long status = db.delete("tbl_status", "id=" + id, null);
        db.close();
        return status;
    }

    //Xoa Priority

    public long deletePriority(int id) {
        SQLiteDatabase db = openDB();
        long status = db.delete("tbl_Priority", "id=" + id, null);
        db.close();
        return status;
    }
    //Xoa note

    public long deleteNote(int id) {
        SQLiteDatabase db = openDB();
        long status = db.delete("tbl_note", "id=" + id, null);
        db.close();
        return status;
    }

    //them user
    public long insertUser(User uesr) {
        SQLiteDatabase db = openDB();
        ContentValues user = new ContentValues();
        user.put("email", uesr.getEmailUser());
        user.put("password", uesr.getPasswordUser());
        user.put("firstname",uesr.getFirstnameUser());
        user.put("lastname",uesr.getLastnameUser());
        db.insert("tbl_user", null, user);
        long adduser = db.insert("tbl_user", null, user);
        db.close();
        return adduser;
    }

    //Update Category
    public long updateCategory(int id, Category newCate) {
        ContentValues categori = new ContentValues();
        categori.put("name", newCate.getNameCategory());
        categori.put("date", newCate.getDate());
        SQLiteDatabase db = openDB();
        long status = db.update("tbl_category", categori, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return status;
    }

    //Update Status
    public long updateStatus(int id, Status newSta) {
        ContentValues status = new ContentValues();
        status.put("name", newSta.getNameStatus());
        status.put("date", newSta.getDate());
        SQLiteDatabase db = openDB();
        long status1 = db.update("tbl_status", status, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return status1;
    }

    //Update Status
    public long updatePriority(int id, Priority newPri) {
        ContentValues priority = new ContentValues();
        priority.put("name", newPri.getNamePriority());
        priority.put("date", newPri.getDate());
        SQLiteDatabase db = openDB();
        long status = db.update("tbl_priority", priority, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return status;
    }
    //Them Note
    public long updateNote(int id, Note Newnote) {
        SQLiteDatabase db = openDB();
        ContentValues note = new ContentValues();
        note.put("name", Newnote.getName());
        note.put("date", Newnote.getDate());
        note.put("plan_date", Newnote.getPlanDate());
        note.put("id_User", Newnote.getIdUser());
        note.put("id_Catagory", Newnote.getIdCategory());
        note.put("id_Status", Newnote.getIdStatus());
        note.put("id_Priority", Newnote.getIdPriority());
        long status = db.update("tbl_note", note, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return status;

    }


    //get Note


    //check mail ton tai
    public Boolean chkemail(String email) {
        SQLiteDatabase db = openDB();
        String sqlemail = "select * from tbl_user where email=?";
        Cursor cursor = db.rawQuery(sqlemail, new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //kiem tra dng nhap
    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = openDB();
        String sqlcheck="select * from tbl_user where email=? and password=?";
        Cursor cursor=db.rawQuery(sqlcheck,new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;


    }


}
