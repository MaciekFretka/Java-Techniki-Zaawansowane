package mj;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControllerOffice {

    Data data = new Data();
    int control=0;
    int nextnumber;
    @FXML
    public Label bilboard=new Label();


public int findMinimum(){

    int minimum=999999999;
    Iterator<Integer> iterator = data.queue.iterator();

    for (int i=0;i<data.queue.size();i++){
        int numberFromQueue=iterator.next();
       if(numberFromQueue<minimum) minimum=numberFromQueue;
    }
    return minimum;
}
    public void removeActual(){

            data.queue.remove(new Integer(nextnumber));


    }
    public void nextByPriority(){
        removeActual();
        if(!data.queue.isEmpty()){
            bilboard.setVisible(true);
            nextnumber=findMinimum();
            String text = "Następny numerek: "+nextnumber+" (piorytet)\n";
            bilboard.setText(text);
        }
    }
    public void nextByQueue(){
        removeActual();
     if(!data.queue.isEmpty()){
         bilboard.setVisible(true);
         nextnumber=data.queue.element();
         String text = "Następny numerek: "+nextnumber+" (kolejka)\n";
         bilboard.setText(text);
     }

    }

    public void next(){

        if(!data.queue.isEmpty() & control<4){
            control=control+1;
            nextByQueue();
        }else if(!data.queue.isEmpty() & control==4){
            control=0;
            nextByPriority();

        }


    }
}
