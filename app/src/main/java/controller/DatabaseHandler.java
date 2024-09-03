package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "users_db";
    public static final String TABLE_NAME = "users";
    public static final int DB_VERSION = 1;

    public DatabaseHandler(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String createTable="CREATE TABLE "+TABLE_NAME+" (id integer primary key autoincrement,email_address text unique not null,password text not null)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertUser(@NonNull User user) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("email_address", user.getUser_name());
            values.put("password", user.getPassword());

            long result = db.insert("users", null, values);

            if (result == -1) {
                throw new Exception("Error inserting user data");
            }
            return true;
        } catch (SQLiteConstraintException e) {
            System.out.println("This email already has an account ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure that the database is closed even if an error occurs
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return false; // Return false if an exception was caught or insertion failed
    }



    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE email_address =? AND password =?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists; // Returns true if user exists
    }













    public boolean updateUser(@NonNull User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email_address", user.getUser_name());
        values.put("password", user.getPassword());
        String args[] = {user.getUser_name(),user.getPassword()};
        int result = db.update(DB_NAME, values,"email_address = ? and password = ?",args);
        return result >0;
    }
    public boolean hasData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean hasData = false;

        try {
            String query = "SELECT 1 FROM " + TABLE_NAME + " LIMIT 1";
            cursor = db.rawQuery(query, null);
            hasData = cursor != null && cursor.moveToFirst();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return hasData;
    }
    public void deleteAllUsers() {
        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase(); // Open the database
            db.delete(TABLE_NAME, null, null); // Delete all rows from the table
        } finally {
            if (db != null && db.isOpen()) {
                db.close(); // Ensure the database is closed
            }
        }
    }



    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> users = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String getAll="SELECT * FROM "+TABLE_NAME;
        Cursor c=null;
        try{
            c = db.rawQuery(getAll,null);// To check if there is data or not

            if(c!=null && c.moveToFirst()) {
                do {
                    int id = c.getInt(0);//or int ids = c.getInt(c.getColumnIndex("id"));
                    String userName = c.getString(1);
                    String password = c.getString(2);
                    users.add(new User(id, userName, password));
                }
                while (c.moveToNext());
            }
        }finally {
            if(c!=null){c.close();}
        }
        db.close();
        return users;
    }




}
