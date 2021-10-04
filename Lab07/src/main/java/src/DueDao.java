package src;

import java.util.List;

public interface DueDao {
    public void addDue (Due d);
    public List<Due> getAllDues();
    public Due getDue(int id);
    public void updateDue (Due d);
    public void deleteDue(int id);

}
