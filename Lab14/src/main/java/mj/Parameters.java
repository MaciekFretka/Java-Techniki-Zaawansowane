package mj;

import javax.management.openmbean.*;
import java.util.ArrayList;
import java.util.List;

public class Parameters implements ParametersMBean  {
    //021469
    private int priorityA = 0;
    private int priorityB = 1;
    private int priorityC = 2;
    public List categories = new ArrayList<String>();


    public Parameters(int priorityA, int priorityB, int priorityC) {
        this.priorityA = priorityA;
        this.priorityB = priorityB;
        this.priorityC = priorityC;
        categories.add("Sprawy meldunkowe (A)");
        categories.add("Akta stanu cywilnego (B)");
        categories.add("Dofinansowania (C)");
    }


    public void setPriorityA(int priorityA) {
        this.priorityA = priorityA;
    }

    public void setPriorityB(int priorityB) {
        this.priorityB = priorityB;
    }

    public void setPriorityC(int priorityC) {
        this.priorityC = priorityC;
    }


    public int getPriorityA() {
        return priorityA;
    }

    public int getPriorityB() {
        return priorityB;
    }


    public int getPriorityC() {
        return priorityC;
    }

    @Override
    public List getCategoriesList() {
        return categories;
    }

    @Override
    public void setCategoriesList(List categoriesList) {
    this.categories=categoriesList;
    }


}