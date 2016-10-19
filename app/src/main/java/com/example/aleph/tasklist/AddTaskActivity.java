package com.example.aleph.tasklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.Repository;
import model.Task;

public class AddTaskActivity extends AppCompatActivity {

    private Button addButton;
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
    }

    private void addButtonClickAction() {
        Task task = new Task(taskName.getText().toString(), taskDesc.getText().toString(), false);

        Repository repo = Repository.getInstance(AddTaskActivity.this);

        repo.addTask(task);

        //Intent intent = new Intent(AddTaskActivity.this, ListActivity.class);
        //startActivity(intent);

        finish();
    }

    private void init() {
        addButton = (Button) findViewById(R.id.add_button);
        taskName = (EditText) findViewById(R.id.task_name);
        taskDesc = (EditText) findViewById(R.id.task_desc);
    }
}
