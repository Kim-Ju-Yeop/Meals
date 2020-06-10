package org.techtown.project5.Model;

import org.techtown.project5.Fragment.Lunch;

import java.util.List;
import java.util.Set;

public class Meals {

    String breakfastList;
    String lunchList;
    String dinnerList;

    public Meals(String breakfastList, String lunchList, String dinnerList) {
        this.breakfastList = breakfastList;
        this.lunchList = lunchList;
        this.dinnerList = dinnerList;
    }

    public String getBreakfastList() {
        return breakfastList;
    }

    public String getLunchList() {
        return lunchList;
    }

    public String getDinnerList() {
        return dinnerList;
    }

    public void setBreakfastList(String breakfastList) {
        this.breakfastList = breakfastList;
    }

    public void setLunchList(String lunchList) {
        this.lunchList = lunchList;
    }

    public void setDinnerList(String dinnerList) {
        this.dinnerList = dinnerList;
    }
}
