package com.example.dell.json_application;

import java.util.ArrayList;

public class EmployeesPresenter {

    private EmployeesView view;
    private EmployeesModel model;



    EmployeesPresenter(EmployeesView view, EmployeesModel model){
        this.view = view;
        this.model = model;
    }

    public void onViewCreated() {
        view.showProgress();
        getArrayEmployees(new GetEmployeesCallback() {
            @Override
            public void onEmployeesArrayCreated(ArrayList arrayEmployees) {
                view.fillListView(arrayEmployees);
                view.hideProgress();
            }
        });
    }

    private void getArrayEmployees(GetEmployeesCallback callback){
        model.getArrayEmployees(callback);
    }
}
