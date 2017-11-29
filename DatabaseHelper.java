package com.uottawa.choremanager;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
// based on https://github.com/valokafor/ProntoShopSQLiteEnd
/**
 * Created by vokafor on 11/19/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String LOG_TAG = DatabaseHelper.class.getSimpleName();
    private final static int DATABASE_VERSION= 1;
    public static final String DATABASE_NAME = "chore_manager.db";

    public static final String TABLE_PROFILE = "profile";
    public static final String TABLE_TASK = "task";

    private static DatabaseHelper mDatabaseInstance = null;
    private Context mContext;


    public static DatabaseHelper newInstance(Context context){
        //first check to see if the database helper
        //member data is null
        //create a new one if it is null

        if (mDatabaseInstance == null){
            mDatabaseInstance = new DatabaseHelper(context.getApplicationContext());
        }

        //either way we have to always return an instance of
        //our database class each time this method is called
        return mDatabaseInstance;
    }


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_PROFILE_TABLE);
            db.execSQL(CREATE_TASK_TABLE);

        } catch (SQLException e) {
            Log.d(LOG_TAG, " Error create database " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS "  + TABLE_TASK);
        onCreate(db);



    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    //String to create a profile table
    private static final String CREATE_PROFILE_TABLE =
            "CREATE TABLE " + "profile" + "("
                    + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name" + " TEXT NOT NULL, "
                    + "isParent" + " INTEGER, " + ")";




    //String to create a product table
    private static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + "task" + "("
                    + "id" + " INTEGER   PRIMARY KEY AUTOINCREMENT,"
                    + "name" + " TEXT NOT NULL, "
                    + "description" + " TEXT, "
                    + "deadline" + " TEXT, "
                    + "done" + " INTEGER, "
                    + "assigned" + " INTEGER, "
                    + "reoccur_rate" + " INTEGER, "
                    + "reoccur_end" + " TEXT, "
                    + "FOREIGN KEY(profile_id) REFERENCES profile(_id)" + ")";



















}
