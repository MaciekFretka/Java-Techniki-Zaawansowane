package src;



import java.util.List;

public interface ClientDao {
    public void addClient (Client c);
    public List<Client> getAllClients();
    public Client getClient(int id);
    public void updateClient(Client c);
    public void deleteClient (int id);
}
