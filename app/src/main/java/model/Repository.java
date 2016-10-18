package model;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;

/**
 * Created by alepH on 10/18/2016.
 */

public class Repository {
    private ArrayList<Task> tasks = new ArrayList<>();

    private static Repository repo = null;

    private DatabaseHelper databaseHelper;

    private Dao<Task, Long> dao;


    private Repository(Context ctx) throws SQLException {

        databaseHelper = OpenHelperManager.getHelper(ctx.getApplicationContext(),
                DatabaseHelper.class);

        dao = databaseHelper.getDao();

        tasks = (ArrayList<Task>) dao.queryForAll();

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
        tasks.remove(task);
        try {
            dao.delete(task);
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

   public static Repository getInstance(Context ctx) {
        if (repo == null)
        {
            try {
                repo = new Repository(ctx);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return repo;
    }


    public ArrayList<String> getNameList() {
        ArrayList<String> names = new ArrayList<>();

        ArrayList<Task> tasks = getTasks();

        for (Task task : tasks) {
            names.add(task.getName());
        }

        return names;
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
