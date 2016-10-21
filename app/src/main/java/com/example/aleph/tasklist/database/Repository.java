package com.example.aleph.tasklist.database;

import android.content.Context;

import com.example.aleph.tasklist.model.Task;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class Repository {
    private static Repository repo = null;
    private ArrayList<Task> tasks = new ArrayList<>();
    private Dao<Task, Long> dao;

    private Repository(Context ctx) throws SQLException {

        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(ctx.getApplicationContext(),
                DatabaseHelper.class);
        dao = databaseHelper.getDao();
        tasks = (ArrayList<Task>) dao.queryForAll();

    }

    public static Repository getInstance(Context ctx) {
        if (repo == null) {
            try {
                repo = new Repository(ctx);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return repo;
    }

    public void addTask(Task task) {
        try {
            dao.create(task);
            tasks.add(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTask(Task task) {

        try {
            dao.delete(task);
            tasks.remove(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(Task task) {
        try {
            dao.update(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getTaskByName(String name) {
        for (Task task : tasks) {
            if (name.equals(task.getName())) {
                return task;
            }
        }

        return null;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
