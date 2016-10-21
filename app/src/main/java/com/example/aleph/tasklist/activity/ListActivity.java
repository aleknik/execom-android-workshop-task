package com.example.aleph.tasklist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aleph.tasklist.R;
import com.example.aleph.tasklist.adapters.TaskListAdapter;
import com.example.aleph.tasklist.database.Repository;
import com.example.aleph.tasklist.model.Task;

public class ListActivity extends AppCompatActivity {
    public static final String TASK_NAME = "task";
    public static TaskListAdapter adapter;
    private ListView tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Repository repo = Repository.getInstance(this);

        tasks = (ListView) findViewById(R.id.ToDoList);

        adapter = new TaskListAdapter(this, repo.getTasks());
        tasks.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        tasks.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemCLickedAction(position);
            }
        });
    }

    private void onItemCLickedAction(int position) {
        Task task = (Task) tasks.getItemAtPosition(position);

        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra(TASK_NAME, task.getName());
        startActivity(intent);
    }
}
