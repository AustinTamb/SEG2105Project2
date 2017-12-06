package com.uottawa.choremanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;

/**
 * Created by Tyler Peyton on 06/12/2017.
 */

public class switchClass {

    private Context context;
    private DataBase dB;
    private Profile p1;
    private String s1;

    public void password(){
        CharSequence text = "Enter password!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //If true, then use the setCurrentUser() method in the database to change the current user
        if (s1 == p1.getPassword()) {
            dB.setCurrentUser(p1);

        }


    }

}
