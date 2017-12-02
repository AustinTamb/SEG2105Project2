package com.uottawa.choremanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class newProfileActivity extends AppCompatActivity {
    DataBase dB;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile);
        dB = MainActivity.getDB();


        CheckBox x = (CheckBox) findViewById(R.id.cbxParentUser);
        if(dB.getCurrentUser().isParent())
            x.setVisibility(View.VISIBLE);
        else
            x.setVisibility(View.INVISIBLE);


        final ImageButton btn1 = findViewById(R.id.imgProfilePic);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //Take in image
            }
        });

        final Button button = findViewById(R.id.btnCreateProfile);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Boolean inputValid = true;
                String errorMessage = "You must fill the following lines:\n";

                String name = ((TextView) findViewById(R.id.txtName)).getText().toString();
                if (name.equals("")){
                    errorMessage+="Name\n";
                    inputValid = false;
                }
                String password = ((TextView)findViewById(R.id.txtPassword)).getText().toString();
                if (name.equals("")){
                    errorMessage+="Password";
                    inputValid = false;
                }

                Boolean isParent = ((CheckBox)findViewById(R.id.cbxParentUser)).isChecked();
                if(inputValid) {
                    dB.addProfile(name, isParent, password);//Add Image when done
                    showError("Profile added!");
                    //setContentView(R.layout.people);
                    finish();//Push by random person on github... https://github.com/michaelsam94
                }else
                    showError(errorMessage);
            }
        });
    }

    //Based off: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
    private void showError(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
