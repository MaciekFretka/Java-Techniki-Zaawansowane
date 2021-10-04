package mj;

import java.util.ArrayList;
import java.util.List;

public interface ParametersMBean {



    public void setPriorityA(int priorityA);
    public void setPriorityB(int priorityB);
    public void setPriorityC(int priorityC);

    public int getPriorityA();
    public int getPriorityB();
    public int getPriorityC();

    public List getCategoriesList();
    public void setCategoriesList(List categoriesList);


}
