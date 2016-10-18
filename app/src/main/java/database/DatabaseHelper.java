package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.aleph.tasklist.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import model.Task;

/**
 * Created by alepH on 10/18/2016.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "TaskList.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Task, Long> dao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates the Todo database table
             */
            TableUtils.createTable(connectionSource, Task.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            TableUtils.dropTable(connectionSource, Task.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public  Dao<Task, Long> getDao() throws SQLException {
        if(dao == null) {
            dao = getDao(Task.class);
        }
        return dao;
    }
}
