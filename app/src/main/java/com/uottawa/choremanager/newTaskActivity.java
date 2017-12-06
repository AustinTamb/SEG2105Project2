package com.uottawa.choremanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Intent.ACTION_MAIN;
import static java.lang.Integer.parseInt;

public class newTaskActivity extends AppCompatActivity{
    private ArrayList<SubTask> subTasks;
    private ArrayList<String> names;
    private newTaskMaterialsAdapter materialsTasksAdapter;
    private DataBase dB;
    private ArrayList<String> profileIdList;
    private ArrayList<Profile> y;
    private ArrayAdapter<String> mArrayAdapter;
    private ArrayAdapter<String> statusArrayAdapter;
    private Profile selectedProfile; //The variable you wanted austin
    private String selectedStatus;
    private TextView startTextTime;
    private TextView startTextDate;
    private TextView endTextTime;
    private TextView endTextDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        dB = MainActivity.getDB();


        //Spinner code based off: https://stackoverflow.com/questions/24825249/how-to-add-item-in-spinner-android
        Spinner spnProfiles = findViewById(R.id.spnProfiles);
        Spinner statusSpinner = findViewById(R.id.spinnerStatus);

        //Populates the list of profiles
        y = dB.getProfiles();
        final ArrayList<String> profileNames = new ArrayList<String>();
        for(Profile x : y){
            profileNames.add(x.getName());
        }

        final ArrayList<String> statusArray = new ArrayList<String>();
        statusArray.add("Active");
        statusArray.add("Postponed");
        statusArray.add("Done");

