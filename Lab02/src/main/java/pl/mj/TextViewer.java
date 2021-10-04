package pl.mj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextViewer extends Viewer{
    TextViewer(String PathToFolder, String choosenfile) {
        super(PathToFolder, choosenfile);
    }

    @Override
    List<String> load() throws FileNotFoundException {
        List<String> lista = new ArrayList<String>()
;        Scanner s= new Scanner(f).useDelimiter("\\s+");
        while(s.hasNext()){
            if(s.hasNextInt()){

               lista.add(s.nextLine()+"\n");
            }else{
                lista.add(s.next()+"\n");

            }

        }
        return lista;
    }
}
