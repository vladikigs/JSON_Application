package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;

public class ModelList {

    CompanyContainer container;

    public interface LoadJsonCallback {
        void onLoad(String Json);
    }

    public void requestJson(LoadJsonCallback callback) {
        Json strConnect = new Json(callback);
        strConnect.execute();
    }
}
