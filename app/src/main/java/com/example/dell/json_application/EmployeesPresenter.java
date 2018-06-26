package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;

public class EmployeesPresenter {

    private EmployeesView view;
    private EmployeesModel model;



    EmployeesPresenter(EmployeesView view, EmployeesModel model){
        this.view = view;
        this.model = model;
    }

    public void onViewCreated() {
        view.showProgress();
        getEmployeesContainer(new GetEmployeesCallback() {
            @Override
            public void onContainerEmployeesCreated(CompanyContainer container) {
                view.setCompanyContainer(container);
                view.hideProgress();
            }
        });
    }

    private void getEmployeesContainer(GetEmployeesCallback callback){
        model.getContainerEmployees(callback);
    }
}
