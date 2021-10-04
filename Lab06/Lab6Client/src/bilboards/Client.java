package bilboards;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client implements IClient{

    //Ostatnie zamówienie
    Order newOrder;// = new Order();
    //Mapa przechowująca : <Idzamówienia,Zamówienie>
    Map<Integer,Order> orders = new HashMap<Integer,Order>();

    @Override
    public void setOrderId(int orderId) throws RemoteException {
        //Gdy klient dostanie id zamówienia (Co jest równoznaczne z jego akceptacją)
        // Umieszcza zamówienie w swojej mapi
        orders.put(orderId,newOrder);
    }

    public void initOrder(IClient client){
        newOrder=new Order();
        newOrder.advertText="Sprzedam Opla";
        newOrder.displayPeriod= Duration.ofMinutes(30);
        newOrder.client=client;

    }
    public void showMyOrders(){
       orders.entrySet().forEach(entry ->{
           System.out.println("Zamówienie nr: " + entry.getKey());
           System.out.println("Napis: " + entry.getValue().advertText);
           System.out.println("Czas trwania: " + entry.getValue().displayPeriod);
       });
    }
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client c = new Client();
        IClient ic = (IClient) UnicastRemoteObject.exportObject(c,0);
        //UnicastRemoteObject.unexportObject(c,true);
      //  Registry reg = LocateRegistry.getRegistry(2000);
        Registry reg = LocateRegistry.createRegistry(2000);
        reg.rebind("Client", ic);
        System.out.println("Client is ready");

        Registry managerRegistry = LocateRegistry.getRegistry("localhost",3000);
        IManager im = (IManager) managerRegistry.lookup("Manager");
        System.out.println("Connected with manager");

        c.initOrder(c);
       im.placeOrder(c.newOrder);
       c.showMyOrders();
    }

}
