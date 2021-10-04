package mj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {



    Parameters parameters;//=new Parameters();
    Data data = new Data();

    @FXML
    public TextArea bilboard;

    @FXML
    public ComboBox<String> categories;

    @FXML
    public Label label;

    @FXML
    public void initialize() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanException, IOException, InterruptedException, AttributeNotFoundException, ReflectionException, InstanceNotFoundException {

        Stage officestage = new Stage();
        AnchorPane officePane = FXMLLoader.load(getClass().getResource("/OfficeView.fxml"));
        Scene officescene = new Scene(officePane);
        officestage.setScene(officescene);
        officestage.show();



//        int jmxPort=8008;
//
//        JMXServiceURL target = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+jmxPort+"/jmxrmi");
//        JMXConnector connector = JMXConnectorFactory.connect(target);
//
//        MBeanServerConnection mbs=connector.getMBeanServerConnection();
//
//        //informacja o dostawcy wirtualnej masyny
//        ObjectName oname = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME);
//        String vendor = (String) mbs.getAttribute(oname,"VmVendor");
//        System.out.println(vendor);
//
//        ManagerMXBean proxy = JMX.newMBeanProxy(mbs,new ObjectName("pl.mj:name="+"Manager"),ManagerMXBean.class);
//        parameters = proxy.getParameters();

        parameters=new Parameters(0,1,2);

        ObjectName objectName = new ObjectName("mj.java.lab14:type=basic,name=parameters");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(parameters, objectName);


        ObservableList data = FXCollections.observableArrayList(parameters.getCategoriesList());
        categories.setItems(data);

        Runnable drawRunnable = new Runnable(){
            @Override
            public void run() {
                refreshBoad();
            }


        };
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(drawRunnable,0,900, TimeUnit.MILLISECONDS);
    }

    public void refreshBoad(){
        Iterator<Integer> iterator = data.queue.iterator();
        bilboard.clear();
        for (int i=0;i<data.queue.size();i++){
            int numberFromQueue=iterator.next();
            String text = numberFromQueue+"\n";
            bilboard.appendText(text);
        }
    }
    public void getNumber(){

        label.setVisible(true);
       int category= categories.getSelectionModel().getSelectedIndex();
       int number = 0;
       if(category==0){
           number = data.queue.size()+1+100* parameters.getPriorityA();
       }else if(category==1){
           number = data.queue.size()+1+100* parameters.getPriorityB();
       }else if(category==2){
           number = data.queue.size()+1+100* parameters.getPriorityC();
       }
        
        data.queue.add(number);
        label.setText("Twój numerek : "+number);



    }


}
