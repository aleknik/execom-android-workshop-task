package com.example.aleph.tasklist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import model.Repository;
import model.Task;

public class TaskDetailsActivity extends AppCompatActivity {

    private CheckBox completed;
    private TextView name;
    private TextView desc;
    private Task task;
    private Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        completed = (CheckBox) findViewById(R.id.chkCompleted);
        name = (TextView) findViewById(R.id.task_name_det);
        desc = (TextView) findViewById(R.id.task_desc_det);
        remove = (Button) findViewById(R.id.remove_btn);

        SharedPreferences sharedPref = getSharedPreferences(ListActivity.TASK, Context.MODE_PRIVATE);
        String name = sharedPref.getString(ListActivity.CURR_TASK,  null);

        task = Repository.getInstance(this).getTaskByName(name);

        init();

        completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkBoxAction(isChecked);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickAction();

            }
        });
    }

    private void onClickAction() {
        Repository.getInstance(this).removeTask(task);
        ListActivity.adapter.notifyDataSetChanged();
        finish();
    }

    private void init() {
        completed.setChecked(task.isDone());
        name.setText(task.getName());
        desc.setText(task.getDescription());

    }

    private void checkBoxAction(boolean isChecked) {
        if  (isChecked)
        {
            task.setDone(true);
        } else {
            task.setDone(false);
        }

        Repository.getInstance(this).updateTask(task);
        ListActivity.adapter.notifyDataSetChanged();
    }

}
