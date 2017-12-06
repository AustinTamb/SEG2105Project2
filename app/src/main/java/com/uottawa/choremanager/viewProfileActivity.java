package com.uottawa.choremanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Raymo on 2017-11-30.
 */

public class viewProfileActivity extends AppCompatActivity{
    DataBase dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        String profileId = getIntent().getStringExtra("profileId");
        dB = MainActivity.getDB();
        final Profile x = dB.getProfile(profileId);
        final Profile currentUser = dB.getCurrentUser();

        //Updates UML with data:
        TextView name = (TextView) findViewById(R.id.txtName);
        name.setText(x.getName());
        CheckBox isParent = (CheckBox) findViewById(R.id.cbxIsParent);
        isParent.setChecked(x.isParent());
        isParent.setEnabled(false);//no point in them clicking this if it doesn't edit...
        TextView tskCmp = (TextView) findViewById(R.id.lblTaskCompleted);
        tskCmp.setText("Number of Tasks completed: "+x.getNumberOfTasksCompleted());

        if(currentUser!=x){
            findViewById(R.id.btnLogin).setEnabled(true);
        }else{
            findViewById(R.id.btnLogin).setEnabled(false);
        }

        //Add on click listener for edit profile button
        if(currentUser.isParent()){//Checks if parent
            findViewById(R.id.btnEdit).setEnabled(true);
            findViewById(R.id.btnDelete).setEnabled(true);
        } else {
            findViewById(R.id.btnEdit).setEnabled(false);
            findViewById(R.id.btnDelete).setEnabled(false);
        }

        final Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            final Dialog dialog = openDialog();
            Button b = (Button) dialog.findViewById(R.id.btnLogin);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView a = (TextView) dialog.findViewById(R.id.txtPassword);
                    if(x.validatePassword(a.getText().toString())){
                        dB.setCurrentUser(x);
                        showError("Switched users!");
                        finish();
                    }else{
                        showError("Incorrect Password!");
                    }
                    dialog.dismiss();
                }
            });
            }
        });

        final Button deleteButton = findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Reassigns tasks to current user
                for(String st : x.getAssignedTasks()){
                    dB.assignTask(currentUser.getId(), st);
                }
                //dB.removeProfile(x.getId());
                showError("Profile deleted!");
                finish();
            }
        });

        final Button editButton = findViewById(R.id.btnEdit);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Add intent to Edit profile page
                finish();
            }
        });

    }

    public Dialog openDialog() {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.enter_password);
        dialog.setTitle("Login");

        dialog.show();
        return dialog;
    }

    private void showError(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
