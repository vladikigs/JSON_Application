package com.example.dell.json_application;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.dell.json_application.DeserializeClass.Company;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewList extends AppCompatActivity{

    private ProgressDialog progressDialog;
    PresenterList presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        if(savedInstanceState != null){
            String string = savedInstanceState.getString("key");
        }
        else {
            init();
        }

    }


    private void init() {
        ModelList model = new ModelList();
        presenter = new PresenterList(model);
        presenter.attachView(this);
        presenter.createActivity();
    }

    public void setDataView(String[] name, String[] info, int size){
        ListView listView = findViewById(R.id.employeesList);

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map;
        for (int i = 0; i < size; i++){
            map = new HashMap<>();
            map.put("Name", name[i]);
            map.put("Info", info[i]);
            arrayList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Info"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Loading data. Please wait");
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}

