package com.example.dell.json_application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;
import android.app.ProgressDialog;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;


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
        if (savedInstanceState != null) {
            arrayListEmployees = (ArrayList<HashMap<String, String>>) savedInstanceState.get(getString(R.string.key_on_save_instance));
        }
        assert arrayListEmployees != null;
        if(arrayListEmployees.size() == 0) {
            init();
        }
        else fillListView(arrayListEmployees);
    }

    private void init() {
        EmployeesModel model = new EmployeesModel();
        EmployeesPresenter presenter = new EmployeesPresenter(this, model);
        presenter.onViewCreated();
    }

    public void setCompanyContainer(CompanyContainer container){
        CompanyContainerToArrayListAdapter adapterList = new CompanyContainerToArrayListAdapter(container);
        ArrayList<HashMap<String, String>> arrayListEmployees = adapterList.getArrayEmployees();
        this.arrayListEmployees = arrayListEmployees;

        fillListView(arrayListEmployees);
    }

    private void fillListView(ArrayList<HashMap<String, String>> arrayListEmployees){
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayListEmployees, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Info"},
                new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = findViewById(R.id.employeesList);
        listView.setAdapter(simpleAdapter);
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

