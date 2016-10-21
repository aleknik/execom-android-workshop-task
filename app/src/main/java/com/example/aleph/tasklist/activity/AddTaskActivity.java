package com.example.aleph.tasklist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aleph.tasklist.R;
import com.example.aleph.tasklist.database.Repository;
import com.example.aleph.tasklist.model.Task;

public class AddTaskActivity extends AppCompatActivity {

    private Button addButton;
    private Button cancelButton;
    private EditText taskName;
    private EditText taskDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        init();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClickAction();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void addButtonClickAction() {

        Repository repo = Repository.getInstance(AddTaskActivity.this);

        if (taskName.getText().toString().trim().equals("")) {
            taskName.setError(getString(R.string.name_required));

        } else if (repo.getTaskByName(taskName.getText().toString()) != null) {
            taskName.setError(getString(R.string.name_unique));
        } else {
            Task task = new Task(taskName.getText().toString(), taskDesc.getText().toString(), false);
            repo.addTask(task);
            finish();
        }
    }

    private void init() {
        addButton = (Button) findViewById(R.id.add_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        taskName = (EditText) findViewById(R.id.task_name);
        taskDesc = (EditText) findViewById(R.id.task_desc);

        taskName.setSelection(taskName.getText().length());
        taskDesc.setSelection(taskDesc.getText().length());
    }
}
