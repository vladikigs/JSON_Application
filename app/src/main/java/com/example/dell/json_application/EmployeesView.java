package com.example.dell.json_application;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vladislav Bashirov
 */
public class EmployeesView extends AppCompatActivity{

    private ArrayList<HashMap<String, String>> arrayListEmployees = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);
        if (savedInstanceState != null)
            arrayListEmployees = savedInstanceState.getStringArrayList(getString(R.string.key_on_save_instance));

        if(arrayListEmployees == null) {
            init();
        }
        else fillListView(arrayListEmployees);
    }

    private void init() {
        EmployeesModel model = new EmployeesModel();
        EmployeesPresenter presenter = new EmployeesPresenter(this, model);
        presenter.onViewCreated();
    }

    public void fillListView(ArrayList<HashMap<String, String>> arrayList){
        this.arrayListEmployees = arrayList;
        SimpleAdapter adapter = new SimpleAdapter(this, arrayListEmployees, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Info"},
                new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = findViewById(R.id.employeesList);
        listView.setAdapter(adapter);
    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.progress_dialog_message));
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        hideProgress();

        outState.putSerializable(getString(R.string.key_on_save_instance), arrayListEmployees);
    }
}

