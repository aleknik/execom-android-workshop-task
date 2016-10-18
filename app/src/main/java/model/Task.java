package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by alepH on 10/18/2016.
 */
@DatabaseTable(tableName = "tasks")
public class Task {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String description;

    @DatabaseField
    private boolean done;

    public Task() {}

    public Task(String name, String description, boolean done) {
        this.name = name;
        this.description = description;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
