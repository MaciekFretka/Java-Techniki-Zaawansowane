package pl.mj;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Viewer {
    File f;
    abstract Object load() throws FileNotFoundException;
    Viewer(String PathToFolder,String choosenfile){
        f=new File(PathToFolder+"\\"+choosenfile);
    }
}
