package com.example.aleph.tasklist.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private ListView tasks;
    public static TaskListAdapter adapter;

    public static final String CURR_TASK = "currentTask";
    public static final String TASK = "task";

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

        SharedPreferences sharedPref = getSharedPreferences(TASK, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CURR_TASK, task.getName());
        editor.apply();

        Intent intent = new Intent(this, TaskDetailsActivity.class);
        startActivity(intent);
    }
}
