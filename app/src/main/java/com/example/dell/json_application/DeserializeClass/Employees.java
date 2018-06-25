package com.example.dell.json_application.DeserializeClass;

import java.util.List;

public  class Employees {
    public   String name;
    public   String phone_number;
    public  List<String> skills;

    public List<String> getSkills() {
        return skills;
    }

    public String getPhoneNumber(){
        return phone_number;
    }

    public String getName(){
        return name;
    }
}
