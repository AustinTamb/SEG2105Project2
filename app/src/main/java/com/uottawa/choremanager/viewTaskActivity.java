package com.uottawa.choremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class viewTaskActivity extends AppCompatActivity {
    private DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        System.out.println("jkl");


        String s = getIntent().getStringExtra("id");
        System.out.println("the task: " + db.getTask(s));
    }
}
