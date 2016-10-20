package com.example.aleph.tasklist.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aleph.tasklist.R;

import com.example.aleph.tasklist.database.Repository;
import com.example.aleph.tasklist.model.Task;

public class TaskDetailsActivity extends AppCompatActivity {

    private CheckBox completed;
    private EditText name;
    private EditText desc;
    private Task task;

    private Button remove;
    private Button ok;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        init();

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveClickAction();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkClickAction();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onOkClickAction() {
        Repository repo = Repository.getInstance(TaskDetailsActivity.this);

        Task pom = repo.getTaskByName(name.getText().toString());

        if (name.getText().toString().trim().equals("")) {
            name.setError(getString(R.string.name_required));

        } else if (pom != null && pom != task) {
            name.setError(getString(R.string.name_unique));
        } else {
            task.setName(name.getText().toString());
            task.setDescription(desc.getText().toString());

            if (completed.isChecked()) {
                task.setDone(true);
            } else {
                task.setDone(false);
            }

            repo.updateTask(task);
            ListActivity.adapter.notifyDataSetChanged();
            finish();
        }
    }

    private void onRemoveClickAction() {
        Repository.getInstance(this).removeTask(task);
        ListActivity.adapter.notifyDataSetChanged();
        finish();
    }

    private void init() {
        Intent intent = getIntent();
        String taskName = intent.getExtras().getString(ListActivity.TASK_NAME);
        task = Repository.getInstance(this).getTaskByName(taskName);

        name = (EditText) findViewById(R.id.task_name_det);
        completed = (CheckBox) findViewById(R.id.chkCompleted);
        desc = (EditText) findViewById(R.id.task_desc_det);
        remove = (Button) findViewById(R.id.remove_btn);
        ok = (Button) findViewById(R.id.ok_btn);
        cancel = (Button) findViewById(R.id.cancel_btn);

        completed.setChecked(task.isDone());
        name.setText(task.getName());
        name.setSelection(name.getText().length());
        desc.setText(task.getDescription());
    }

}
