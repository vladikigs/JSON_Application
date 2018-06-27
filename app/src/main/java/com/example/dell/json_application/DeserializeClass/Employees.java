package com.example.dell.json_application.DeserializeClass;

import java.io.Serializable;
import java.util.List;

public  class Employees implements Serializable {

    private String name;
    private String phone_number;
    private List<String> skills;

    public Employees() {
    }

    public String getName(){
        return name;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Phone: ").append(phone_number).append("\n");

        info.append("Skills: ");
        int countSkills = skills.size();
        for (int j = 0; j < countSkills; j++){
            info.append(skills.get(j));
            if (j != countSkills - 1) info.append(", ");
        }
        return info.toString();
    }
}
