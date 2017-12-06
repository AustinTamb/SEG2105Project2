package com.uottawa.choremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
        TextView startDateText = (TextView) findViewById(R.id.textStartDate);
        TextView endDateText = (TextView) findViewById(R.id.textEndDate);
        TextView startTimeText = (TextView) findViewById(R.id.textStartTime);
        TextView endTimeText = (TextView) findViewById(R.id.textEndTime);

        TextView descriptionText = (TextView) findViewById(R.id.txtNotes);
        ListView lv = (ListView) findViewById(R.id.listViewMaterials);

        titleText.setText(theTask.getName());

        Profile profileOfOwnerOfTask = db.getProfile(theTask.getOwnerId());
        String nameOfOwnerOfTask = profileOfOwnerOfTask.getName();
        nameText.setText(nameOfOwnerOfTask);


        startDateText.setText(processDate(theTask.getStartDate(), false));
        endDateText.setText(processDate(theTask.getEndDate(), false));
        startTimeText.setText(processDate(theTask.getStartDate(), true));
        endTimeText.setText(processDate(theTask.getEndDate(), true));


        descriptionText.setText(theTask.getDescription());


        List<SubTask> subTaskList = theTask.getSubTasks();
        String[] subTaskListString;
        if (subTaskList != null){

            subTaskListString = new String[subTaskList.size()];
            for (int i = 0; i < subTaskList.size(); i++) {
                subTaskListString[i] = subTaskList.get(i).getName();
            }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.subtask_template, subTaskListString);
        }

    }


    private String processDate(String oldString, boolean getTime) {
        StringBuilder s = new StringBuilder();
        // mm/dd/yyyy/hh/mm
        if (oldString == null) {
            return ("null");
        }
        if(oldString.length() == 16) {
            if (!getTime) {
                s.append(oldString.substring(0, 11));
                return (s.toString());

            } else {
                s.append(oldString.substring(11, 13));
                s.append(":");
                s.append(oldString.substring(14));
                return (s.toString());
            }
        }

        return(oldString);

    }
}
