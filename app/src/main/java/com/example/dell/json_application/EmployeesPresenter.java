package com.example.dell.json_application;

import java.util.ArrayList;

public class EmployeesPresenter {

    private EmployeesView view;




    EmployeesPresenter(EmployeesView view){
        this.view = view;
    }

    public void onViewCreated() {
        view.showProgress();
        getArrayEmployees(new GetEmployeesCallback() {
            @Override
            public void getArrayEmployees(ArrayList arrayEmployees) {
                view.fillListView(arrayEmployees);
                view.hideProgress();
            }
        });
    }

    private void getArrayEmployees(GetEmployeesCallback callback){
        EmployeesModel employeesModel = new EmployeesModel(callback);
        employeesModel.getArrayEmployes();
    }
}
