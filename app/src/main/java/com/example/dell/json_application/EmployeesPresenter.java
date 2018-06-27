package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;

public class EmployeesPresenter {

    private EmployeesView view;
    private EmployeesModel model;



    EmployeesPresenter(EmployeesView view, EmployeesModel model){
        this.view = view;
        this.model = model;
    }

    public void onViewReady() {
        view.showProgress();
        getEmployeesContainer(new GetEmployeesCallback() {
            @Override
            public void onContainerEmployeesCreated(CompanyContainer container) {
                view.createAdapter(container.getCompany().getEmployees());
                view.hideProgress();
            }
        });
    }

    private void getEmployeesContainer(GetEmployeesCallback callback){
        model.getContainerEmployees(callback);
    }

    public interface GetEmployeesCallback {
        void onContainerEmployeesCreated(CompanyContainer container);
    }
}
