package com.example.aleph.tasklist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleph.tasklist.R;
import com.example.aleph.tasklist.model.Task;

import java.util.ArrayList;

/**
 * Created by alepH on 10/18/2016.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {
    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        // Lookup view for data population
        TextView taskName = (TextView) convertView.findViewById(R.id.name);
        ImageView taskStatus = (ImageView) convertView.findViewById(R.id.status);
        // Populate the data into the template view using the data object
        taskName.setText(task.getName());

        if (task.isDone()) {
            taskStatus.setImageResource(R.drawable.ic_check_black_24dp);
        } else {
            taskStatus.setImageDrawable(null);
        }
        // Return the completed view to render on screen
        return convertView;
    }

}
