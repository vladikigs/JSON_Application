package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeesModel {

    private String resultJson = "";
    private static final String localJson = "{ \"company\": { \"name\":\"High Technologies Center\", \"age\": \"15\", \"competences\": [\"Android\", \"IOS\", \".NET\", \"PHP\", \"Smart-TV\"], \"employees\": [{ \"name\": \"John\", \"phone_number\": \"769453\", \"skills\": [\"Java\", \"Android\"] }, { \"name\": \"Diego\", \"phone_number\": \"987924\", \"skills\": [\"Java\", \"Smart-TV\"] }, { \"name\": \"Alfred\", \"phone_number\": \"452533\", \"skills\": [\"Objective-C\", \"Android\", \"Photoshop\"] }, { \"name\": \"John\", \"phone_number\": \"212456\", \"skills\": [\"Java\", \"Phython\"] }, { \"name\": \"Mat\", \"phone_number\": \"778975\", \"skills\": [\"Android\", \"MovieMaker\"] }, { \"name\": \"Bob\", \"phone_number\": \"456468\", \"skills\": [\"Groovy\", \"Kotlin\"] }, { \"name\": \"Marty\", \"phone_number\": \"321789\", \"skills\": [\"Android\", \"PHP\", \"C#\"] }] } }";
    private final GetEmployeesCallback callback;

    EmployeesModel(GetEmployeesCallback callback){
        this.callback = callback;
    }



    private void requestJson(LoadJsonCallback callback) {
        JsonConnect strConnect = new JsonConnect(callback);
        strConnect.execute();
    }

    public void getArrayEmployes(){
        requestJson(new LoadJsonCallback() {
            @Override
            public void onLoad(String Json) {
                resultJson = Json;
                if (resultJson.equals("")) resultJson = localJson;
                parseJson();
            }
        });
    }

    private void parseJson(){
        Gson g = new Gson();
        CompanyContainer container = g.fromJson(resultJson, CompanyContainer.class);

        containerArraysConversion(container);
    }

    private void containerArraysConversion(CompanyContainer container){
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
        createArrayMap(nameEmployees,infoEmployees,countEmployees);
    }

    private void createArrayMap(String[] nameEmployees, String[] infoEmployees,int countEmployees){
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map;

        for (int i = 0; i < countEmployees; i++){
            map = new HashMap<>();
            map.put("Name", nameEmployees[i]);
            map.put("Info", infoEmployees[i]);
            arrayList.add(map);
        }

        if(callback != null){
            callback.getArrayEmployees(arrayList);
        }
    }
}
