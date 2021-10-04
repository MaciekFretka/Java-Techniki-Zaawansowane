package src;

import java.util.List;

public interface InstallationDao {
    public void addInstallation (Installation i);
    public List<Installation> getAllInstallations();
    public  Installation getInstallation(int id);
    public void updateInstallation (Installation i);
    public void deleteInstallation (int id);
}
