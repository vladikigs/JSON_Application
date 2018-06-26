package com.example.dell.json_application;

import com.example.dell.json_application.DeserializeClass.CompanyContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyContainerToArrayListAdapter extends EmployeesView {

    private CompanyContainer container;

    CompanyContainerToArrayListAdapter(CompanyContainer container){
        this.container = container;
    }

    public ArrayList<HashMap<String, String>> getArrayEmployees(){
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
        return createArrayEmployees(nameEmployees,infoEmployees,countEmployees);

    }

    private ArrayList<HashMap<String, String>> createArrayEmployees(String[] nameEmployees, String[] infoEmployees, int countEmployees) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map;

        for (int i = 0; i < countEmployees; i++) {
            map = new HashMap<>();
            map.put("Name", nameEmployees[i]);
            map.put("Info", infoEmployees[i]);
            arrayList.add(map);
        }
        return arrayList;
    }
}
