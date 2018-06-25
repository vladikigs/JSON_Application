package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;
import com.google.gson.Gson;

public class EmployeesModel {

    private String resultJson = "";
    CompanyContainer container;
    private EmployeesPresenter presenter;

    public void attachPresenter(EmployeesPresenter presenter){
        this.presenter = presenter;
    }

    public interface LoadJsonCallback {
        void onLoad(String Json);
    }

    private void requestJson(LoadJsonCallback callback) {
        JsonConnect strConnect = new JsonConnect(callback);
        strConnect.execute();
    }

    public void getJson(){
        requestJson(new EmployeesModel.LoadJsonCallback() {
            @Override
            public void onLoad(String Json) {
                resultJson = Json;
                if (resultJson.equals("")) {
                    JsonString jsonString = new JsonString();
                    resultJson = jsonString.strJson;
                }
                parseJson();
            }
        });
    }

    private void parseJson(){
        Gson g = new Gson();
        container = g.fromJson(resultJson, CompanyContainer.class);

        presenter.structuringContainer(container);
    }
}
