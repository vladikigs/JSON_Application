package com.example.dell.json_application.DeserializeClass;

import java.util.List;

public class Company{

    private String name;
    private int age;
    private List<String> competences;
    private List<Employees> employees;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

}
