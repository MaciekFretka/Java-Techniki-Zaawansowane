package mj;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Controller {

    @FXML
    ComboBox c=new ComboBox();

    public void type(){
        c.getSelectionModel().getSelectedIndex();
        System.out.println("xD");
    }
}
