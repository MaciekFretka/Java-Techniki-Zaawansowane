package pl.mj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.*;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MainController {

    String PathToFolder = new String();
    List<String> files= new ArrayList<String>();
  // ConcurrentHashMap<String,Viewer> map = new ConcurrentHashMap<>(10, );

    WeakHashMap <String, Viewer> map = new WeakHashMap<>();
     //   Map<String,Viewer> map = new HashMap<>();
    @FXML
    public Button DirectoryChooserBut;

    @FXML
    public Button butabout;

    @FXML
    public ListView text;

    @FXML
    public Label path=new Label("");

    @FXML
    public TextArea textArea;

    @FXML
    public ImageView imageView=new ImageView();

    @FXML
    public Image image;

    @FXML
    public Label Collection;

    public void refreshlabel(){
        Collection.setVisible(true);
        Collection.setText("W WeakHashMap jest: "+map.size() + " referencji ");
    }

    public void butcheckaction(ActionEvent event) throws IOException, NoSuchAlgorithmException {


    }

    public void butgensnapaction(ActionEvent event) throws IOException, NoSuchAlgorithmException {

    }

    public void butaboutaction(ActionEvent event){


    }

    public void file(ActionEvent event) throws IOException {

        String choosenfile= (String) text.getSelectionModel().getSelectedItem();


     if(choosenfile.endsWith(".txt")){
         textArea.setVisible(true);
         imageView.setVisible(false);
         textArea.clear();
if(map.containsKey(choosenfile)){


    List<String> tmp_list= (List<String>) map.get(choosenfile).load();
    for(String line : tmp_list){

        textArea.appendText(line);
    }
    System.out.println("Wczytano plik z WeakHashmap");
}else{
    TextViewer txtviewer = new TextViewer(PathToFolder,choosenfile);
    //WeakReference<TextViewer> weakreference = new WeakReference<TextViewer>(txtviewer);
    map.put(choosenfile,txtviewer);
    List<String> tmp_list= (List<String>) map.get(choosenfile).load();
    for(String line : tmp_list){

        textArea.appendText(line);
    }
    System.out.println("utworzono nową referencję");
    txtviewer=null;

}


     }else if(choosenfile.endsWith(".png") ||choosenfile.endsWith(".PNG") ){
if(map.containsKey(choosenfile)){
    imageView.setImage((Image) map.get(choosenfile).load());
    System.out.println("Wczytano plik z WeakHashmap");
}else{
    textArea.setVisible(false);
    imageView.setVisible(true);
    ImageViewer imgv = new ImageViewer(PathToFolder,choosenfile);

    //    WeakReference<ImageViewer> weakreference = new WeakReference<ImageViewer>(imgv);

    map.put(choosenfile,imgv);
    imageView.setImage((Image) map.get(choosenfile).load());
    System.out.println("utworzono nową referencję");
    imgv=null;

}


     }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Niewspierany format");
        alert.setContentText("WSpierane są formaty .txt oraz .png");


        alert.showAndWait();
     }

    System.gc();

        refreshlabel();
    }

    public void DirectoryChooserButAction(ActionEvent event) throws IOException {
    files.clear();
   DirectoryChooser dc = new DirectoryChooser();
  dc.setInitialDirectory(new File("C:"));
  File selectedDirectory = dc.showDialog(null);


   if (selectedDirectory != null){
     PathToFolder=selectedDirectory.getAbsolutePath();
   }
   System.out.println(PathToFolder);
    path.setText(PathToFolder);

    Files.walk(Paths.get(PathToFolder),1).forEach(filein->{
        String name=filein.getFileName().toString();
        files.add(name);
        text.setItems(FXCollections.observableList(files));
    });
refreshlabel();
    }
}
