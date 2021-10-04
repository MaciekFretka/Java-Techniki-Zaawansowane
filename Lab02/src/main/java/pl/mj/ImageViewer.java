package pl.mj;

import javafx.scene.image.Image;

public class ImageViewer extends Viewer {


    ImageViewer(String PathToFolder, String choosenfile) {
        super(PathToFolder, choosenfile);
    }

    @Override
    Image load() {
        Image image = new Image(f.toURI().toString());
        return image;
    }

}