        //Handles the status spinner
        statusArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, statusArray);
        statusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusArrayAdapter);

        //Handles the profiles spinner
        mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, profileNames);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProfiles.setAdapter(mArrayAdapter);

        //Handles what happens when you select a status in the status spinenr
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStatus = statusArray.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Handles what happens when you select a profile in the profile spinenr
        spnProfiles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProfile = y.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        //End spinner code

        names = new ArrayList<String>();

        subTasks = new ArrayList<SubTask>();
        //Get array of Profiles, loop through and getNames->spinnerOption
        //Followed Tutorial https://developer.android.com/guide/topics/ui/controls/spinner.html#SelectListener
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //profiles.setAdapter(adapter);
        //https://stackoverflow.com/questions/2652414/how-do-you-get-the-selected-value-of-a-spinner


        ListView test =(ListView) findViewById(R.id.listViewMaterials);
        //String testStr = test.toString();


        ListView materialsListView = (ListView) findViewById(R.id.listViewMaterials);
        //System.out.println("-------------------------------------------------> PRINTING ID" + testStr);


        materialsTasksAdapter = new newTaskMaterialsAdapter(getApplicationContext(),subTasks);
        materialsListView.setAdapter(materialsTasksAdapter);


        //Handles start date EditText
        //https://stackoverflow.com/questions/38604157/android-date-time-picker-in-one-dialog
        //https://developer.android.com/guide/topics/ui/controls/pickers.html
        //https://stackoverflow.com/questions/17901946/timepicker-dialog-from-clicking-edittext


        final Calendar startCalendar = Calendar.getInstance();

        //Handle the txtStartTime TextView
        startTextTime = (TextView) findViewById(R.id.txtStartTime);
        //REMOVE ME
        startTextTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Creating variables needed for constructor of timePickerDialog
                Calendar currentDate = Calendar.getInstance();
                final int hour = currentDate.get(Calendar.HOUR_OF_DAY);
                final int minute = currentDate.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(newTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        startCalendar.set(Calendar.HOUR_OF_DAY, i);
                        startCalendar.set(Calendar.MINUTE, i1);
                        startTextTime.setText(startCalendar.get(Calendar.HOUR_OF_DAY) + ":" + startCalendar.get(Calendar.MINUTE));
                        startTextTime.invalidate();
                    }
                },hour, minute, false);
                //REMOVE ME
                timePickerDialog.setTitle("Select a time");
                timePickerDialog.show();
            }
        });


        startTextDate = (TextView) findViewById(R.id.txtStartDate);
        //REMOVE ME
        startTextDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                final int year = currentDate.get(Calendar.YEAR);
                final int month = currentDate.get(Calendar.MONTH);
                final int day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(newTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        startCalendar.set(Calendar.YEAR, i);
                        startCalendar.set(Calendar.MONTH, i1);
                        startCalendar.set(Calendar.DAY_OF_MONTH, i2);
                        startTextDate.setText(String.valueOf(startCalendar.get(Calendar.MONTH)) + "/"
                                + String.valueOf(startCalendar.get(Calendar.DAY_OF_MONTH)) + "/"
                                + String.valueOf(startCalendar.get(Calendar.YEAR)));
                        startTextDate.invalidate();
                    }
                },year, month, day);
                //REMOVE ME
                datePickerDialog.setTitle("Select a Date");
                datePickerDialog.show();
            }
        });

        final Calendar endCalendar = Calendar.getInstance();


        endTextTime = (TextView) findViewById(R.id.txtEndTime);
        endTextTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Creating variables needed for constructor of timePickerDialog
                Calendar currentDate = Calendar.getInstance();
                final int hour = currentDate.get(Calendar.HOUR_OF_DAY);
                final int minute = currentDate.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(newTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        endCalendar.set(Calendar.HOUR_OF_DAY, i);
                        endCalendar.set(Calendar.MINUTE, i1);
                        endTextTime.setText(endCalendar.get(Calendar.HOUR_OF_DAY)
                                + ":" + endCalendar.get(Calendar.MINUTE));
                        endTextTime.invalidate();
                    }
                },hour, minute, false);
                timePickerDialog.setTitle("Select a time");
                timePickerDialog.show();
            }
        });


        //Handles the endDate text view
        endTextDate = (TextView) findViewById(R.id.txtEndDate);
        endTextDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                final int year = currentDate.get(Calendar.YEAR);
                final int month = currentDate.get(Calendar.MONTH);
                final int day = currentDate.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(newTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        endCalendar.set(Calendar.YEAR, i);
                        endCalendar.set(Calendar.MONTH, i1);
                        endCalendar.set(Calendar.DAY_OF_MONTH, i2);
                        endTextDate.setText(String.valueOf(endCalendar.get(Calendar.MONTH)) + "/"
                                + String.valueOf(endCalendar.get(Calendar.DAY_OF_MONTH)) + "/"
                                + String.valueOf(endCalendar.get(Calendar.YEAR)));
                        endTextDate.invalidate();
                    }
                },year, month, day);
                //REMOVE ME
                datePickerDialog.setTitle("Select a Date");
                datePickerDialog.show();
            }
        });


        //Handles the button that adds a task
        final Button addTaskButton = findViewById(R.id.btnAddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean done = false;
                Boolean valid = true; //False if an input is invalid
                StringBuilder response = new StringBuilder();//Informs user on what error they made
                StringBuilder startDateAndTimeString = new StringBuilder();
                StringBuilder endDateAndTimeString = new StringBuilder();
                String ownerID = selectedProfile.getId();
                String id = "";

                //Check if the dates make sense
                if(startCalendar.after(endCalendar) || Calendar.getInstance().after(startCalendar)
                        || Calendar.getInstance().after(endCalendar)){

                    valid = false;
                    response.append("Date/Time entries are incorrect\n");
                }

                String name = ((TextView)findViewById(R.id.txtTaskName)).getText().toString();

                if(name.length() == 0){
                    valid = false;
                    response.append("Task name is invalid\n");
                }

                String description = ((TextView)findViewById(R.id.txtNotes)).getText().toString();


                //Turns the startCalendar into one long with layout mm/dd/yyyy/hh/mm
                startDateAndTimeString.append(processString(startCalendar.get(Calendar.MONTH)));
                startDateAndTimeString.append("/");
                startDateAndTimeString.append(processString(startCalendar.get(Calendar.DAY_OF_MONTH)));
                startDateAndTimeString.append("/");
                startDateAndTimeString.append(processString(startCalendar.get(Calendar.YEAR)));
                startDateAndTimeString.append("/");
                startDateAndTimeString.append(processString(startCalendar.get(Calendar.HOUR_OF_DAY)));
                startDateAndTimeString.append("/");
                startDateAndTimeString.append(processString(startCalendar.get(Calendar.MINUTE)));

                endDateAndTimeString.append(processString(endCalendar.get(Calendar.MONTH)));
                endDateAndTimeString.append("/");
                endDateAndTimeString.append(processString(endCalendar.get(Calendar.DAY_OF_MONTH)));
                endDateAndTimeString.append("/");
                endDateAndTimeString.append(processString(endCalendar.get(Calendar.YEAR)));
                endDateAndTimeString.append("/");
                endDateAndTimeString.append(processString(endCalendar.get(Calendar.HOUR_OF_DAY)));
                endDateAndTimeString.append("/");
                endDateAndTimeString.append(processString(endCalendar.get(Calendar.MINUTE)));




                if(valid){
                    //https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
                   Task xyz = dB.addTask(name, startDateAndTimeString.toString(), description, endDateAndTimeString.toString(), ownerID, subTasks, selectedStatus);
                   //Intent update = new Intent(newTaskActivity.this, MainActivity.class);
                    //startActivity(update);
                    boolean editTask = true;
                    String editTaskID = "";
                    try {
                        String editTaskString = getIntent().getStringExtra("editTask");
                        if (editTaskString != null) {
                            editTaskID = editTaskString;
                            System.out.println(id);
                        }

                    }catch (NullPointerException e){
                        editTask = false;
                    }


                    int duration = Toast.LENGTH_SHORT;
                    if(!editTask) {

                        Toast toast = Toast.makeText(newTaskActivity.this, "New task added!", duration);
                    }else{
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",editTaskID);
                        setResult(Activity.RESULT_OK,returnIntent);

                        Toast toast = Toast.makeText(newTaskActivity.this, "Task Edited!", duration);
                    }

                    finish();

                    //https://stackoverflow.com/questions/12202432/how-to-call-method-in-main-activity-from-other-activity


                }else{
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(newTaskActivity.this, response, duration);
                    toast.show();
                }


            }
        });



        //Handles the button that adds subtasks
        final Button addButton = findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String subTaskToAdd = ((TextView)findViewById(R.id.txtSTName)).getText().toString();


                if(!subTaskToAdd.equals("")) {
                    subTasks.add(new SubTask(subTaskToAdd, false));
                    names.add(subTaskToAdd);
                    materialsTasksAdapter.notifyDataSetChanged();

                }
            }
        });

        final Button cancelButton = findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //Handles the button that removes subtasks
        final Button removeButton = findViewById(R.id.btnRemove);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String subTaskToRemove = ((TextView)findViewById(R.id.txtSTName)).getText().toString();

                int indexOfSubTaskToBeRemoved = 9999999;

                //Checks list if the input is valid
                for(SubTask str: subTasks){

                    if(str.getName().equals(subTaskToRemove)){
                        indexOfSubTaskToBeRemoved = subTasks.indexOf(str);
                        break;
                    }
                }

                try {
                    subTasks.remove(indexOfSubTaskToBeRemoved);
                    materialsTasksAdapter.notifyDataSetChanged();
                } catch (IndexOutOfBoundsException e) {
                    CharSequence response = "Requested Material does not exist";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext(), response, duration);
                    toast.show();
                }

            }
        });


    }

    private String processString(int d){
        String newS = Integer.toString(d);

        if(newS.length() == 1){
            newS = "0" + newS;
        }
        return(newS);
    }

}
