package com.example.dell.json_application;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.dell.json_application.DeserializeClass.Employees;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vladislav Bashirov
 */
public class EmployeesView extends AppCompatActivity{

    //private ArrayList<HashMap<String, String>> arrayListEmployees = new ArrayList<>();
    private ProgressDialog progressDialog;
    private List<Employees> listEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        if (savedInstanceState != null) {
            listEmployees = (List<Employees>) savedInstanceState.get(getString(R.string.key_on_save_list));
        }

        if(listEmployees == null) {
            init();
        }
        else createAdapter(listEmployees);
    }

    private void init() {
        EmployeesModel model = new EmployeesModel();
        EmployeesPresenter presenter = new EmployeesPresenter(this, model);
        presenter.onViewReady();
    }

    public void createAdapter(List<Employees> conteinerEmployees){
        this.listEmployees = conteinerEmployees;
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, conteinerEmployees);
        fillListView(listViewAdapter);
    }

    private void fillListView(BaseAdapter simpleAdapter){
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

        outState.putSerializable(getString(R.string.key_on_save_list), (Serializable) listEmployees);
    }
}

