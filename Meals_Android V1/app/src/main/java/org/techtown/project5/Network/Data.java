package org.techtown.project5.Network;


import org.techtown.project5.Model.Meals;
import org.techtown.project5.Model.SchoolList;

import java.util.List;

public class Data {

    private List<SchoolList> schoolList;

    public Meals meals;

    public List<SchoolList> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolList> schoolInfo) {
        this.schoolList = schoolList;
    }

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }
}