package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;
import com.google.gson.Gson;

public class PresenterList {

    private String resultJson = "";
    private ViewList view;
    private ModelList model;
    CompanyContainer container;

    PresenterList(ModelList model){
        this.model = model;
    }

    public void attachView(ViewList view){
        this.view = view;
    }

    public void createActivity() {
        view.showProgress();
        model.requestJson(new ModelList.LoadJsonCallback() {
            @Override
            public void onLoad(String Json) {
                resultJson = Json;
                if (resultJson.equals("")) {
                    NoConnectJson noConnectJson = new NoConnectJson();
                    resultJson = noConnectJson.strJson;
                }
                parseJson();
            }
        });
    }

    private void parseJson(){
        Gson g = new Gson();
        container = g.fromJson(resultJson, CompanyContainer.class);
        structuringContainer();
    }

    private void structuringContainer(){
        int countEmployees = container.company.employees.size();
        String[] nameEmployees = new String[countEmployees];
        String[] infoEmployees = new String[countEmployees];
        StringBuilder bufSkills = new StringBuilder("Skills: ");

        for (int i = 0; i < countEmployees; i++){
            nameEmployees[i] = container.company.employees.get(i).name;

            int countSkills = container.company.employees.get(i).skills.size();
            for (int j = 0; j < countSkills; j++){
                bufSkills.append(container.company.employees.get(i).skills.get(j));
                if (j != countSkills - 1) bufSkills.append(", ");
            }

            infoEmployees[i] = "Phone: " + container.company.employees.get(i).phone_number + "\n" + bufSkills;
            bufSkills = new StringBuilder("Skills: ");
        }
        view.hideProgress();
        view.setDataView(nameEmployees,infoEmployees,countEmployees);
    }


}
