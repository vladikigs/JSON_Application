package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;
import com.google.gson.Gson;

public class EmployeesModel {

    private String resultJson = "";
    private static final String LOCAL_JSON = "{ \"company\": { \"name\":\"High Technologies Center\", \"age\": \"15\", \"competences\": [\"Android\", \"IOS\", \".NET\", \"PHP\", \"Smart-TV\"], \"employees\": [{ \"name\": \"John\", \"phone_number\": \"769453\", \"skills\": [\"Java\", \"Android\"] }, { \"name\": \"Diego\", \"phone_number\": \"987924\", \"skills\": [\"Java\", \"Smart-TV\"] }, { \"name\": \"Alfred\", \"phone_number\": \"452533\", \"skills\": [\"Objective-C\", \"Android\", \"Photoshop\"] }, { \"name\": \"John\", \"phone_number\": \"212456\", \"skills\": [\"Java\", \"Phython\"] }, { \"name\": \"Mat\", \"phone_number\": \"778975\", \"skills\": [\"Android\", \"MovieMaker\"] }, { \"name\": \"Bob\", \"phone_number\": \"456468\", \"skills\": [\"Groovy\", \"Kotlin\"] }, { \"name\": \"Marty\", \"phone_number\": \"321789\", \"skills\": [\"Android\", \"PHP\", \"C#\"] }] } }";
    private GetEmployeesCallback callback;

    private void requestJson(LoadJsonCallback callback) {
        JsonConnect strConnect = new JsonConnect(callback);
        strConnect.execute();
    }

    public void getContainerEmployees(GetEmployeesCallback callback){
        this.callback = callback;
        requestJson(new LoadJsonCallback() {
            @Override
            public void onLoad(String Json) {
                resultJson = Json;
                if (resultJson.equals("")) {
                    resultJson = LOCAL_JSON;
                }
                parseJson();
            }
        });
    }

    private void parseJson(){
        Gson g = new Gson();
        CompanyContainer container = g.fromJson(resultJson, CompanyContainer.class);

        if(callback != null){
            callback.onContainerEmployeesCreated(container);
        }
    }
}
