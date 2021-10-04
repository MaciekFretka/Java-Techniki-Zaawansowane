package bilboards;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Manager implements IManager {

    List<Order> orders = new ArrayList<Order>();

    @Override
    public int bindBillboard(IBillboard billboard) throws RemoteException {
        try{

            
            Registry billboardRegistry = LocateRegistry.getRegistry("localhost",4000);
            IBillboard ib = (IBillboard) billboardRegistry.lookup("Billboard");
            System.out.println("Connected with bilboard");
        }catch (NotBoundException  e ){

        }

        return 2;
    }

    @Override
    public boolean unbindBillboard(int billboardId) throws RemoteException {
        return false;
    }

    @Override
    public boolean placeOrder(Order order) throws RemoteException {

        Boolean isClientRegulated = true;
        IClient client= order.client;
        if(isClientRegulated){
            orders.add(order);
            client.setOrderId(2);
            System.out.println("Dodano zamówienie o id: " + 2);
        }else{

            return false;
        }
    return false;
    }

    @Override
    public boolean withdrawOrder(int orderId) throws RemoteException {
        return false;
    }

    public void acceptOrder(Order order, IClient client) throws RemoteException {
        orders.add(order);
        client.setOrderId(2);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Manager c = new Manager();
        IManager im = (IManager) UnicastRemoteObject.exportObject(c,0);

        Registry reg = LocateRegistry.createRegistry(3000);
        reg.rebind("Manager", im);
        System.out.println("Manager is ready");



    }
}
