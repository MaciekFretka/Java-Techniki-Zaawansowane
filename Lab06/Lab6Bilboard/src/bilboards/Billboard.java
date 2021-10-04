package bilboards;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;

public class Billboard implements IBillboard{
    @Override
    public boolean addAdvertisement(String advertText, Duration displayPeriod, int orderId) throws RemoteException {
        System.out.println(advertText);
        return false;
    }

    @Override
    public boolean removeAdvertisement(int orderId) throws RemoteException {
        return false;
    }

    @Override
    public int[] getCapacity() throws RemoteException {
        return new int[0];
    }

    @Override
    public void setDisplayInterval(Duration displayInterval) throws RemoteException {

    }

    @Override
    public boolean start() throws RemoteException {
        return false;
    }

    @Override
    public boolean stop() throws RemoteException {
        return false;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {


        Billboard b = new Billboard();
        IBillboard ib = (IBillboard) UnicastRemoteObject.exportObject(b,0);

        Registry reg = LocateRegistry.createRegistry(4000);
        reg.rebind("Billboard", ib);
        System.out.println("Billboard is ready");

        Registry managerRegistry = LocateRegistry.getRegistry("localhost",3000);
        IManager im = (IManager) managerRegistry.lookup("Manager");
        System.out.println("Connected with Manager");

        im.bindBillboard(ib);
    }
}
