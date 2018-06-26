package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;

import java.util.ArrayList;

public interface GetEmployeesCallback {
    void onContainerEmployeesCreated(CompanyContainer container);
}