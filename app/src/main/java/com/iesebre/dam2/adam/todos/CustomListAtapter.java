package com.iesebre.dam2.adam.todos;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adam on 20/11/15.
 */
public class CustomListAtapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<TodoItem> list;
    private final LayoutInflater layputInflater;

    public CustomListAtapter(Context context, ArrayList listData) {
        this.context = context;
        this.list = listData;
        layputInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = layputInflater.inflate(R.layout.todolistitem, null);
        } else {

        }

        TextView tv = (TextView) convertView.findViewById(R.id.todolistitemtext);
        final CheckBox done = (CheckBox) convertView.findViewById(R.id.checkboxDone);
        RadioButton priority = (RadioButton) convertView.findViewById(R.id.priority);
        priority.setChecked(true);
        priority.setClickable(false);

        tv.setText(list.get(position).getName());

        done.setChecked(list.get(position).isDone());

        int urgentColor = Color.parseColor("#fe0000");
        int mediumColor = Color.parseColor("#0099ff");
        int notUrgentColor = Color.parseColor("#00ff19");

        if(list.get(position).getPriority() == 1){priority.setButtonTintList(ColorStateList.valueOf(urgentColor));}
        if(list.get(position).getPriority() == 2){priority.setButtonTintList(ColorStateList.valueOf(mediumColor));}
        if(list.get(position).getPriority() == 3){priority.setButtonTintList(ColorStateList.valueOf(notUrgentColor));}

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!done.isChecked()) {
                    done.setChecked(false);
                    list.get(position).setDone(false);
                } else {
                    done.setChecked(true);
                    list.get(position).setDone(true);
                }

            }
        });

        return convertView;
    }

}
