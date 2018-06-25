package com.example.dell.json_application;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;

public class EmployeesView extends AppCompatActivity{

    private ArrayList arrayList;

    private ProgressDialog progressDialog;

    EmployeesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);

        if(savedInstanceState != null){
            arrayList = savedInstanceState.getStringArrayList("saveArrayList");
            if (arrayList != null){
                setDataView(arrayList);
            }
            else {
                init();
            }
        }
        else {
            init();
        }
    }

    private void init() {
        EmployeesModel model = new EmployeesModel();
        presenter = new EmployeesPresenter(model);
        presenter.attachView(this);
        presenter.createActivity();
    }

    public void setDataView(ArrayList arrayList){
        this.arrayList = arrayList;
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Info"},
                new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = findViewById(R.id.employeesList);
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

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        hideProgress();
        outState.putStringArrayList("saveArrayList", arrayList);
    }
}

