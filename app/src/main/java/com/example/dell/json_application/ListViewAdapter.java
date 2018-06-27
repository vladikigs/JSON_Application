package com.example.dell.json_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.json_application.DeserializeClass.Employees;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<Employees> mEmployeesContainer;
    private LayoutInflater layoutInflater;

    ListViewAdapter(Context context, List<Employees> container){
        this.mEmployeesContainer = container;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mEmployeesContainer.size();
    }

    @Override
    public Object getItem(int i) {
        return mEmployeesContainer.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null){
            view = layoutInflater.inflate(android.R.layout.simple_list_item_2, viewGroup, false);
        }

        ((TextView) view.findViewById(android.R.id.text1)).setText(mEmployeesContainer.get(i).getName());
        ((TextView) view.findViewById(android.R.id.text2)).setText(mEmployeesContainer.get(i).getInfo());

        return view;
    }
}
