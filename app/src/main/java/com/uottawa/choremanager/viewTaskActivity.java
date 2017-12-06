package com.uottawa.choremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class viewTaskActivity extends AppCompatActivity {
    private DataBase db = MainActivity.getDB();
    private Task theTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);


        String s = getIntent().getStringExtra("id");
        theTask = db.getTask(s);

        TextView titleText = (TextView) findViewById(R.id.txtTaskName);
        TextView nameText = (TextView) findViewById(R.id.txtPersonName);
        TextView startText = (TextView) findViewById(R.id.txtStartDate);
        TextView endText = (TextView) findViewById(R.id.txtEndDate);
        TextView descriptionText = (TextView) findViewById(R.id.txtNotes);
        ListView lv = (ListView) findViewById(R.id.listViewMaterials);

        titleText.setText(theTask.getName());

        Profile profileOfOwnerOfTask = db.getProfile(theTask.getOwnerId());
        String nameOfOwnerOfTask = profileOfOwnerOfTask.getName();
        nameText.setText(nameOfOwnerOfTask);



    }

    private String processDate(long date){
        StringBuilder stringVersion = new StringBuilder();
        String s = String.valueOf(date);
        // mm/dd/yyyy
        stringVersion.append(s.charAt(0));
        stringVersion.append(s.charAt(1));
        stringVersion.append("/");
        stringVersion.append(s.charAt(2));
        stringVersion.append(s.charAt(3));
        stringVersion.append("/");
        stringVersion.append(s.charAt(4));
        stringVersion.append(s.charAt(5));
        stringVersion.append(s.charAt(6));
        stringVersion.append(s.charAt(7));

        //  hh//mm
        stringVersion.append("  ");
        stringVersion.append(s.charAt(8));
        stringVersion.append(s.charAt(9));
        stringVersion.append(":");
        stringVersion.append(s.charAt(10));
        stringVersion.append(s.charAt(11));

        return(stringVersion.toString());

    }
}
